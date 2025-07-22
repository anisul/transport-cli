package dev.cyanide.transportcli.cli;


import dev.cyanide.transportcli.OutputFormatter;
import picocli.CommandLine;

@CommandLine.Command(name = "departures", description = "Get departures from a station")
public class DepartureCommand implements Runnable {

    @CommandLine.ParentCommand
    private TransportCommand parent;

    @CommandLine.Option(
            names = {"-s", "--station"},
            description = "Station name",
            required = true
    )
    private String station;

    @CommandLine.Option(
            names = {"-l", "--limit"},
            description = "Number limit for departures",
            defaultValue = "10"
    )
    private Integer limit;

    @CommandLine.Option(
            names = {"-o", "--offset"},
            description = "Offset in minutes for departures",
            defaultValue = "0"
    )
    private Integer offsetInMinutes;

    @CommandLine.Option(
            names = {"-f", "--format"},
            description = "Output format: table, json",
            defaultValue = "table"
    )
    private String format;

    @Override
    public void run() {
        try {
            var departures = parent.transportService().getDepartures(station, limit, offsetInMinutes);
            OutputFormatter.format(departures, format);
        } catch (Exception e) {
            System.err.println("Error getting departures: " + e.getMessage());
            System.exit(1);
        }
    }
}
