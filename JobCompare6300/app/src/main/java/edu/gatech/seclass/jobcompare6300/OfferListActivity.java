package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.seclass.jobcompare6300.models.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.models.CurrentJob;
import edu.gatech.seclass.jobcompare6300.models.Job;
import edu.gatech.seclass.jobcompare6300.models.JobOffer;

public class OfferListActivity extends AppCompatActivity {
    private JobComparisonDBHelper _db;
    ArrayList<Job> clickedJobs;
    ArrayList<Job> jobs;
    Button compare;
    int Compare_offer_request_code = 1;
    ComparisonSettings compSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offer_list);
        compare = findViewById(R.id.compare);
        compare.setEnabled(false);

        compSettings = MainActivity.comparisonSettings;

        final ListView listView = (ListView) findViewById(R.id.listview);
        _db = new JobComparisonDBHelper(this);
        clickedJobs = new ArrayList<>();

        jobs = convertToJobArrayList(MainActivity.jobOffers);

        CurrentJob currJob = null;
        if (MainActivity.currentJob != null) {
            currJob = MainActivity.currentJob.copy();
        }
        if (currJob != null){
            currJob.setTitle(currJob.getTitle() + " (Current Job)");
            jobs.add(currJob);
        }


        jobs = sortJobOffersBasedOnScore(jobs, compSettings);


        SimpleAdapter adapter = new SimpleAdapter(this, GetJobAdapter(jobs),android.R.layout.simple_list_item_2,
                new String[] {"First", "Second"}, new int[] {android.R.id.text1, android.R.id.text2});

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){

                // only allow 2 jobs in clickedJobs. If a job was already in clickedJobs, clicking it again will deselect it
                Job clickedJob = jobs.get(position);
                if (clickedJobs.indexOf(clickedJob) == -1) {
                    while (clickedJobs.size() > 1) { //remove the earliest clicked job to make room for the newest one
                        clickedJobs.remove(0);
                    }
                    // add the clicked job to queue
                    clickedJobs.add(clickedJob);
                } else {
                    clickedJobs.remove(clickedJob); // If a job was already in clickedJobs, clicking it again will deselect it
                }

                // setting all View background to transparent by default
                for (int i = 0; i < jobs.size(); i++) {
                    View tempView = getViewByPosition(i, listView);
                    tempView.setBackgroundColor(Color.TRANSPARENT);
                }

                // highlighting clickedJobs
                for (int j =0; j < clickedJobs.size(); j ++) {
                    View viewOfThisSelectedJob = getViewByPosition(jobs.indexOf(clickedJobs.get(j)), listView);
                    viewOfThisSelectedJob.setBackgroundColor(Color.GRAY);
                }

                if (clickedJobs.size() > 1){
                    compare.setEnabled(true);
                } else {
                    compare.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListView listView = (ListView) findViewById(R.id.listview);
        // setting all View background to transparent by default
        for (int i = 0; i < jobs.size(); i++) {
            View tempView = getViewByPosition(i, listView);
            tempView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    public void Compare(View view){
        if (clickedJobs.get(0).getClass() == CurrentJob.class){
            Log.d("currJob0", "currJob0");
        }
        Intent compareOffersIntent = new Intent(OfferListActivity.this, CompareOffersActivity.class);
        compareOffersIntent.putExtra("JOB_1_ID", clickedJobs.get(0).getId());
        compareOffersIntent.putExtra("JOB_1_CURRENT_JOB", clickedJobs.get(0).getClass() == CurrentJob.class);
        compareOffersIntent.putExtra("JOB_2_ID", clickedJobs.get(1).getId());
        compareOffersIntent.putExtra("JOB_2_CURRENT_JOB", clickedJobs.get(1).getClass() == CurrentJob.class);
        clickedJobs = new ArrayList<>();
        compare.setEnabled(false);
        OfferListActivity.this.startActivityForResult(compareOffersIntent, Compare_offer_request_code);
    }

    public void CancelCompare(View view){
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Compare_offer_request_code){
            if (resultCode == RESULT_OK){
                Boolean mainMenu = data.getBooleanExtra("mainMenu", false);
                if (mainMenu) {
                    finish();
                }
            }
        }

    }

    private List<Map<String, String>> GetJobAdapter(ArrayList<Job> jobs){
        List<Map<String, String>> jobAdapter = new ArrayList<>();

        for (int i = 0; i < jobs.size(); i++){
            Map<String, String> currMap = new HashMap<String, String>(2);
            currMap.put("First", jobs.get(i).getTitle());
            currMap.put("Second", jobs.get(i).getCompany());
            jobAdapter.add(currMap);
        }
        return jobAdapter;
    }

    private ArrayList<Job> convertToJobArrayList(ArrayList<JobOffer> jobOffers){
        ArrayList<Job> jobs = new ArrayList<>();
        for (int i = 0; i < jobOffers.size(); i++){
            jobs.add(jobOffers.get(i));
        }
        return jobs;
    }

    private ArrayList<Job> sortJobOffersBasedOnScore(ArrayList<Job> jobs, ComparisonSettings compSettings){
        ArrayList<Job> sortedJobs = new ArrayList<>();
        for(int i = 0; i < jobs.size(); i++){
            boolean added = false;
            for (int j = 0; j < sortedJobs.size(); j++){
                if (jobs.get(i).getScore(compSettings) > sortedJobs.get(j).getScore(compSettings)){
                    sortedJobs.add(j, jobs.get(i));
                    added = true;
                    break;
                }
            }
            if (!added){
                sortedJobs.add(jobs.get(i));
            }
        }
        return sortedJobs;
    }


}
