package dev.cyanide.transportcli.service;

import dev.cyanide.transportcli.api.MVGAPIClient;
import dev.cyanide.transportcli.types.Departure;
import dev.cyanide.transportcli.types.Location;
import dev.cyanide.transportcli.types.TransportType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {

    private final MVGAPIClient mvgApiClient;

    public TransportService(MVGAPIClient mvgApiClient) {
        this.mvgApiClient = mvgApiClient;
    }

    public List<Departure> getDepartures(
            String query,
            Integer limit,
            Integer offsetInMinutes,
            String transportType
    ) {
        var locations = mvgApiClient.getLocations(query);

        var nearestStationId = locations.stream()
                .filter(Location::isStation)
                .findFirst()
                .orElse(null);

        var transportTypeEnum = "ALL".equals(transportType) ? null : TransportType.fromStringSafe(transportType);

        if (nearestStationId != null) {
            return getDeparturesForStation(nearestStationId.getGlobalId(), limit, offsetInMinutes, transportTypeEnum);
        }

        return List.of();
    }

    public List<Departure> getDeparturesForStation(
            String stationId,
            Integer limit,
            Integer offsetInMinutes,
            TransportType transportType
    ) {
        return mvgApiClient.getDepartures(stationId, limit, offsetInMinutes, transportType);
    }
}
