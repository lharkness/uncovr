package info.uncovr;

import java.util.Arrays;
import java.util.List;

/**
 * Driver for the Engima simulator encryption/decryption 
 */
public class EnigmaApp 
{
    private static List<Character> encipheredChars = 
        Arrays.asList('A', 'B', 'C', 'D',
        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 
        'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
    
    public static void main( String[] args ) {
        Collections.shuffle(encipheredChars);
        EnigmaSettings settings = getEnigmaSettings();
        Enigma enigma = new Enigma(settings);
        String cipherText = enigma.encrypt("HELLOGAGE");
        enigma = new Enigma(settings);
        String plainText = enigma.decrypt(cipherText);
        System.out.println(cipherText);
        System.out.println(plainText);
    }
    
    private static EnigmaSettings getEnigmaSettings() {
        
        Rotor firstRotor = new Rotor(encipheredChars);
        Rotor secondRotor = new Rotor(encipheredChars);
        
        Spindle mySpindle = new Spindle();
        mySpindle.addRotor(firstRotor);
        mySpindle.addRotor(secondRotor);
        
        EnigmaSettings settings = new EnigmaSettings(
            new Plugboard(), mySpindle
        );
        
        return settings;
        
    }
}
