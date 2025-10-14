import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern VALID_COMPONENT_PATTERN = Pattern.compile("^[a-zA-Z0-9 .-]{3,30}$");

    public static boolean isValidComponent(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        return VALID_COMPONENT_PATTERN.matcher(input.trim()).matches();
    }
}
