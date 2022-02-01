package edu.gatech.seclass.jobcompare6300.models;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrentJobTest {

    private CurrentJob myJob;

    @BeforeEach
    void setUp() {
        Location myLocation = new Location("Portland", "Oregon");
        myJob = new CurrentJob("CEO", "Super Likely Company", myLocation, 200, 1, 100000, 20000, 20, 10);
        // retirement benefits should have been implemented in percentage
        // This job pays 100K annually, 20K bonus, 20% retirement benefits, 10 leave day per year in a location with cost of living 200 per index and 1 hr commute time per day
    }

    @AfterEach
    void tearDown() {
        myJob = null;
    }

    @Test
    void setTitleEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> myJob.setTitle("")
        );
    }

    @Test
    void setTitleNull() {
        assertThrows(IllegalArgumentException.class,
                () -> myJob.setTitle(null)
        );
    }


    @Test
    void setCompanyEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> myJob.setCompany("")
        );
    }

    @Test
    void setCompanyNull() {
        assertThrows(IllegalArgumentException.class,
                () -> myJob.setCompany(null)
        );
    }

    @Test
    void setCostOfLivingIndexNull() {
        assertThrows(IllegalArgumentException.class,
                () -> myJob.setTitle(null)
        );
    }

    @Test
    void setCostOfLivingIndexNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> myJob.setCostOfLivingIndex(-1)
        );
    }

    @Test
    void setCostOfLivingIndexZero() {
        assertThrows(IllegalArgumentException.class,
                () -> myJob.setCostOfLivingIndex(0)
        );
    }


    @Test
    void setCommuteTimeNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> myJob.setCommuteTime(-1)
        );
    }

    @Test
    void setCommuteTimeTooLarge() {
        assertThrows(IllegalArgumentException.class,
                () -> myJob.setCommuteTime(25)
        );
    }


    @Test
    void setYearlySalaryNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> myJob.setYearlySalary(-1)
        );
    }

    @Test
    void setYearlyBonusNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> myJob.setYearlyBonus(-1)
        );
    }

    @Test
    void setRetirementBenefitsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> myJob.setRetirementBenefits(-1)
        );
    }

    @Test
    void setRetirementBenefitsLargerThanOne() {
        //retirement benefits is percentage matched as per definition and cannot be > 1
        assertThrows(IllegalArgumentException.class,
                () -> myJob.setRetirementBenefits(101)
        );
    }


    @Test
    void setLeaveTimeNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> myJob.setLeaveTime(-1)
        );
    }

    @Test
    void getScore() {
        ComparisonSettings equalWeights = new ComparisonSettings(1, 1, 1, 1, 1);

        double AYS = (double) (myJob.getYearlySalary() / (myJob.getCostOfLivingIndex() / 100.0));
        double AYB = (double) (myJob.getYearlyBonus() / (myJob.getCostOfLivingIndex() / 100.0));
        double RBP = (double) myJob.getRetirementBenefits() / 100.0;
        double LT = myJob.getLeaveTime();
        double CT = myJob.getCommuteTime();


        double equalWeightsSum = equalWeights.getYearlySalaryWeight()
                + equalWeights.getYearlyBonusWeight()
                + equalWeights.getRetirementBenefitsWeight()
                + equalWeights.getLeaveTimeWeight()
                + equalWeights.getCommuteTimeWeight();
        double equalWeightsScore = (AYS * equalWeights.getYearlySalaryWeight()
                + AYB * equalWeights.getYearlyBonusWeight()
                + RBP * AYS * equalWeights.getRetirementBenefitsWeight()
                + (LT * AYS / 260) * equalWeights.getLeaveTimeWeight()
                - (CT * AYS / 8) * equalWeights.getCommuteTimeWeight())
                / equalWeightsSum;

        assertAll("Get Score for this comparison settings",
                () -> assertEquals(equalWeightsScore, myJob.getScore(equalWeights), 0.1)
        );
    }
}