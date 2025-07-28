package dev.cyanide.transportcli.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {

    @JsonProperty("uniqueId")
    long uniqueId;

    @JsonProperty("distance")
    double distance;

    @JsonProperty("parts")
    List<RoutePart> parts;

    public long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public List<RoutePart> getParts() {
        return parts;
    }

    public void setParts(List<RoutePart> parts) {
        this.parts = parts;
    }
}
