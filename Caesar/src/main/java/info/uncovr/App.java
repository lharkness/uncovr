package info.uncovr;

/**
 * Driver for the Caesar Cipher implementation
 */
public class App {
    
    public static final String DEFAULT_PLAINTEXT = "Default plainText a z.";
    public static final int DEFAULT_ROTATION_FACTOR = 4;
    
    /**
     * Usage: java App "text to modify" rotationFactor
<<<<<<< HEAD
     * @param args the command line arguments
=======
>>>>>>> 8921bd37cab9063ec103c365a8b78495f622b777
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
