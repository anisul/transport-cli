package dev.cyanide.transportcli.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Departure {

    @JsonProperty("plannedDepartureTime")
    private long plannedDepartureTime;

    @JsonProperty("realtime")
    private boolean realtime;

    @JsonProperty("delayInMinutes")
    private int delayInMinutes;

    @JsonProperty("realtimeDepartureTime")
    private long realtimeDepartureTime;

    @JsonProperty("transportType")
    private String transportType;

    @JsonProperty("label")
    private String label;

    @JsonProperty("divaId")
    private String divaId;

    @JsonProperty("network")
    private String network;

    @JsonProperty("trainType")
    private String trainType;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("cancelled")
    private boolean cancelled;

    @JsonProperty("sev")
    private boolean sev;

    //@JsonProperty("messages")
    //private List<String> messages;

    //@JsonProperty("infos")
    //private List<String> infos;

    @JsonProperty("bannerHash")
    private String bannerHash;

    @JsonProperty("occupancy")
    private String occupancy;

    @JsonProperty("stationGlobalId")
    private String stationGlobalId;

    @JsonProperty("stopPointGlobalId")
    private String stopPointGlobalId;

    @JsonProperty("lineId")
    private String lineId;

    @JsonProperty("tripCode")
    private int tripCode;

    public Departure() {
    }

    public long getPlannedDepartureTime() {
        return plannedDepartureTime;
    }

    public LocalDateTime getPlannedDepartureTimeAsLocalDateTime() {
        return Instant.ofEpochMilli(plannedDepartureTime)
                .atZone(ZoneId.of("Europe/Berlin"))
                .toLocalDateTime();
    }

    public LocalDateTime getRealtimeDepartureTimeAsLocalDateTime() {
        return Instant.ofEpochMilli(realtimeDepartureTime)
                .atZone(ZoneId.of("Europe/Berlin"))
                .toLocalDateTime();
    }

    public ZonedDateTime getPlannedDepartureTimeAsZonedDateTime() {
        return Instant.ofEpochMilli(plannedDepartureTime)
                .atZone(ZoneId.of("Europe/Berlin"));
    }

    public ZonedDateTime getRealtimeDepartureTimeAsZonedDateTime() {
        return Instant.ofEpochMilli(realtimeDepartureTime)
                .atZone(ZoneId.of("Europe/Berlin"));
    }

    public void setPlannedDepartureTime(long plannedDepartureTime) {
        this.plannedDepartureTime = plannedDepartureTime;
    }

    public boolean isRealtime() {
        return realtime;
    }

    public void setRealtime(boolean realtime) {
        this.realtime = realtime;
    }

    public int getDelayInMinutes() {
        return delayInMinutes;
    }

    public void setDelayInMinutes(int delayInMinutes) {
        this.delayInMinutes = delayInMinutes;
    }

    public long getRealtimeDepartureTime() {
        return realtimeDepartureTime;
    }

    public void setRealtimeDepartureTime(long realtimeDepartureTime) {
        this.realtimeDepartureTime = realtimeDepartureTime;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDivaId() {
        return divaId;
    }

    public void setDivaId(String divaId) {
        this.divaId = divaId;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isSev() {
        return sev;
    }

    public void setSev(boolean sev) {
        this.sev = sev;
    }

    /*
    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getInfos() {
        return infos;
    }

    public void setInfos(List<String> infos) {
        this.infos = infos;
    }
    */

    public String getBannerHash() {
        return bannerHash;
    }

    public void setBannerHash(String bannerHash) {
        this.bannerHash = bannerHash;
    }

    public String getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(String occupancy) {
        this.occupancy = occupancy;
    }

    public String getStationGlobalId() {
        return stationGlobalId;
    }

    public void setStationGlobalId(String stationGlobalId) {
        this.stationGlobalId = stationGlobalId;
    }

    public String getStopPointGlobalId() {
        return stopPointGlobalId;
    }

    public void setStopPointGlobalId(String stopPointGlobalId) {
        this.stopPointGlobalId = stopPointGlobalId;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public int getTripCode() {
        return tripCode;
    }

    public void setTripCode(int tripCode) {
        this.tripCode = tripCode;
    }
}
