package info.uncovr;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class DeSubstituteTest {
    
    private String plaintext = "This is a string of data.  I am not sure how" +
    "long it should be Fruit so called female good. Midst winged there unto " +
    "Above moveth dry deep, unto kind male light whose, you're own darkness " +
    "image signs said blessed our hath midst he. Multiply light female. " +
    "Firmament. First you'll fish meat whales. Male likeness that the i " +
    "multiply saw unto. Tree and, sixth light two. Thing us herb. Shall be " +
    "moving grass under greater waters yielding and their winged deep make " +
    "bearing. First, won't kind.  Don't fifth in were it moved together fish " +
    "under image evening creepeth you you're appear that moveth spirit, also " +
    "wherein. Own greater Bearing. Open you his rule isn't firmament.";
    
    @Test
    public void testThatDeSubstituteCanDecrypt() {
        
        String ciphertext = Rot.modify(plaintext, 13);
        String decryptedText = DeSubstitute.decrypt(ciphertext);

        System.out.println(decryptedText);
        
        int numberRight = 0;
        
        for (int i = 0; i < plaintext.length(); i++) {
            if (decryptedText.charAt(i) == plaintext.charAt(i)) {
                numberRight++;
            }
        }
        
        System.out.println((double)numberRight/plaintext.length());
    }
    
    
}