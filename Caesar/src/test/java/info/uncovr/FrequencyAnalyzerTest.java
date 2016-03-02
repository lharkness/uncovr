package info.uncovr;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class FrequencyAnalyzerTest {
    
    String input = "abcdefghijklmnopqrstuvwxyz";
    
    @Test
    public void testThatFrequenciesAreWhatWeExpect() {
        String localInput = "a";
        
        FrequencyAnalyzer target = new FrequencyAnalyzer();
        target.consume(localInput);
        
        List<LetterFrequency> results = target.getFrequencies();
        
        assertThat(results.size(), is(1));
        assertThat(results.get(0).getLetter(), is('a'));
        assertThat(results.get(0).getFrequency(), is(1.0));
        
    }
    
    @Test
    public void testThatFrequenciesAreWhatWeExpectForMultipleInputs() {
        FrequencyAnalyzer target = new FrequencyAnalyzer();
        target.consume(input);
        
        List<LetterFrequency> results = target.getFrequencies();
        
        assertThat(results.size(), is(input.length()));
        
        int index = 0;
        for (LetterFrequency item : results) {
            assertThat(item.getLetter(), is(input.charAt(index)));
            assertThat(item.getFrequency(), is((double)1/input.length()));
            index++;
        }
    }
}