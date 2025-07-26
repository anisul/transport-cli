package dev.cyanide.transportcli.cli;

import dev.cyanide.transportcli.service.TransportService;
import picocli.CommandLine;

@CommandLine.Command(
        name = "transport-cli",
        mixinStandardHelpOptions = true,
        version = "1.0.0",
        description = "Real-time transportation information CLI tool",
        subcommands = {DepartureCommand.class, TransportCommand.class, RouteCommand.class},
        commandListHeading = "%nCommands:%n",
        optionListHeading = "%nOptions:%n",
        footer = {
                "",
                "Examples:",
                "  departures -s \"Central Station\" -l 10",
                "  departures --station \"Main Street\" --format json",
                "",
                "Interactive commands:",
                "  help     - Show this help message",
                "  clear    - Clear the screen",
                "  exit     - Exit the application"
        }
)
public record TransportCommand(TransportService transportService) implements Runnable {

    @Override
    public void run() {
        // Default behavior when no subcommand is provided
        // System.out.println("Transport CLI - use --help to see available commands");
    }
}