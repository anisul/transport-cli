package dev.cyanide.transportcli.types;

public enum TransportType {
    SCHIFF,
    UBAHN,
    TRAM,
    SBAHN,
    BUS,
    REGIONAL_BUS,
    BAH,
    BAHN,
    PEDESTRIAN;

    /**
     * Returns the enum constant for the given string, ignoring case.
     * @param value the string value (case-insensitive)
     * @return the corresponding TransportType enum constant
     * @throws IllegalArgumentException if no matching enum constant is found
     */
    public static TransportType fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Transport type cannot be null");
        }
        return TransportType.valueOf(value.toUpperCase());
    }

    /**
     * Alternative method that returns null instead of throwing exception for invalid values
     */
    public static TransportType fromStringSafe(String value) {
        if (value == null) {
            return null;
        }
        try {
            return TransportType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
