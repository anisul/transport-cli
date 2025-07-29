package dev.cyanide.transportcli.output;

import dev.cyanide.transportcli.types.Departure;
import dev.cyanide.transportcli.types.Route;
import dev.cyanide.transportcli.types.RoutePart;

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
        var optionNumber = 1;
        for (Route route : routes) {
            printRouteOption(route, optionNumber++);
        }
    }

    private static void printRouteOption(Route route, int optionNumber) {
        System.out.println("Option " + optionNumber + ":");

        var parts = route.getParts();
        for (int i = 0; i < parts.size(); i++) {
            boolean isFirstPart = (i == 0);
            boolean isLastPart = (i == parts.size() - 1);

            printRoutePart(parts.get(i), isFirstPart, isLastPart);
        }
    }

    private static void printRoutePart(RoutePart part, boolean isFirstPart, boolean isLastPart) {
        if (isFirstPart) {
            printTableBorder(BorderType.TOP);
        }

        printRoutePartFromRow(part);
        printRoutePartToRow(part);

        if (isLastPart) {
            printTableBorder(BorderType.BOTTOM);
        } else {
            printTableBorder(BorderType.MIDDLE);
        }
    }

    private static void printRoutePartFromRow(RoutePart part) {
        var delayText = formatDelayText(part.getFrom().getDepartureDelayInMinutes());
        System.out.printf("│ %-10s │ %-30s │ %-10s %-10s │%n",
                truncate(part.getLine().getLabel(), 10),
                truncate(part.getFrom().getName(), 30),
                truncate(part.getFrom().getLocalPlannedDepartureIn24HrAsString(), 10),
                truncate(delayText, 10)
        );
    }

    private static void printRoutePartToRow(RoutePart part) {
        var delayText = formatDelayText(part.getTo().getArrivalDelayInMinutes());
        System.out.printf("│ %-10s │ %-30s │ %-10s %-10s │%n",
                truncate("", 10),
                truncate(part.getTo().getName(), 30),
                truncate(part.getTo().getLocalPlannedDepartureIn24HrAsString(), 10),
                truncate(delayText, 10)
        );
    }

    private static void formatDeparturesTable(List<Departure> departures) {
        printDepartureTableHeader();

        for (Departure departure : departures) {
            printDepartureRow(departure);
        }

        printDepartureTableFooter();
    }

    private static void printDepartureTableHeader() {
        System.out.println("┌──────────┬─────────────────────────┬─────────────────────────┬────────┬─────────┐");
        System.out.println("│   Line   │       Destination       │ Departure               │  Type  │  Delay  │");
        System.out.println("├──────────┼─────────────────────────┼─────────────────────────┼────────┼─────────┤");
    }

    private static void printDepartureRow(Departure departure) {
        var delayText = formatDepartureDelayText(departure.getDelayInMinutes());
        System.out.printf("│ %-8s │ %-23s │ %-23s │ %-6s │ %-7s │%n",
                truncate(departure.getLabel(), 8),
                truncate(departure.getDestination(), 23),
                truncate(departure.getPlannedDepartureTimeAsLocalDateTime().toString(), 23),
                truncate(departure.getTransportType(), 6),
                delayText
        );
    }

    private static void printDepartureTableFooter() {
        System.out.println("└──────────┴─────────────────────────┴─────────────────────────┴────────┴─────────┘");
    }

    private static void printTableBorder(BorderType borderType) {
        switch (borderType) {
            case TOP:
                System.out.println("┌─────────────────────────────────────────────────────────────────────┐");
                break;
            case MIDDLE:
                System.out.println("├─────────────────────────────────────────────────────────────────────┤");
                break;
            case BOTTOM:
                System.out.println("└─────────────────────────────────────────────────────────────────────┘");
                break;
        }
    }

    private static String formatDelayText(int delayInMinutes) {
        return delayInMinutes > 0 ? "(+" + delayInMinutes + "min)" : "";
    }

    private static String formatDepartureDelayText(int delayInMinutes) {
        return delayInMinutes == 0 ? "On time" : "+" + delayInMinutes + "m";
    }

    private static String truncate(String str, int maxLength) {
        if (str == null) return "";
        return str.length() <= maxLength ? str : str.substring(0, maxLength - 3) + "...";
    }

}
