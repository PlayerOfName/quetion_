package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The type Flight.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Flight {
    /**
     *  field aircraftNumber
     */
    private String aircraftNumber;
    /**
     * field aircraftType
     */
    private String aircraftType;
    /**
     * field takeoffTime
     */
    private LocalDateTime takeoffTime;
    /**
     * field landingTime
     */
    private LocalDateTime landingTime;
    /**
     * field departureAirport
     */
    private String departureAirport;
    /**
     * field arrivalAirport
     */
    private String arrivalAirport;
    /**
     * field crew
     */
    private List<Pilot> crew;
}
