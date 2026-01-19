// Alec Huang
// CSE 123 
// P0
// TA: Ishita

public class CaesarShift extends Substitution {

    public CaesarShift(int shift) {
        super(buildEncoding(shift));
    }

    public static String buildEncoding(int shift) {
        if (shift < 0) {
            throw new IllegalArgumentException();
        }

        int n = Cipher.VALID_CHARS.length();
        shift = shift % n;
        String result = "";

        for (int i = shift; i < n; i++) {
            result = result + Cipher.VALID_CHARS.charAt(i);
        }

        for (int i = 0; i < shift; i++) {
            result = result + Cipher.VALID_CHARS.charAt(i);
        }

        return result;
    }
}