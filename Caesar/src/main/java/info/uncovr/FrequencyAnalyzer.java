package info.uncovr;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Handles frequency analysis
 */
public class FrequencyAnalyzer {
    
    private final Map<Character, Integer> frequencyMap = new HashMap<>();
    private int dataLength;
    
    /**
     * Grabs input to analyze
     * @param data the data to analyze
     * @return this instance
     */
    public FrequencyAnalyzer consume(String data) {
        data.chars().forEach(c -> consume((char)c));
        return this;
    }
    
    /**
     * grab a single character and add it to the analysis
     * @param c the character to analyze
     * @return this instance
     */
    public FrequencyAnalyzer consume(char c) {
        if (Character.isAlphabetic(c)) {
            frequencyMap.compute(c, (k, v) -> 
                v == null ?
                1
                :
                v + 1
            );
            dataLength += 1;
        }
        return this;
    }
    
    /**
     * Calculates and populates the frequencies of the input.
     * @return the List, with frequencies filled in
     */
    public List<LetterFrequency> getFrequencies() {
    
        List<LetterFrequency> retList = new LinkedList<>();
        if (frequencyMap.isEmpty()) {
            return retList;
        }
        
       frequencyMap.forEach((k, v) -> retList.add(new LetterFrequency(k, v)));
        
        return retList.stream()
            .map(item -> item.calculateFrequency(dataLength))
            .collect(Collectors.toList());
    
    }

    public LetterFrequency getFrequencyFor(char c) {
        if (!frequencyMap.containsKey(c)) {
            return new LetterFrequency(c, 0);
        }
        return new LetterFrequency(c, frequencyMap.get(c));
    }
    
}