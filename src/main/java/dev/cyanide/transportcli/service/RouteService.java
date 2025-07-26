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
            String destinationStationId,
            String originStationId,
            OffsetDateTime plannedDepartureTime,
            TransportType transportType,
            boolean routingDateTimeIsArrival
    ) {

        if (plannedDepartureTime == null) {
            plannedDepartureTime = OffsetDateTime.now();
        }

        var transportTypeOptional = transportType != null ? Optional.of(transportType) : Optional.empty();

        return mvgApiClient.getRoutes(
                destinationStationId,
                originStationId,
                plannedDepartureTime,
                transportTypeOptional,
                routingDateTimeIsArrival);
    }
}
