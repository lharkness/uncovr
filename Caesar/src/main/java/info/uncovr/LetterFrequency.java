package info.uncovr;

/**
 * Container class to link letter, occurrences and frequencies
 */
public class LetterFrequency implements Comparable<LetterFrequency> {
    
    private char letter;
    private int occurrences;
    private double frequency;
   
    /**
     * default ctor
     */
    public LetterFrequency() {}
    
    /**
     * Initialization ctor - single character
     * @param letter the character 
     */
    public LetterFrequency(char letter) {
        this();
        this.letter = letter;
    }
    
    /**
     * initialization ctor for character and frequency
     * @param letter the character
     * @frequency the frequency
     */
    public LetterFrequency(char letter, double frequency) {
        this(letter);
        this.frequency = frequency;
    }
    
    /**
     * initialization ctor for character and occurrence
     * @param letter the character
     * @param occurrence the number of times it occurrs
     */
    public LetterFrequency(char letter, int occurrences) {
        this(letter);
        this.occurrences = occurrences;
    }
    
    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }
    
    public void setFrequency(double newFrequency) {
        this.frequency = newFrequency;
    }
    
    public void setLetter(char newLetter) {
        this.letter = letter;
    }
    
    public int getOccurrences() {
        return this.occurrences;
    }
    
    public double getFrequency() {
        return this.frequency;
    }
    
    public char getLetter() {
        return letter;
    }
    
    /**
     * Calculates the frequency given the data size
     * @param size the data size
     * @return the frequency
     */
    public LetterFrequency calculateFrequency(int size) {
        this.frequency = (double)this.occurrences/size;
        return this;
    }
    
    @Override
    public String toString() {
        return "Letter: " + letter + " occurrences " + occurrences + 
            " frequency " + frequency;
    }
    
    @Override
    public int compareTo(LetterFrequency other) {
        return (int)((other.frequency * 100)  - (this.frequency * 100));
    }
}