package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.jobcompare6300.models.ComparisonSettings;

public class ComparisonSettingsActivity extends AppCompatActivity {
    private JobComparisonDBHelper _db;

    public EditText commuteTime;
    public EditText yearlySalary;
    public EditText yearlyBonus;
    public EditText retirementBenefits;
    public EditText leaveTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comparison_settings);
        _db = new JobComparisonDBHelper(this);
        commuteTime = findViewById(R.id.comparison_settings_commute_time_weight);
        yearlySalary = findViewById(R.id.comparison_settings_yearly_salary);
        yearlyBonus = findViewById(R.id.comparison_settings_yearly_bonus);
        retirementBenefits = findViewById(R.id.comparison_settings_retirement_benefits);
        leaveTime = findViewById(R.id.comparison_settings_leave_time);

       ComparisonSettings compSettings =  MainActivity.comparisonSettings;

       commuteTime.setText(String.valueOf(compSettings.getCommuteTimeWeight()));
       yearlySalary.setText(String.valueOf(compSettings.getYearlySalaryWeight()));
       yearlyBonus.setText(String.valueOf(compSettings.getYearlyBonusWeight()));
       retirementBenefits.setText(String.valueOf(compSettings.getRetirementBenefitsWeight()));
       leaveTime.setText(String.valueOf(compSettings.getLeaveTimeWeight()));
    }

    public void saveComparisonSettings(View view){
        if (!hasErrors()){
            ComparisonSettings c = getComparisonSettingsFromUserInput();
            MainActivity.comparisonSettings = c;
            Boolean success = _db.insertComparisonSettings(c);
            if (success) {
                Toast.makeText(this, "Comparison Settings Saved", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void cancelComparisonSettings(View view){
        finish();
    }

    public ComparisonSettings getComparisonSettingsFromUserInput(){
        return new ComparisonSettings(
            Integer.parseInt(commuteTime.getText().toString()),
            Integer.parseInt(yearlySalary.getText().toString()),
            Integer.parseInt(yearlyBonus.getText().toString()),
            Integer.parseInt(retirementBenefits.getText().toString()),
            Integer.parseInt(leaveTime.getText().toString())
        );

    }

    public boolean hasErrors(){
        boolean hasErrors = false;
        try{
            Integer commute = Integer.parseInt(commuteTime.getText().toString());
            if (commute<=0)
            {
                commuteTime.setError("Invalid entry: Must be positive number");
                hasErrors = true;
            }else {
                commuteTime.setError(null);
            }
        }catch (Exception e){
            commuteTime.setError("Invalid Number");
            hasErrors = true;
        }

        try {
            Integer salary = Integer.parseInt(yearlySalary.getText().toString());
            if (salary <= 0) {
                yearlySalary.setError("Invalid entry: Must be positive number");
                hasErrors = true;
            } else {
                yearlySalary.setError(null);
            }
        }catch (Exception e){
            yearlySalary.setError("Invalid Number");
            hasErrors = true;
        }

        try {
            Integer bonus = Integer.parseInt(yearlyBonus.getText().toString());
            if (bonus <= 0) {
                yearlyBonus.setError("Invalid entry: Must be positive number");
                hasErrors = true;
            } else {
                yearlyBonus.setError(null);
            }
        }catch (Exception e){
            yearlyBonus.setError("Invalid Number");
            hasErrors = true;
        }

        try {
            Integer retirement = Integer.parseInt(retirementBenefits.getText().toString());
            if (retirement <= 0) {
                retirementBenefits.setError("Invalid entry: Must be positive number");
                hasErrors = true;
            } else {
                retirementBenefits.setError(null);
            }
        }catch (Exception e){
            retirementBenefits.setError("Invalid Number");
            hasErrors = true;
        }

        try {
            Integer time = Integer.parseInt(leaveTime.getText().toString());
            if (time <= 0) {
                leaveTime.setError("Invalid entry: Must be positive number");
                hasErrors = true;
            } else {
                leaveTime.setError(null);
            }
        }catch (Exception e){
            leaveTime.setError("Invalid Number");
            hasErrors = true;
        }
        return hasErrors;
    }


}