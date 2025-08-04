package dev.cyanide.transportcli.service;

import dev.cyanide.transportcli.api.MVGAPIClient;
import dev.cyanide.transportcli.types.Location;
import dev.cyanide.transportcli.types.Route;
import dev.cyanide.transportcli.types.TransportType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {

    @Mock
    private MVGAPIClient mvgApiClient;

    @Mock
    private LocationService locationService;

    private RouteService routeService;

    @BeforeEach
    void setUp() {
        routeService = new RouteService(mvgApiClient, locationService);
    }

    @Test
    void getRoutes_WithValidStationsAndTransportType_ShouldReturnRoutes() {
        var originQuery = "Marienplatz";
        var destinationQuery = "Hauptbahnhof";
        var transportType = "UBAHN";
        var routingDateTimeIsArrival = false;

        var originLocation = createMockLocation("origin-123");
        var destinationLocation = createMockLocation("dest-456");
        var mockRoute = createMockRoute();
        var mockRoutes = List.of(mockRoute);

        when(locationService.getNearestStationLocation(originQuery)).thenReturn(originLocation);
        when(locationService.getNearestStationLocation(destinationQuery)).thenReturn(destinationLocation);
        when(mvgApiClient.getRoutes(
                eq("origin-123"),
                eq("dest-456"),
                any(OffsetDateTime.class),
                eq(Optional.of(TransportType.UBAHN)),
                eq(false)
        )).thenReturn(mockRoutes);

        var result = routeService.getRoutes(originQuery, destinationQuery, transportType, routingDateTimeIsArrival);

        assertThat(result).hasSize(1);
        assertThat(result.getFirst()).isEqualTo(mockRoute);

        verify(locationService).getNearestStationLocation(originQuery);
        verify(locationService).getNearestStationLocation(destinationQuery);
        verify(mvgApiClient).getRoutes(
                eq("origin-123"),
                eq("dest-456"),
                any(OffsetDateTime.class),
                eq(Optional.of(TransportType.UBAHN)),
                eq(false)
        );
    }

    @Test
    void getRoutes_WithEmptyTransportType_ShouldPassEmptyOptional() {
        var originQuery = "Sendlinger Tor";
        var destinationQuery = "Odeonsplatz";
        var transportType = "";
        var routingDateTimeIsArrival = true;

        var originLocation = createMockLocation("origin-789");
        var destinationLocation = createMockLocation("dest-101");
        List<Route> mockRoutes = List.of();

        when(locationService.getNearestStationLocation(originQuery)).thenReturn(originLocation);
        when(locationService.getNearestStationLocation(destinationQuery)).thenReturn(destinationLocation);
        when(mvgApiClient.getRoutes(
                eq("origin-789"),
                eq("dest-101"),
                any(OffsetDateTime.class),
                eq(Optional.empty()),
                eq(true)
        )).thenReturn(mockRoutes);

        var result = routeService.getRoutes(originQuery, destinationQuery, transportType, routingDateTimeIsArrival);

        assertThat(result).isEmpty();
        verify(mvgApiClient).getRoutes(
                eq("origin-789"),
                eq("dest-101"),
                any(OffsetDateTime.class),
                eq(Optional.empty()),
                eq(true)
        );
    }

    @Test
    void getRoutes_WithAllTransportTypes_ShouldReturnAllRoutes() {
        var originQuery = "Theresienwiese";
        var destinationQuery = "Maximilianstrasse";
        var transportType = "SBAHN";
        var routingDateTimeIsArrival = false;

        var originLocation = createMockLocation("origin-202");
        var destinationLocation = createMockLocation("dest-303");
        var route1 = createMockRoute();
        var route2 = createMockRoute();
        var mockRoutes = List.of(route1, route2);

        when(locationService.getNearestStationLocation(originQuery)).thenReturn(originLocation);
        when(locationService.getNearestStationLocation(destinationQuery)).thenReturn(destinationLocation);
        when(mvgApiClient.getRoutes(
                eq("origin-202"),
                eq("dest-303"),
                any(OffsetDateTime.class),
                eq(Optional.of(TransportType.SBAHN)),
                eq(false)
        )).thenReturn(mockRoutes);

        var result = routeService.getRoutes(originQuery, destinationQuery, transportType, routingDateTimeIsArrival);

        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(route1, route2);
    }

    @Test
    void getRoutes_WithArrivalTime_ShouldSetCorrectFlag() {
        var originQuery = "Giesing";
        var destinationQuery = "Trudering";
        var transportType = "BUS";
        var routingDateTimeIsArrival = true;

        var originLocation = createMockLocation("origin-404");
        var destinationLocation = createMockLocation("dest-505");
        List<Route> mockRoutes = List.of();

        when(locationService.getNearestStationLocation(originQuery)).thenReturn(originLocation);
        when(locationService.getNearestStationLocation(destinationQuery)).thenReturn(destinationLocation);
        when(mvgApiClient.getRoutes(
                anyString(),
                anyString(),
                any(OffsetDateTime.class),
                any(Optional.class),
                eq(true)
        )).thenReturn(mockRoutes);

        routeService.getRoutes(originQuery, destinationQuery, transportType, routingDateTimeIsArrival);

        verify(mvgApiClient).getRoutes(
                eq("origin-404"),
                eq("dest-505"),
                any(OffsetDateTime.class),
                eq(Optional.of(TransportType.BUS)),
                eq(true)
        );
    }

    @Test
    void getRoutes_ShouldUsePlannedDepartureTimeAroundNow() {
        var originQuery = "Karlsplatz";
        var destinationQuery = "Lehel";
        var transportType = "TRAM";
        var routingDateTimeIsArrival = false;

        var originLocation = createMockLocation("origin-606");
        var destinationLocation = createMockLocation("dest-707");
        List<Route> mockRoutes = List.of();

        when(locationService.getNearestStationLocation(originQuery)).thenReturn(originLocation);
        when(locationService.getNearestStationLocation(destinationQuery)).thenReturn(destinationLocation);
        when(mvgApiClient.getRoutes(
                anyString(),
                anyString(),
                any(OffsetDateTime.class),
                any(Optional.class),
                anyBoolean()
        )).thenReturn(mockRoutes);

        var beforeCall = OffsetDateTime.now();

        routeService.getRoutes(originQuery, destinationQuery, transportType, routingDateTimeIsArrival);

        var afterCall = OffsetDateTime.now();

        ArgumentCaptor<OffsetDateTime> timeCaptor = ArgumentCaptor.forClass(OffsetDateTime.class);
        verify(mvgApiClient).getRoutes(
                anyString(),
                anyString(),
                timeCaptor.capture(),
                any(Optional.class),
                anyBoolean()
        );

        var capturedTime = timeCaptor.getValue();
        assertThat(capturedTime).isBetween(beforeCall, afterCall);
    }

    @Test
    void getRoutes_WithDifferentTransportTypes_ShouldHandleAllEnumValues() {
        var originQuery = "Freimann";
        var destinationQuery = "Bogenhausen";
        var routingDateTimeIsArrival = false;

        var originLocation = createMockLocation("origin-808");
        var destinationLocation = createMockLocation("dest-909");
        List<Route> mockRoutes = List.of();

        when(locationService.getNearestStationLocation(originQuery)).thenReturn(originLocation);
        when(locationService.getNearestStationLocation(destinationQuery)).thenReturn(destinationLocation);
        when(mvgApiClient.getRoutes(
                anyString(),
                anyString(),
                any(OffsetDateTime.class),
                any(Optional.class),
                anyBoolean()
        )).thenReturn(mockRoutes);

        String[] transportTypes = {"UBAHN", "SBAHN", "BUS", "TRAM"};

        for (String transportType : transportTypes) {
            routeService.getRoutes(originQuery, destinationQuery, transportType, routingDateTimeIsArrival);

            verify(mvgApiClient).getRoutes(
                    eq("origin-808"),
                    eq("dest-909"),
                    any(OffsetDateTime.class),
                    eq(Optional.of(TransportType.valueOf(transportType))),
                    eq(false)
            );
        }
    }

    private Location createMockLocation(String globalId) {
        Location location = mock(Location.class);
        when(location.getGlobalId()).thenReturn(globalId);
        return location;
    }

    private Route createMockRoute() {
        return mock(Route.class);
    }
}