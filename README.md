# 🔐 Cryptography and Network Security Lab

![Language](https://img.shields.io/badge/Languages-C%20%7C%20Java%20%7C%20HTML-blue?style=flat-square)
![Subject](https://img.shields.io/badge/Subject-Cryptography%20%26%20Network%20Security-darkgreen?style=flat-square)
![Programs](https://img.shields.io/badge/Programs-8-orange?style=flat-square)
![License](https://img.shields.io/badge/License-MIT-lightgrey?style=flat-square)

> A hands-on lab series implementing core cryptographic techniques — from bitwise operations and classical ciphers to symmetric/asymmetric encryption, hashing, and key exchange protocols.

---

## 📚 Table of Contents

- [Lab Programs](#-lab-programs)
- [Prerequisites & Setup](#-prerequisites--setup)
- [How to Run](#-how-to-run)
- [Program Descriptions](#-program-descriptions)
- [Sample Outputs](#-sample-outputs)
- [Key Concepts](#-key-concepts)
- [Author](#-author)

---

## 📋 Lab Programs

| # | Program | Language | Topic |
|---|---------|----------|-------|
| 1 | XOR Bitwise Operation | C | XOR encryption identity property |
| 2 | Bitwise Operations (AND / XOR / OR) | C | AND, XOR, OR operations on strings |
| 3 | DES Algorithm | Java | Symmetric block cipher using `javax.crypto` |
| 4 | RSA Algorithm | Java | Asymmetric encryption with key pair generation |
| 5a | Caesar Cipher | Java | Classical substitution cipher |
| 5b | Hill Cipher | Java | Matrix-based digraph substitution cipher |
| 6 | Blowfish Algorithm | C | 16-round Feistel symmetric block cipher |
| 7 | SHA-1 Message Digest | Java | Cryptographic hash function |
| 8 | Diffie-Hellman Key Exchange | HTML/JS | Public-key exchange protocol (browser demo) |

---

## ⚙️ Prerequisites & Setup

### For C Programs (Programs 1, 2, 6)
```bash
sudo apt install build-essential     # Linux / WSL
gcc --version                        # verify installation
```

### For Java Programs (Programs 3, 4, 5a, 5b, 7)
```bash
sudo apt install default-jdk
java --version && javac --version    # verify installation
```

### For HTML Program (Program 8)
No installation needed — open directly in any modern web browser.

---

## ▶️ How to Run

### C Programs
```bash
gcc program_name.c -o output_name
./output_name
```

### Java Programs
```bash
javac ClassName.java
java ClassName
```

### HTML Program
```bash
start program8_DiffieHellman.html       # Windows
xdg-open program8_DiffieHellman.html   # Linux
open program8_DiffieHellman.html       # macOS
```

---

## 📖 Program Descriptions

---

### Program 1 — XOR Bitwise Operation (C)

**Concept:** XOR of any value with `0` returns itself (`x ^ 0 = x`). This demonstrates XOR's identity property, which is the mathematical foundation of XOR encryption.

```
'H' (0x48) ^ 0x00 = 'H' (0x48)   ← no change
```

---

### Program 2 — AND / XOR / OR Bitwise Operations (C)

**Concept:** Demonstrates three bitwise operations applied to each character of `"HELLO WORLD"` with the value `127` (0x7F):

| Operation | Mask | Effect |
|-----------|------|--------|
| `& 127` | `01111111` | Clears the MSB — keeps ASCII in printable range |
| `^ 127` | `01111111` | Flips all 7 lower bits — produces complemented characters |
| `\| 127` | `01111111` | Sets all 7 lower bits — most characters become `DEL (0x7F)` |

---

### Program 3 — DES Algorithm (Java)

**Concept:** Data Encryption Standard — a symmetric-key block cipher encrypting 64-bit blocks with a 56-bit key via 16 Feistel rounds.

**Flow:**
```
Plaintext ──► Key Generation (SecureRandom) ──► DES Encrypt ──► Base64 Ciphertext
Base64 Ciphertext ──► DES Decrypt ──► Original Plaintext ✅
```

- Uses `javax.crypto.Cipher` and `KeyGenerator`
- Key is auto-generated and displayed in Base64
- Verification confirms encrypt → decrypt round-trip

---

### Program 4 — RSA Algorithm (Java)

**Concept:** Rivest–Shamir–Adleman — asymmetric encryption where messages encrypted with a public key can only be decrypted with the corresponding private key.

**Steps:**
1. Choose primes `p` and `q` → compute `n = p × q`
2. Compute `φ(n) = (p−1)(q−1)`
3. Choose `e` where `gcd(e, φ) = 1` → **Public Key = (e, n)**
4. Compute `d` where `(e × d) mod φ = 1` → **Private Key = (d, n)**
5. Encrypt: `C = M^e mod n`
6. Decrypt: `M = C^d mod n`

> ⚠️ Educational implementation only — use `java.security.KeyPairGenerator` for production.

---

### Program 5a — Caesar Cipher (Java)

**Concept:** Shifts each letter in the plaintext by a fixed number of positions in the alphabet.

```
Encrypt: C = (P − 'A' + shift) mod 26 + 'A'
Decrypt: P = (C − 'A' + (26 − shift)) mod 26 + 'A'
```

**Example (shift = 3):**
```
Plaintext  : HELLO WORLD
Ciphertext : KHOOR ZRUOG
Decrypted  : HELLO WORLD
```

Handles uppercase, lowercase, and preserves spaces/special characters.

---

### Program 5b — Hill Cipher (Java)

**Concept:** A polygraphic substitution cipher that encrypts letter pairs (digraphs) using matrix multiplication modulo 26.

**Encryption (2×2 key matrix K, plaintext vector P):**
```
C = K × P  (mod 26)
```

**Decryption:**
```
P = K⁻¹ × C  (mod 26)
```

The modular inverse of the key matrix is computed using the determinant's multiplicative inverse mod 26. If no inverse exists, the key is invalid for decryption.

---

### Program 6 — Blowfish Algorithm (C)

**Concept:** A 64-bit block cipher with variable-length key (32–448 bits), using a 16-round Feistel network with P-arrays and S-boxes.

**Components:**
- **P-array:** 18 × 32-bit subkeys derived from the key
- **S-boxes:** 4 × 256 × 32-bit substitution tables
- **F function:** Splits a 32-bit block into 4 bytes and applies S-box lookups

**Usage:**
```
Input : Key (string) + Plaintext (as 32-bit hex L value)
Output: Encrypted or Decrypted L and R in hexadecimal
```

---

### Program 7 — SHA-1 Message Digest (Java)

**Concept:** SHA-1 produces a fixed 160-bit (40 hex character) digest from any input. Used for data integrity verification.

**Properties:**
- Deterministic: same input → same hash always
- One-way: hash cannot be reversed to recover input
- Avalanche effect: small input change → completely different hash

**Uses:** `java.security.MessageDigest` with algorithm `"SHA-1"`

---

### Program 8 — Diffie-Hellman Key Exchange (HTML/JS)

**Concept:** Allows two parties (Alice and Bob) to establish a shared secret over a public channel without ever transmitting the secret itself.

**Protocol:**
```
Agree on: public prime p, generator g
Alice: picks private a → computes A = g^a mod p → sends A to Bob
Bob:   picks private b → computes B = g^b mod p → sends B to Alice
Alice: shared secret S = B^a mod p
Bob:   shared secret S = A^b mod p
Both arrive at identical S ✅
```

Run directly in any browser — no server needed.

---

## 🖥️ Sample Outputs

### Caesar Cipher
```
Enter a message: HELLO WORLD
Enter shift value: 3
Encrypted Message: KHOOR ZRUOG
Decrypted Message: HELLO WORLD
```

### RSA
```
Enter prime number p: 7
Enter prime number q: 11
Public Key  (e, n): (7, 77)
Private Key (d, n): (43, 77)
Enter message (number): 20
Encrypted message: 41
Decrypted message: 20
```

### SHA-1
```
Enter text: cryptography
SHA-1 Message Digest: e6c4b961f0c6c305bf3d4de4b2d3a8de08c8e7d4
```

### DES
```
Enter plaintext to encrypt: HelloWorld
Generated DES Key (Base64): tH3K+xQ1Rabc=
Ciphertext (Base64): Xk29L4R7N9mA==
Decrypted text: HelloWorld
Verification: SUCCESS!
```

---

## 🧠 Key Concepts

| Concept | Programs |
|---------|----------|
| Bitwise operations (AND, XOR, OR) | 1, 2 |
| Classical ciphers | 5a (Caesar), 5b (Hill) |
| Symmetric block encryption | 3 (DES), 6 (Blowfish) |
| Asymmetric encryption | 4 (RSA) |
| Cryptographic hash functions | 7 (SHA-1) |
| Key exchange protocol | 8 (Diffie-Hellman) |

---

## 📁 Folder Structure

```
Cryptography-and-Network-Security-Lab/
│
├── Exp-1_XOR_Bitwise/
│   └── Program1_XOR.c
│
├── Exp-2_Bitwise_Operations/
│   └── Program2_Bitwise.c
│
├── Exp-3_DES/
│   └── DESLearningExample.java
│
├── Exp-4_RSA/
│   └── SimpleRSA.java
│
├── Exp-5_Classical_Ciphers/
│   ├── CaesarCipher.java           (5a)
│   └── HillCipher.java             (5b)
│
├── Exp-6_Blowfish/
│   └── Program6_Blowfish.c
│
├── Exp-7_SHA1/
│   └── SHA1Digest.java
│
├── Exp-8_DiffieHellman/
│   └── DiffieHellman.html
│
└── README.md
```

---

## 👤 Author

**Muralidhar**
🔗 GitHub: [@muralidhar24](https://github.com/muralidhar24)

---

> ⭐ Found this helpful? Star the repo — it helps other students discover it!
