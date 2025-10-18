// Validates user input using enum-based whitelists.
// Maps console strings (e.g., "i5", "16", "512", "Nvidia", "13")
// to enum constant names (e.g., "I5", "_16", "_512", "NVIDIA", "_13").

public class InputValidator {

    // Public String -> boolean API so we can pass method references to
    // Predicate<String>
    public static boolean isValidCpu(String s) {
        return tryEnum(CPU.class, mapCpu(s));
    }

    public static boolean isValidRam(String s) {
        return tryEnum(RAM.class, mapRam(s));
    }

    public static boolean isValidDisk(String s) {
        return tryEnum(DISK.class, mapDisk(s));
    }

    public static boolean isValidGpu(String s) {
        return tryEnum(GPU.class, mapGpu(s));
    }

    public static boolean isValidScreen(String s) {
        return tryEnum(SCREEN.class, mapScreen(s));
    }

    // Generic enum lookup by constant name
    private static <E extends Enum<E>> boolean tryEnum(Class<E> clazz, String enumName) {
        if (enumName == null)
            return false;
        String name = enumName.trim();
        if (name.isEmpty())
            return false;
        try {
            Enum.valueOf(clazz, name);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    // ----- Mappers: convert user-facing strings to enum constant names -----

    // CPU: "i5"/"i7" -> "I5"/"I7"
    private static String mapCpu(String v) {
        if (v == null)
            return null;
        String u = v.trim().toUpperCase();
        return ("I5".equals(u) || "I7".equals(u)) ? u : "__INVALID__";
    }

    // RAM: "16"/"32" -> "_16"/"_32" (teammate style with leading underscore)
    private static String mapRam(String v) {
        if (v == null)
            return null;
        String t = v.trim();
        if ("16".equals(t))
            return "_16";
        if ("32".equals(t))
            return "_32";
        return "__INVALID__";
    }

    // DISK: "512"/"1024" -> "_512"/"_1024"
    private static String mapDisk(String v) {
        if (v == null)
            return null;
        String t = v.trim();
        if ("512".equals(t))
            return "_512";
        if ("1024".equals(t))
            return "_1024";
        return "__INVALID__";
    }

    // GPU: "Nvidia"/"AMD" (case-insensitive) -> "NVIDIA"/"AMD"
    private static String mapGpu(String v) {
        if (v == null)
            return null;
        String u = v.trim().toUpperCase();
        return ("NVIDIA".equals(u) || "AMD".equals(u)) ? u : "__INVALID__";
    }

    // SCREEN: "13"/"14" -> "_13"/"_14"
    private static String mapScreen(String v) {
        if (v == null)
            return null;
        String t = v.trim();
        if ("13".equals(t))
            return "_13";
        if ("14".equals(t))
            return "_14";
        return "__INVALID__";
    }
}
