package info.uncovr;

import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

/**
 * Utility class to handle decrypting substitution ciphers.
 */
public class DeSubstitute {
    
   /**
    * Attempts to decrypt a message through only frequency analysis.
    * This works best with large sized ciphertext.  We assume english text.
    * @param ciphertext the text to decrypt.
    * @return the decrypted text.
    */
    public static String decrypt(String ciphertext) {
         
        List<LetterFrequency> cipherList = 
            new FrequencyAnalyzer().consume(ciphertext).getFrequencies();
        
        Map<Character, Character> key = buildKeyMap(cipherList);
        
        StringBuilder decipheredText = new StringBuilder();
        for(char c : ciphertext.toCharArray()) {
            decipheredText.append(key.getOrDefault(c, c));
        }
        
        return decipheredText.toString();
    }
    
    /**
     * returns our "standard" frequency list of English letters
     * @return the frequencies of English letters
     */
    private static List<LetterFrequency> getStandardEnglishFrequencyList() {
        
        List<LetterFrequency> retList = new LinkedList<>();
        
        retList.add(new LetterFrequency('A', 8.167));
        retList.add(new LetterFrequency('B', 1.492));
        retList.add(new LetterFrequency('C', 2.782));
        retList.add(new LetterFrequency('D', 4.253));
        retList.add(new LetterFrequency('E', 12.702));
        retList.add(new LetterFrequency('F', 2.228));
        retList.add(new LetterFrequency('G', 2.015));
        retList.add(new LetterFrequency('H', 6.094));
        retList.add(new LetterFrequency('I', 6.966));
        retList.add(new LetterFrequency('J', 0.153));
        retList.add(new LetterFrequency('K', 0.772));
        retList.add(new LetterFrequency('L', 4.025));
        retList.add(new LetterFrequency('M', 2.406));
        retList.add(new LetterFrequency('N', 6.749));
        retList.add(new LetterFrequency('O', 7.507));
        retList.add(new LetterFrequency('P', 1.929));
        retList.add(new LetterFrequency('Q', 0.095));
        retList.add(new LetterFrequency('R', 5.987));
        retList.add(new LetterFrequency('S', 6.327));
        retList.add(new LetterFrequency('T', 9.056));
        retList.add(new LetterFrequency('U', 2.758));
        retList.add(new LetterFrequency('V', 0.978));
        retList.add(new LetterFrequency('W', 2.361));
        retList.add(new LetterFrequency('X', 0.150));
        retList.add(new LetterFrequency('Y', 1.974));
        retList.add(new LetterFrequency('Z', 0.074));

        return retList;
    }
    
    /**
     * Builds up a mapping between the english frequency list
     * and the ciphertext frequency list characters
     * @param cipherList the ciphertext frequency list
     * @return a Map from ciphertext to english characters.
     */
    private static Map<Character, Character> buildKeyMap(
        List<LetterFrequency> cipherList) {
          
        List<LetterFrequency> englishList = 
            getStandardEnglishFrequencyList();
            
        Map<Character, Character> retMap = new HashMap<>();
        
        Collections.sort(englishList);
        Collections.sort(cipherList);
        
        int index = 0;
        for(LetterFrequency letterFrequency : cipherList) {
            if (index > 25) {
                retMap.put(letterFrequency.getLetter(), '?');
            }
            else {
                retMap.put(letterFrequency.getLetter(), 
                    englishList.get(index).getLetter());
            }
            index++;
        }
        
        return retMap;
    }
}