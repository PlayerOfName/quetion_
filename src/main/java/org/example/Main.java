package org.example;

import org.example.models.Flight;
import org.example.service.DataLoader;
import org.example.service.DataProcessor;
import org.example.service.DataSaver;

import java.io.IOException;
import java.time.Duration;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        DataLoader dataLoader = new DataLoader();
        List<Flight> flights = dataLoader.loadFlights("flight.txt");
        DataProcessor dataProcessor = new DataProcessor();
        Map<String, Map<YearMonth, Duration>> pilotTimes = dataProcessor.processFlights(flights);
        DataSaver dataSaver = new DataSaver();
        dataSaver.saveData(pilotTimes, "pilot_times.xml");
    }
}