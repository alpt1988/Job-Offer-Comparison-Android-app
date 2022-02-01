package edu.gatech.seclass.jobcompare6300.models;

import java.time.Year;

public class ComparisonSettings {
    private int CommuteTimeWeight;
    private int YearlySalaryWeight;
    private int YearlyBonusWeight;
    private int RetirementBenefitsWeight;
    private int LeaveTimeWeight;

    public ComparisonSettings(int commuteTimeWeight,
                                   int yearlySalaryWeight,
                                   int yearlyBonusWeight,
                                   int retirementBenefitsWeight,
                                   int leaveTimeWeight){
        CommuteTimeWeight = commuteTimeWeight;
        YearlySalaryWeight = yearlySalaryWeight;
        YearlyBonusWeight = yearlyBonusWeight;
        RetirementBenefitsWeight = retirementBenefitsWeight;
        LeaveTimeWeight = leaveTimeWeight;
    }

    public int getCommuteTimeWeight(){
        return this.CommuteTimeWeight;
    }

    public void setCommuteTimeWeight(int commuteTimeWeight){
        this.CommuteTimeWeight = commuteTimeWeight;
    }

    public int getYearlySalaryWeight(){
        return this.YearlySalaryWeight;
    }

    public void setYearlySalaryWeight(int yearlySalaryWeight){
        this.YearlySalaryWeight = yearlySalaryWeight;
    }

    public int getYearlyBonusWeight(){
        return this.YearlyBonusWeight;
    }

    public void setYearlyBonusWeight(int yearlyBonusWeight){
        this.YearlyBonusWeight = yearlyBonusWeight;
    }

    public int getRetirementBenefitsWeight(){
        return this.RetirementBenefitsWeight;
    }

    public void setRetirementBenefitsWeight(int retirementBenefitsWeight){
        this.RetirementBenefitsWeight = retirementBenefitsWeight;
    }

    public int getLeaveTimeWeight(){
        return this.LeaveTimeWeight;
    }

    public void setLeaveTimeWeight(int leaveTimeWeight){
        this.LeaveTimeWeight = leaveTimeWeight;
    }

}
