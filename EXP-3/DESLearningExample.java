import javax.crypto.Cipher; 
import javax.crypto.KeyGenerator; 
import javax.crypto.SecretKey; 
import javax.crypto.spec.SecretKeySpec; 
import java.util.Base64; 
import java.util.Scanner; 
import java.security.SecureRandom; 
 
public class DESLearningExample { 
    private static final String ALGORITHM = "DES"; 
     
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in); 
         
        try { 
            System.out.println("=== DES Algorithm   ==="); 
            System.out.print("Enter plaintext to encrypt: "); 
            String plaintext = scanner.nextLine(); 
             
             
            SecretKey key = generateDESKey(); 
            String keyBase64 = Base64.getEncoder().encodeToString(key.getEncoded()); 
            System.out.println("Generated DES Key (Base64): " + keyBase64); 
             
             
            String ciphertext = encrypt(plaintext, key); 
            System.out.println("Ciphertext (Base64): " + ciphertext); 
             
            String decrypted = decrypt(ciphertext, key); 
            System.out.println("Decrypted text: " + decrypted); 
             
            System.out.println("\nVerification: " + (plaintext.equals(decrypted) ? "SUCCESS!" : 
"FAILED!")); 
             
        } catch (Exception e) { 
            System.err.println("Error: " + e.getMessage()); 
        } finally { 
            scanner.close(); 
        } 
    } 
     
     
    private static SecretKey generateDESKey() throws Exception { 
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM); 
        keyGen.init(new SecureRandom()); 
        return keyGen.generateKey(); 
    } 
     
    private static String encrypt(String plaintext, SecretKey key) throws Exception { 
        Cipher cipher = Cipher.getInstance(ALGORITHM); 
        cipher.init(Cipher.ENCRYPT_MODE, key); 
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes()); 
        return Base64.getEncoder().encodeToString(encryptedBytes); 
    } 
     
 
private static String decrypt(String ciphertext, SecretKey key) throws Exception { 
Cipher cipher = Cipher.getInstance(ALGORITHM); 
cipher.init(Cipher.DECRYPT_MODE, key); 
byte[] decodedBytes = Base64.getDecoder().decode(ciphertext); 
byte[] decryptedBytes = cipher.doFinal(decodedBytes); 
return new String(decryptedBytes); 
} 
}