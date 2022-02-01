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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(AndroidJUnit4.class)
public class JobOffersActivityTest {

    private JobComparisonDBHelper _db;
    private SQLiteDatabase db;

    @Rule
    public ActivityScenarioRule main = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        _db = new JobComparisonDBHelper(InstrumentationRegistry.getInstrumentation().getTargetContext());
        db = _db.getWritableDatabase();
    }

    @After
    public void finish() {
        db.delete(JobComparisonDBHelper.JOB_OFFER_TABLE, JobComparisonDBHelper.JOB_COL_2 + " LIKE 'Test%'",null);
        db.close();
    }

    /**
     * Test Case 64: Enter empty value and check for error message
     */
    @Test
    public void testEmpty() {
        ActivityScenario scenario = main.getScenario();
        onView(withId(R.id.EnterJobOffer)).perform(click());
        onView(withId(R.id.joTitleID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.joCompanyID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.joCityID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.joStateID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.joCostOfLivingID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.joCommuteTimeID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.joYearlySalaryID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.joYearlyBonusID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.joRetirementBenefitsID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.joLeaveTimeID)).perform(clearText(), replaceText(""));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.joSaveButton)).perform(click());

        onView(withId(R.id.joTitleID)).check(matches(hasErrorText("Title cannot be blank")));
        onView(withId(R.id.joCompanyID)).check(matches(hasErrorText("Company cannot be blank")));
        onView(withId(R.id.joCityID)).check(matches(hasErrorText("City cannot be blank")));
        onView(withId(R.id.joStateID)).check(matches(hasErrorText("State cannot be blank")));
        onView(withId(R.id.joCostOfLivingID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.joCommuteTimeID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.joYearlySalaryID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.joYearlyBonusID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.joRetirementBenefitsID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.joLeaveTimeID)).check(matches(hasErrorText("Must be an integer")));
    }

    /**
     * Test Case 65: Enter negative value and check for error message
     */
    @Test
    public void testNegative() {
        ActivityScenario scenario = main.getScenario();
        onView(withId(R.id.EnterJobOffer)).perform(click());
        onView(withId(R.id.joCostOfLivingID)).perform(clearText(), replaceText("-1"));
        onView(withId(R.id.joCommuteTimeID)).perform(clearText(), replaceText("-1"));
        onView(withId(R.id.joYearlySalaryID)).perform(clearText(), replaceText("-1"));
        onView(withId(R.id.joYearlyBonusID)).perform(clearText(), replaceText("-1"));
        onView(withId(R.id.joRetirementBenefitsID)).perform(clearText(), replaceText("-1"));
        onView(withId(R.id.joLeaveTimeID)).perform(clearText(), replaceText("-1"));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.joSaveButton)).perform(click());

        onView(withId(R.id.joCostOfLivingID))
                .check(matches(hasErrorText("COL index should be a positive integer")));
        onView(withId(R.id.joCommuteTimeID))
                .check(matches(hasErrorText("Commute time should be >= 0 and < 25")));
        onView(withId(R.id.joYearlySalaryID))
                .check(matches(hasErrorText("Salary should be a positive integer")));
        onView(withId(R.id.joYearlyBonusID))
                .check(matches(hasErrorText("Bonus should be a positive integer")));
        onView(withId(R.id.joRetirementBenefitsID))
                .check(matches(hasErrorText("Retirement benefits should be >= 0 and < 100")));
        onView(withId(R.id.joLeaveTimeID))
                .check(matches(hasErrorText("Leave time should be >= 0")));
    }

    /**
     * Test Case 66: Enter non-integer value and check for error message
     */
    @Test
    public void testNonInteger() {
        ActivityScenario scenario = main.getScenario();
        onView(withId(R.id.EnterJobOffer)).perform(click());
        onView(withId(R.id.joCostOfLivingID)).perform(clearText(), replaceText("awd"));
        onView(withId(R.id.joCommuteTimeID)).perform(clearText(), replaceText("AAA"));
        onView(withId(R.id.joYearlySalaryID)).perform(clearText(), replaceText("1.23"));
        onView(withId(R.id.joYearlyBonusID)).perform(clearText(), replaceText("#$%@"));
        onView(withId(R.id.joRetirementBenefitsID)).perform(clearText(), replaceText("1a4"));
        onView(withId(R.id.joLeaveTimeID)).perform(clearText(), replaceText("5.06"));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.joSaveButton)).perform(click());

        onView(withId(R.id.joCostOfLivingID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.joCommuteTimeID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.joYearlySalaryID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.joYearlyBonusID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.joRetirementBenefitsID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.joLeaveTimeID)).check(matches(hasErrorText("Must be an integer")));
    }

    /**
     * Test Case 67: Enter very large value and check for error message
     */
    @Test
    public void testVeryLarge() {
        ActivityScenario scenario = main.getScenario();
        onView(withId(R.id.EnterJobOffer)).perform(click());
        onView(withId(R.id.joCommuteTimeID)).perform(clearText(), replaceText("50"));
        onView(withId(R.id.joRetirementBenefitsID)).perform(clearText(), replaceText("150"));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.joSaveButton)).perform(click());

        onView(withId(R.id.joCommuteTimeID))
                .check(matches(hasErrorText("Commute time should be >= 0 and < 25")));
        onView(withId(R.id.joRetirementBenefitsID))
                .check(matches(hasErrorText("Retirement benefits should be >= 0 and < 100")));
    }
}
