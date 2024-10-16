import org.example.models.Flight;
import org.example.models.Pilot;
import org.example.service.DataLoader;
import org.example.service.DataProcessor;
import org.example.service.DataSaver;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type App test.
 */
public class AppTest {
    /**
     * Test load flights.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testLoadFlights() throws IOException {
        DataLoader dataLoader = new DataLoader();
        List<Flight> flights = dataLoader.loadFlights("flight.txt");
        assertNotNull(flights);
        assertEquals(2, flights.size());
        assertEquals("testType", flights.getFirst().getAircraftType());
        assertEquals("testNamePilot1", flights.getFirst().getCrew().getFirst().getName());
        assertEquals("testNamePilot4", flights.getLast().getCrew().getLast().getName());
    }

    /**
     * Test process flights.
     */
    @Test
    public void testProcessFlights() {
        DataProcessor dataProcessor = new DataProcessor();
        List<Flight> flights = new ArrayList<>();
        List<Pilot> pilots = new ArrayList<>();
        List<Pilot> pilots1 = new ArrayList<>();
        pilots.add(new Pilot("pilot_1"));
        pilots.add(new Pilot("pilot_2"));
        pilots1.add(new Pilot("pilot_3"));
        pilots1.add(new Pilot("pilot_4"));
        flights.add(new Flight("Boeing 737", "737", LocalDateTime.of(2022, 1, 1, 10, 0), LocalDateTime.of(2022, 1, 1, 12, 0), "LAX", "SFO", pilots));
        flights.add(new Flight("Boeing 737", "737", LocalDateTime.of(2022, 1, 1, 10, 0), LocalDateTime.of(2022, 1, 1, 12, 0), "LAX", "SFO", pilots1));
        Map<String, Map<YearMonth, Duration>> pilotTimes = dataProcessor.processFlights(flights);
        assertNotNull(pilotTimes);
        assertEquals(4, pilotTimes.size());
    }

    /**
     * Test save data.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testSaveData() throws IOException {
        DataSaver dataSaver = new DataSaver();
        DataProcessor dataProcessor = new DataProcessor();
        List<Flight> flights = new ArrayList<>();
        List<Pilot> pilots = new ArrayList<>();
        List<Pilot> pilots1 = new ArrayList<>();
        pilots.add(new Pilot("pilot_1"));
        pilots.add(new Pilot("pilot_2"));
        pilots1.add(new Pilot("pilot_3"));
        pilots1.add(new Pilot("pilot_4"));
        flights.add(new Flight("Boeing 737", "737", LocalDateTime.of(2022, 1, 1, 10, 0), LocalDateTime.of(2022, 1, 1, 12, 0), "LAX", "SFO", pilots));
        flights.add(new Flight("Boeing 737", "737", LocalDateTime.of(2022, 1, 1, 10, 0), LocalDateTime.of(2022, 1, 1, 12, 0), "LAX", "SFO", pilots1));
        Map<String, Map<YearMonth, Duration>> pilotTimes = dataProcessor.processFlights(flights);
        dataSaver.saveData(pilotTimes, "pilot_times.xml");
        File file = new File("pilot_times.xml");
        assertTrue(file.exists());
    }
}
