package etf.openpgp.sr170398dsl170423d.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Date;

import org.bouncycastle.bcpg.HashAlgorithmTags;
import org.bouncycastle.bcpg.PublicKeyAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPKeyPair;
import org.bouncycastle.openpgp.PGPKeyRing;
import org.bouncycastle.openpgp.PGPKeyRingGenerator;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureSubpacketGenerator;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.bc.BcPGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
import org.bouncycastle.openpgp.jcajce.JcaPGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.jcajce.JcaPGPSecretKeyRing;
import org.bouncycastle.openpgp.jcajce.JcaPGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.operator.PBESecretKeyEncryptor;
import org.bouncycastle.openpgp.operator.PGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPKeyPair;
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
	
}
