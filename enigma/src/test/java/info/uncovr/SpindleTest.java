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