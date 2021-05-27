package etf.openpgp.sr170398dsl170423d.impl;

import java.io.IOException;
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
import org.bouncycastle.openpgp.PGPKeyRingGenerator;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureSubpacketGenerator;
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
}
