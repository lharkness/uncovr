package info.uncovr;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class PlugboardTest {
    
    @Test
    public void testThatNoOpPlugboardLeavesThingsAsIs() {
        Plugboard noOpPlugBoard = new Plugboard();
        
        assertThat('A', is(noOpPlugBoard.alter('A')));
    }
    
    @Test
    public void testThatPlugboardAltersLettersCorrectly() {
        
        Plugboard target = new Plugboard();
        Plugboard.LetterPair letterPair = new Plugboard.LetterPair('A', 'E');
        target.addLetterPair(letterPair);
        
        assertThat(target.alter('A'), is('E'));
        assertThat(target.alter('E'), is('A'));
        assertThat(target.alter('B'), is('B'));
        
    }
    
}