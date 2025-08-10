# Munich Transport CLI

![CI Pipeline](https://github.com/anisul/transport-cli/actions/workflows/ci.yml/badge.svg?branch=main) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [![Java](https://img.shields.io/badge/Java-21-orange)](https://openjdk.org/projects/jdk/21/) [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot) [![Gradle](https://img.shields.io/badge/Gradle-8.x-blue.svg)](https://gradle.org/) [![GitHub release (latest by date)](https://img.shields.io/github/v/release/anisul/transport-cli)](https://github.com/anisul/transport-cli/releases) [![GitHub all releases](https://img.shields.io/github/downloads/anisul/transport-cli/total)](https://github.com/anisul/transport-cli/releases)




A Spring Boot CLI application for accessing Munich public transport information via the MVG (Münchner Verkehrsgesellschaft) API. Get real-time departure information, plan routes, and search for stations right from your command line.

## Features

- 🚇 **Real-time departure information** - Get live updates for all transport modes
- 🗺️ **Route planning** - Plan journeys between any two stations
- 🔍 **Location-based station search** - Find nearby stations and stops
- 🚊 **Full transport support** - U-Bahn, S-Bahn, Bus, and Tram coverage
- 🖥️ **Cross-platform** - Works on Windows, macOS, and Linux

## Requirements
- Internet connection for real-time data

## Installation

### Option 1: Unix/Linux/macOS Package

```bash
# Download and extract
wget https://github.com/anisul/transport-cli/releases/download/v1.2.0/transport-cli-1.2.0-unix.tar.gz
tar -xzf transport-cli-1.2.0-unix.tar.gz

# Run the CLI
./transport-cli --help

# Optional: Add to your PATH
sudo cp transport-cli transport-cli.jar /usr/local/bin/
```

### Option 2: Windows Package

1. Download `transport-cli-1.2.0-windows.zip` from the [releases page](https://github.com/anisul/transport-cli/releases)
2. Extract the ZIP file
3. Run: `transport-cli.bat --help`

### Option 3: Download and Run (Requires Java 21+)

```bash
# Download the JAR file
wget https://github.com/anisul/transport-cli/releases/download/v1.2.0/transport-cli-1.2.0.jar

# Run directly
java -jar transport-cli-1.2.0.jar --help
```

### File Verification

Download `checksums.txt` to verify file integrity:

```bash
sha256sum -c checksums.txt
```

## Usage

### Basic Commands

```bash
# Show help
transport-cli --help

# Get departures from a specific station
transport-cli departures -s Marienplatz

# Get departures with custom limit
transport-cli departures - Hauptbahnhof --limit 5

# Get routes from source station to destination station
transport-cli route -s Haar -d Hauptbahnhof  

# Get routes from source station to destination station with specific transport type
transport-cli route -s Haar -d Hauptbahnhof -t SBAHN  

# Search for stations
transport-cli stations --search Universität
```



## Development

### Building from Source

```bash
# Clone the repository
git clone https://github.com/anisul/transport-cli.git
cd transport-cli

# Build with Gradle
./gradlew build

# Run locally
./gradlew bootRun --args="--help"

# Create distribution packages
./gradlew assembleDist
```

### Testing

```bash
# Run all tests
./gradlew test

# Run with coverage
./gradlew test jacocoTestReport
```

## Contributing

We welcome contributions! Please see our [Contributing Guidelines](CONTRIBUTING.md) for details.

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Transport Types Supported

- **UBAHN** - Munich subway system
- **SBAHN** - Suburban railway network
- **BUS** - City and regional buses
- **TRAM** - Munich tram network

## Issues & Support

Found a bug or have a feature request? Please report issues at:
**https://github.com/anisul/transport-cli/issues**

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- [MVG API](https://www.mvg.de/) for providing the transport data
- The Munich public transport community

---

**Latest Release**: [v1.2.0](https://github.com/anisul/transport-cli/releases/latest) | **Issues**: [Report here](https://github.com/anisul/transport-cli/issues) | **License**: [MIT](LICENSE)