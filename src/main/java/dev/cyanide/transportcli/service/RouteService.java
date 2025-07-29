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
    private final LocationService locationService;

    public RouteService(MVGAPIClient mvgApiClient, LocationService locationService) {
        this.mvgApiClient = mvgApiClient;
        this.locationService = locationService;
    }

    public List<Route> getRoutes(
            String originStationQuery,
            String destinationStationQuery,
            String transportType,
            boolean routingDateTimeIsArrival
    ) {
        var plannedDepartureTime = OffsetDateTime.now();

        var originStationLocation = locationService.getNearestStationLocation(originStationQuery);
        var sourceStationLocation = locationService.getNearestStationLocation(destinationStationQuery);

        var transportTypeOptional = !transportType.isEmpty() ? Optional.of(TransportType.valueOf(transportType)) : Optional.empty();

        return mvgApiClient.getRoutes(
                originStationLocation.getGlobalId(),
                sourceStationLocation.getGlobalId(),
                plannedDepartureTime,
                transportTypeOptional,
                routingDateTimeIsArrival);
    }
}
