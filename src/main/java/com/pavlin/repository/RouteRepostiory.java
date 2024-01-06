package com.pavlin.repository;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.pavlin.model.Route;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.pavlin.config.PathConfig.ROUTE_READ_PATH;
import static com.pavlin.config.PathConfig.ROUTE_WRITE_PATH;

public class RouteRepostiory {
    public static List<Route> readFromCSV() throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.builder()
                .addColumn("airline")
                .addColumn("airlineId")
                .addColumn("sourceAirport")
                .addColumn("sourceAirportId")
                .addColumn("destinationAirport")
                .addColumn("destinationAirportId")
                .addColumn("codeshare")
                .addColumn("stops")
                .addColumn("equipment")
                .build()
                .withColumnReordering(true);

        File file = new File(ROUTE_READ_PATH);

        try(MappingIterator<Route> mappingIterator = csvMapper.readerFor(Route.class).with(csvSchema).readValues(file)) {
            return mappingIterator.readAll();
        }
    }

    public static void writeListToCSV(List<Route> routes) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.builder()
                .addColumn("airline")
                .addColumn("airlineId")
                .addColumn("sourceAirport")
                .addColumn("sourceAirportId")
                .addColumn("destinationAirport")
                .addColumn("destinationAirportId")
                .addColumn("codeshare")
                .addColumn("stops")
                .addColumn("equipment")
                .build()
                .withHeader()
                .withColumnReordering(true);

        File file = new File(ROUTE_WRITE_PATH);
        SequenceWriter sequenceWriter = csvMapper.writerFor(Route.class).with(csvSchema).writeValues(file).writeAll(routes);
        sequenceWriter.close();
    }
}
