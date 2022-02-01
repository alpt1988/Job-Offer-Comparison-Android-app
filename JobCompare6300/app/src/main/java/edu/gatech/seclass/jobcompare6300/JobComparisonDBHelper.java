package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.models.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.models.CurrentJob;
import edu.gatech.seclass.jobcompare6300.models.Job;
import edu.gatech.seclass.jobcompare6300.models.JobOffer;
import edu.gatech.seclass.jobcompare6300.models.Location;

public class JobComparisonDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DB_Name = "JobComparsionDB";
    public static final String SCHEMA = "main";
    public static final String JOB_OFFER_TABLE = "Job_Offer";
    public static final String CURRENT_JOB_TABLE = "Current_Job";
    public static final String COMPARISON_SETTINGS_TABLE = "Comparison_Settings";
    public static final String JOB_COL_1 = "id";
    public static final String JOB_COL_2 = "title";
    public static final String JOB_COL_3 = "company";
    public static final String JOB_COL_4 = "city";
    public static final String JOB_COL_5 = "state";
    public static final String JOB_COL_6 = "cost_of_living";
    public static final String JOB_COL_7 = "communte_time";
    public static final String JOB_COL_8 = "yearly_salary";
    public static final String JOB_COL_9 = "yearly_bonus";
    public static final String JOB_COL_10 = "retirement_benefits";
    public static final String JOB_COL_11 = "leave_time";
    public static final String COMP_COL_1 = "id";
    public static final String COMP_COL_2 = "commute_time_weight";
    public static final String COMP_COL_3 = "yearly_salary_weight";
    public static final String COMP_COL_4 = "yearly_bonus_weight";
    public static final String COMP_COL_5 = "retirement_benefits_weight";
    public static final String COMP_COL_6 = "leave_time_weight";






    public JobComparisonDBHelper(Context context){
        super(context, DB_Name, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + SCHEMA + "." + CURRENT_JOB_TABLE +
                "(" + JOB_COL_1 + " INTEGER PRIMARY KEY, "
                + JOB_COL_2 + " TEXT NOT NULL, "
                + JOB_COL_3 + " TEXT NOT NULL, "
                + JOB_COL_4 + " TEXT NOT NULL, "
                + JOB_COL_5 + " TEXT NOT NULL, "
                + JOB_COL_6 + " INTEGER NOT NULL, "
                + JOB_COL_7 + " INTEGER NOT NULL, "
                + JOB_COL_8 + " INTEGER NOT NULL, "
                + JOB_COL_9 + " INTEGER NOT NULL, "
                + JOB_COL_10 + " INTEGER NOT NULL,"
                + JOB_COL_11 + " INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + SCHEMA + "." + JOB_OFFER_TABLE +
                "(" + JOB_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + JOB_COL_2 + " TEXT NOT NULL, "
                + JOB_COL_3 + " TEXT NOT NULL, "
                + JOB_COL_4 + " TEXT NOT NULL, "
                + JOB_COL_5 + " TEXT NOT NULL, "
                + JOB_COL_6 + " INTEGER NOT NULL, "
                + JOB_COL_7 + " INTEGER NOT NULL, "
                + JOB_COL_8 + " INTEGER NOT NULL, "
                + JOB_COL_9 + " INTEGER NOT NULL, "
                + JOB_COL_10 + " INTEGER NOT NULL,"
                + JOB_COL_11 + " INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + SCHEMA + "." + COMPARISON_SETTINGS_TABLE +
                "(" + COMP_COL_1 + " INGEGER PRIMARY KEY,"
                + COMP_COL_2 + " INTEGER NOT NULL,"
                + COMP_COL_3 + " INTEGER NOT NULL,"
                + COMP_COL_4 + " INTEGER NOT NULL,"
                + COMP_COL_5 + " INTEGER NOT NULL,"
                + COMP_COL_6 + " INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + CURRENT_JOB_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + JOB_OFFER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + COMPARISON_SETTINGS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS JOB" );
        onCreate(db);
    }

    public boolean insertCurrentJobData(CurrentJob job){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(JOB_COL_1, 1);
        cv.put(JOB_COL_2, job.getTitle());
        cv.put(JOB_COL_3, job.getCompany());
        cv.put(JOB_COL_4, job.getLocation().getCity());
        cv.put(JOB_COL_5, job.getLocation().getState());
        cv.put(JOB_COL_6, job.getCostOfLivingIndex());
        cv.put(JOB_COL_7, job.getCommuteTime());
        cv.put(JOB_COL_8, job.getYearlySalary());
        cv.put(JOB_COL_9, job.getYearlyBonus());
        cv.put(JOB_COL_10, job.getRetirementBenefits());
        cv.put(JOB_COL_11, job.getLeaveTime());
        long result = db.insertWithOnConflict(CURRENT_JOB_TABLE, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
        if (result == -1){
            return false;
        }
        return true;
    }

    public int insertNewJobOfferData(JobOffer job){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(JOB_COL_2, job.getTitle());
        cv.put(JOB_COL_3, job.getCompany());
        cv.put(JOB_COL_4, job.getLocation().getCity());
        cv.put(JOB_COL_5, job.getLocation().getState());
        cv.put(JOB_COL_6, job.getCostOfLivingIndex());
        cv.put(JOB_COL_7, job.getCommuteTime());
        cv.put(JOB_COL_8, job.getYearlySalary());
        cv.put(JOB_COL_9, job.getYearlyBonus());
        cv.put(JOB_COL_10, job.getRetirementBenefits());
        cv.put(JOB_COL_11, job.getLeaveTime());
        long result = db.insert(JOB_OFFER_TABLE, null, cv);
        return (int)result;
    }

    public boolean insertComparisonSettings(ComparisonSettings comparisonSettings){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COMP_COL_1, 1);
        cv.put(COMP_COL_2, comparisonSettings.getCommuteTimeWeight());
        cv.put(COMP_COL_3, comparisonSettings.getYearlySalaryWeight());
        cv.put(COMP_COL_4, comparisonSettings.getYearlyBonusWeight());
        cv.put(COMP_COL_5, comparisonSettings.getRetirementBenefitsWeight());
        cv.put(COMP_COL_6, comparisonSettings.getLeaveTimeWeight());
        long result = db.insertWithOnConflict(COMPARISON_SETTINGS_TABLE, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
        if (result == -1){
            return false;
        }
        return true;
    }

    public CurrentJob getCurrentJob() {
        CurrentJob ret = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CURRENT_JOB_TABLE, null);
        if (c.moveToNext()) {
            ret = new CurrentJob(
                c.getString(1),
                c.getString(2),
                new Location(c.getString(3), c.getString(4)),
                Integer.parseInt(c.getString(5)),
                Integer.parseInt(c.getString(6)),
                Integer.parseInt(c.getString(7)),
                Integer.parseInt(c.getString(8)),
                Integer.parseInt(c.getString(9)),
                Integer.parseInt(c.getString(10)));
        }
        db.close();
        c.close();
        return ret;
    }

    public JobOffer getJobOfferById(int id){
        JobOffer ret = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + JOB_OFFER_TABLE + " WHERE id = ?", new String[] {String.valueOf(id)});
        if (c.moveToNext()) {
            ret = new JobOffer(
                    c.getString(1),
                    c.getString(2),
                    new Location(c.getString(3), c.getString(4)),
                    Integer.parseInt(c.getString(5)),
                    Integer.parseInt(c.getString(6)),
                    Integer.parseInt(c.getString(7)),
                    Integer.parseInt(c.getString(8)),
                    Integer.parseInt(c.getString(9)),
                    Integer.parseInt(c.getString(10)));
            ret.setId(Integer.parseInt(c.getString(0)));
        }
        db.close();
        c.close();
        return ret;

    }

    public ArrayList<JobOffer> getAllJobOffers(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + JOB_OFFER_TABLE, null);
        ArrayList<JobOffer> ret = new ArrayList<>();
        while (c.moveToNext()){
            JobOffer currOffer =  new JobOffer(
                    c.getString(1),
                    c.getString(2),
                    new Location(c.getString(3), c.getString(4)),
                    Integer.parseInt(c.getString(5)),
                    Integer.parseInt(c.getString(6)),
                    Integer.parseInt(c.getString(7)),
                    Integer.parseInt(c.getString(8)),
                    Integer.parseInt(c.getString(9)),
                    Integer.parseInt(c.getString(10)));
            currOffer.setId(Integer.parseInt(c.getString(0)));
            ret.add(currOffer);
        }
        db.close();
        c.close();
        return ret;
    }

    public ComparisonSettings getComparisonSettings() {
        ComparisonSettings ret = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + COMPARISON_SETTINGS_TABLE, null);
        if (c.moveToNext()) {
            ret = new ComparisonSettings(
                    Integer.parseInt(c.getString(1)),
                    Integer.parseInt(c.getString(2)),
                    Integer.parseInt(c.getString(3)),
                    Integer.parseInt(c.getString(4)),
                    Integer.parseInt(c.getString(5))
            );
        }
        db.close();
        c.close();
        return ret;
    }

    public int getJobOfferCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db,JOB_OFFER_TABLE);
        db.close();
        return count;
    }

    public int getCurrentJobCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db,CURRENT_JOB_TABLE);
        db.close();
        return count;
    }

    public void initializeComparisonSettings(){
        SQLiteDatabase db = this.getReadableDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db,COMPARISON_SETTINGS_TABLE);
        if (count == 0){
            insertComparisonSettings(new ComparisonSettings(1, 1,1,1,1));
        }
        db.close();
    }

}
