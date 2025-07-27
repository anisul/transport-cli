package dev.cyanide.transportcli.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteStation {

    boolean isViaStop;
    double latitude;
    double longitude;
    String name;
    String place;
    OffsetDateTime plannedDepartureTime;
    int departureDelayInMinutes;
    int arrivalDelayInMinutes;
    int platform;
    boolean platformChanged;
    int stationDivaId;
    List<TransportType> transportTypes;

    public boolean isViaStop() {
        return isViaStop;
    }

    public void setViaStop(boolean viaStop) {
        isViaStop = viaStop;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public OffsetDateTime getPlannedDepartureTime() {
        return plannedDepartureTime;
    }

    public void setPlannedDepartureTime(OffsetDateTime plannedDepartureTime) {
        this.plannedDepartureTime = plannedDepartureTime;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public boolean isPlatformChanged() {
        return platformChanged;
    }

    public void setPlatformChanged(boolean platformChanged) {
        this.platformChanged = platformChanged;
    }

    public int getStationDivaId() {
        return stationDivaId;
    }

    public void setStationDivaId(int stationDivaId) {
        this.stationDivaId = stationDivaId;
    }

    public List<TransportType> getTransportTypes() {
        return transportTypes;
    }

    public void setTransportTypes(List<TransportType> transportTypes) {
        this.transportTypes = transportTypes;
    }

    public int getDepartureDelayInMinutes() {
        return departureDelayInMinutes;
    }

    public void setDepartureDelayInMinutes(int departureDelayInMinutes) {
        this.departureDelayInMinutes = departureDelayInMinutes;
    }

    public int getArrivalDelayInMinutes() {
        return arrivalDelayInMinutes;
    }

    public void setArrivalDelayInMinutes(int arrivalDelayInMinutes) {
        this.arrivalDelayInMinutes = arrivalDelayInMinutes;
    }
}
