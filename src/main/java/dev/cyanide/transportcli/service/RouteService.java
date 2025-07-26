package dev.cyanide.transportcli.service;

import dev.cyanide.transportcli.api.MVGAPIClient;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    private final MVGAPIClient mvgApiClient;

    public RouteService(MVGAPIClient mvgApiClient) {
        this.mvgApiClient = mvgApiClient;
    }

    public void getRoute() {

    }
}
