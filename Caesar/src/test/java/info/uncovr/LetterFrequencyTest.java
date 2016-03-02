package info.uncovr;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.greaterThan;

public class LetterFrequencyTest {
    
    private LetterFrequency smallFrequency;
    private LetterFrequency largeFrequency;
    
    @Before
    public void setup() {
        smallFrequency = new LetterFrequency('A', 2).calculateFrequency(1);
        largeFrequency = new LetterFrequency('B', 42).calculateFrequency(1);
    }
    
    // Don't forget - descending order is preferred
    
    @Test
    public void testThatCompareToReturnsPositiveForLessThan() {
    
        assertThat(smallFrequency.compareTo(largeFrequency), greaterThan(0));
    
    }
    
    @Test
    public void testThatCompareToReturnsNegativeForGreaterThan() {
    
        assertThat(largeFrequency.compareTo(smallFrequency), lessThan(0));
    
    }
    
    @Test
    public void testThatCompareToReturnsZeroForEqual() {
    
        assertThat(smallFrequency.compareTo(smallFrequency), is(0));
    
    }
    
}