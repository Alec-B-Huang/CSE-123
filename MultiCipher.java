// Alec Huang
// 01/19/26
// CSE 123 
// P0
// TA: Ishita Mundra

import java.util.*;

// Class comment: Represents cipher that adds multiple ciphers at once
public class MultiCipher extends Cipher {
    private List<Cipher> ciphers;

    // BEHAVIOR: Constructs Multicipher
    // EXCEPTIONS: IllegalArgumentException if ciphers is null
    // RETURNS: none
    // PARAMETERS: List<Cipher> ciphers; list of ciphers to apply in order
    public MultiCipher(List<Cipher> ciphers) {
        if (ciphers == null) {
            throw new IllegalArgumentException();
        }

        this.ciphers = ciphers;
    }

    // BEHAVIOR: Encrypts the message
    // EXCEPTIONS: IllegalArgumentException if input is null
    // RETURNS: String with encrypted input
    // PARAMETERS: String input; message to encrypt
    public String encrypt(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        String current = input;
        
        for (Cipher cipher : ciphers) {
            current = cipher.encrypt(current);
        }

        return current;
    }

    // BEHAVIOR: Decryptes the message
    // EXCEPTIONS: IllegalArgumentException if input is null
    // RETURNS: String with decrypted input
    // PARAMETERS: String input; message to decrypt
    public String decrypt(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        String current = input;

        for (int i = ciphers.size() - 1; i >= 0; i--) {
            current = ciphers.get(i).decrypt(current);
        }

        return current;
    }
}