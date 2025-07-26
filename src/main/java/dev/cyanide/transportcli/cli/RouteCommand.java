package dev.cyanide.transportcli.cli;


import picocli.CommandLine;

@CommandLine.Command(name = "route", description = "Get a route from a departure station to a destination station")
public class RouteCommand implements Runnable {

    @CommandLine.Option(
            names = {"-s", "--source"},
            description = "Source station name",
            required = true
    )
    private String sourceStation;

    @CommandLine.Option(
            names = {"-d", "--destination"},
            description = "Destination station name",
            required = true
    )
    private String destinationStation;


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
            names = {"-t", "--type"},
            description = "Transport type",
            defaultValue = "ALL"
    )
    private String transportType;


    @Override
    public void run() {

    }
}
