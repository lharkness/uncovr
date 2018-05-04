package info.uncovr;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SpindleTest {
    
    @Test
    public void testThatSpindleEnciphersCharactersCorrectly() {
        
        Rotor firstRotor = mock(Rotor.class);
        Rotor secondRotor = mock(Rotor.class);
        
        List<Rotor> rotorList = Arrays.asList(firstRotor, secondRotor);
        
        Spindle target = new Spindle(rotorList);
        
        when(firstRotor.getEncipheredCharacter('A')).thenReturn('B');
        when(secondRotor.getEncipheredCharacter('B')).thenReturn('C');
        when(firstRotor.getEncipheredCharacter('C')).thenReturn('D');
        
        when(firstRotor.getRotorIndex()).thenReturn(0);
        when(secondRotor.getRotorIndex()).thenReturn(1);
        
        assertThat(target.alter('A'), is('D'));
        
    }
    
    @Test
    public void testThatSpindleDeciphersCharactersCorrectly() {
        
        Rotor firstRotor = mock(Rotor.class);
        Rotor secondRotor = mock(Rotor.class);
        
        List<Rotor> rotorList = Arrays.asList(firstRotor, secondRotor);
        
        Spindle target = new Spindle(rotorList);
        
        when(firstRotor.getEncipheredCharacter('A')).thenReturn('B');
        when(secondRotor.getEncipheredCharacter('B')).thenReturn('C');
        when(firstRotor.getEncipheredCharacter('C')).thenReturn('D');
        
        when(firstRotor.getRotorIndex()).thenReturn(0);
        when(secondRotor.getRotorIndex()).thenReturn(1);
        
        assertThat(target.alter('A'), is('D'));
        
        assertThat(target.decrypt('D'), is('A'));
        
    }

}