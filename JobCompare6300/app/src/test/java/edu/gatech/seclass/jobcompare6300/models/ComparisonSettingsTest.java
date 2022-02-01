package edu.gatech.seclass.jobcompare6300.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComparisonSettingsTest {
    @Test
    void testComparisonSettingsCreation() {
        ComparisonSettings settings = new ComparisonSettings(1, 1, 1,1,1);
        assertAll("Test Comparisons Settings Object creation",
                () -> assertEquals(settings.getCommuteTimeWeight(), 1, 0),
                () -> assertEquals(settings.getLeaveTimeWeight(), 1),
                () -> assertEquals(settings.getRetirementBenefitsWeight(), 1),
                () -> assertEquals(settings.getYearlySalaryWeight(), 1),
                () -> assertEquals(settings.getYearlyBonusWeight(), 1)
        );
    }



}