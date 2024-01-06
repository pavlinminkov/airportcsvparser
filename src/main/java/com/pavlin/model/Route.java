package com.pavlin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    private String airline;
    private String airlineId;
    private String sourceAirport;
    private String sourceAirportId;
    private String destinationAirport;
    private String destinationAirportId;
    private String codeshare;
    private String stops;
    private String equipment;
}