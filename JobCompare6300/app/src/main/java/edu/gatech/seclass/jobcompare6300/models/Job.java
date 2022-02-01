package edu.gatech.seclass.jobcompare6300.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Job{
    private int id;
    private String title;
    private String company;
    private Location location;
    private int costOfLivingIndex;
    private int commuteTime;
    private int yearlySalary;
    private int yearlyBonus;
    private int retirementBenefits;
    private int leaveTime;

    public Job() {
    }
    public int getId(){ return this.id;}

    public void setId(int id){this.id = id;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null && !title.isEmpty())
            this.title = title;
        else
            throw new IllegalArgumentException("Title cannot be blank");
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        if (company != null && !company.isEmpty())
            this.company = company;
        else
            throw new IllegalArgumentException("Company cannot be blank");
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getCostOfLivingIndex() {
        return costOfLivingIndex;
    }

    public void setCostOfLivingIndex(int costOfLivingIndex) {
        if (costOfLivingIndex > 0)
            this.costOfLivingIndex = costOfLivingIndex;
        else
            throw new IllegalArgumentException("COL index should be a positive integer");
    }

    public int getCommuteTime() {
        return commuteTime;
    }

    public void setCommuteTime(int commuteTime) {
        if (commuteTime > -1 && commuteTime < 25)
            this.commuteTime = commuteTime;
        else
            throw new IllegalArgumentException("Commute time should be >= 0 and < 25");
    }

    public int getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(int yearlySalary) {
        if (yearlySalary > -1)
            this.yearlySalary = yearlySalary;
        else
            throw new IllegalArgumentException("Salary should be a positive integer");
    }

    public int getYearlyBonus() {
        return yearlyBonus;
    }

    public void setYearlyBonus(int yearlyBonus) {
        if (yearlyBonus > -1)
            this.yearlyBonus = yearlyBonus;
        else
            throw new IllegalArgumentException("Bonus should be a positive integer");
    }

    public int getRetirementBenefits() {
        return retirementBenefits;
    }

    public void setRetirementBenefits(int retirementBenefits) {
        if (retirementBenefits > -1 && retirementBenefits < 100)
            this.retirementBenefits = retirementBenefits;
        else
            throw new IllegalArgumentException("Retirement benefits should be >= 0 and < 100");
    }

    public int getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(int leaveTime) {
        if (leaveTime > -1)
            this.leaveTime = leaveTime;
        else
            throw new IllegalArgumentException("Leave time should be >= 0");
    }

    public double getScore(ComparisonSettings comparisonSettings) {
        double AYS = (double) (this.getYearlySalary() / (this.getCostOfLivingIndex() / 100.0));
        double AYB = (double) (this.getYearlyBonus() / (this.getCostOfLivingIndex() / 100.0));
        double RBP = (double) this.getRetirementBenefits() / 100.0;
        double LT = this.getLeaveTime();
        double CT = this.getCommuteTime();


        double sum = comparisonSettings.getYearlySalaryWeight()
                + comparisonSettings.getYearlyBonusWeight()
                + comparisonSettings.getRetirementBenefitsWeight()
                + comparisonSettings.getLeaveTimeWeight()
                + comparisonSettings.getCommuteTimeWeight();
        double score = (AYS * comparisonSettings.getYearlySalaryWeight()
                + AYB * comparisonSettings.getYearlyBonusWeight()
                + RBP * AYS * comparisonSettings.getRetirementBenefitsWeight()
                + (LT * AYS / 260) * comparisonSettings.getLeaveTimeWeight()
                - (CT * AYS / 8) * comparisonSettings.getCommuteTimeWeight())
                / sum;
        return score;
    }


}
