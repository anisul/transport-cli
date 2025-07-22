package dev.cyanide.transportcli;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dev.cyanide.transportcli.types.Departure;

import java.util.List;

public class OutputFormatter {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static void format(List<?> data, String format) {
        if (data.isEmpty()) {
            System.out.println("No data found.");
            return;
        }

        switch (format.toLowerCase()) {
            case "json" -> formatJson(data);
            case "table" -> formatTable(data);
            default -> {
                System.err.println("Unknown format: " + format + ". Using table format.");
                formatTable(data);
            }
        }
    }

    private static void formatJson(List<?> data) {
        try {
            System.out.println(objectMapper.writeValueAsString(data));
        } catch (Exception e) {
            System.err.println("Error formatting JSON: " + e.getMessage());
        }
    }

    private static void formatTable(List<?> data) {
        if (data.isEmpty()) return;
        formatDeparturesTable((List<Departure>) data);
    }

    private static void formatDeparturesTable(List<Departure> departures) {
        System.out.println("┌──────────┬─────────────────────────┬─────────────────────────┬────────┬──────────┬─────────┐");
        System.out.println("│   Line   │       Destination       │ Departure               │  Type  │ Platform │  Delay  │");
        System.out.println("├──────────┼─────────────────────────┼─────────────────────────┼────────┼──────────┼─────────┤");

        for (Departure dep : departures) {
            String delayStr = dep.getDelayInMinutes() == 0 ? "On time" : "+" + dep.getDelayInMinutes() + "m";
            System.out.printf("│ %-8s │ %-23s │ %-23s │ %-6s │ %-8s │ %-7s │%n",
                    truncate(dep.getLabel(), 8),
                    truncate(dep.getDestination(), 23),
                    truncate(dep.getPlannedDepartureTimeAsLocalDateTime().toString(), 23),
                    truncate(dep.getTransportType(), 6),
                    truncate(dep.getNetwork(), 8),
                    delayStr
            );
        }

        System.out.println("└──────────┴─────────────────────────┴─────────────────────────┴────────┴──────────┴─────────┘");
    }

    private static String truncate(String str, int maxLength) {
        if (str == null) return "";
        return str.length() <= maxLength ? str : str.substring(0, maxLength - 3) + "...";
    }
}
