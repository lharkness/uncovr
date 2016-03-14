package info.uncovr;

public class EnigmaSettings {
    
    private Plugboard plugboard;
    private Spindle spindle;
    
    public EnigmaSettings() {
        this.plugboard = new Plugboard();
        this.spindle = new Spindle();
    }
    
    public EnigmaSettings(Plugboard plugboard, Spindle spindle) {
        this.plugboard = plugboard;
        this.spindle = spindle;
    }
    
    public Plugboard getPlugboard() {
        return this.plugboard;
    }
    
    public Spindle getSpindle() {
        return this.spindle;
    }
    
}