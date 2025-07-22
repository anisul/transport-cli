package dev.cyanide.transportcli.service;

import dev.cyanide.transportcli.api.MVGAPIClient;
import dev.cyanide.transportcli.types.Departure;
import dev.cyanide.transportcli.types.Location;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {

    private final MVGAPIClient mvgApiClient;

    public TransportService(MVGAPIClient mvgApiClient) {
        this.mvgApiClient = mvgApiClient;
    }

    public List<Departure> getDepartures(String query, Integer limit, Integer offsetInMinutes) {
        var locations = mvgApiClient.getLocations(query);

        var nearestStationId = locations.stream()
                .filter(Location::isStation)
                .findFirst()
                .orElse(null);

        if (nearestStationId != null) {
            return getDeparturesForStation(nearestStationId.getGlobalId(), limit, offsetInMinutes);
        }

        return List.of();
    }

    public List<Departure> getDeparturesForStation(String stationId, Integer limit, Integer offsetInMinutes) {
        return mvgApiClient.getDepartures(stationId, limit, offsetInMinutes);
    }
}
