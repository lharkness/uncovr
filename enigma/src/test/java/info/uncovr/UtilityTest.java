package info.uncovr;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class UtilityTest {
    
    @Test
    public void testThatSwapWorksForEvenSizedList() {
        List<Character> inputList = Arrays.asList('A', 'B', 'C', 'D');
        List<Character> shuffledList = Utility.shuffle(inputList);
        
        System.out.println("#########" + shuffledList);
    }
    
    @Test
    public void testThatSwapWorksForOddSizedList() {
        List<Character> inputList = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 
            'G');
        List<Character> shuffledList = Utility.shuffle(inputList);
        
        System.out.println("#########" + shuffledList);
    }
    
}