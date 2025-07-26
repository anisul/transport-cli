package dev.cyanide.transportcli.api;

import dev.cyanide.transportcli.types.Departure;
import dev.cyanide.transportcli.types.Location;
import dev.cyanide.transportcli.types.Route;
import dev.cyanide.transportcli.types.TransportType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MVGAPIClient {

    private final String API_BASE_URL = "https://www.mvg.de";

    private final RestTemplate restTemplate;

    public MVGAPIClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Departure> getDepartures(String stationId, Integer limit, Integer offsetInMinutes, TransportType transportType) {
        var url = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .pathSegment("api", "bgw-pt", "v3", "departures")
                .queryParam("globalId", stationId)
                .queryParamIfPresent("limit", limit != null ? Optional.of(limit) : Optional.empty())
                .queryParamIfPresent("offsetInMinutes", offsetInMinutes != null
                        ? Optional.of(offsetInMinutes) : Optional.empty())
                .queryParamIfPresent("transportTypes", transportType != null
                        ? Optional.of(transportType) : Optional.empty())
                .build()
                .toUriString();

        var response = restTemplate.getForEntity(url, Departure[].class);
        return List.of(response.getBody());
    }

    public List<Location> getLocations(String query) {
        var url = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .pathSegment("api", "bgw-pt", "v3", "locations")
                .queryParam("query", query)
                .queryParam("limit", 5)
                .build()
                .toUriString();

        var response = restTemplate.getForEntity(url, Location[].class);
        return List.of(response.getBody());
    }

    public List<Route> getRoutes(
            String destinationStationId,
            String originStationId,
            OffsetDateTime plannedDepartureTime,
            Optional transportTypeOptional,
            boolean routingDateTimeIsArrival
    ) {
        var url = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .pathSegment("api", "bgw-pt", "v3", "routes")
                .queryParam("originStationGlobalId", originStationId)
                .queryParam("destinationStationGlobalId", destinationStationId)
                .queryParam("plannedDepartureTime", plannedDepartureTime)
                .queryParam("routingDateTimeIsArrival", routingDateTimeIsArrival)
                .queryParamIfPresent("transportTypes", transportTypeOptional)
                .build()
                .toUriString();

        var response = restTemplate.getForEntity(url, Route[].class);
        return List.of(response.getBody());
    }
}
