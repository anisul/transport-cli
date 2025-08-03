package dev.cyanide.transportcli.service;

import dev.cyanide.transportcli.api.MVGAPIClient;
import dev.cyanide.transportcli.types.Departure;
import dev.cyanide.transportcli.types.Location;
import dev.cyanide.transportcli.types.TransportType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TransportServiceTest {

    @Mock
    private MVGAPIClient mvgApiClient;

    private TransportService transportService;

    @BeforeEach
    void setUp() {
        transportService = new TransportService(mvgApiClient);
    }

    @Test
    void getDepartures_WithValidQuery_ShouldReturnDepartures() {
        var query = "Marienplatz";
        var limit = 10;
        var offsetInMinutes = 0;
        var transportType = "UBAHN";

        var mockStation = createMockStation("station-123", true);
        var mockLocations = List.of(mockStation);

        var mockDeparture = createMockDeparture();
        List<Departure> mockDepartures = List.of(mockDeparture);

        when(mvgApiClient.getLocations(query)).thenReturn(mockLocations);
        when(mvgApiClient.getDepartures(eq("station-123"), eq(limit), eq(offsetInMinutes), any(TransportType.class)))
                .thenReturn(mockDepartures);

        List<Departure> result = transportService.getDepartures(query, limit, offsetInMinutes, transportType);

        assertThat(result).hasSize(1);
        assertThat(result.getFirst()).isEqualTo(mockDeparture);

        verify(mvgApiClient).getLocations(query);
        verify(mvgApiClient).getDepartures("station-123", limit, offsetInMinutes, TransportType.fromStringSafe(transportType));
    }

    @Test
    void getDepartures_WithAllTransportType_ShouldPassNullToClient() {
        var query = "Hauptbahnhof";
        var limit = 5;
        var offsetInMinutes = 10;
        var transportType = "ALL";

        var mockStation = createMockStation("station-456", true);
        var mockLocations = List.of(mockStation);
        List<Departure> mockDepartures = List.of();

        when(mvgApiClient.getLocations(query)).thenReturn(mockLocations);
        when(mvgApiClient.getDepartures(eq("station-456"), eq(limit), eq(offsetInMinutes), isNull()))
                .thenReturn(mockDepartures);

        transportService.getDepartures(query, limit, offsetInMinutes, transportType);

        verify(mvgApiClient).getDepartures("station-456", limit, offsetInMinutes, null);
    }

    @Test
    void getDepartures_WithNoStationFound_ShouldReturnEmptyList() {
        var query = "NonExistentPlace";
        var limit = 10;
        var offsetInMinutes = 0;
        var transportType = "UBAHN";

        var mockNonStation = createMockStation("poi-123", false);
        var mockLocations = List.of(mockNonStation);

        when(mvgApiClient.getLocations(query)).thenReturn(mockLocations);

        List<Departure> result = transportService.getDepartures(query, limit, offsetInMinutes, transportType);

        assertThat(result).isEmpty();
        verify(mvgApiClient).getLocations(query);
        verify(mvgApiClient, never()).getDepartures(anyString(), anyInt(), anyInt(), any());
    }

    @Test
    void getDepartures_WithEmptyLocations_ShouldReturnEmptyList() {
        var query = "EmptyQuery";
        var limit = 10;
        var offsetInMinutes = 0;
        var transportType = "BUS";

        when(mvgApiClient.getLocations(query)).thenReturn(List.of());

        List<Departure> result = transportService.getDepartures(query, limit, offsetInMinutes, transportType);

        assertThat(result).isEmpty();
        verify(mvgApiClient).getLocations(query);
        verify(mvgApiClient, never()).getDepartures(anyString(), anyInt(), anyInt(), any());
    }

    @Test
    void getDepartures_WithMultipleLocations_ShouldUseFirstStation() {
        var query = "Multiple Results";
        var limit = 10;
        var offsetInMinutes = 0;
        var transportType = "SBAHN";

        var mockNonStation = createMockStation("poi-123", false);
        var mockFirstStation = createMockStation("station-first", true);
        var mockSecondStation = createMockStation("station-second", true);
        var mockLocations = List.of(mockNonStation, mockFirstStation, mockSecondStation);

        List<Departure> mockDepartures = List.of();

        when(mvgApiClient.getLocations(query)).thenReturn(mockLocations);
        when(mvgApiClient.getDepartures(eq("station-first"), eq(limit), eq(offsetInMinutes), any(TransportType.class)))
                .thenReturn(mockDepartures);

        transportService.getDepartures(query, limit, offsetInMinutes, transportType);

        verify(mvgApiClient).getDepartures("station-first", limit, offsetInMinutes, TransportType.fromStringSafe(transportType));
        verify(mvgApiClient, never()).getDepartures(eq("station-second"), anyInt(), anyInt(), any());
    }

    @Test
    void getDeparturesForStation_ShouldCallClientDirectly() {
        var stationId = "station-789";
        var limit = 15;
        var offsetInMinutes = 5;
        var transportType = TransportType.UBAHN;

        var mockDeparture = createMockDeparture();
        var mockDepartures = List.of(mockDeparture);

        when(mvgApiClient.getDepartures(stationId, limit, offsetInMinutes, transportType))
                .thenReturn(mockDepartures);

        var result = transportService.getDeparturesForStation(stationId, limit, offsetInMinutes, transportType);

        assertThat(result).hasSize(1);
        assertThat(result.getFirst()).isEqualTo(mockDeparture);
        verify(mvgApiClient).getDepartures(stationId, limit, offsetInMinutes, transportType);
    }

    @Test
    void getDeparturesForStation_WithNullTransportType_ShouldPassNull() {
        var stationId = "station-null-test";
        var limit = 20;
        var offsetInMinutes = 15;
        TransportType transportType = null;

        List<Departure> mockDepartures = List.of();

        when(mvgApiClient.getDepartures(stationId, limit, offsetInMinutes, null))
                .thenReturn(mockDepartures);

        var result = transportService.getDeparturesForStation(stationId, limit, offsetInMinutes, transportType);

        assertThat(result).isEmpty();
        verify(mvgApiClient).getDepartures(stationId, limit, offsetInMinutes, null);
    }

    private Location createMockStation(String globalId, boolean isStation) {
        Location location = mock(Location.class);
        when(location.getGlobalId()).thenReturn(globalId);
        when(location.isStation()).thenReturn(isStation);
        return location;
    }

    private Departure createMockDeparture() {
        return mock(Departure.class);
    }
}