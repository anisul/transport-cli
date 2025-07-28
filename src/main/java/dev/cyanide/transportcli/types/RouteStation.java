package dev.cyanide.transportcli.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteStation {

    @JsonProperty("departureDelayInMinutes")
    int departureDelayInMinutes;

    @JsonProperty("arrivalDelayInMinutes")
    int arrivalDelayInMinutes;

    @JsonProperty("isViaStop")
    boolean isViaStop;

    @JsonProperty("latitude")
    double latitude;

    @JsonProperty("longitude")
    double longitude;

    @JsonProperty("name")
    String name;

    @JsonProperty("place")
    String place;

    @JsonProperty("plannedDeparture")
    OffsetDateTime plannedDeparture;

    @JsonProperty("stationDivaId")
    int stationDivaId;

    @JsonProperty("transportTypes")
    List<TransportType> transportTypes;

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

    public OffsetDateTime getPlannedDeparture() {
        return plannedDeparture;
    }

    public String getLocalPlannedDepartureIn24HrAsString() {
        var zonedDateTime = plannedDeparture.atZoneSameInstant(ZoneId.systemDefault());
        return zonedDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public void setPlannedDeparture(OffsetDateTime plannedDeparture) {
        this.plannedDeparture = plannedDeparture;
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
}
