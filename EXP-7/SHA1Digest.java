import java.security.MessageDigest;
import java.util.Scanner;

public class SHA1Digest {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter text: ");
            String text = sc.nextLine();

            // Create SHA-1 object
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // Generate hash
            byte[] hash = md.digest(text.getBytes());

            System.out.print("SHA-1 Message Digest: ");

            // Convert bytes to hexadecimal
            for (byte b : hash) {
                System.out.print(String.format("%02x", b));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
