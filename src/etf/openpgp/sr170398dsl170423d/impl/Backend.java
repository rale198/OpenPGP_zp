package etf.openpgp.sr170398dsl170423d.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Date;
import java.util.Iterator;

import org.bouncycastle.bcpg.BCPGOutputStream;
import org.bouncycastle.bcpg.HashAlgorithmTags;
import org.bouncycastle.bcpg.PublicKeyAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPKeyPair;
import org.bouncycastle.openpgp.PGPKeyRing;
import org.bouncycastle.openpgp.PGPKeyRingGenerator;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPLiteralDataGenerator;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureGenerator;
import org.bouncycastle.openpgp.PGPSignatureSubpacketGenerator;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.bc.BcPGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
import org.bouncycastle.openpgp.jcajce.JcaPGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.jcajce.JcaPGPSecretKeyRing;
import org.bouncycastle.openpgp.jcajce.JcaPGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.PBESecretKeyEncryptor;
import org.bouncycastle.openpgp.operator.PGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
import org.bouncycastle.openpgp.operator.bc.BcPBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPDigestCalculatorProvider;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPKeyPair;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyEncryptorBuilder;

public class Backend {
	
	public Backend() {
		Security.addProvider(new BouncyCastleProvider());
		Utils.createRingFile(Constants.SecretKeyRingFilename);
		Utils.createRingFile(Constants.PublicKeyRingFilename);
	}
	
	
	
	public boolean generateKeyPair(String username, String email, int keySize, String password)
	{
		try {
			KeyPairGenerator kpGen = KeyPairGenerator.getInstance(Constants.RSA);
			kpGen.initialize(keySize);
			
			PGPKeyPair keyPair = 
					new JcaPGPKeyPair(PublicKeyAlgorithmTags.RSA_GENERAL, kpGen.generateKeyPair(), new Date());
			
			PGPKeyRingGenerator ringGen = this.generateKeyRingGenerator(Utils.USER_ID(username, email), password, keyPair);
			
			PGPPublicKeyRing publicKey = ringGen.generatePublicKeyRing();
			
			Utils.write(publicKey.getEncoded(), 
					Utils.PublicKeyFilename(username, email, publicKey.getPublicKey().getFingerprint()));
			
			PGPSecretKeyRingCollection secretKeysRing = getSecretKeyRingCollection();
			PGPSecretKeyRing keyRing = ringGen.generateSecretKeyRing();
			secretKeysRing = PGPSecretKeyRingCollection.addSecretKeyRing(secretKeysRing, keyRing);
			Utils.write(secretKeysRing.getEncoded(), Constants.SecretKeyRingFilename);
			return true;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (PGPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	private PGPKeyRingGenerator generateKeyRingGenerator(String USER_ID, String password, PGPKeyPair masterKeyPair) throws PGPException
	{
		PGPDigestCalculator digestSha1 = new JcaPGPDigestCalculatorProviderBuilder()
                .build().get(HashAlgorithmTags.SHA1);
		
		JcaPGPContentSignerBuilder signerBuilder = new JcaPGPContentSignerBuilder(
				masterKeyPair.getPublicKey().getAlgorithm(),HashAlgorithmTags.SHA256);
		
		PBESecretKeyEncryptor encryptor =
                new JcePBESecretKeyEncryptorBuilder(PGPEncryptedData.AES_256, digestSha1)
                .setProvider(Constants.BouncyCastle).build(password.toCharArray());
		
		PGPSignatureSubpacketGenerator ssg = new PGPSignatureSubpacketGenerator();
		ssg.setKeyExpirationTime(false, Constants.DurationInSeconds);
		
		return new PGPKeyRingGenerator(
                PGPSignature.POSITIVE_CERTIFICATION,
                masterKeyPair,
                USER_ID,
                digestSha1,
                ssg.generate(),
                null,
                signerBuilder,
                encryptor
        );
	}
	
	private PGPSecretKeyRingCollection getSecretKeyRingCollection() throws FileNotFoundException, IOException, PGPException
	{
		File f = new File(Constants.SecretKeyRingFilename);
		return new JcaPGPSecretKeyRingCollection(new FileInputStream(f));
	}
	
	private PGPPublicKeyRingCollection getPublicKeyRingCollection() throws FileNotFoundException, IOException, PGPException
	{
		File f = new File(Constants.PublicKeyRingFilename);
		return new JcaPGPPublicKeyRingCollection(new FileInputStream(f));	
	}
	
	public boolean importKey(String filepath)
	{
		try {
			InputStream file = PGPUtil.getDecoderStream(new FileInputStream(filepath));
			JcaPGPObjectFactory factory = new JcaPGPObjectFactory(file);
			Object keyRing = factory.nextObject();
			
			PGPKeyRing inputKeyRing = null;
			if(keyRing instanceof PGPPublicKeyRing == true)
			{
				inputKeyRing = (PGPPublicKeyRing)(keyRing);
				PGPPublicKeyRingCollection publicRingCollection = getPublicKeyRingCollection();
				publicRingCollection = PGPPublicKeyRingCollection.addPublicKeyRing(publicRingCollection, (PGPPublicKeyRing) inputKeyRing);
				Utils.write(publicRingCollection.getEncoded(), Constants.PublicKeyRingFilename);
				System.out.println("Public key successfully imported!");
			}
			else if(keyRing instanceof PGPSecretKeyRing == true)
			{
				inputKeyRing = (PGPSecretKeyRing)(keyRing);
				PGPSecretKeyRingCollection secretRingCollection = getSecretKeyRingCollection();
				secretRingCollection = PGPSecretKeyRingCollection.addSecretKeyRing(secretRingCollection, (PGPSecretKeyRing) inputKeyRing);
				Utils.write(secretRingCollection.getEncoded(), Constants.SecretKeyRingFilename);
				System.out.println("Secret key successfully improted!");
			}
			else 
			{
				throw new PGPException("File is neither private nor public key!");
			}
			
			
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PGPException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean removeKeyPair(long keyID, String password)
	{
		try {
			PGPSecretKeyRingCollection coll = getSecretKeyRingCollection();
			
			if(coll.contains(keyID) == true) {
				
				PBESecretKeyDecryptor decryptor = 
						new JcePBESecretKeyDecryptorBuilder(new BcPGPDigestCalculatorProvider())
						.setProvider(Constants.BouncyCastle)
						.build(password.toCharArray());
				
				PGPSecretKeyRing ring = coll.getSecretKeyRing(keyID);
				PGPSecretKey secretKey = ring.getSecretKey();
				PGPPrivateKey privateKey = secretKey.extractPrivateKey(decryptor);
				
				coll = PGPSecretKeyRingCollection.removeSecretKeyRing(coll, ring);
				Utils.write(coll.getEncoded(), Constants.SecretKeyRingFilename);
				
				return true;
			}
			
			throw new PGPException("Ring collection doesn't have this keyring!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PGPException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeImportKey(long keyID)
	{
		try {
			PGPPublicKeyRingCollection coll = getPublicKeyRingCollection();
			
			if(coll.contains(keyID) == true)
			{
				
				PGPPublicKeyRing ring = coll.getPublicKeyRing(keyID);
				coll = PGPPublicKeyRingCollection.removePublicKeyRing(coll, ring);
				Utils.write(coll.getEncoded(), Constants.PublicKeyRingFilename);
				return true;
			}
			
			throw new PGPException("Ring collection doesn't have this keyring!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PGPException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public void showPrivateKeyRingCollection()
	{
		try {
			PGPSecretKeyRingCollection coll = getSecretKeyRingCollection();
			Iterator<PGPSecretKeyRing> iterator = coll.getKeyRings();
			
			while(iterator.hasNext())
			{
				PGPSecretKeyRing ring = iterator.next();
				PGPPublicKey publ = ring.getPublicKey();
				if(publ == null)
				{
					System.out.println("NUll");
				}
				PGPPublicKey publicKey = ring.getPublicKey();
				PGPSecretKey secretKey = ring.getSecretKey();
				
				long keyID = publicKey.getKeyID();
				Date creationTime = publicKey.getCreationTime();
				byte[] fingerPrint = publicKey.getFingerprint();
				String userID = publicKey.getUserIDs().next();
				
				System.out.println(userID+" "+keyID+" "+creationTime+" "+fingerPrint);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PGPException e) {
			e.printStackTrace();
		}
		
	}
	
	public void showPublicKeyRingCollection()
	{
		try {
			PGPPublicKeyRingCollection coll = getPublicKeyRingCollection();
			Iterator<PGPPublicKeyRing> iterator = coll.getKeyRings();
			
			while(iterator.hasNext())
			{
				PGPPublicKey publicKey = iterator.next().getPublicKey();
				long keyID = publicKey.getKeyID();
				Date creationTime = publicKey.getCreationTime();
				byte[] fingerPrint = publicKey.getFingerprint();
				String userID = publicKey.getUserIDs().next();
				
				System.out.println(userID+" "+keyID+" "+creationTime+" "+fingerPrint);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PGPException e) {
			e.printStackTrace();
		}
	}
	
	public boolean exportPublicKey(long keyID, String filepath)
	{
		try {
			PGPSecretKeyRingCollection coll = getSecretKeyRingCollection();
			
			if(coll.contains(keyID) == true)
			{
				printPublicToFile(coll.getSecretKeyRing(keyID), filepath);
				return true;
			}
			
			PGPPublicKeyRingCollection pcoll = getPublicKeyRingCollection();
			
			if(pcoll.contains(keyID) == true)
			{
				printPublicToFile(pcoll.getPublicKeyRing(keyID), filepath);
				return true;
			}
			
			throw new PGPException("There are no key with this keyID!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PGPException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private void printPublicToFile(PGPKeyRing ring, String filepath) throws IOException
	{
		PGPPublicKey publicKey = ring.getPublicKey();
		Utils.write(publicKey.getEncoded(), filepath);
	}
	
	public boolean printSecretKey(long keyID, String filepath)
	{
		try {
			PGPSecretKeyRingCollection coll = getSecretKeyRingCollection();
			
			if(coll.contains(keyID) == true)
			{
				PGPSecretKeyRing ring = coll.getSecretKeyRing(keyID);
				Utils.write(ring.getEncoded(), filepath);
				return true;
			}
			
			throw new PGPException("There are no secret keys with this ID");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PGPException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean sendMessage(
			File message,
			boolean encrypt,
			boolean signature,
			boolean zip,
			boolean radix64,
			String symmetricAlgortihm,
			long secretKeyID,
			long[] publicKeyIDs,
			String passphrase)
	{
		try {
			
			if(signature == true)
			{
				PGPSecretKeyRingCollection coll = getSecretKeyRingCollection();
				if(coll.contains(secretKeyID) == true)
				{
					byte[] signedMsg = signMessage(passphrase, secretKeyID, coll, message);
					Utils.write(signedMsg, "C:\\Users\\Sonja\\Desktop\\signed1.gpg");
					return true;
				}
								
			}
			throw new PGPException("Msg not signed properely");
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (PGPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public byte[] signMessage(String passphrase, long secretKeyID, PGPSecretKeyRingCollection coll, File message) throws PGPException, IOException
	{
		PBESecretKeyDecryptor decryptor = 
				new JcePBESecretKeyDecryptorBuilder(new BcPGPDigestCalculatorProvider())
				.setProvider(Constants.BouncyCastle)
				.build(passphrase.toCharArray());
		
		PGPSecretKeyRing ring = coll.getSecretKeyRing(secretKeyID);
		PGPSecretKey secretKey = ring.getSecretKey();
		PGPPrivateKey privateKey = secretKey.extractPrivateKey(decryptor);
		
		PGPSignatureGenerator signGenerator = new PGPSignatureGenerator(
				new JcaPGPContentSignerBuilder(secretKey.getPublicKey().getAlgorithm(), PGPUtil.SHA1)
				.setProvider(Constants.BouncyCastle));
		
		signGenerator.init(PGPSignature.BINARY_DOCUMENT, privateKey);
		ByteArrayOutputStream bArrStream = new ByteArrayOutputStream();
		BCPGOutputStream bcpgOut = new BCPGOutputStream(bArrStream);
		signGenerator.generateOnePassVersion(false).encode(bcpgOut);
		
		PGPLiteralDataGenerator ldataGen = new PGPLiteralDataGenerator();
		byte[] messageInBytes = Utils.read(message.getAbsolutePath());
		
		OutputStream out = ldataGen.open(
				bcpgOut,
				PGPLiteralData.BINARY, 
				PGPLiteralData.CONSOLE, 
				messageInBytes.length, 
				new Date());
		
		out.write(messageInBytes);
		signGenerator.update(messageInBytes);
		
		ldataGen.close();
		
		signGenerator.generate().encode(bcpgOut);
		
		bcpgOut.close();
		return bArrStream.toByteArray();
	}
}
