package org.example.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.YearMonth;
import java.util.Map;

/**
 * The type Data saver.
 */
public class DataSaver {
    /**
     * Save data.
     *
     * @param pilotTimes the pilot times
     * @param filePath   the file path
     * @throws IOException the io exception
     */
    public void saveData(Map<String, Map<YearMonth, Duration>> pilotTimes, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<pilots>\n");
            for (Map.Entry<String, Map<YearMonth, Duration>> entry : pilotTimes.entrySet()) {
                writer.write("  <pilot name=\"" + entry.getKey() + "\">\n");
                for (Map.Entry<YearMonth, Duration> monthEntry : entry.getValue().entrySet()) {
                    writer.write("    <month year=\"" + monthEntry.getKey().getYear() + "\" month=\"" + monthEntry.getKey().getMonthValue() + "\">\n");
                    writer.write("      <duration>" + monthEntry.getValue().toString() + "</duration>\n");
                    writer.write("    </month>\n");
                }
                writer.write("  </pilot>\n");
            }
            writer.write("</pilots>\n");
        }
    }
}
