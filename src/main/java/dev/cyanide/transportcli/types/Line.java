package dev.cyanide.transportcli.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Line {

    String destination;
    String divaId;
    String label;
    String network;
    boolean sev;
    TransportType transportType;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDivaId() {
        return divaId;
    }

    public void setDivaId(String divaId) {
        this.divaId = divaId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public boolean isSev() {
        return sev;
    }

    public void setSev(boolean sev) {
        this.sev = sev;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }
}
