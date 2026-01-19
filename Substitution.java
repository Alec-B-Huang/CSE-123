// Alec Huang
// CSE 123 
// P0
// TA: Ishita

import java.util.*;

public class Substitution extends Cipher {
    private String encoding;
    private boolean hasEncoding;

    public Substitution() {
        hasEncoding = false;
    }

    public Substitution(String encoding) {
        setEncoding(encoding);
    }

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

    @Override
    public String encrypt(String input) {
        if (hasEncoding == false) {
            throw new IllegalStateException();
        }

        String result = "";
        for (int i = 0; i < input.length(); i++) {
            int charIndex = Cipher.VALID_CHARS.indexOf(input.charAt(i));
            Character encodedChar = encoding.charAt(charIndex);
            result = result + encodedChar;
        }

        return result;
    }

    @Override
    public String decrypt(String input) {
        if (hasEncoding == false) {
            throw new IllegalStateException();
        }

        String result = "";
        for (int i = 0; i < input.length(); i++) {
            int charIndex = encoding.indexOf(input.charAt(i));
            Character encodedChar = Cipher.VALID_CHARS.charAt(charIndex);
            result = result + encodedChar;
        }
        
        return result;
    }
}
