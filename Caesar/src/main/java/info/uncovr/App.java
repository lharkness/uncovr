package info.uncovr;

/**
 * Driver for the Caesar Cipher implementation
 */
public class App {
    
    public static final String DEFAULT_PLAINTEXT = "Default plainText a z.";
    public static final int DEFAULT_ROTATION_FACTOR = 4;
    
    /**
     * Usage: java App "text to modify" rotationFactor
     * @param args the command line arguments
     */
    public static void main( String[] args ) {
        String inputText = App.DEFAULT_PLAINTEXT;
        int rotationFactor = App.DEFAULT_ROTATION_FACTOR;
        
        if (args.length > 0) {
            inputText = args[0];
        }
        if (args.length > 1) {
            try {
                rotationFactor = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException nfe) {
                System.out.println("Invalid rotation factor [" + args[1] + 
                    "] using default");
            }
        }
        
        System.out.println(Rot.modify(inputText, rotationFactor));
        
    }
}
