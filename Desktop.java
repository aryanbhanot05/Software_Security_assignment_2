//Desktop computer: adds GPU type

public class Desktop { // Inherits from Computer
    private final Computer computer;
    private final String GPUType;

    // Constructors

    public Desktop(String CPU, String RAM, String disk, String GPUType) {

        if (!InputValidator.isValidGpu(GPUType)) {
            throw new IllegalArgumentException("Invalid GPU format. Input failed whitelist validation.");
        }

        this.computer = new Computer(CPU, RAM, disk);
        this.GPUType = GPUType;
    }

    // Getter
    public String getGPUType() {
        return this.GPUType;
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

    // Return formatted version of data
    @Override
    public String toString() {
        return "Type:Desktop\tCPU:" + this.computer.getCPU() + "\tRAM:" + this.computer.getRAM() + "\tDisk:"
                + this.computer.getDisk() + "\tGPU:" + this.GPUType;
    }

}