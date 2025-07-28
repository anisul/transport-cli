package dev.cyanide.transportcli;

import dev.cyanide.transportcli.output.OutputType;
import dev.cyanide.transportcli.types.Departure;
import dev.cyanide.transportcli.types.Route;

import java.util.List;

public class OutputFormatter {

    public static void format(List<?> data, OutputType outputType) {
        if (data.isEmpty()) {
            System.out.println("No data found.");
            return;
        }

        if (outputType == OutputType.ROUTES) {
            formatRoutesTable((List<Route>) data);
        } else if (outputType == OutputType.DEPARTURES) {
            formatDeparturesTable((List<Departure>) data);
        }
    }

    private static void formatRoutesTable(List<Route> routes) {
        System.out.println("──────────────────────────────────────────────────────────────");

        for (var route : routes) {
            for (var part : route.getParts()) {
                // print from
                System.out.printf("│ %-5s │ %-15s │ %-20s │ %-10s │%n",
                        truncate(part.getLine().getLabel(), 10),
                        truncate(part.getFrom().getName(), 10),
                        truncate(part.getFrom().getPlannedDeparture().toString(), 10),
                        truncate("(+ test min)", 5)
                );

                // linebreak
                System.out.println();

                // print to
                System.out.printf("│ %-5s │ %-15s │ %-20s │ %-10s │%n",
                        truncate(" ", 10),
                        truncate(part.getTo().getName(), 10),
                        truncate(part.getTo().getPlannedDeparture().toString(), 10),
                        truncate("(+" + part.getTo().getPlannedDeparture().toString() + " min)", 5)
                );

                System.out.println("──────────────────────────────────────────────────────────────");
            }
        }

    }

    private static void formatDeparturesTable(List<Departure> departures) {
        System.out.println("┌──────────┬─────────────────────────┬─────────────────────────┬────────┬─────────┐");
        System.out.println("│   Line   │       Destination       │ Departure               │  Type  │  Delay  │");
        System.out.println("├──────────┼─────────────────────────┼─────────────────────────┼────────┼─────────┤");

        for (Departure dep : departures) {
            String delayStr = dep.getDelayInMinutes() == 0 ? "On time" : "+" + dep.getDelayInMinutes() + "m";
            System.out.printf("│ %-8s │ %-23s │ %-23s │ %-6s │ %-7s │%n",
                    truncate(dep.getLabel(), 8),
                    truncate(dep.getDestination(), 23),
                    truncate(dep.getPlannedDepartureTimeAsLocalDateTime().toString(), 23),
                    truncate(dep.getTransportType(), 6),
                    delayStr
            );
        }

        System.out.println("└──────────┴─────────────────────────┴─────────────────────────┴────────┴─────────┘");
    }

    private static String truncate(String str, int maxLength) {
        if (str == null) return "";
        return str.length() <= maxLength ? str : str.substring(0, maxLength - 3) + "...";
    }
}
