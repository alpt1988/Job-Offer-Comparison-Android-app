package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.jobcompare6300.models.CurrentJob;
import edu.gatech.seclass.jobcompare6300.models.Location;

public class JobDetailsActivity extends AppCompatActivity {
    private JobComparisonDBHelper _db;
    EditText title;
    EditText company;
    EditText city;
    EditText state;
    EditText costOfLiving;
    EditText commuteTime;
    EditText yearlySalary;
    EditText yearlyBonus;
    EditText retirementBenefits;
    EditText leaveTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_details);
        _db = new JobComparisonDBHelper(this);


        title = findViewById(R.id.jdTitleID);
        company = findViewById(R.id.jdCompanyID);
        city = findViewById(R.id.jdCityID);
        state = findViewById(R.id.jdStateID);
        costOfLiving = findViewById(R.id.jdCostOfLivingID);
        commuteTime = findViewById(R.id.jdCommuteTimeID);
        yearlySalary = findViewById(R.id.jdYearlySalaryID);
        yearlyBonus = findViewById(R.id.jdYearlyBonusID);
        retirementBenefits = findViewById(R.id.jdRetirementBenefitsID);
        leaveTime = findViewById(R.id.jdLeaveTimeID);

        CurrentJob currJob =  MainActivity.currentJob;

        if (currJob != null) {
            title.setText(currJob.getTitle());
            company.setText(currJob.getCompany());
            city.setText(currJob.getLocation().getCity());
            state.setText(currJob.getLocation().getState());
            costOfLiving.setText(String.valueOf(currJob.getCostOfLivingIndex()));
            commuteTime.setText(String.valueOf(currJob.getCommuteTime()));
            yearlySalary.setText(String.valueOf(currJob.getYearlySalary()));
            yearlyBonus.setText(String.valueOf(currJob.getYearlyBonus()));
            retirementBenefits.setText(String.valueOf(currJob.getRetirementBenefits()));
            leaveTime.setText(String.valueOf(currJob.getLeaveTime()));
        }
    }

    public void saveCurrentJob(View view) {
        boolean hasErrors = false;
        CurrentJob job = new CurrentJob();
        Location jobLocation = new Location();

        String titleValue = title.getText().toString();
        String companyValue = company.getText().toString();
        String cityValue = city.getText().toString();
        String stateValue = state.getText().toString();
        String costOfLivingValue = costOfLiving.getText().toString();
        String commuteTimeValue = commuteTime.getText().toString();
        String yearlySalaryValue = yearlySalary.getText().toString();
        String yearlyBonusValue = yearlyBonus.getText().toString();
        String retirementBenefitsValue = retirementBenefits.getText().toString();
        String leaveTimeValue = leaveTime.getText().toString();

        try { job.setTitle(titleValue); } catch (IllegalArgumentException e) {
            hasErrors = true;
            title.setError(e.getMessage());
        }
        try { job.setCompany(companyValue); } catch (IllegalArgumentException e) {
            hasErrors = true;
            company.setError(e.getMessage());
        }
        try { jobLocation.setCity(cityValue); } catch (IllegalArgumentException e) {
            hasErrors = true;
            city.setError(e.getMessage());
        }
        try { jobLocation.setState(stateValue); } catch (IllegalArgumentException e) {
            hasErrors = true;
            state.setError(e.getMessage());
        }
        try {
            job.setCostOfLivingIndex(Integer.parseInt(costOfLivingValue));
        } catch (NumberFormatException e) {
            hasErrors = true;
            costOfLiving.setError("Must be an integer");
        } catch (IllegalArgumentException e) {
            hasErrors = true;
            costOfLiving.setError(e.getMessage());
        }
        try {
            job.setCommuteTime(Integer.parseInt(commuteTimeValue));
        } catch (NumberFormatException e) {
            hasErrors = true;
            commuteTime.setError("Must be an integer");
        } catch (IllegalArgumentException e) {
            hasErrors = true;
            commuteTime.setError(e.getMessage());
        }
        try {
            job.setYearlySalary(Integer.parseInt(yearlySalaryValue));
        } catch (NumberFormatException e) {
            hasErrors = true;
            yearlySalary.setError("Must be an integer");
        } catch (IllegalArgumentException e) {
            hasErrors = true;
            yearlySalary.setError(e.getMessage());
        }
        try {
            job.setYearlyBonus(Integer.parseInt(yearlyBonusValue));
        } catch (NumberFormatException e) {
            hasErrors = true;
            yearlyBonus.setError("Must be an integer");
        } catch (IllegalArgumentException e) {
            hasErrors = true;
            yearlyBonus.setError(e.getMessage());
        }
        try {
            job.setRetirementBenefits(Integer.parseInt(retirementBenefitsValue));
        } catch (NumberFormatException e) {
            hasErrors = true;
            retirementBenefits.setError("Must be an integer");
        } catch (IllegalArgumentException e) {
            hasErrors = true;
            retirementBenefits.setError(e.getMessage());
        }
        try {
            job.setLeaveTime(Integer.parseInt(leaveTimeValue));
        } catch (NumberFormatException e) {
            hasErrors = true;
            leaveTime.setError("Must be an integer");
        } catch (IllegalArgumentException e) {
            hasErrors = true;
            leaveTime.setError(e.getMessage());
        }

        if (!hasErrors) {
            job.setLocation(jobLocation);
            MainActivity.currentJob = job;
            _db.insertCurrentJobData(job);
            Toast.makeText(this, "Current Job Saved", Toast.LENGTH_LONG).show();
        }
    }

    public void cancelCurrentJob(View view){
        finish();
    }

}