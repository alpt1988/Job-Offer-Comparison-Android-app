package edu.gatech.seclass.jobcompare6300;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class JobDetailsActivityTest {

    @Rule
    public ActivityScenarioRule main = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {

    }

    /**
     * Test Case 53: Enter valid data and check that data saved
     */
    @Test
    public void testValid() {
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
        onView(withId(R.id.EnterJobDetails)).perform(click());

        onView(withId(R.id.jdTitleID)).check(matches(withText("Test Job")));
        onView(withId(R.id.jdCompanyID)).check(matches(withText("Test Company")));
        onView(withId(R.id.jdCityID)).check(matches(withText("Test City")));
        onView(withId(R.id.jdStateID)).check(matches(withText("Test State")));
        onView(withId(R.id.jdCostOfLivingID)).check(matches(withText(Integer.toString(100))));
        onView(withId(R.id.jdCommuteTimeID)).check(matches(withText(Integer.toString(20))));
        onView(withId(R.id.jdYearlySalaryID)).check(matches(withText(Integer.toString(100000))));
        onView(withId(R.id.jdYearlyBonusID)).check(matches(withText(Integer.toString(20000))));
        onView(withId(R.id.jdRetirementBenefitsID)).check(matches(withText(Integer.toString(5))));
        onView(withId(R.id.jdLeaveTimeID)).check(matches(withText(Integer.toString(20))));
    }

    /**
     * Test Case 54: Enter empty value and check for error message
     */
    @Test
    public void testEmpty() {
        ActivityScenario scenario = main.getScenario();
        onView(withId(R.id.EnterJobDetails)).perform(click());
        onView(withId(R.id.jdTitleID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.jdCompanyID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.jdCityID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.jdStateID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.jdCostOfLivingID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.jdCommuteTimeID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.jdYearlySalaryID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.jdYearlyBonusID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.jdRetirementBenefitsID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.jdLeaveTimeID)).perform(clearText(), replaceText(""));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jdSaveButton)).perform(click());

        onView(withId(R.id.jdTitleID)).check(matches(hasErrorText("Title cannot be blank")));
        onView(withId(R.id.jdCompanyID)).check(matches(hasErrorText("Company cannot be blank")));
        onView(withId(R.id.jdCityID)).check(matches(hasErrorText("City cannot be blank")));
        onView(withId(R.id.jdStateID)).check(matches(hasErrorText("State cannot be blank")));
        onView(withId(R.id.jdCostOfLivingID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.jdCommuteTimeID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.jdYearlySalaryID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.jdYearlyBonusID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.jdRetirementBenefitsID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.jdLeaveTimeID)).check(matches(hasErrorText("Must be an integer")));
    }

    /**
     * Test Case 55: Enter negative value and check for error message
     */
    @Test
    public void testNegative() {
        ActivityScenario scenario = main.getScenario();
        onView(withId(R.id.EnterJobDetails)).perform(click());
        onView(withId(R.id.jdCostOfLivingID)).perform(clearText(), replaceText("-1"));
        onView(withId(R.id.jdCommuteTimeID)).perform(clearText(), replaceText("-1"));
        onView(withId(R.id.jdYearlySalaryID)).perform(clearText(), replaceText("-1"));
        onView(withId(R.id.jdYearlyBonusID)).perform(clearText(), replaceText("-1"));
        onView(withId(R.id.jdRetirementBenefitsID)).perform(clearText(), replaceText("-1"));
        onView(withId(R.id.jdLeaveTimeID)).perform(clearText(), replaceText("-1"));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jdSaveButton)).perform(click());

        onView(withId(R.id.jdCostOfLivingID))
                .check(matches(hasErrorText("COL index should be a positive integer")));
        onView(withId(R.id.jdCommuteTimeID))
                .check(matches(hasErrorText("Commute time should be >= 0 and < 25")));
        onView(withId(R.id.jdYearlySalaryID))
                .check(matches(hasErrorText("Salary should be a positive integer")));
        onView(withId(R.id.jdYearlyBonusID))
                .check(matches(hasErrorText("Bonus should be a positive integer")));
        onView(withId(R.id.jdRetirementBenefitsID))
                .check(matches(hasErrorText("Retirement benefits should be >= 0 and < 100")));
        onView(withId(R.id.jdLeaveTimeID))
                .check(matches(hasErrorText("Leave time should be >= 0")));
    }

    /**
     * Test Case 56: Enter non-integer value and check for error message
     */
    @Test
    public void testNonInteger() {
        ActivityScenario scenario = main.getScenario();
        onView(withId(R.id.EnterJobDetails)).perform(click());
        onView(withId(R.id.jdCostOfLivingID)).perform(clearText(), replaceText("awd"));
        onView(withId(R.id.jdCommuteTimeID)).perform(clearText(), replaceText("AAA"));
        onView(withId(R.id.jdYearlySalaryID)).perform(clearText(), replaceText("1.23"));
        onView(withId(R.id.jdYearlyBonusID)).perform(clearText(), replaceText("#$%@"));
        onView(withId(R.id.jdRetirementBenefitsID)).perform(clearText(), replaceText("1a4"));
        onView(withId(R.id.jdLeaveTimeID)).perform(clearText(), replaceText("5.06"));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jdSaveButton)).perform(click());

        onView(withId(R.id.jdCostOfLivingID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.jdCommuteTimeID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.jdYearlySalaryID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.jdYearlyBonusID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.jdRetirementBenefitsID)).check(matches(hasErrorText("Must be an integer")));
        onView(withId(R.id.jdLeaveTimeID)).check(matches(hasErrorText("Must be an integer")));
    }

    /**
     * Test Case 57: Enter very large value and check for error message
     */
    @Test
    public void testVeryLarge() {
        ActivityScenario scenario = main.getScenario();
        onView(withId(R.id.EnterJobDetails)).perform(click());
        onView(withId(R.id.jdCommuteTimeID)).perform(clearText(), replaceText("50"));
        onView(withId(R.id.jdRetirementBenefitsID)).perform(clearText(), replaceText("150"));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jdSaveButton)).perform(click());

        onView(withId(R.id.jdCommuteTimeID))
                .check(matches(hasErrorText("Commute time should be >= 0 and < 25")));
        onView(withId(R.id.jdRetirementBenefitsID))
                .check(matches(hasErrorText("Retirement benefits should be >= 0 and < 100")));
    }
}
