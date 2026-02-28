//Aim: To implement the Blowfish Algorithm by using C 
//Source Code. 
#include <stdio.h> 
#include <stdint.h> 
#include <string.h> 
#define ROUNDS 16 
uint32_t P[18]; 
uint32_t S[4][256]; 
/* ---------- F Function ---------- */ 
uint32_t F(uint32_t x) { 
unsigned char a, b, c, d; 
d = x & 0xFF; 
x >>= 8; 
c = x & 0xFF; 
x >>= 8; 
b = x & 0xFF; 
x >>= 8; 
a = x & 0xFF; 
return ((S[0][a] + S[1][b]) ^ S[2][c]) + S[3][d]; 
} 
/* ---------- Initialize P & S ---------- */ 
void initialize() { 
    int i, j; 
 
    for (i = 0; i < 18; i++) 
        P[i] = 0x243F6A88 ^ i;  // simplified constants 
 
    for (i = 0; i < 4; i++) 
        for (j = 0; j < 256; j++) 
            S[i][j] = (0x13198A2E * (i + 1)) ^ j; 
} 
 
/* ---------- Key Expansion ---------- */ 
void key_expansion(char key[]) { 
    int keyLen = strlen(key); 
    int i, j = 0, k; 
    uint32_t data; 
 
    for (i = 0; i < 18; i++) { 
        data = 0; 
        for (k = 0; k < 4; k++) { 
            data = (data << 8) | key[j]; 
            j = (j + 1) % keyLen; 
        } 
        P[i] ^= data; 
    } 
} 
 
/* ---------- Encryption ---------- */ 
void encrypt(uint32_t *L, uint32_t *R) { 
    uint32_t temp; 
    int i;
 
    for (i = 0; i < ROUNDS; i++) { 
        *L ^= P[i]; 
        *R ^= F(*L); 
 
        // Swap 
        temp = *L; 
        *L = *R; 
        *R = temp; 
    } 
 
    // Undo final swap 
    temp = *L; 
    *L = *R; 
    *R = temp; 
 
    *R ^= P[16]; 
    *L ^= P[17]; 
} 
 
/* ---------- Decryption ---------- */ 
void decrypt(uint32_t *L, uint32_t *R) { 
    uint32_t temp; 
 	int i;
    for (i = 17; i > 1; i--) { 
        *L ^= P[i]; 
        *R ^= F(*L); 
 
        // Swap 
        temp = *L; 
        *L = *R; 
        *R = temp; 
    } 
 
    // Undo final swap 
    temp = *L; 
    *L = *R; 
    *R = temp; 
 
    *R ^= P[1]; 
    *L ^= P[0]; 
} 
 
/* ---------- Main ---------- */ 
int main() { 
 
    char key[56]; 
    uint32_t L, R; 
    int choice; 
 
    initialize(); 
 
    printf("Enter key: "); 
    scanf("%s", key); 
 
    key_expansion(key); 
 
    printf("\n1. Encrypt\n2. Decrypt\nEnter choice: "); 
    scanf("%d", &choice); 
 
    printf("\nEnter  plain text (Hex): "); 
    scanf("%x", &L); 
    if (choice == 1) { 
        encrypt(&L, &R); 
        printf("\nEncrypted Output:\n"); 
    } 
    else if (choice == 2) { 
        decrypt(&L, &R); 
        printf("\nDecrypted Output:\n"); 
    } 
    else { 
        printf("Invalid choice!\n"); 
        return 0; 
    } 
    printf("L = %08X\n", L); 
    printf("R = %08X\n", R); 
 
    return 0; 
} 
 
