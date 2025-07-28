package dev.cyanide.transportcli.cli;


import dev.cyanide.transportcli.OutputFormatter;
import dev.cyanide.transportcli.output.OutputType;
import picocli.CommandLine;

@CommandLine.Command(name = "route", description = "Get a route from a departure station to a destination station")
public class RouteCommand implements Runnable {

    @CommandLine.ParentCommand
    private TransportCommand parent;

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
            names = {"-t", "--type"},
            description = "Transport type",
            defaultValue = ""
    )
    private String transportType;


    @Override
    public void run() {
        try {
            var routes = parent.routeService().getRoutes(sourceStation, destinationStation, transportType, false);
            OutputFormatter.format(routes, OutputType.ROUTES);
        }catch (Exception e) {
            System.err.println("Error getting Route: " + e.getMessage());
        }
    }
}
