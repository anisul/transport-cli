package dev.cyanide.transportcli.service;

import dev.cyanide.transportcli.api.MVGAPIClient;
import dev.cyanide.transportcli.types.Location;
import dev.cyanide.transportcli.types.LocationType;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private final MVGAPIClient mvgApiClient;

    public LocationService(MVGAPIClient mvgApiClient) {
        this.mvgApiClient = mvgApiClient;
    }

    public Location getNearestStationLocation(String query) {
        var locations = mvgApiClient.getLocations(query);

        return locations.stream()
                .filter(Location::isStation)
                .findFirst()
                .orElse(null);
    }
}
