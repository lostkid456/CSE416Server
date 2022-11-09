package com.server.server.model.geojson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


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

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("OBJECTID")
    private long objectid;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("District_A")
    private long districtA;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("Shape_Leng")
    private double shapeLeng;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("Shape_Area")
    private double shapeArea;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("CONG_DIST")
    private String congDist;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("LONGNAME")
    private String longName;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("SHORTNAME")
    private String shortName;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("DISTRICT")
    private long district;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("COLOR")
    private long color;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("TOTAL")
    private long total;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("TARGET_DEV")
    private String targetDev;
}
