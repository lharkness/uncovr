package info.uncovr;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

public class DecryptApp {
    public static void main(String[] args) throws Exception {
        String filename = args[0];
        
        String ciphertext = new String(readAllBytes(get(filename)));
        
        System.out.println(DeRot.bruteForceDecrypt(ciphertext));
    }
}