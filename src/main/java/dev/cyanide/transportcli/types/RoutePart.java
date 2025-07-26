package dev.cyanide.transportcli.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoutePart {

    String changeStatus;
    double distance;
    RouteStation from;
    Line line;
    RouteStation to;

    public String getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
    }

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
