package info.uncovr;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Arrays;

public class Rotor {
    private Map<Character, Character> characterMap;
    private Map<Character, Character> reverseCharacterMap;
    private List<Character> alphabet;
    private List<Character> cipherAlphabet;
    int curPos;
    private Spindle mySpindle;
    private int rotorIndex;
    
    public Rotor() {
        characterMap = new HashMap<>();
        reverseCharacterMap = new HashMap<>();
        alphabet = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 
            'W', 'X', 'Y', 'Z');
        curPos = 0;
    }
    
    public Rotor(List<Character> encipheredChars) {
        this();
        this.cipherAlphabet = new LinkedList<>(encipheredChars);
        buildCharacterMap();
        buildReverseCharacterMap();
    }
    
    public void setSpindle(Spindle spindle) {
        this.mySpindle = spindle;
    }

    public void setRotorIndex(int i) {
        this.rotorIndex = i;
    }

    public int getRotorIndex() {
        return this.rotorIndex;
    }
    
    void step() {
        Collections.rotate(cipherAlphabet, 1);
        buildCharacterMap();
        buildReverseCharacterMap();
        
        curPos++;
        if (curPos > 25) {
            curPos = 0;
            if (mySpindle != null) {
                mySpindle.advance(this);
            }
        }
    }
    
    public char getEncipheredCharacter(char plainChar) {
        char retChar = this.characterMap.get(plainChar);
        step();
        return retChar;
    }
    
    public char getDecipheredCharacter(char cipherChar) {
        
        char retChar = this.reverseCharacterMap.get(cipherChar);
        step();
        return retChar;
    }
    
    public void setCipherAlphabet(List<Character> cipherAlphabet) {
        this.cipherAlphabet = new LinkedList<>(cipherAlphabet);
        buildCharacterMap();
        buildReverseCharacterMap();
    }
    
    private void buildCharacterMap() {
        // We don't have a zip method in Java8?
        for (int i = 0; i < cipherAlphabet.size(); i++) {
            characterMap.put(alphabet.get(i), cipherAlphabet.get(i));
        }
    }
    
    private void buildReverseCharacterMap() {
        // We don't have a zip method in Java8?
        for (int i = 0; i < alphabet.size(); i++) {
            reverseCharacterMap.put(cipherAlphabet.get(i), alphabet.get(i));
        }
    }
}