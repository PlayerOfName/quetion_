package org.example.service;

import org.example.models.Flight;
import org.example.models.Pilot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Data processor.
 */
public class DataProcessor {
    /**
     * Process flights map.
     *
     * @param flights the flights
     * @return the map
     */
    public Map<String, Map<YearMonth, Duration>> processFlights(List<Flight> flights) {
        Map<String, Map<YearMonth, Duration>> pilotTimes = new HashMap<>();
        Map<YearMonth, Duration> monthDuration = new HashMap<>();
        for (Flight flight : flights) {
            List<Pilot> crew = flight.getCrew();
            LocalDateTime takeoff = flight.getTakeoffTime();
            LocalDateTime landing = flight.getLandingTime();
            monthDuration.putIfAbsent(YearMonth.from(takeoff), Duration.between(takeoff, landing));
            for (Pilot pilot : crew) {
                pilotTimes.putIfAbsent(pilot.getName(), monthDuration);
            }
        }
        return pilotTimes;
    }
}
