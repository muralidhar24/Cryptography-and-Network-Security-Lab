import java.util.Scanner; 
 
public class SimpleRSA { 
 
    // Function to find gcd 
    static int gcd(int a, int b) { 
        while (b != 0) { 
            int temp = b; 
            b = a % b; 
            a = temp; 
        } 
        return a; 
    } 
 
    // Function to find modular inverse 
    static int modInverse(int e, int phi) { 
        for (int d = 1; d < phi; d++) { 
            if ((e * d) % phi == 1) 
                return d; 
        } 
        return 1; 
    } 
 
    public static void main(String[] args) { 
 
        Scanner sc = new Scanner(System.in); 
 
        // Step 1: Choose two prime numbers 
        System.out.print("Enter prime number p: "); 
        int p = sc.nextInt(); 
 
        System.out.print("Enter prime number q: "); 
        int q = sc.nextInt(); 
 
        // Step 2: Calculate n 
        int n = p * q; 
 
        // Step 3: Calculate phi 
        int phi = (p - 1) * (q - 1); 
 
        // Step 4: Choose e 
        int e; 
        for (e = 2; e < phi; e++) { 
            if (gcd(e, phi) == 1) 
                break; 
        } 
 
        // Step 5: Calculate d 
        int d = modInverse(e, phi); 
 
        System.out.println("\nPublic Key (e, n): (" + e + ", " + n + ")"); 
        System.out.println("Private Key (d, n): (" + d + ", " + n + ")"); 
 
        // Encryption 
        System.out.print("\nEnter message (number): "); 
        int msg = sc.nextInt(); 
 
        int cipher = 1; 
        for (int i = 0; i < e; i++) { 
            cipher = (cipher * msg) % n; 
        } 
 
        System.out.println("Encrypted message: " + cipher); 
 
        // Decryption 
        int decrypted = 1; 
        for (int i = 0; i < d; i++) { 
            decrypted = (decrypted * cipher) % n; 
        } 
 
        System.out.println("Decrypted message: " + decrypted); 
 
        sc.close(); 
    } 
} 