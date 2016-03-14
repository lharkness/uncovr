package info.uncovr;

public class Enigma {
    
    private EnigmaSettings settings;
    
    public Enigma(EnigmaSettings settings) {
        this.settings = settings;
    }
    
    public String encrypt(String plaintext) {
        StringBuilder result = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            result.append(
               settings.getPlugboard().alter(
                   settings.getSpindle().alter(
                       settings.getPlugboard().alter(c))
                )
            );
        }
        
        return result.toString();
    }
    
    public String decrypt(String plaintext) {
        StringBuilder result = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            result.append(
               settings.getPlugboard().alter(
                   settings.getSpindle().decrypt(
                       settings.getPlugboard().alter(c))
                )
            );
        }
        
        return result.toString();
    }
    
}