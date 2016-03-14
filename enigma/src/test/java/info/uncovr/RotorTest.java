package info.uncovr;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.Mock;
import static org.mockito.MockitoAnnotations.initMocks;

public class RotorTest {
    
    @Mock
    private Spindle mockSpindle;
    
    private  List<Character> alphabet = Arrays.asList('A', 'B', 'C', 'D', 'E', 
        'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 
        'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
    
    @Before
    public void setup() {
        initMocks(this);
    }
    
    @Test
    public void testThatNoOpRotorDoesNotChangeAnything() {
        
        Rotor target = new Rotor(alphabet);
        
        assertThat(target.getEncipheredCharacter('A'), is('A'));
        
    }
    
    
    @Test
    public void testThatRotorEnciphersAsExpected() {
    
        List<Character> encipheredAlphabet = Arrays.asList('E', 'B', 'C', 'D', 
            'A', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
        
        Rotor target = new Rotor(encipheredAlphabet);
        
        assertThat(target.getEncipheredCharacter('A'), is('E'));
       
    }
    
    @Test
    public void testThatRotorEnciphersWithSteppingAsExpected() {
    
        List<Character> encipheredAlphabet = Arrays.asList('E', 'B', 'C', 'D', 
            'A', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
        
        Rotor target = new Rotor(encipheredAlphabet);
        
        assertThat(target.getEncipheredCharacter('A'), is('E'));
        assertThat(target.getEncipheredCharacter('A'), is('Z'));
       
    }
    
    @Test
    public void testThatRotorEnciphersWithSteppingPastZAsExpected() {
    
        List<Character> encipheredAlphabet = Arrays.asList('E', 'B', 'C', 'D', 
            'A', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
        
        Rotor target = new Rotor(encipheredAlphabet);
        
        char expected = target.getEncipheredCharacter('A');
        
        for (int i = 0; i < 25; i++) {
            target.getEncipheredCharacter('A');
        }
        
        assertThat(target.getEncipheredCharacter('A'), is(expected));
    }
    
    @Test
    public void testThatTwentySixEnciphermentsNotifiesSpindle() {
        
        List<Character> encipheredAlphabet = Arrays.asList('E', 'B', 'C', 'D', 
            'A', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
        
        Rotor target = new Rotor(encipheredAlphabet);
        target.setSpindle(mockSpindle);
        
        for (int i = 0; i < 27; i++) {
            target.getEncipheredCharacter('A');
        }
        
        verify(mockSpindle).advance(target);
    }
    
    @Test
    public void testThatRotorDecryptsAsExpected() {
        List<Character> encipheredAlphabet = Arrays.asList('E', 'B', 'C', 'D', 
            'A', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
        
        Rotor target = new Rotor(encipheredAlphabet);
        
        assertThat(target.getEncipheredCharacter('A'), is('E'));
        
        target = new Rotor(encipheredAlphabet);
        
        assertThat(target.getDecipheredCharacter('E'), is('A'));
    }
        
    
}