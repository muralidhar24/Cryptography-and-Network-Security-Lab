//5b. write a java program to perform encryption and decryption for hill cipher  
import java.util.Scanner; 
 
public class HillCipher { 
 
    // Function to get modulo 26 positive value 
    static int mod26(int x) { 
        int r = x % 26; 
        if (r < 0) 
            r += 26; 
        return r; 
    } 
 
    // Function to find multiplicative inverse of determinant 
    static int multiplicativeInverse(int det) { 
        det = mod26(det); 
        for (int i = 1; i < 26; i++) { 
            if ((det * i) % 26 == 1) 
                return i; 
        } 
        return -1; // No inverse 
    } 
 
    // Encryption function 
    static String encrypt(String text, int key[][]) { 
        text = text.toUpperCase().replaceAll(" ", ""); 
 
        if (text.length() % 2 != 0) 
            text += "X"; // padding 
 
        String cipher = ""; 
 
        for (int i = 0; i < text.length(); i += 2) { 
            int a = text.charAt(i) - 'A'; 
            int b = text.charAt(i + 1) - 'A'; 
 
            int c1 = mod26(key[0][0] * a + key[0][1] * b); 
            int c2 = mod26(key[1][0] * a + key[1][1] * b); 
 
            cipher += (char) (c1 + 'A'); 
            cipher += (char) (c2 + 'A'); 
        } 
 
        return cipher; 
    } 
 
    // Decryption function 
    static String decrypt(String cipher, int key[][]) { 
        cipher = cipher.toUpperCase().replaceAll(" ", ""); 
 
        int det = key[0][0] * key[1][1] - key[0][1] * key[1][0]; 
        int invDet = multiplicativeInverse(det); 
 
        if (invDet == -1) { 
            return "Key is not invertible!"; 
        } 
 
        // Inverse key matrix 
        int invKey[][] = new int[2][2]; 
 
        invKey[0][0] = mod26(key[1][1] * invDet); 
        invKey[0][1] = mod26(-key[0][1] * invDet); 
        invKey[1][0] = mod26(-key[1][0] * invDet); 
        invKey[1][1] = mod26(key[0][0] * invDet); 
 
        String plain = ""; 
 
        for (int i = 0; i < cipher.length(); i += 2) { 
            int a = cipher.charAt(i) - 'A'; 
            int b = cipher.charAt(i + 1) - 'A'; 
 
            int p1 = mod26(invKey[0][0] * a + invKey[0][1] * b); 
            int p2 = mod26(invKey[1][0] * a + invKey[1][1] * b); 
 
            plain += (char) (p1 + 'A'); 
            plain += (char) (p2 + 'A'); 
        } 
 
        return plain; 
    } 
 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
 
        int key[][] = new int[2][2]; 
 
        System.out.println("Enter 2x2 Key Matrix (4 numbers):"); 
        for (int i = 0; i < 2; i++) { 
            for (int j = 0; j < 2; j++) { 
                key[i][j] = sc.nextInt(); 
            } 
        } 
 
        sc.nextLine(); // clear buffer 
 
        System.out.println("Enter Plain Text:"); 
        String text = sc.nextLine(); 
 
        String cipher = encrypt(text, key); 
        System.out.println("Encrypted Text: " + cipher); 
 
        String decrypted = decrypt(cipher, key); 
        System.out.println("Decrypted Text: " + decrypted); 
 
        sc.close(); 
    } 
} 
