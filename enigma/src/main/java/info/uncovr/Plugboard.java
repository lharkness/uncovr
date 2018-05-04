package info.uncovr;

import java.util.Map;
import java.util.HashMap;

public class Plugboard {
    private Map<Character, Character> characterMap;
    
    public Plugboard() {
        characterMap = new HashMap<>();
    }
    
    public Plugboard addLetterPair(LetterPair letterPair) {
        if (characterMap.size() > 13) {
            return this;
        }
        characterMap.put(letterPair.firstEntry(), letterPair.secondEntry());
        characterMap.put(letterPair.secondEntry(), letterPair.firstEntry());
        return this;
    }
    
    public char alter(char input) {
        if (characterMap.containsKey(input)) {
            return characterMap.get(input);
        }
        else {
            return input;
        }
    }
    
    
    public static class LetterPair {
        private char firstEntry;
        private char secondEntry;

        public LetterPair(char firstEntry, char secondEntry) {
            this.firstEntry = firstEntry;
            this.secondEntry = secondEntry;
        }
        
        public char firstEntry() {
            return this.firstEntry;
        }
        
        public char secondEntry() {
            return this.secondEntry;
        }
        
        @Override
        public String toString() {
            return "firstEntry: " + firstEntry + " : secondEntry: " + 
            secondEntry;
        }
    }
}