package dev.cyanide.transportcli.cli;

import dev.cyanide.transportcli.service.RouteService;
import dev.cyanide.transportcli.service.TransportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.Scanner;

@Component
public class TransportCliRunner implements CommandLineRunner {

    private final TransportService transportService;
    private final RouteService routeService;
    private volatile boolean running = true;

    public TransportCliRunner(TransportService transportService, RouteService routeService) {
        this.transportService = transportService;
        this.routeService = routeService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("===========================================");
        System.out.println("    Transport CLI v1.0.0");
        System.out.println("    Real-time transportation information");
        System.out.println("===========================================");
        System.out.println("Type 'help' for available commands or 'exit' to quit.");
        System.out.println();

        if (args.length > 0) {
            executeCommand(String.join(" ", args));
        }

        startInteractiveMode();
    }

    private void startInteractiveMode() {
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.print("transport-cli> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye!");
                break;
            }

            if (input.equalsIgnoreCase("clear") || input.equalsIgnoreCase("cls")) {
                clearScreen();
                continue;
            }

            executeCommand(input);
            System.out.println();
        }

        scanner.close();
    }

    private void executeCommand(String input) {
        try {
            var cmdArgs = parseInput(input);
            var commandLine = new CommandLine(new TransportCommand(transportService, routeService));

            commandLine.setExecutionStrategy(new CommandLine.RunAll());
            commandLine.setUnmatchedArgumentsAllowed(false);

            var exitCode = commandLine.execute(cmdArgs);

            if (exitCode != 0 && !input.contains("--help") && !input.contains("-h")) {
                System.err.println("Command failed with exit code: " + exitCode);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            if (input.contains("--help") || input.contains("-h") || input.equalsIgnoreCase("help")) {

                try {
                    CommandLine commandLine = new CommandLine(new TransportCommand(transportService, routeService));
                    commandLine.execute("--help");
                } catch (Exception helpException) {
                    System.err.println("Could not display help: " + helpException.getMessage());
                }
            }
        }
    }

    private String[] parseInput(String input) {
        return input.trim().split("\\s+");
    }

    private void clearScreen() {
        try {
            // For Unix/Linux/Mac
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            for (var i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
