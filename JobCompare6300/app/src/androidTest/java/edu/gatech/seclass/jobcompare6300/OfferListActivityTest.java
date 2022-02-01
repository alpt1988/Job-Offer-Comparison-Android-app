package edu.gatech.seclass.jobcompare6300;

import android.database.sqlite.SQLiteDatabase;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;

@RunWith(AndroidJUnit4.class)
public class OfferListActivityTest {

    private JobComparisonDBHelper _db;
    private SQLiteDatabase db;

    @Rule
    public ActivityScenarioRule main = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        _db = new JobComparisonDBHelper(InstrumentationRegistry.getInstrumentation().getTargetContext());
        db = _db.getWritableDatabase();
        db.delete(JobComparisonDBHelper.CURRENT_JOB_TABLE, null,null);
        db.delete(JobComparisonDBHelper.JOB_OFFER_TABLE, null,null);
    }

    @After
    public void finish() {
        db.delete(JobComparisonDBHelper.CURRENT_JOB_TABLE, null,null);
        db.delete(JobComparisonDBHelper.JOB_OFFER_TABLE, null,null);
        db.close();
    }

    /**
     * Test case 43: GetJobOffers : No Current Job One Offer
     */
    @Test
    public void testNoCurrentOneOffer() {
        ActivityScenario scenario = main.getScenario();
        onView(withId(R.id.EnterJobOffer)).perform(click());
        onView(withId(R.id.joTitleID)).perform(clearText(), replaceText("Test Job"));
        onView(withId(R.id.joCompanyID)).perform(clearText(), replaceText("Test Company"));
        onView(withId(R.id.joCityID)).perform(clearText(), replaceText("Test City"));
        onView(withId(R.id.joStateID)).perform(clearText(), replaceText("Test State"));
        onView(withId(R.id.joCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(100)));
        onView(withId(R.id.joCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(20)));
        onView(withId(R.id.joYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(100000)));
        onView(withId(R.id.joYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(20000)));
        onView(withId(R.id.joRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(5)));
        onView(withId(R.id.joLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(20)));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.joSaveButton)).perform(click());
        onView(withText("Main Menu")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.CompareJobs)).check(matches(not(isEnabled())));
    }

    /**
     * Test case 44: GetJobOffers : No Current Job Two Offers
     */
    @Test
    public void testNoCurrentTwoOffers() {
        ActivityScenario scenario = main.getScenario();
        onView(withId(R.id.EnterJobOffer)).perform(click());
        for (int i=0; i<2; i++) {
            onView(withId(R.id.joTitleID)).perform(clearText(), replaceText("Test Job"));
            onView(withId(R.id.joCompanyID)).perform(clearText(), replaceText("Test Company"));
            onView(withId(R.id.joCityID)).perform(clearText(), replaceText("Test City"));
            onView(withId(R.id.joStateID)).perform(clearText(), replaceText("Test State"));
            onView(withId(R.id.joCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(100)));
            onView(withId(R.id.joCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(20)));
            onView(withId(R.id.joYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(100000)));
            onView(withId(R.id.joYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(20000)));
            onView(withId(R.id.joRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(5)));
            onView(withId(R.id.joLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(20)));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.joSaveButton)).perform(click());
            if (i == 1)
                onView(withText("Main Menu")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
            else
                onView(withText("Enter Another Offer")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        }
        onView(withId(R.id.CompareJobs)).perform(click());

        onData(anything())
                .inAdapterView(allOf(withId(R.id.listview), isDisplayed()))
                .atPosition(1)
                .check(matches(isDisplayed()));
    }

    /**
     * Test case 45: GetJobOffers : Has Current Job One Offer
     */
    @Test
    public void testHasCurrentOneOffer() {
        ActivityScenario scenario = main.getScenario();

        onView(withId(R.id.EnterJobDetails)).perform(click());
        onView(withId(R.id.jdTitleID)).perform(clearText(), replaceText("Test Job"));
        onView(withId(R.id.jdCompanyID)).perform(clearText(), replaceText("Test Company"));
        onView(withId(R.id.jdCityID)).perform(clearText(), replaceText("Test City"));
        onView(withId(R.id.jdStateID)).perform(clearText(), replaceText("Test State"));
        onView(withId(R.id.jdCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(100)));
        onView(withId(R.id.jdCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(20)));
        onView(withId(R.id.jdYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(100000)));
        onView(withId(R.id.jdYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(20000)));
        onView(withId(R.id.jdRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(5)));
        onView(withId(R.id.jdLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(20)));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jdSaveButton)).perform(click());
        onView(withId(R.id.cancelCurrentJob)).perform(click());

        onView(withId(R.id.EnterJobOffer)).perform(click());
        onView(withId(R.id.joTitleID)).perform(clearText(), replaceText("Test Job"));
        onView(withId(R.id.joCompanyID)).perform(clearText(), replaceText("Test Company"));
        onView(withId(R.id.joCityID)).perform(clearText(), replaceText("Test City"));
        onView(withId(R.id.joStateID)).perform(clearText(), replaceText("Test State"));
        onView(withId(R.id.joCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(100)));
        onView(withId(R.id.joCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(20)));
        onView(withId(R.id.joYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(100000)));
        onView(withId(R.id.joYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(20000)));
        onView(withId(R.id.joRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(5)));
        onView(withId(R.id.joLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(20)));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.joSaveButton)).perform(click());
        onView(withText("Main Menu")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.CompareJobs)).perform(click());
        onData(anything())
                .inAdapterView(allOf(withId(R.id.listview), isDisplayed()))
                .atPosition(1)
                .check(matches(isDisplayed()));
    }

    /**
     * Test case 46: GetJobOffers : Has Current Job Two Offers
     */
    @Test
    public void testHasCurrentTwoOffers() {
        ActivityScenario scenario = main.getScenario();

        onView(withId(R.id.EnterJobDetails)).perform(click());
        onView(withId(R.id.jdTitleID)).perform(clearText(), replaceText("Test Job"));
        onView(withId(R.id.jdCompanyID)).perform(clearText(), replaceText("Test Company"));
        onView(withId(R.id.jdCityID)).perform(clearText(), replaceText("Test City"));
        onView(withId(R.id.jdStateID)).perform(clearText(), replaceText("Test State"));
        onView(withId(R.id.jdCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(100)));
        onView(withId(R.id.jdCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(20)));
        onView(withId(R.id.jdYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(100000)));
        onView(withId(R.id.jdYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(20000)));
        onView(withId(R.id.jdRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(5)));
        onView(withId(R.id.jdLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(20)));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jdSaveButton)).perform(click());
        onView(withId(R.id.cancelCurrentJob)).perform(click());

        onView(withId(R.id.EnterJobOffer)).perform(click());
        for (int i=0; i<2; i++) {
            onView(withId(R.id.joTitleID)).perform(clearText(), replaceText("Test Job"));
            onView(withId(R.id.joCompanyID)).perform(clearText(), replaceText("Test Company"));
            onView(withId(R.id.joCityID)).perform(clearText(), replaceText("Test City"));
            onView(withId(R.id.joStateID)).perform(clearText(), replaceText("Test State"));
            onView(withId(R.id.joCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(100)));
            onView(withId(R.id.joCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(20)));
            onView(withId(R.id.joYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(100000)));
            onView(withId(R.id.joYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(20000)));
            onView(withId(R.id.joRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(5)));
            onView(withId(R.id.joLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(20)));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.joSaveButton)).perform(click());
            if (i == 1)
                onView(withText("Main Menu")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
            else
                onView(withText("Enter Another Offer")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        }
        onView(withId(R.id.CompareJobs)).perform(click());

        onData(anything())
                .inAdapterView(allOf(withId(R.id.listview), isDisplayed()))
                .atPosition(2)
                .check(matches(isDisplayed()));
    }

    /**
     * Test case 47: GetJobOffers : Has Current Job Ten Offers
     */
    @Test
    public void testHasCurrentTenOffers() {
        ActivityScenario scenario = main.getScenario();

        onView(withId(R.id.EnterJobDetails)).perform(click());
        onView(withId(R.id.jdTitleID)).perform(clearText(), replaceText("Test Job"));
        onView(withId(R.id.jdCompanyID)).perform(clearText(), replaceText("Test Company"));
        onView(withId(R.id.jdCityID)).perform(clearText(), replaceText("Test City"));
        onView(withId(R.id.jdStateID)).perform(clearText(), replaceText("Test State"));
        onView(withId(R.id.jdCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(100)));
        onView(withId(R.id.jdCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(20)));
        onView(withId(R.id.jdYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(100000)));
        onView(withId(R.id.jdYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(20000)));
        onView(withId(R.id.jdRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(5)));
        onView(withId(R.id.jdLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(20)));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jdSaveButton)).perform(click());
        onView(withId(R.id.cancelCurrentJob)).perform(click());

        onView(withId(R.id.EnterJobOffer)).perform(click());
        for (int i=0; i<10; i++) {
            onView(withId(R.id.joTitleID)).perform(clearText(), replaceText("Test Job"));
            onView(withId(R.id.joCompanyID)).perform(clearText(), replaceText("Test Company"));
            onView(withId(R.id.joCityID)).perform(clearText(), replaceText("Test City"));
            onView(withId(R.id.joStateID)).perform(clearText(), replaceText("Test State"));
            onView(withId(R.id.joCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(100)));
            onView(withId(R.id.joCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(20)));
            onView(withId(R.id.joYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(100000)));
            onView(withId(R.id.joYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(20000)));
            onView(withId(R.id.joRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(5)));
            onView(withId(R.id.joLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(20)));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.joSaveButton)).perform(click());
            if (i == 9)
                onView(withText("Main Menu")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
            else
                onView(withText("Enter Another Offer")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        }
        onView(withId(R.id.CompareJobs)).perform(click());

        onData(anything())
                .inAdapterView(allOf(withId(R.id.listview), isDisplayed()))
                .atPosition(10)
                .check(matches(isDisplayed()));
    }

    /**
     * Test case 58: RankJobs : Same jobs given
     */
    @Test
    public void testRankSameJobs() {
        ActivityScenario scenario = main.getScenario();

        onView(withId(R.id.EnterJobDetails)).perform(click());
        onView(withId(R.id.jdTitleID)).perform(clearText(), replaceText("Test Job"));
        onView(withId(R.id.jdCompanyID)).perform(clearText(), replaceText("Test Company"));
        onView(withId(R.id.jdCityID)).perform(clearText(), replaceText("Test City"));
        onView(withId(R.id.jdStateID)).perform(clearText(), replaceText("Test State"));
        onView(withId(R.id.jdCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(100)));
        onView(withId(R.id.jdCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(20)));
        onView(withId(R.id.jdYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(100000)));
        onView(withId(R.id.jdYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(20000)));
        onView(withId(R.id.jdRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(5)));
        onView(withId(R.id.jdLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(20)));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jdSaveButton)).perform(click());
        onView(withId(R.id.cancelCurrentJob)).perform(click());

        onView(withId(R.id.EnterJobOffer)).perform(click());
        onView(withId(R.id.joTitleID)).perform(clearText(), replaceText("Test Job"));
        onView(withId(R.id.joCompanyID)).perform(clearText(), replaceText("Test Company"));
        onView(withId(R.id.joCityID)).perform(clearText(), replaceText("Test City"));
        onView(withId(R.id.joStateID)).perform(clearText(), replaceText("Test State"));
        onView(withId(R.id.joCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(100)));
        onView(withId(R.id.joCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(20)));
        onView(withId(R.id.joYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(100000)));
        onView(withId(R.id.joYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(20000)));
        onView(withId(R.id.joRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(5)));
        onView(withId(R.id.joLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(20)));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.joSaveButton)).perform(click());
        onView(withText("Main Menu")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.CompareJobs)).perform(click());

        onData(anything())
                .inAdapterView(allOf(withId(R.id.listview), isDisplayed()))
                .atPosition(1)
                .check(matches(isDisplayed()));
    }

    /**
     * Test case 59: RankJobs : Empty list of jobs
     */
    @Test
    public void testRankEmptyJobs() {
        ActivityScenario scenario = main.getScenario();
        onView(withId(R.id.CompareJobs)).check(matches(not(isEnabled())));
    }

    /**
     * Test case 60: RankJobs : List of 3 jobs, equal weight in settings
     */
    @Test
    public void testRankEqualJobs() {
        ActivityScenario scenario = main.getScenario();

        onView(withId(R.id.ComparisonSettings)).perform(click());
        onView(withId(R.id.comparison_settings_commute_time_weight))
                .perform(clearText(), replaceText("1"));
        onView(withId(R.id.comparison_settings_yearly_salary))
                .perform(clearText(), replaceText("1"));
        onView(withId(R.id.comparison_settings_yearly_bonus))
                .perform(clearText(), replaceText("1"));
        onView(withId(R.id.comparison_settings_retirement_benefits))
                .perform(clearText(), replaceText("1"));
        onView(withId(R.id.comparison_settings_leave_time))
                .perform(clearText(), replaceText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.button2)).perform(click());

        // First job is worst and should be ranked lowest
        onView(withId(R.id.EnterJobDetails)).perform(click());
        onView(withId(R.id.jdTitleID)).perform(clearText(), replaceText("Test Job1"));
        onView(withId(R.id.jdCompanyID)).perform(clearText(), replaceText("Test Company1"));
        onView(withId(R.id.jdCityID)).perform(clearText(), replaceText("Test City1"));
        onView(withId(R.id.jdStateID)).perform(clearText(), replaceText("Test State1"));
        onView(withId(R.id.jdCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(75)));
        onView(withId(R.id.jdCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(15)));
        onView(withId(R.id.jdYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(50000)));
        onView(withId(R.id.jdYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(0)));
        onView(withId(R.id.jdRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(0)));
        onView(withId(R.id.jdLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(0)));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jdSaveButton)).perform(click());
        onView(withId(R.id.cancelCurrentJob)).perform(click());
        // Second job should be ranked in middle
        onView(withId(R.id.EnterJobOffer)).perform(click());
        onView(withId(R.id.joTitleID)).perform(clearText(), replaceText("Test Job2"));
        onView(withId(R.id.joCompanyID)).perform(clearText(), replaceText("Test Company2"));
        onView(withId(R.id.joCityID)).perform(clearText(), replaceText("Test City2"));
        onView(withId(R.id.joStateID)).perform(clearText(), replaceText("Test State2"));
        onView(withId(R.id.joCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(50)));
        onView(withId(R.id.joCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(10)));
        onView(withId(R.id.joYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(100000)));
        onView(withId(R.id.joYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(10000)));
        onView(withId(R.id.joRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(10)));
        onView(withId(R.id.joLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(10)));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.joSaveButton)).perform(click());
        onView(withText("Enter Another Offer")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        // Third job is best and should be ranked highest
        onView(withId(R.id.joTitleID)).perform(clearText(), replaceText("Test Job3"));
        onView(withId(R.id.joCompanyID)).perform(clearText(), replaceText("Test Company3"));
        onView(withId(R.id.joCityID)).perform(clearText(), replaceText("Test City3"));
        onView(withId(R.id.joStateID)).perform(clearText(), replaceText("Test State3"));
        onView(withId(R.id.joCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(25)));
        onView(withId(R.id.joCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(5)));
        onView(withId(R.id.joYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(200000)));
        onView(withId(R.id.joYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(20000)));
        onView(withId(R.id.joRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(20)));
        onView(withId(R.id.joLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(20)));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.joSaveButton)).perform(click());
        onView(withText("Main Menu")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.CompareJobs)).perform(click());

        onData(anything())
                .inAdapterView(allOf(withId(R.id.listview), isDisplayed()))
                .atPosition(0)
                .perform(click());

        onData(anything())
                .inAdapterView(allOf(withId(R.id.listview), isDisplayed()))
                .atPosition(2)
                .perform(click());

        onView(withId(R.id.compare)).perform(click());
        onView(withId(R.id.job1_title)).check(matches(withText(startsWith("Test Job3"))));
        onView(withId(R.id.job2_title)).check(matches(withText(startsWith("Test Job1"))));
    }

    /**
     * Test case 61: RankJobs : List of 3 jobs, non equal weight in settings
     */
    @Test
    public void testRankNonEqualJobs() {
        ActivityScenario scenario = main.getScenario();

        onView(withId(R.id.ComparisonSettings)).perform(click());
        onView(withId(R.id.comparison_settings_commute_time_weight))
                .perform(clearText(), replaceText("1"));
        onView(withId(R.id.comparison_settings_yearly_salary))
                .perform(clearText(), replaceText("1"));
        onView(withId(R.id.comparison_settings_yearly_bonus))
                .perform(clearText(), replaceText("1"));
        onView(withId(R.id.comparison_settings_retirement_benefits))
                .perform(clearText(), replaceText("1"));
        onView(withId(R.id.comparison_settings_leave_time))
                .perform(clearText(), replaceText("50"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.button2)).perform(click());

        // First job is overall bad but has best leave time so should be ranked first
        onView(withId(R.id.EnterJobDetails)).perform(click());
        onView(withId(R.id.jdTitleID)).perform(clearText(), replaceText("Test Job1"));
        onView(withId(R.id.jdCompanyID)).perform(clearText(), replaceText("Test Company1"));
        onView(withId(R.id.jdCityID)).perform(clearText(), replaceText("Test City1"));
        onView(withId(R.id.jdStateID)).perform(clearText(), replaceText("Test State1"));
        onView(withId(R.id.jdCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(75)));
        onView(withId(R.id.jdCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(15)));
        onView(withId(R.id.jdYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(50000)));
        onView(withId(R.id.jdYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(0)));
        onView(withId(R.id.jdRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(0)));
        onView(withId(R.id.jdLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(50)));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jdSaveButton)).perform(click());
        onView(withId(R.id.cancelCurrentJob)).perform(click());
        // Second job should be ranked in middle
        onView(withId(R.id.EnterJobOffer)).perform(click());
        onView(withId(R.id.joTitleID)).perform(clearText(), replaceText("Test Job2"));
        onView(withId(R.id.joCompanyID)).perform(clearText(), replaceText("Test Company2"));
        onView(withId(R.id.joCityID)).perform(clearText(), replaceText("Test City2"));
        onView(withId(R.id.joStateID)).perform(clearText(), replaceText("Test State2"));
        onView(withId(R.id.joCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(50)));
        onView(withId(R.id.joCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(10)));
        onView(withId(R.id.joYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(100000)));
        onView(withId(R.id.joYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(10000)));
        onView(withId(R.id.joRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(10)));
        onView(withId(R.id.joLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(15)));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.joSaveButton)).perform(click());
        onView(withText("Enter Another Offer")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

        // Third job is overall good but has no leave time so should be ranked last
        onView(withId(R.id.joTitleID)).perform(clearText(), replaceText("Test Job3"));
        onView(withId(R.id.joCompanyID)).perform(clearText(), replaceText("Test Company3"));
        onView(withId(R.id.joCityID)).perform(clearText(), replaceText("Test City3"));
        onView(withId(R.id.joStateID)).perform(clearText(), replaceText("Test State3"));
        onView(withId(R.id.joCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(25)));
        onView(withId(R.id.joCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(5)));
        onView(withId(R.id.joYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(200000)));
        onView(withId(R.id.joYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(20000)));
        onView(withId(R.id.joRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(20)));
        onView(withId(R.id.joLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(0)));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.joSaveButton)).perform(click());
        onView(withText("Main Menu")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.CompareJobs)).perform(click());

        onData(anything())
                .inAdapterView(allOf(withId(R.id.listview), isDisplayed()))
                .atPosition(0)
                .perform(click());

        onData(anything())
                .inAdapterView(allOf(withId(R.id.listview), isDisplayed()))
                .atPosition(2)
                .perform(click());

        onView(withId(R.id.compare)).perform(click());
        onView(withId(R.id.job1_title)).check(matches(withText(startsWith("Test Job1"))));
        onView(withId(R.id.job2_title)).check(matches(withText(startsWith("Test Job3"))));
    }

    /**
     * Test case 62: CompareJobOffersView : Same jobs given
     */
    @Test
    public void testCompareSameJobs() {
        ActivityScenario scenario = main.getScenario();

        onView(withId(R.id.EnterJobDetails)).perform(click());
        onView(withId(R.id.jdTitleID)).perform(clearText(), replaceText("Test Job"));
        onView(withId(R.id.jdCompanyID)).perform(clearText(), replaceText("Test Company"));
        onView(withId(R.id.jdCityID)).perform(clearText(), replaceText("Test City"));
        onView(withId(R.id.jdStateID)).perform(clearText(), replaceText("Test State"));
        onView(withId(R.id.jdCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(100)));
        onView(withId(R.id.jdCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(20)));
        onView(withId(R.id.jdYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(100000)));
        onView(withId(R.id.jdYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(20000)));
        onView(withId(R.id.jdRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(5)));
        onView(withId(R.id.jdLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(20)));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jdSaveButton)).perform(click());
        onView(withId(R.id.cancelCurrentJob)).perform(click());

        onView(withId(R.id.EnterJobOffer)).perform(click());
        onView(withId(R.id.joTitleID)).perform(clearText(), replaceText("Test Job"));
        onView(withId(R.id.joCompanyID)).perform(clearText(), replaceText("Test Company"));
        onView(withId(R.id.joCityID)).perform(clearText(), replaceText("Test City"));
        onView(withId(R.id.joStateID)).perform(clearText(), replaceText("Test State"));
        onView(withId(R.id.joCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(100)));
        onView(withId(R.id.joCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(20)));
        onView(withId(R.id.joYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(100000)));
        onView(withId(R.id.joYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(20000)));
        onView(withId(R.id.joRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(5)));
        onView(withId(R.id.joLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(20)));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.joSaveButton)).perform(click());
        onView(withText("Main Menu")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.CompareJobs)).perform(click());

        onData(anything())
                .inAdapterView(allOf(withId(R.id.listview), isDisplayed()))
                .atPosition(0)
                .perform(click());

        onData(anything())
                .inAdapterView(allOf(withId(R.id.listview), isDisplayed()))
                .atPosition(1)
                .perform(click());

        onView(withId(R.id.compare)).perform(click());
        onView(withId(R.id.job1_title)).check(matches(withText(startsWith("Test Job"))));
        onView(withId(R.id.job2_title)).check(matches(withText(startsWith("Test Job"))));
    }

    /**
     * Test case 63: CompareJobOffersView : Two different jobs
     */
    @Test
    public void testCompareDifferentJobs() {
        ActivityScenario scenario = main.getScenario();

        onView(withId(R.id.ComparisonSettings)).perform(click());
        onView(withId(R.id.comparison_settings_commute_time_weight))
                .perform(clearText(), replaceText("1"));
        onView(withId(R.id.comparison_settings_yearly_salary))
                .perform(clearText(), replaceText("1"));
        onView(withId(R.id.comparison_settings_yearly_bonus))
                .perform(clearText(), replaceText("1"));
        onView(withId(R.id.comparison_settings_retirement_benefits))
                .perform(clearText(), replaceText("1"));
        onView(withId(R.id.comparison_settings_leave_time))
                .perform(clearText(), replaceText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.button2)).perform(click());

        onView(withId(R.id.EnterJobDetails)).perform(click());
        onView(withId(R.id.jdTitleID)).perform(clearText(), replaceText("Test Job1"));
        onView(withId(R.id.jdCompanyID)).perform(clearText(), replaceText("Test Company1"));
        onView(withId(R.id.jdCityID)).perform(clearText(), replaceText("Test City1"));
        onView(withId(R.id.jdStateID)).perform(clearText(), replaceText("Test State1"));
        onView(withId(R.id.jdCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(100)));
        onView(withId(R.id.jdCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(20)));
        onView(withId(R.id.jdYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(100000)));
        onView(withId(R.id.jdYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(20000)));
        onView(withId(R.id.jdRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(5)));
        onView(withId(R.id.jdLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(20)));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jdSaveButton)).perform(click());
        onView(withId(R.id.cancelCurrentJob)).perform(click());

        onView(withId(R.id.EnterJobOffer)).perform(click());
        onView(withId(R.id.joTitleID)).perform(clearText(), replaceText("Test Job2"));
        onView(withId(R.id.joCompanyID)).perform(clearText(), replaceText("Test Company2"));
        onView(withId(R.id.joCityID)).perform(clearText(), replaceText("Test City2"));
        onView(withId(R.id.joStateID)).perform(clearText(), replaceText("Test State2"));
        onView(withId(R.id.joCostOfLivingID)).perform(clearText(), replaceText(Integer.toString(100)));
        onView(withId(R.id.joCommuteTimeID)).perform(clearText(), replaceText(Integer.toString(20)));
        onView(withId(R.id.joYearlySalaryID)).perform(clearText(), replaceText(Integer.toString(150000)));
        onView(withId(R.id.joYearlyBonusID)).perform(clearText(), replaceText(Integer.toString(20000)));
        onView(withId(R.id.joRetirementBenefitsID)).perform(clearText(), replaceText(Integer.toString(5)));
        onView(withId(R.id.joLeaveTimeID)).perform(clearText(), replaceText(Integer.toString(20)));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.joSaveButton)).perform(click());
        onView(withText("Main Menu")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.CompareJobs)).perform(click());

        onData(anything())
                .inAdapterView(allOf(withId(R.id.listview), isDisplayed()))
                .atPosition(0)
                .perform(click());

        onData(anything())
                .inAdapterView(allOf(withId(R.id.listview), isDisplayed()))
                .atPosition(1)
                .perform(click());

        onView(withId(R.id.compare)).perform(click());
        onView(withId(R.id.job1_title)).check(matches(withText(startsWith("Test Job1"))));
        onView(withId(R.id.job2_title)).check(matches(withText(startsWith("Test Job2"))));
    }
}
