package info.uncovr;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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