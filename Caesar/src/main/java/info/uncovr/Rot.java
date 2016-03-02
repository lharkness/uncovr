package info.uncovr;

/**
 * Utility class to handle scrambling text via ROTation
 */
public class Rot {
    
    /**
     * Rotates the given text by the given rotation factor
     * @param inputText the text to rotate
     * @param rotationFactor the amount to rotate the input text
     */
    public static String modify(String inputText, int rotationFactor) {
        String retVal = inputText.toUpperCase();
        int[] modifiedCharacters = retVal.chars()
            .map(c -> transform((char)c, rotationFactor))
            .toArray();
            
        return new String(modifiedCharacters, 0, modifiedCharacters.length);
    }
    
    /**
     * Rotates a single character
     * @param c the character to rotate
     * @rotationFactor the amount to rotate c
     */
    private static char transform(char c, int rotationFactor) {
        if (!Character.isAlphabetic(c)) { return c; }
        
        char modifiedCharacter = (char)(c + rotationFactor);
        
        // These fencepost adjustments annoy me
        if (modifiedCharacter > 'Z') {
            modifiedCharacter = (char)('A' + (modifiedCharacter - 'Z' - 1));
        }
        if (modifiedCharacter < 'A') {
            modifiedCharacter = (char)('Z' - ('A' - modifiedCharacter) + 1);
        }
        
        return (char)(modifiedCharacter);
    }
}