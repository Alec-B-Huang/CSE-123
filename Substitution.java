// TODO: Write your implementation to Subsitution here!

// Alec Huang
// CSE 123 
// P0
// TA:
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
        return null;
    }

    @Override
    public String decrypt(String input) {
        return null;    
    }
}