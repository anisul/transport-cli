package dev.cyanide.transportcli.service;

import dev.cyanide.transportcli.api.MVGAPIClient;
import dev.cyanide.transportcli.types.Route;
import dev.cyanide.transportcli.types.TransportType;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final MVGAPIClient mvgApiClient;

    public RouteService(MVGAPIClient mvgApiClient) {
        this.mvgApiClient = mvgApiClient;
    }

    public List<Route> getRoutes(
            String originStationId,
            String destinationStationId,
            String transportType,
            boolean routingDateTimeIsArrival
    ) {
        var plannedDepartureTime = OffsetDateTime.now();

        var transportTypeOptional = !transportType.isEmpty() ? Optional.of(TransportType.valueOf(transportType)) : Optional.empty();

        return mvgApiClient.getRoutes(
                originStationId,
                destinationStationId,
                plannedDepartureTime,
                transportTypeOptional,
                routingDateTimeIsArrival);
    }
}
