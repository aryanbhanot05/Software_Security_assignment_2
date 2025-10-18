//Computer class: manages computer CPU, RAM and Disk information

public class Computer {
    private final String CPU;
    private final String RAM;
    private final String disk;

    // Constructors
    public Computer(String CPU, String RAM, String disk) {

        // Whitelist Validation: Throw an exception if input is not safe
        if (!InputValidator.isValidCpu(CPU)) {
            throw new IllegalArgumentException("Invalid CPU format.");
        }
        if (!InputValidator.isValidRam(RAM)) {
            throw new IllegalArgumentException("Invalid RAM format.");
        }
        if (!InputValidator.isValidDisk(disk)) {
            throw new IllegalArgumentException("Invalid Disk format.");
        }

        this.CPU = CPU;
        this.RAM = RAM;
        this.disk = disk;
    }

    // Getters
    public String getCPU() {
        return this.CPU;
    }

    public String getRAM() {
        return this.RAM;
    }

    public String getDisk() {
        return this.disk;
    }
}