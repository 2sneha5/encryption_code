import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;

public class Random_Key {
  public static void main(String[] args) throws Exception {
    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    
    byte[] input = "abc".getBytes();
    //AES/CBC/PKCS5Padding
    //AES/GCM/NoPadding
    //RSA/None/OAEPWithSHA1AndMGF1Padding", "BC"
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    SecureRandom random = new SecureRandom();
    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");

    generator.initialize(512, random);

    KeyPair pair = generator.generateKeyPair();
    Key pubKey = pair.getPublic();

    Key privKey = pair.getPrivate();

    cipher.init(Cipher.ENCRYPT_MODE, pubKey, random);
    byte[] cipherText = cipher.doFinal(input);
    System.out.println("cipher: " + new String(cipherText));

    cipher.init(Cipher.DECRYPT_MODE, privKey);
    byte[] plainText = cipher.doFinal(cipherText);
    System.out.println("plain : " + new String(plainText));
  }
}