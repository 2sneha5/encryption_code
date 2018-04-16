import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class MainClass {
    public static void main(String[] args) throws Exception {
        String password = "password";
        HMacSha1_pro hn = new HMacSha1_pro();

        String hmac = hn.calcRFC2104HMAC("data", "key");

        System.out.println(hmac);
        assert hmac.equals("104152c5bfdca07bc633eebd46199f0255c9f49d");
        System.out.println("key generation process->");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        String publicKeyFilename = "public";

        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        System.out.println("public key : " + publicKeyBytes);
        FileOutputStream fos = new FileOutputStream(publicKeyFilename);
        fos.write(publicKeyBytes);
        fos.close();
        
        RSA akg = new RSA(256);
        HMacSha1_pro obj = new HMacSha1_pro();
	String hmacnew = obj.calcRFC2104HMAC("data", "key");
        System.out.println(hmacnew);
	assert hmacnew.equals("104152c5bfdca07bc633eebd46199f0255c9f49d");
        
        String privateKeyFilename = "privateKeyFilename";
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        byte[] encryptedPrivateKeyBytes = passwordEncrypt(password.toCharArray(), privateKeyBytes);
        System.out.println("Encrypted private key : " + encryptedPrivateKeyBytes);
        fos = new FileOutputStream(privateKeyFilename);
        fos.write(encryptedPrivateKeyBytes);
        System.out.println("process completed");
        fos.close();
    }

    private static byte[] passwordEncrypt(char[] password, byte[] plaintext) throws Exception {
        int MD5_ITERATIONS = 1000;
        byte[] salt = new byte[8];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        for (Provider provider : Security.getProviders()) {
            System.out.println(provider.getName());
            for (String key : provider.stringPropertyNames()) {
                   System.out.println("\n" + key + "\t" + provider.getProperty(key));
            }
        }
        //PBKDF2WithHmacSHA256AndAES_128
        //PBEWithMD5AndDES
        //PBEWithHmacSHA256AndAES_128
        PBEKeySpec keySpec = new PBEKeySpec(password);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithHmacSHA256AndAES_128");
        SecretKey key = keyFactory.generateSecret(keySpec);
        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, MD5_ITERATIONS);
        Cipher cipher = Cipher.getInstance("PBEWithHmacSHA256AndAES_128");
        cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

        byte[] ciphertext = cipher.doFinal(plaintext);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(salt);
        baos.write(ciphertext);
        return baos.toByteArray();
    }
}
