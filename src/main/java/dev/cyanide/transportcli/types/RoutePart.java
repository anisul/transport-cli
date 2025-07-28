package dev.cyanide.transportcli.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoutePart {

    @JsonProperty("distance")
    double distance;

    @JsonProperty("from")
    RouteStation from;

    @JsonProperty("line")
    Line line;

    @JsonProperty("to")
    RouteStation to;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public RouteStation getFrom() {
        return from;
    }

    public void setFrom(RouteStation from) {
        this.from = from;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public RouteStation getTo() {
        return to;
    }

    public void setTo(RouteStation to) {
        this.to = to;
    }
}
