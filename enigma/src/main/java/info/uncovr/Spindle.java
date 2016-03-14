package info.uncovr;

import java.util.List;
import java.util.LinkedList;

public class Spindle {
    private List<Rotor> rotors;
    
    public Spindle() {
        this.rotors = new LinkedList<>();
    }
    
    public Spindle(List<Rotor> rotors) {
        this.rotors = rotors;
    }
    
    public Spindle addRotor(Rotor rotor) {
        this.rotors.add(rotor);
        return this;
    }
    
    public void advance(Rotor rotor) {
        if (rotor.getRotorIndex() < this.rotors.size() - 1) {
            this.rotors.get(rotor.getRotorIndex() + 1).step();
        }
    }
    
    public char alter(char plainChar) {
        char firstRunResult = rotors.stream()
            .reduce(plainChar, 
                (inputChar, curRotor) -> 
                    curRotor.getEncipheredCharacter(inputChar),
                (char1, char2) -> char1);
        
        // Reflector
        int finalResult = rotors.stream()
            .sorted((firstEntry, secondEntry) -> Integer.compare(
              secondEntry.getRotorIndex(), firstEntry.getRotorIndex()  
            ))
            .skip(1)
            .reduce(firstRunResult, 
                (inputChar, curRotor) -> 
                    curRotor.getEncipheredCharacter(inputChar),
                (char1, char2) -> char1);
                
        return (char)finalResult;
    }
    
    public char decrypt(char cipherChar) {
        char firstRunResult = rotors.stream()
            .reduce(cipherChar, 
                (inputChar, curRotor) -> 
                    curRotor.getDecipheredCharacter(inputChar),
                (char1, char2) -> char1);
        
        // Reflector
        int finalResult = rotors.stream()
            .sorted((firstEntry, secondEntry) -> Integer.compare(
              secondEntry.getRotorIndex(), firstEntry.getRotorIndex()  
            ))
            .skip(1)
            .reduce(firstRunResult, 
                (inputChar, curRotor) -> 
                    curRotor.getDecipheredCharacter(inputChar),
                (char1, char2) -> char1);
                
        return (char)finalResult;
    }
}