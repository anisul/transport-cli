package dev.cyanide.transportcli.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    private double latitude;
    private double longitude;
    private String place;
    private String name;
    private String type;

    // Station-specific fields (optional)
    private String globalId;
    private Integer divaId;
    private Boolean hasZoomData;
    private List<String> transportTypes;
    private String aliases;
    private String tariffZones;

    // Default constructor
    public Location() {}

    // Location with required fields
    public Location(double latitude, double longitude, String place, String name, String type) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.place = place;
        this.name = name;
        this.type = type;
    }

    // Utility methods
    public boolean isPOI() {
        return "POI".equals(type);
    }

    public boolean isStation() {
        return "STATION".equals(type);
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public Integer getDivaId() {
        return divaId;
    }

    public void setDivaId(Integer divaId) {
        this.divaId = divaId;
    }

    public Boolean getHasZoomData() {
        return hasZoomData;
    }

    public void setHasZoomData(Boolean hasZoomData) {
        this.hasZoomData = hasZoomData;
    }

    public List<String> getTransportTypes() {
        return transportTypes;
    }

    public void setTransportTypes(List<String> transportTypes) {
        this.transportTypes = transportTypes;
    }

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getTariffZones() {
        return tariffZones;
    }

    public void setTariffZones(String tariffZones) {
        this.tariffZones = tariffZones;
    }
}
