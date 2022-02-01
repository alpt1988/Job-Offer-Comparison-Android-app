package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

import edu.gatech.seclass.jobcompare6300.models.Job;

public class CompareOffersActivity extends AppCompatActivity {
    private JobComparisonDBHelper _db;

    TextView job1_title;
    TextView job1_company;
    TextView job1_city;
    TextView job1_state;
    TextView job1_commuteTime;
    TextView job1_yearlySalary;
    TextView job1_yearlyBonus;
    TextView job1_retirementBenefits;
    TextView job1_leaveTime;
    TextView job2_title;
    TextView job2_company;
    TextView job2_city;
    TextView job2_state;
    TextView job2_commuteTime;
    TextView job2_yearlySalary;
    TextView job2_yearlyBonus;
    TextView job2_retirementBenefits;
    TextView job2_leaveTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_offers);
        _db = new JobComparisonDBHelper(this);

        int job1Id = getIntent().getIntExtra("JOB_1_ID", 0);
        Boolean job1CurrentJob = getIntent().getBooleanExtra("JOB_1_CURRENT_JOB", false);
        int job2Id = getIntent().getIntExtra("JOB_2_ID", 0);
        Boolean job2CurrentJob = getIntent().getBooleanExtra("JOB_2_CURRENT_JOB", false);

        Job job1 = null;
        Job job2 = null;

        if (job1CurrentJob){
            job1 = _db.getCurrentJob();
            job1.setTitle(job1.getTitle() + "(Current Job)");
        }else{
            job1 = _db.getJobOfferById(job1Id);
        }

        if (job2CurrentJob){
            job2 = _db.getCurrentJob();
            job2.setTitle(job2.getTitle() + "(Current Job)");
        }else{
            job2 = _db.getJobOfferById(job2Id);
        }

        job1_title = findViewById(R.id.job1_title);
        job1_company = findViewById(R.id.job1_company);
        job1_city = findViewById(R.id.job1_city);
        job1_state = findViewById(R.id.job1_state);
        job1_commuteTime = findViewById(R.id.job1_commute_time);
        job1_yearlySalary = findViewById(R.id.job1_yearly_salary);
        job1_yearlyBonus = findViewById(R.id.job1_yearly_bonus);
        job1_retirementBenefits = findViewById(R.id.job1_retirement_benefits);
        job1_leaveTime = findViewById(R.id.job1_leave_time);

        job2_title = findViewById(R.id.job2_title);
        job2_company = findViewById(R.id.job2_company);
        job2_city = findViewById(R.id.job2_city);
        job2_state = findViewById(R.id.job2_state);
        job2_commuteTime = findViewById(R.id.job2_commute_time);
        job2_yearlySalary = findViewById(R.id.job2_yearly_salary);
        job2_yearlyBonus = findViewById(R.id.job2_yearly_bonus);
        job2_retirementBenefits = findViewById(R.id.job2_retirement_benefits);
        job2_leaveTime = findViewById(R.id.job2_leave_time);

        if (job1 != null) {
            double col_index = (double)job1.getCostOfLivingIndex() / 100.0;
            DecimalFormat df = new DecimalFormat("#.##");
            String adjustedYearlySalary = df.format( (double)job1.getYearlySalary()/col_index);
            String adjustedYearlyBonus = df.format((double)job1.getYearlyBonus()/col_index);


            job1_title.setText(job1.getTitle());
            job1_company.setText(job1.getCompany());
            job1_city.setText(job1.getLocation().getCity());
            job1_state.setText(job1.getLocation().getState());
            job1_commuteTime.setText(String.valueOf(job1.getCommuteTime()));
            job1_yearlySalary.setText(adjustedYearlySalary);
            job1_yearlyBonus.setText(adjustedYearlyBonus);
            job1_retirementBenefits.setText(String.valueOf(job1.getRetirementBenefits()));
            job1_leaveTime.setText(String.valueOf(job1.getLeaveTime()));
        }

        if (job2 != null) {
            double col_index2 = (double)job2.getCostOfLivingIndex() / 100.0;
            DecimalFormat df = new DecimalFormat("#.##");
            String adjustedYearlySalary2 = df.format((double)job2.getYearlySalary()/col_index2);
            String adjustedYearlyBonus2 = df.format((double)job2.getYearlyBonus()/col_index2);


            job2_title.setText(job2.getTitle());
            job2_company.setText(job2.getCompany());
            job2_city.setText(job2.getLocation().getCity());
            job2_state.setText(job2.getLocation().getState());
            job2_commuteTime.setText(String.valueOf(job2.getCommuteTime()));
            job2_yearlySalary.setText(adjustedYearlySalary2);
            job2_yearlyBonus.setText(adjustedYearlyBonus2);
            job2_retirementBenefits.setText(String.valueOf(job2.getRetirementBenefits()));
            job2_leaveTime.setText(String.valueOf(job2.getLeaveTime()));
        }


    }

    public void mainMenu(View view){
        Intent retIntent = new Intent();
        retIntent.putExtra("mainMenu", true);
        setResult(Activity.RESULT_OK,retIntent);
        finish();

    }

    public void compareAnotherOffer(View view){
        Intent retIntent = new Intent();
        retIntent.putExtra("mainMenu", false);
        setResult(Activity.RESULT_OK,retIntent);
        finish();
    }
}