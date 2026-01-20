// Alec Huang
// 01/19/26
// CSE 123 
// P0
// TA: Ishita Mundra

import java.util.*;

// Class comment: Represents CaesarKey cipher by making a substitution
// cipher by placing key at front of encoding then putting remaining characters
// in order after key.
public class CaesarKey extends Substitution {

    // BEHAVIOR: constructs CaesarKey cipher
    // EXCEPTIONS: none 
    // RETURNS: none
    // PARAMETERS: String key used to make substitution encoding
    public CaesarKey(String key) {
        super(buildEncoding(key));
    }

    // BEHAVIOR: Builds and returns encoding
    // EXCEPTIONS: IllegalArgumentException thrown if key is null
    //             if character doesn't exist n VALID_CHARS
    //             if duplicate letters in key
    // RETURNS: String encoding which is the encrypted string
    // PARAMETERS: String key used to make substitution encoding
    private static String buildEncoding(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < key.length(); i++) {
            if (Cipher.VALID_CHARS.indexOf(key.charAt(i)) == -1) {
                throw new IllegalArgumentException();
            }
        }

        Set<Character> keyDupes = new HashSet<>();
        for (int i = 0; i < key.length(); i ++) {

            if (keyDupes.contains(key.charAt(i))) {
                throw new IllegalArgumentException();

            } else {
                keyDupes.add(key.charAt(i));
            }
        }

        String encoding = key;
        for (int i = 0; i < Cipher.VALID_CHARS.length(); i++) {
            Character ch = Cipher.VALID_CHARS.charAt(i);
            if (encoding.indexOf(ch) == -1) {
                encoding = encoding + ch;
            }
        }

        return encoding;
    }
}
