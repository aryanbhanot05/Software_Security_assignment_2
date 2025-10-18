//Laptop computer: adds screen size to other Computer info

public class Laptop { //Laptop inherits from Computer
    
    private final Computer computer;
    private final String screenSize;

    //Constructors

    public Laptop(Computer computer, String screenSize) {
        
        if (!InputValidator.isValidComponent(screenSize)) {
            throw new IllegalArgumentException("Invalid Screen Size format. Input failed whitelist validation.");
        }
        
        this.computer = computer;
        this.screenSize=screenSize;
    }

    //Getter
    public String getScreenSize() {
        return this.screenSize;
    }

    public String getCPU() {
        return this.computer.getCPU();
    }

    public String getRAM() {
        return this.computer.getRAM();
    }

    public String getDisk() {
        return this.computer.getDisk();
    }

    //Return formatted version of data
    @Override
    public String toString() {
        return "Type:Laptop\tCPU:" + this.computer.getCPU() + "\tRAM:" + this.computer.getRAM() + "\tDisk:" + this.computer.getDisk() + "\tScreen:" + this.screenSize;
    }
    
}