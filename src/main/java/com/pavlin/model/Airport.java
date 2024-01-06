package com.pavlin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    private String airportId;
    private String name;
    private String city;
    private String country;
    private String iata;
    private String icao;
    private String latitude;
    private String longitude;
    private String altitude;
    private String timezone;
    private String dst;
    private String tzDatabaseTimezone;
    private String type;
    private String source;
}