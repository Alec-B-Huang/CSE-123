// Alec Huang
// 01/19/26
// CSE 123 
// P0
// TA: Ishita Mundra

import java.util.*;

// Class comment: Represents a substitution cipher by encrypting and decrypting
// messages by mapping characters to corresponding characters.
public class Substitution extends Cipher {
    private String encoding;
    private boolean hasEncoding;

    // BEHAVIOR: Constructs substituion cipher with no encoding set
    // EXCEPTION: none
    // RETURNS: none
    // PARAMETERS: none
    public Substitution() {
        hasEncoding = false;
    }

    // BEHAVIOR: Constructs substitution cipher with encoding set
    // EXCEPTION: none
    // RETURNS: none
    // PARAMETERS: String encoding; substitution encoding to use
    public Substitution(String encoding) {
        setEncoding(encoding);
    }

    // BEHAVIOR: Sets Cipher to given encoding
    // EXCEPTION: IllegalArgumentException:
    //            if encoding is null
    //            if encoding length is wrong
    //            if contains duplicates
    //            if containts invalid characters
    // RETURNS: none
    // PARAMETERS: String encoding; substitution encoding to use
    public void setEncoding(String encoding) {
        Set<Character> seenChar = new HashSet<>();

        if (encoding == null) {
            throw new IllegalArgumentException();
        }

        if (encoding.length() != Cipher.VALID_CHARS.length()) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < encoding.length(); i++) {
            char ch = encoding.charAt(i);

            if (Cipher.VALID_CHARS.indexOf(ch) == -1) {
                throw new IllegalArgumentException();
            }

            if (seenChar.contains(ch)) {
                throw new IllegalArgumentException();
            }

            seenChar.add(ch);
        }

        this.encoding = encoding;
        hasEncoding = true;
    }

    // BEHAVIOR: Encrypts message
    // EXCEPTION: IllegalArgumentException
    //            if not encoded
    //            input is null
    //            if character not in VALID_CHARS
    // RETURNS: String result; encrypted message
    // PARAMETERS: String input; message to encrypt
    @Override
    public String encrypt(String input) {
        if (hasEncoding == false) {
            throw new IllegalStateException();
        }

        if (input == null) {
            throw new IllegalArgumentException();
        }

        String result = "";
        for (int i = 0; i < input.length(); i++) {
            int charIndex = Cipher.VALID_CHARS.indexOf(input.charAt(i));

            if (charIndex == -1) {
                throw new IllegalArgumentException();
            }

            Character encodedChar = encoding.charAt(charIndex);
            result = result + encodedChar;
        }

        return result;
    }

    // BEHAVIOR: Decrypts message
    // EXCEPTION: IllegalArgumentException
    //            if not encoded
    //            input is null
    //            if character not in VALID_CHARS
    // RETURNS: String result; decrypted message
    // PARAMETERS: String input; message to decrypt
    @Override
    public String decrypt(String input) {
        if (hasEncoding == false) {
            throw new IllegalStateException();
        }

        if (input == null) {
            throw new IllegalArgumentException();
        }

        String result = "";
        for (int i = 0; i < input.length(); i++) {
            int charIndex = encoding.indexOf(input.charAt(i));

            if (charIndex == -1) {
                throw new IllegalArgumentException();
            }

            Character encodedChar = Cipher.VALID_CHARS.charAt(charIndex);
            result = result + encodedChar;
        }
        
        return result;
    }
}
