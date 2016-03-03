package info.uncovr;

import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.Collectors;

/**
 * Utility class used to decrypt ROT-scrambled messages.
 */
public class DeRot {
    
    private static final String DICTIONARY_FILE_NAME = "words.txt";
   
   /**
     * Finds the best rotation factor to descramble a ROT-scrambled message.
     * This works by comparing descrambled words to a dictionary.  This will 
     * fail if a message has a lot of words in it which are not in the 
     * dictionary.
     * 
     * @param ciphertext the ROT-scrambled message.
     * @return the descrambled message.
     */
    public static String bruteForceDecrypt(String ciphertext) {
        
        Optional<Set<String>> englishWords = getEnglishWords();
        if (!englishWords.isPresent()) {
            System.out.println("ERROR: Could not load dictionary file");
            return null;
        }
        int bestFitFactor = 0;
        String bestPlaintext = "";
        // start with Rot(-1) and go to Rot(-13)
        for (int i = -1; i > -26; i--) {
            
            String plaintext = Rot.modify(ciphertext, i);
            int fit = determineFitFor(plaintext, englishWords.get());
            
            if (fit > bestFitFactor) {
                bestFitFactor = fit;
                bestPlaintext = plaintext;
            }
        }
        
        return bestPlaintext;
    }

    /**
     * Used to read the dictionary into a Set of words.
     * @return the Set of words found in the dictionary.
     */
    private static Optional<Set<String>> getEnglishWords() {
        
		try (Stream<String> stream = Files.lines(Paths.get(
		        ClassLoader.getSystemResource(
		            DICTIONARY_FILE_NAME).toURI()))) {

		    return Optional.of(
		        stream
		        .map(String::toLowerCase)
		        .collect(Collectors.toSet()));

		} catch (IOException | java.net.URISyntaxException e) {
			e.printStackTrace();
		}

        return Optional.empty();
    }
    
    /**
     * Returns the number of words in the descrambled text which are in the 
     * dictionary. 
     * @param plaintext the plain text.
     * @param englishWords the dictionary.
     * @return the number of words in plantext which are in englishWords.
     */
    private static int determineFitFor(String plaintext, 
        Set<String> englishWords) {
        
        int retVal = 0;
        String[] plainWords = plaintext.split(" ");
        
        for (String word : plainWords) {
            if (englishWords.contains(word.toLowerCase())) {
                retVal++;
            }
        }
        
        return retVal;
        
    }
    
}