package info.uncovr;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SpindleIntegrationTest {
    
    List<Character> encipheredChars = Arrays.asList('A', 'B', 'C', 'D', 'E', 
        'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 
        'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
    
    @Before
    public void setup() {
        encipheredChars = Utility.shuffle(encipheredChars);
    }
    
    @Test
    public void testThatSpindleDeciphersCorrectlyOneRotor() {
        
        List<Character> initialAlphabet =
                new LinkedList<>(encipheredChars);
        
        Rotor rotor = buildRotor(initialAlphabet);
        Spindle target = new Spindle();
        target.addRotor(rotor);
        
        char h = target.alter('H');
        char e = target.alter('E');
        
        rotor = buildRotor(initialAlphabet);
        target = new Spindle();
        target.addRotor(rotor);
        
        char decipheredH = target.decrypt(h);
        char decipheredE = target.decrypt(e);
        
        assertThat(decipheredH, is('H'));
        assertThat(decipheredE, is('E'));
        
    }
    
    private Rotor buildRotor(List<Character> encipheredChars) {
        
        Rotor rotor = new Rotor();
        rotor.setCipherAlphabet(encipheredChars);
        
        return rotor;
        
    }
  
    
}