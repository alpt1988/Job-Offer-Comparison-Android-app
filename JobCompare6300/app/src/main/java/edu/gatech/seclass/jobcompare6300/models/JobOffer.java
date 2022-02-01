package edu.gatech.seclass.jobcompare6300.models;

public class JobOffer extends Job {

    public JobOffer() {
    }

    public JobOffer(String title, String company, Location location, int costOfLiving,
                      int commuteTime, int yearlySalary, int yearlyBonus, int retirementBenefits, int leaveTime)
                    throws IllegalArgumentException {
        String errorText = "";
        try { setTitle(title); } catch (IllegalArgumentException e) {
            errorText += errorText.isEmpty() ? e.getMessage() : "\n"+e.getMessage();
        }
        try { setCompany(company); } catch (IllegalArgumentException e) {
            errorText += errorText.isEmpty() ? e.getMessage() : "\n"+e.getMessage();
        }
        try { setLocation(location); } catch (IllegalArgumentException e) {
            errorText += errorText.isEmpty() ? e.getMessage() : "\n"+e.getMessage();
        }
        try { setCostOfLivingIndex(costOfLiving); } catch (IllegalArgumentException e) {
            errorText += errorText.isEmpty() ? e.getMessage() : "\n"+e.getMessage();
        }
        try { setCommuteTime(commuteTime); } catch (IllegalArgumentException e) {
            errorText += errorText.isEmpty() ? e.getMessage() : "\n"+e.getMessage();
        }
        try { setYearlySalary(yearlySalary); } catch (IllegalArgumentException e) {
            errorText += errorText.isEmpty() ? e.getMessage() : "\n"+e.getMessage();
        }
        try { setYearlyBonus(yearlyBonus); } catch (IllegalArgumentException e) {
            errorText += errorText.isEmpty() ? e.getMessage() : "\n"+e.getMessage();
        }
        try { setRetirementBenefits(retirementBenefits); } catch (IllegalArgumentException e) {
            errorText += errorText.isEmpty() ? e.getMessage() : "\n"+e.getMessage();
        }
        try { setLeaveTime(leaveTime); } catch (IllegalArgumentException e) {
            errorText += errorText.isEmpty() ? e.getMessage() : "\n"+e.getMessage();
        }

        if (!errorText.isEmpty())
            throw new IllegalArgumentException(errorText);
    }

}
