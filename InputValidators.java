import java.util.Scanner;

public class InputValidators {
    public static <E extends Enum<E>> E readEnumOrRetry(Scanner s, Class<E> enumType, String prompt) {
        while (true) {
            System.out.print(prompt);
            String in = s.nextLine().trim();

            // match by enum name (case-insensitive)
            for (E c : enumType.getEnumConstants()) {
                if (c.name().equalsIgnoreCase(in) || c.toString().equalsIgnoreCase(in))
                    return c;
            }

            // numeric mapping for RAM, DISK, SCREEN
            try {
                int n = Integer.parseInt(in);
                for (E c : enumType.getEnumConstants()) {
                    if (enumType.getSimpleName().equals("RAM")    && ((RAM)c).getGb()   == n) return c;
                    if (enumType.getSimpleName().equals("DISK")   && ((DISK)c).getGb()  == n) return c;
                    if (enumType.getSimpleName().equals("SCREEN") && ((SCREEN)c).getInch()== n) return c;
                }
            } catch (NumberFormatException ignore) {}

            System.out.print("Invalid value. Allowed: ");
            for (E c : enumType.getEnumConstants())
                System.out.print(c.name() + " ");
            System.out.println();
        }
    }
}