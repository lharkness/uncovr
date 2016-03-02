package info.uncovr;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class RotTest {
    
    @Test
    public void testThatRotNormalizesToUpperCase() {
        String plaintext = "This is some plaintext";
        String cipherText = Rot.modify(plaintext, 0);
        
        assertThat(cipherText, is(plaintext.toUpperCase()));
    }
    
    @Test
    public void testThatRotHandlesWrappingPastZ() {
        String plaintext = "z";
        
        assertThat(Rot.modify(plaintext, 1), is("A"));
    }
    
    @Test
    public void testThatRotHandlesWrappingPastA() {
        String plaintext = "a";
        
        assertThat(Rot.modify(plaintext, -1), is("Z"));
    }
    
    @Test
    public void testThatRotPassesNonAlphabeticCharactersThrough() {
        String plaintext = "123 .%^";
        
        assertThat(Rot.modify(plaintext, 12), is(plaintext.toUpperCase()));
    }

}
