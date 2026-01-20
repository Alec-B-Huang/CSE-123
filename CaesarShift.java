// Alec Huang
// 01/19/26
// CSE 123 
// P0
// TA: Ishita Mundra

// Class comment: Represents a CaesarShift cipher that shifts each character
// a fixed number of places to the left.
public class CaesarShift extends Substitution {

    // BEHAVIOR: Constructs CaesarShift cipher.
    // EXCEPTIONS: none
    // RETURNS: none 
    // PARAMETERS: Int shift is the number of places to shift 
    //             characters in the phrase over by.
    public CaesarShift(int shift) {
        super(buildEncoding(shift));
    }

    // BEHAVIOR: Builds encoded string by moving certain characters
    // set amount to the left.
    // EXCEPTIONS: Illegal Argument Exception if shift is 
    //             less than one.
    // RETURNS: String result which is the encrypted string
    // PARAMETERS: Int shift is the number of places to shift 
    //             characters in the phrase over by.
    private static String buildEncoding(int shift) {
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
