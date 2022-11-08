package com.server.server.model.geojson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
public class Properties {
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("REGION")
    private String region;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("NAME")
    private String name;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("DIVISION")
    private String division;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("STATEFP")
    private String statefp;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("STATENS")
    private String statens;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("GEOID")
    private String geoid;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("STUSPS")
    private String stusps;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("LSAD")
    private String lsad;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("MTFCC")
    private String mtfcc;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("FUNCSTAT")
    private String funcstat;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("ALAND")
    private long aland;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("AWATER")
    private long awater;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("INTPTLAT")
    private String intptlat;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("INTPTLON")
    private String intptlon;

}
