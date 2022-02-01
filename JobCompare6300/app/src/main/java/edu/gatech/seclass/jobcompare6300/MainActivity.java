package edu.gatech.seclass.jobcompare6300;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.gatech.seclass.jobcompare6300.models.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.models.CurrentJob;
import edu.gatech.seclass.jobcompare6300.models.JobOffer;

public class MainActivity extends AppCompatActivity {

    private JobComparisonDBHelper _db;
    Button compareOffers;

    public static CurrentJob currentJob;
    public static ArrayList<JobOffer> jobOffers;
    public static ComparisonSettings comparisonSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _db = new JobComparisonDBHelper(this);
        _db.initializeComparisonSettings();
        comparisonSettings = _db.getComparisonSettings();
        compareOffers = (Button) findViewById(R.id.CompareJobs);
        if (_db.getJobOfferCount() + _db.getCurrentJobCount() < 2){
            compareOffers.setEnabled(false);
        }else{
            compareOffers.setEnabled(true);
        }
        currentJob = _db.getCurrentJob();
        jobOffers = _db.getAllJobOffers();
    }

    @Override
    public void onRestart(){
        super.onRestart();
        if (_db.getJobOfferCount() + _db.getCurrentJobCount() < 2){
            compareOffers.setEnabled(false);
        }else{
            compareOffers.setEnabled(true);
        }
    }

    public void handleCurrentJob(View view) {
        Intent currentJobIntent = new Intent(MainActivity.this, JobDetailsActivity.class);
        MainActivity.this.startActivity(currentJobIntent);
    }

    public void handleJobOffer(View view) {
        Intent jobOfferIntent = new Intent(MainActivity.this, JobOffersActivity.class);
        MainActivity.this.startActivity(jobOfferIntent);
    }

    public void handleComparisonSettings(View view) {
        Intent comparisonSettingsIntent = new Intent(MainActivity.this, ComparisonSettingsActivity.class);
        MainActivity.this.startActivity(comparisonSettingsIntent);
    }

    public void handleCompareOffers(View view) {
        Intent compareOffersIntent = new Intent(MainActivity.this, OfferListActivity.class);
        MainActivity.this.startActivity(compareOffersIntent);
    }

}