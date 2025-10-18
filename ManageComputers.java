//Manage Computers program: maintains an ArrayList of Computer objects, 
//can be either Laptop or Desktop, but never just Computer-type objects themselves

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class ManageComputers {

    public static void main(String args[]) {

        // This ArrayList will hold all the computers in the system. Note that the type
        // of objects expected in this
        // ArrayList are Computer, not Laptop or Desktop, but since those are subclasses
        // of Computer they can be
        // stored in an ArrayLiust<Computer> anyway.
        // ---- For the assignment: keep a single list that holds both Laptop and
        // Desktop (composition + immutability).
        ArrayList<Object> computers = new ArrayList<Object>();

        Scanner s = new Scanner(System.in);
        String menuOption = "";

        do { // Start of main program loop

            // Show computer data in ArrayList<Computer>
            showComputers(computers);

            // Display menu and return menu option selected by the user
            menuOption = getMenuSelection(s);

            switch (menuOption) {
                // Add new computer
                case "a":
                    addComputer(computers, s);
                    break;

                // Delete a computer
                case "d":
                    deleteComputer(computers, s);
                    break;

                // Edit a computer
                case "e":
                    editComputer(computers, s);
                    break;
            }

        } while (!menuOption.equals("x")); // Stop when "x" is entered

        s.close(); // Close keyboard scanner
    } // End of main

    // -----------------------------
    // Display menu and get user selection, return it
    private static String getMenuSelection(Scanner s) {
        String menuOption = "";

        // Display menu options on-screen
        System.out.println("----------");
        System.out.println("A) Add Computer");
        System.out.println("D) Delete Computer");
        System.out.println("E) Edit Computer");
        System.out.println("X) eXit");
        System.out.println("----------");

        // Get menu selection from keyboard
        System.out.print("Enter menu selection:");
        menuOption = s.nextLine();

        menuOption = menuOption.toLowerCase(); // Make lower case for comparison purposes

        return menuOption;
    } // End of getMenuSelection

    // -----------------------------
    // Show data for all laptops and desktops stored in ArrayList<Computer> create
    // in main() method
    private static void showComputers(ArrayList<Object> computers) {
        int computerListNumber = 0; // This variable is used to hold the "list number" for each computer, starting
                                    // at 1.

        System.out.println("=========");
        System.out.println("LIST OF COMPUTERS:-");

        for (Object o : computers) {
            computerListNumber++; // Increment list number for each computer
            // Call overridden toString() method for current object to get and display its
            // data
            System.out.println(computerListNumber + ": " + o.toString());
        }

        System.out.println("=========");
    } // End of showComputers

    // -----------------------------
    // Add a new Laptop or Desktop computer to the ArrayList<Computer>
    private static void addComputer(ArrayList<Object> computers, Scanner s) {
        String computerType = "";

        System.out.println("ADDING COMPUTER:-");
        System.out.print("Enter type of computer to add ('L' for Laptop, 'D' for Desktop):");
        computerType = s.nextLine();
        computerType = computerType.toLowerCase(); // Convert to lower case for comparison purposes

        switch (computerType) {

            // Add a laptop
            case "l":
                // Get CPU, RAM and Disk info (validated via whitelist)
                Computer baseLaptop = getComputerData(s);

                // Laptop-specific attribute (validated)
                String screenSize = promptUntilValid(
                        s,
                        "Enter screen size " + allowedList(SCREEN.values(), false),
                        InputValidator::isValidScreen);

                // Add new Laptop to ArrayList in main() method (immutable, composition)
                computers.add(new Laptop(
                        baseLaptop.getCPU(),
                        baseLaptop.getRAM(),
                        baseLaptop.getDisk(),
                        screenSize));
                break;

            // Add a desktop
            case "d":
                // Get CPU, RAM and Disk info (validated via whitelist)
                Computer baseDesktop = getComputerData(s);

                // Desktop-specific attribute (validated)
                String gpuType = promptUntilValid(
                        s,
                        "Enter GPU " + allowedList(GPU.values(), false),
                        InputValidator::isValidGpu);

                // Add new Desktop to ArrayList in main() method (immutable, composition)
                computers.add(new Desktop(
                        baseDesktop.getCPU(),
                        baseDesktop.getRAM(),
                        baseDesktop.getDisk(),
                        gpuType));
                break;

            // Invalid computer type to add entered
            default:
                System.out.println("Invalid computer type entered!");
        }
    } // End of addComputer

    // -----------------------------
    // Delete a specified computer from the ArrayList
    private static void deleteComputer(ArrayList<Object> computers, Scanner s) {
        int computerListNumberToDelete = 0;

        System.out.println("DELETE COMPUTER:-");
        System.out.print("Enter number of computer to delete:");
        computerListNumberToDelete = Integer.parseInt(s.nextLine());

        // Check if computer list number is valid before deleting computer from list
        if (computerListNumberToDelete >= 1 && computerListNumberToDelete <= computers.size()) {
            // Subtract 1 to get ArrayList index from on-screen list number to create
            // correct index in ArrayList to delete
            computers.remove(computerListNumberToDelete - 1);
        } else {
            System.out.println("Invalid computer number entered!");
        }
    } // End of deleteComputer

    // -----------------------------
    // Edit a computer. With immutability, create a NEW object and replace the
    // element in the list.
    private static void editComputer(ArrayList<Object> computers, Scanner s) {
        int computerListNumberToEdit = 0;
        String computerType = "";

        System.out.println("EDIT COMPUTER:-");
        System.out.print("Enter number of computer to edit:");
        computerListNumberToEdit = Integer.parseInt(s.nextLine());

        // Check that computerListNumberToEdit is valid first
        if (computerListNumberToEdit >= 1 && computerListNumberToEdit <= computers.size()) {

            // Determine exact type of computer being edited
            Object target = computers.get(computerListNumberToEdit - 1);
            if (target instanceof Laptop) {
                computerType = "laptop";
            } else if (target instanceof Desktop) {
                computerType = "desktop";
            }

            // Edit computer
            switch (computerType) {

                // Editing a laptop
                case "laptop":
                    System.out.println("Editing a Laptop:");

                    // Get CPU, RAM and Disk info (validated)
                    Computer newLaptopBase = getComputerData(s);

                    // Laptop-specific attribute (validated)
                    String newScreen = promptUntilValid(
                            s,
                            "Enter screen size " + allowedList(SCREEN.values(), false),
                            InputValidator::isValidScreen);

                    // Immutable replacement
                    computers.set(
                            computerListNumberToEdit - 1,
                            new Laptop(newLaptopBase.getCPU(), newLaptopBase.getRAM(), newLaptopBase.getDisk(),
                                    newScreen));
                    break;

                // Editing a desktop
                case "desktop":
                    System.out.println("Editing a Desktop:");

                    // Get CPU, RAM and Disk info (validated)
                    Computer newDesktopBase = getComputerData(s);

                    // Desktop-specific attribute (validated)
                    String newGpu = promptUntilValid(
                            s,
                            "Enter GPU " + allowedList(GPU.values(), false),
                            InputValidator::isValidGpu);

                    // Immutable replacement
                    computers.set(
                            computerListNumberToEdit - 1,
                            new Desktop(newDesktopBase.getCPU(), newDesktopBase.getRAM(), newDesktopBase.getDisk(),
                                    newGpu));
                    break;

                default:
                    System.out.println("Invalid computer type to edit!");
                    break;
            }

        } else {
            System.out.println("Invalid computer number entered!");
        }
    } // End of editComputer

    // -----------------------------
    // Helper method to get data common to Laptop and Desktop (CPU, RAM and disk)
    // objects.
    // Returns a Computer-type object holding these values as attributes.
    private static Computer getComputerData(Scanner s) {
        // IMPORTANT: use lower-case local variable names to avoid shadowing enum type
        // names.
        String cpu = promptUntilValid(
                s,
                "Enter CPU " + allowedList(CPU.values(), true),
                InputValidator::isValidCpu);
        String ram = promptUntilValid(
                s,
                "Enter RAM " + allowedList(RAM.values(), false),
                InputValidator::isValidRam);
        String disk = promptUntilValid(
                s,
                "Enter Disk " + allowedList(DISK.values(), false),
                InputValidator::isValidDisk);
        return new Computer(cpu, ram, disk);
    } // End of getComputerData

    // Pretty-print enum constants for prompts, e.g., "_16" -> "16", "I5" -> "i5" if
    // lower==true
    private static String pretty(Enum<?> e, boolean lower) {
        String base = e.name().replaceFirst("^_", "");
        return lower ? base.toLowerCase() : base;
    }

    // Build allowed values list from enum constants, e.g., (i5/i7): or (16/32):
    private static String allowedList(Enum<?>[] vals, boolean lower) {
        StringBuilder b = new StringBuilder("(");
        for (int i = 0; i < vals.length; i++) {
            b.append(pretty(vals[i], lower));
            if (i < vals.length - 1)
                b.append("/");
        }
        return b.append("): ").toString();
    }

    // Small helper to repeatedly prompt until the given predicate returns true.
    private static String promptUntilValid(Scanner sc, String prompt, Predicate<String> check) {
        while (true) {
            System.out.print(prompt);
            String v = sc.nextLine().trim();
            if (check.test(v))
                return v;
            System.out.println("Invalid input. Try again.");
        }
    }

} // End of ManageComputer class
