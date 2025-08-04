package dev.cyanide.transportcli.service;

import dev.cyanide.transportcli.api.MVGAPIClient;
import dev.cyanide.transportcli.types.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class LocationServiceTest {

    @Mock
    private MVGAPIClient mvgApiClient;

    private LocationService locationService;

    @BeforeEach
    void setUp() {
        locationService = new LocationService(mvgApiClient);
    }

    @Test
    void getNearestStationLocation_WithValidQuery_ShouldReturnFirstStation() {
        var query = "Marienplatz";

        var station1 = createMockLocation("station-123", true);
        var station2 = createMockLocation("station-456", true);
        var mockLocations = List.of(station1, station2);

        when(mvgApiClient.getLocations(query)).thenReturn(mockLocations);

        var result = locationService.getNearestStationLocation(query);

        assertThat(result).isEqualTo(station1);
        verify(mvgApiClient).getLocations(query);
    }


    @Test
    void getNearestStationLocation_WithEmptyResults_ShouldReturnNull() {
        var query = "NonExistentLocation";
        List<Location> emptyLocations = List.of();

        when(mvgApiClient.getLocations(query)).thenReturn(emptyLocations);

        var result = locationService.getNearestStationLocation(query);

        assertThat(result).isNull();
        verify(mvgApiClient).getLocations(query);
    }

    @Test
    void getNearestStationLocation_WithSingleStation_ShouldReturnThatStation() {
        var query = "Sendlinger Tor";

        var singleStation = createMockLocation("station-single", true);
        var mockLocations = List.of(singleStation);

        when(mvgApiClient.getLocations(query)).thenReturn(mockLocations);

        var result = locationService.getNearestStationLocation(query);

        assertThat(result).isEqualTo(singleStation);
        verify(mvgApiClient).getLocations(query);
    }

    @Test
    void getNearestStationLocation_WithMultipleStations_ShouldReturnFirstOne() {
        var query = "Central Area";

        var firstStation = createMockLocation("station-first", true);
        var secondStation = createMockLocation("station-second", true);
        var thirdStation = createMockLocation("station-third", true);
        var mockLocations = List.of(firstStation, secondStation, thirdStation);

        when(mvgApiClient.getLocations(query)).thenReturn(mockLocations);

        var result = locationService.getNearestStationLocation(query);

        assertThat(result).isEqualTo(firstStation);
        assertThat(result).isNotEqualTo(secondStation);
        assertThat(result).isNotEqualTo(thirdStation);
        verify(mvgApiClient).getLocations(query);
    }

    @Test
    void getNearestStationLocation_WithStationAtEnd_ShouldReturnThatStation() {
        var query = "Mixed Results";

        var poi1 = createMockLocation("poi-aaa", false);
        var poi2 = createMockLocation("poi-bbb", false);
        var poi3 = createMockLocation("poi-ccc", false);
        var station = createMockLocation("station-end", true);
        var mockLocations = List.of(poi1, poi2, poi3, station);

        when(mvgApiClient.getLocations(query)).thenReturn(mockLocations);

        Location result = locationService.getNearestStationLocation(query);

        assertThat(result).isEqualTo(station);
        verify(mvgApiClient).getLocations(query);
    }

    @Test
    void getNearestStationLocation_ShouldCallApiClientOnlyOnce() {
        var query = "Efficiency Test";

        var station = createMockLocation("station-efficient", true);
        var mockLocations = List.of(station);

        when(mvgApiClient.getLocations(query)).thenReturn(mockLocations);

        locationService.getNearestStationLocation(query);

        verify(mvgApiClient, times(1)).getLocations(query);
        verifyNoMoreInteractions(mvgApiClient);
    }

    private Location createMockLocation(String globalId, boolean isStation) {
        Location location = mock(Location.class);
        when(location.getGlobalId()).thenReturn(globalId);
        when(location.isStation()).thenReturn(isStation);
        return location;
    }
}