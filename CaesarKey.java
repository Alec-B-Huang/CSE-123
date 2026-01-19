// Alec Huang
// CSE 123 
// P0
// TA: Ishita

import java.util.*;

public class CaesarKey extends Substitution {

    public CaesarKey(String key) {
        super(buildEncoding(key));
    }

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