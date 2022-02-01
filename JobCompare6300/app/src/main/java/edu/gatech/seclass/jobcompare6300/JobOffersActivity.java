package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.jobcompare6300.models.CurrentJob;
import edu.gatech.seclass.jobcompare6300.models.JobOffer;
import edu.gatech.seclass.jobcompare6300.models.Location;

public class JobOffersActivity extends AppCompatActivity {
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

    int Compare_offer_request_code = 2;
    int currentId = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_offers);
        _db = new JobComparisonDBHelper(this);

        title = findViewById(R.id.joTitleID);
        company = findViewById(R.id.joCompanyID);
        city = findViewById(R.id.joCityID);
        state = findViewById(R.id.joStateID);
        costOfLiving = findViewById(R.id.joCostOfLivingID);
        commuteTime = findViewById(R.id.joCommuteTimeID);
        yearlySalary = findViewById(R.id.joYearlySalaryID);
        yearlyBonus = findViewById(R.id.joYearlyBonusID);
        retirementBenefits = findViewById(R.id.joRetirementBenefitsID);
        leaveTime = findViewById(R.id.joLeaveTimeID);
    }

    public void saveJobOffer(View view){
        boolean saved = false;
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
            JobOffer newOffer = new JobOffer(titleValue,
                    companyValue,
                    new Location(cityValue, stateValue),
                    Integer.parseInt(costOfLivingValue),
                    Integer.parseInt(commuteTimeValue),
                    Integer.parseInt(yearlySalaryValue),
                    Integer.parseInt(yearlyBonusValue),
                    Integer.parseInt(retirementBenefitsValue),
                    Integer.parseInt(leaveTimeValue));

            int id = _db.insertNewJobOfferData(new JobOffer(
                    titleValue,
                    companyValue,
                    new Location(cityValue, stateValue),
                    Integer.valueOf(costOfLivingValue),
                    Integer.valueOf(commuteTimeValue),
                    Integer.valueOf(yearlySalaryValue),
                    Integer.valueOf(yearlyBonusValue),
                    Integer.valueOf(retirementBenefitsValue),
                    Integer.valueOf(leaveTimeValue)));

            currentId = id;
            newOffer.setId(id);
            MainActivity.jobOffers.add(newOffer);
            saved = true;
        }

        if (saved) {

            title.setText("");
            company.setText("");
            city.setText("");
            state.setText("");
            costOfLiving.setText("");
            commuteTime.setText("");
            yearlySalary.setText("");
            yearlyBonus.setText("");
            retirementBenefits.setText("");
            leaveTime.setText("");

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Job Offer for " + titleValue + " Saved");
            builder.setMessage("What would you like to do next?");
            builder.setPositiveButton("Enter Another Offer", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("Main Menu", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            if (_db.getCurrentJobCount() > 0) {
                builder.setNeutralButton("Compare to Current Job", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent compareOffersIntent = new Intent(JobOffersActivity.this, CompareOffersActivity.class);
                        compareOffersIntent.putExtra("JOB_1_ID", MainActivity.currentJob.getId());
                        compareOffersIntent.putExtra("JOB_1_CURRENT_JOB", true);
                        compareOffersIntent.putExtra("JOB_2_ID", currentId);
                        compareOffersIntent.putExtra("JOB_2_CURRENT_JOB", false);
                        JobOffersActivity.this.startActivityForResult(compareOffersIntent, Compare_offer_request_code);
                    }
                });
            }

            builder.show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Compare_offer_request_code){
            if (resultCode == RESULT_OK){
                Boolean mainMenu = data.getBooleanExtra("mainMenu", false);
                if (mainMenu) {
                    finish();
                }else{
                    Intent offerListIntent = new Intent(JobOffersActivity.this, OfferListActivity.class);
                    finish();
                    startActivity(offerListIntent);
                }
            }
        }

    }

    public void cancelJobOffer(View view){
        finish();
    }

}