package com.pavlin.repository;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.pavlin.model.Airport;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.pavlin.config.PathConfig.AIRPORT_PATH;

public class AirportRepository {
    public static List<Airport> readFromCSV() throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.builder()
                .addColumn("airportId")
                .addColumn("name")
                .addColumn("city")
                .addColumn("country")
                .addColumn("iata")
                .addColumn("icao")
                .addColumn("latitude")
                .addColumn("longitude")
                .addColumn("altitude")
                .addColumn("timezone")
                .addColumn("dst")
                .addColumn("tzDatabaseTimezone")
                .addColumn("type")
                .addColumn("source")
                .build()
                .withColumnReordering(true);

        File file = new File(AIRPORT_PATH);

        try (MappingIterator<Airport> mappingIterator = csvMapper.readerFor(Airport.class).with(csvSchema).readValues(file)) {
            return mappingIterator.readAll();
        }
    }
}
