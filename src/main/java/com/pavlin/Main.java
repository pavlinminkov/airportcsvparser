package com.pavlin;

import com.pavlin.model.Airport;
import com.pavlin.model.Route;
import com.pavlin.repository.AirportRepository;
import com.pavlin.repository.RouteRepostiory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final java.lang.String NULL_SYMBOL = "\\N";

    public static void main(String[] args) throws IOException {
        List<Route> routes = RouteRepostiory.readFromCSV();
        List<Route> invalidRoutes = new ArrayList<>();
        List<String> airportIds = AirportRepository.readFromCSV().stream().map(Airport::getAirportId).toList();

        System.out.println(routes.size());

        routes.removeIf(route -> {
            if (route.getAirline().equals(NULL_SYMBOL) ||
                    route.getAirlineId().equals(NULL_SYMBOL) ||
                    route.getSourceAirport().equals(NULL_SYMBOL) ||
                    route.getSourceAirportId().equals(NULL_SYMBOL) ||
                    route.getDestinationAirport().equals(NULL_SYMBOL) ||
                    route.getDestinationAirportId().equals(NULL_SYMBOL) ||
                    route.getStops().equals(NULL_SYMBOL)) {
                invalidRoutes.add(route);
                return true;
            }

            if (!airportIds.contains(route.getSourceAirportId()) || !airportIds.contains(route.getDestinationAirportId())) {
                invalidRoutes.add(route);
                return true;
            }

            return false;
        });

        System.out.println(routes.size());

        RouteRepostiory.writeListToCSV(routes);
    }
}