package info.uncovr;

public class Rot {
    public static String modify(String inputText, int rotationFactor) {
        String retVal = inputText.toUpperCase();
        int[] modifiedCharacters = retVal.chars()
            .map(c -> transform((char)c, rotationFactor))
            .toArray();
            
        return new String(modifiedCharacters, 0, modifiedCharacters.length);
    }
    
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