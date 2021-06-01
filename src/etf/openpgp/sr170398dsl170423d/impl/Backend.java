package etf.openpgp.sr170398dsl170423d.impl;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.BCPGOutputStream;
import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.bcpg.HashAlgorithmTags;
import org.bouncycastle.bcpg.PublicKeyAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPKeyPair;
import org.bouncycastle.openpgp.PGPKeyRing;
import org.bouncycastle.openpgp.PGPKeyRingGenerator;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPLiteralDataGenerator;
import org.bouncycastle.openpgp.PGPOnePassSignature;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureGenerator;
import org.bouncycastle.openpgp.PGPSignatureList;
import org.bouncycastle.openpgp.PGPSignatureSubpacketGenerator;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
import org.bouncycastle.openpgp.jcajce.JcaPGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.jcajce.JcaPGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.PBESecretKeyEncryptor;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
import org.bouncycastle.openpgp.operator.bc.BcPGPDigestCalculatorProvider;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentVerifierBuilderProvider;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPKeyPair;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyEncryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyDataDecryptorFactoryBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyKeyEncryptionMethodGenerator;
import org.bouncycastle.util.io.Streams;


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
	
	public String isUsingEncryption(String filepath)
	{
		InputStream in;
		String retStr = null;
		try {
			in = PGPUtil.getDecoderStream(new FileInputStream(filepath));
			
			JcaPGPObjectFactory pgpF = new JcaPGPObjectFactory(in);
            PGPEncryptedDataList enc;

            Object o = pgpF.nextObject();
            PGPPublicKeyEncryptedData   pbe = null;
            
            if(o instanceof PGPEncryptedDataList)
            {
                PGPSecretKey secretKey = null;
                
            	enc = (PGPEncryptedDataList)o;
            	Iterator<PGPEncryptedData> it = enc.getEncryptedDataObjects();
            	PGPSecretKeyRingCollection coll = getSecretKeyRingCollection();
            	PGPPrivateKey sKey = null;
                
                while (sKey == null && it.hasNext())
                {
                    pbe = (PGPPublicKeyEncryptedData)it.next();
                    
                    if(coll.contains(pbe.getKeyID()) == true)
                    {
                    	secretKey = coll.getSecretKey(pbe.getKeyID());
                    	retStr = secretKey.getUserIDs().next();
                    	return retStr;
                    }
                }
            }
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch(PGPException e2)
		{
			e2.printStackTrace();
		}
	
		return retStr;
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
	public ArrayList<RingOutput> showPrivateKeyRingCollection()
	{
		try {
			ArrayList<RingOutput> ret = new ArrayList<>();
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
				
				Date dateUntil = new Date(System.currentTimeMillis() + 1000*publicKey.getValidSeconds());
				long keyID = publicKey.getKeyID();
				Date creationTime = publicKey.getCreationTime();
				byte[] fingerPrint = publicKey.getFingerprint();
				String userID = publicKey.getUserIDs().next();
				int first = userID.indexOf('<');
				int second = userID.indexOf('>');
				String name = userID.substring(0, first - 1);
				String email = userID.substring(first + 1, second);
				DateFormat f = new SimpleDateFormat("YYYY-MM");
				//System.out.println(name+" "+email+" "+keyID+" "+f.format(creationTime) + " "+ f.format(dateUntil) +" "+fingerPrint);
				
				RingOutput r = new RingOutput();
				r.setEmail(email);
				r.setFingerPrint(fingerPrint);
				r.setKeyID(keyID);
				r.setName(name);
				r.setValidFrom(creationTime);
				r.setValidUntil(dateUntil);
				r.setPrivate(true);
				ret.add(r);
			}
			
			return ret;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PGPException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<RingOutput> showPublicKeyRingCollection()
    {
        try {
            PGPPublicKeyRingCollection coll = getPublicKeyRingCollection();
            Iterator<PGPPublicKeyRing> iterator = coll.getKeyRings();
            ArrayList<RingOutput> ret = new ArrayList<>();
            while(iterator.hasNext())
            {
                PGPPublicKey publicKey = iterator.next().getPublicKey();
                Date dateUntil = new Date(System.currentTimeMillis() + 1000*publicKey.getValidSeconds());
                long keyID = publicKey.getKeyID();
                Date creationTime = publicKey.getCreationTime();
                byte[] fingerPrint = publicKey.getFingerprint();
                String userID = publicKey.getUserIDs().next();
                int first = userID.indexOf('<');
                int second = userID.indexOf('>');
                String name = userID.substring(0, first - 1);
                String email = userID.substring(first + 1, second);

                RingOutput r = new RingOutput();
                r.setEmail(email);
                r.setFingerPrint(fingerPrint);
                r.setKeyID(keyID);
                r.setName(name);
                r.setValidFrom(creationTime);
                r.setValidUntil(dateUntil);
                ret.add(r);

            }

            return ret;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PGPException e) {
            e.printStackTrace();
        }
        return null;
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
			if(signature == false && encrypt == false)
				throw new PGPException("You should have at least one of those two(Encryption, Signature)");
			
			String filepath = message.getAbsolutePath();
			String currentPath = filepath;
			String finalPath = message.getAbsolutePath().split("\\.")[0]+"_"+System.currentTimeMillis()+".gpg";
			if(signature == true)
			{
				PGPSecretKeyRingCollection coll = getSecretKeyRingCollection();
				if(coll.contains(secretKeyID) == true)
				{
					byte[] signedMsg = signMessage(passphrase, secretKeyID, coll, message);
					
					currentPath = Constants.TempDir+"signed.gpg";
					Utils.write(signedMsg, currentPath);
					
					if(encrypt == false)
					{
						byte[] MSG = (zip)?
								 (compressMessage(currentPath)):
							     (signedMsg);
						
						OutputStream out = (radix64)?
								   (new ArmoredOutputStream(new FileOutputStream(currentPath))):
								   (new FileOutputStream(currentPath));
						
						out.write(MSG);
						out.close();
					}
				}		
			}
			else
			{
				byte[] litData = getLiteralMsg(currentPath);
				currentPath = Constants.TempDir +"literaled.gpg";
				Utils.write(litData, currentPath);
			}
			
			if(encrypt == true)
			{
				int algorithmTag = 0;
				switch(symmetricAlgortihm)
				{
					case Constants.CAST5:
						algorithmTag = PGPEncryptedDataGenerator.CAST5;
						break;
					case Constants.TripleDES:
						algorithmTag = PGPEncryptedDataGenerator.TRIPLE_DES;
						break;
				}
				
				PGPEncryptedDataGenerator encGen = new PGPEncryptedDataGenerator
						(new JcePGPDataEncryptorBuilder(algorithmTag)
								.setWithIntegrityPacket(true)
								.setSecureRandom(new SecureRandom())
								.setProvider(Constants.BouncyCastle)
						);
				
				ArrayList<PGPPublicKey> receivers = getAllReceivers(publicKeyIDs);
				
				for(PGPPublicKey key: receivers)
				{
					encGen.addMethod(
							new JcePublicKeyKeyEncryptionMethodGenerator(key)
							.setProvider(Constants.BouncyCastle));
				}
				byte[] MSG = (zip)?(compressMessage(currentPath)):(Utils.read(currentPath));
				
				currentPath = Constants.TempDir +"encrypted.pgp";
				OutputStream out = (radix64)?
								   (new ArmoredOutputStream(new FileOutputStream(currentPath))):
								   (new FileOutputStream(currentPath));
				
				
				OutputStream cOut = encGen.open(out, MSG.length);
				cOut.write(MSG);
				
				cOut.close();
				encGen.close();
				out.close();
			}
			
			Files.copy(Path.of(currentPath), Path.of(finalPath));
			return true;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (PGPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private byte[] getLiteralMsg(String filename) throws IOException
	{
		ByteArrayOutputStream bArrStream = new ByteArrayOutputStream();
		PGPUtil.writeFileToLiteralData(bArrStream, PGPLiteralData.BINARY, new File(filename));

		return bArrStream.toByteArray();
	}
	
	private byte[] compressMessage(String filename) throws IOException
	{
		ByteArrayOutputStream bArrStream = new ByteArrayOutputStream();
		PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(CompressionAlgorithmTags.ZIP);
		
		//PGPUtil.writeFileToLiteralData(comData.open(bArrStream), PGPLiteralData.BINARY, new File(filename));
		byte[] msg = Utils.read(filename);
		OutputStream cOut = comData.open(bArrStream);
		cOut.write(msg);
		comData.close();
		
		return bArrStream.toByteArray();
	}
	
	
	private ArrayList<PGPPublicKey> getAllReceivers(long[] publicKeyIDs) throws FileNotFoundException, IOException, PGPException
	{
		ArrayList<PGPPublicKey> publicKeys = new ArrayList<>();
		PGPSecretKeyRingCollection coll = getSecretKeyRingCollection();
		PGPPublicKeyRingCollection pcoll = getPublicKeyRingCollection();
		
		for(int i = 0; i < publicKeyIDs.length; i++)
		{
			if(coll.contains(publicKeyIDs[i]) == true)
			{
				PGPSecretKeyRing keyRing = coll.getSecretKeyRing(publicKeyIDs[i]);
				publicKeys.add(keyRing.getPublicKey());
				continue;
			}
			
			if(pcoll.contains(publicKeyIDs[i]) == true)
			{
				PGPPublicKeyRing keyRing = pcoll.getPublicKeyRing(publicKeyIDs[i]);
				publicKeys.add(keyRing.getPublicKey());
			}
		}
		return publicKeys;
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

	public String getPassphrase(String s)
	{
		System.out.println("Enter the password for User id: "+ s);
		return "123";
	}
	
	public ArrayList<String> receiveMessage(String filepath, String passphrase)
	{
		InputStream in;
		//String passphrase = null;
		boolean signFlag = false, encryptFlag = false;
		ArrayList<String> returnMsg = new ArrayList<String>();
		
		try {
			in = PGPUtil.getDecoderStream(new FileInputStream(filepath));
			
			JcaPGPObjectFactory pgpF = new JcaPGPObjectFactory(in);
            PGPEncryptedDataList enc;

            Object o = pgpF.nextObject();
            PGPPublicKeyEncryptedData   pbe = null;
            
            if(o instanceof PGPEncryptedDataList)
            {
            	encryptFlag = true;
                PGPSecretKey secretKey = null;
                
            	enc = (PGPEncryptedDataList)o;
            	Iterator<PGPEncryptedData> it = enc.getEncryptedDataObjects();
            	PGPSecretKeyRingCollection coll = getSecretKeyRingCollection();
            	PGPPrivateKey sKey = null;
                
                while (sKey == null && it.hasNext())
                {
                    pbe = (PGPPublicKeyEncryptedData)it.next();
                    
                    if(coll.contains(pbe.getKeyID()) == true)
                    {
                    	secretKey = coll.getSecretKey(pbe.getKeyID());
                    	//passphrase = getPassphrase(secretKey.getUserIDs().next());
                    
                    	sKey = secretKey
                    			.extractPrivateKey(
                    			new JcePBESecretKeyDecryptorBuilder()
                    			.setProvider(Constants.BouncyCastle)
                    			.build(passphrase.toCharArray()));
                    }
                }
                
                if (sKey == null)
                {
                    throw new IllegalArgumentException("secret key for message not found.");
                }
                
                InputStream clear = pbe
                		.getDataStream(
                				new JcePublicKeyDataDecryptorFactoryBuilder()
                				.setProvider(Constants.BouncyCastle)
                				.build(sKey));
                
                pgpF = new JcaPGPObjectFactory(clear);
                
                o = pgpF.nextObject();
                
            }
            
            if (o instanceof PGPCompressedData)
            {
                PGPCompressedData cData = (PGPCompressedData)o;
                pgpF = new JcaPGPObjectFactory(cData.getDataStream());
                
                o = pgpF.nextObject();
            }
            
            if (o instanceof PGPOnePassSignatureList)
            {
            	signFlag = true;
            	
            	PGPOnePassSignatureList p1 = (PGPOnePassSignatureList)o;                
                PGPOnePassSignature ops = p1.get(0);
                    
                PGPLiteralData p2 = (PGPLiteralData)pgpF.nextObject();

                InputStream dIn = p2.getInputStream();

                PGPPublicKeyRingCollection  pgpRing = getPublicKeyRingCollection();
                PGPPublicKey key = pgpRing.getPublicKey(ops.getKeyID());

                FileOutputStream out = new FileOutputStream("result.txt");

                ops.init(new JcaPGPContentVerifierBuilderProvider()
                		.setProvider(Constants.BouncyCastle), key);

                byte[] msgInBytes= dIn.readAllBytes();
                out.write(msgInBytes);
                ops.update(msgInBytes);
                out.close();
                
                PGPSignatureList p3 = (PGPSignatureList)pgpF.nextObject();

                if (ops.verify(p3.get(0)))
                {
                    returnMsg.add("Signature verified successfully!");
                }
                else
                {
                    returnMsg.add("Signature verification failed!");
                }
                o = pgpF.nextObject();
            }
            
            if(o instanceof PGPLiteralData)
            {
            	PGPLiteralData ld = (PGPLiteralData)o;

                String outFileName = ld.getFileName();
                if (outFileName.length() == 0)
                {
                    outFileName = "name.txt";
                }
                System.out.println(outFileName);
                InputStream unc = ld.getInputStream();
                OutputStream fOut = new BufferedOutputStream(new FileOutputStream(outFileName));

                Streams.pipeAll(unc, fOut);

                fOut.close();
            }
            if(signFlag == false && encryptFlag == false)
            {
            	throw new PGPException("message is not a simple encrypted file - type unknown.");
            }
            
            if (encryptFlag == true && pbe.isIntegrityProtected())
            {
                if (!pbe.verify())
                {
                	returnMsg.add("message failed integrity check");
                }
                else
                {
                    returnMsg.add("message integrity check passed");
                }
            }
            
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch(PGPException e2)
		{
			e2.printStackTrace();
		}
	
		return returnMsg;
	}
}