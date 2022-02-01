package edu.gatech.seclass.jobcompare6300.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    @Test
    void testLocationCreation() {
        assertAll("Test Location Object creation",
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Location(null, null)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Location("", "Ohio")),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Location("Portland", ""))
        );
    }

    @Test
    void testSetNullEmptyCityAndState() {
        Location myLocation = new Location("Portland", "Oregon");
        assertAll("Test setting city and state to empty or null value",
                () -> assertThrows(IllegalArgumentException.class,
                        () -> myLocation.setCity(null)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> myLocation.setCity("")),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> myLocation.setState(null)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> myLocation.setState(""))
        );
    }
}