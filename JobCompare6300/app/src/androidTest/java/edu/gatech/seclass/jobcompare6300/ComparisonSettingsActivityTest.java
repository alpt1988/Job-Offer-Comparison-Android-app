package edu.gatech.seclass.jobcompare6300;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

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
public class ComparisonSettingsActivityTest {

    @Rule
    public ActivityScenarioRule main = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Test Case 48: Enter valid value and check that settings saved
     */
    @Test
    public void testValid() {
        ActivityScenario scenario = main.getScenario();
        onView(withId(R.id.ComparisonSettings)).perform(click());
        onView(withId(R.id.comparison_settings_commute_time_weight))
                .perform(clearText(), replaceText("1"));
        onView(withId(R.id.comparison_settings_yearly_salary))
                .perform(clearText(), replaceText("2"));
        onView(withId(R.id.comparison_settings_yearly_bonus))
                .perform(clearText(), replaceText("3"));
        onView(withId(R.id.comparison_settings_retirement_benefits))
                .perform(clearText(), replaceText("4"));
        onView(withId(R.id.comparison_settings_leave_time))
                .perform(clearText(), replaceText("5"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.ComparisonSettings)).perform(click());
        onView(withId(R.id.comparison_settings_commute_time_weight)).check(matches(withText("1")));
        onView(withId(R.id.comparison_settings_yearly_salary)).check(matches(withText("2")));
        onView(withId(R.id.comparison_settings_yearly_bonus)).check(matches(withText("3")));
        onView(withId(R.id.comparison_settings_retirement_benefits)).check(matches(withText("4")));
        onView(withId(R.id.comparison_settings_leave_time)).check(matches(withText("5")));
    }

    /**
     * Test Case 49: Enter empty value and check for error message
     */
    @Test
    public void testEmpty() {
        ActivityScenario scenario = main.getScenario();
        onView(withId(R.id.ComparisonSettings)).perform(click());
        onView(withId(R.id.comparison_settings_commute_time_weight))
                .perform(clearText(), replaceText(""));
        onView(withId(R.id.comparison_settings_yearly_salary))
                .perform(clearText(), replaceText(""));
        onView(withId(R.id.comparison_settings_yearly_bonus))
                .perform(clearText(), replaceText(""));
        onView(withId(R.id.comparison_settings_retirement_benefits))
                .perform(clearText(), replaceText(""));
        onView(withId(R.id.comparison_settings_leave_time))
                .perform(clearText(), replaceText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.comparison_settings_commute_time_weight))
                .check(matches(hasErrorText("Invalid Number")));
        onView(withId(R.id.comparison_settings_yearly_salary))
                .check(matches(hasErrorText("Invalid Number")));
        onView(withId(R.id.comparison_settings_yearly_bonus))
                .check(matches(hasErrorText("Invalid Number")));
        onView(withId(R.id.comparison_settings_retirement_benefits))
                .check(matches(hasErrorText("Invalid Number")));
        onView(withId(R.id.comparison_settings_leave_time))
                .check(matches(hasErrorText("Invalid Number")));
    }

    /**
     * Test Case 50: Enter zero value and check for error message
     */
    @Test
    public void testZero() {
        ActivityScenario scenario = main.getScenario();
        onView(withId(R.id.ComparisonSettings)).perform(click());
        onView(withId(R.id.comparison_settings_commute_time_weight))
                .perform(clearText(), replaceText("0"));
        onView(withId(R.id.comparison_settings_yearly_salary))
                .perform(clearText(), replaceText("0"));
        onView(withId(R.id.comparison_settings_yearly_bonus))
                .perform(clearText(), replaceText("0"));
        onView(withId(R.id.comparison_settings_retirement_benefits))
                .perform(clearText(), replaceText("0"));
        onView(withId(R.id.comparison_settings_leave_time))
                .perform(clearText(), replaceText("0"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.comparison_settings_commute_time_weight))
                .check(matches(hasErrorText("Invalid entry: Must be positive number")));
        onView(withId(R.id.comparison_settings_yearly_salary))
                .check(matches(hasErrorText("Invalid entry: Must be positive number")));
        onView(withId(R.id.comparison_settings_yearly_bonus))
                .check(matches(hasErrorText("Invalid entry: Must be positive number")));
        onView(withId(R.id.comparison_settings_retirement_benefits))
                .check(matches(hasErrorText("Invalid entry: Must be positive number")));
        onView(withId(R.id.comparison_settings_leave_time))
                .check(matches(hasErrorText("Invalid entry: Must be positive number")));
    }

    /**
     * Test Case 51: Enter negative value and check for error message
     */
    @Test
    public void testNegative() {
        ActivityScenario scenario = main.getScenario();
        onView(withId(R.id.ComparisonSettings)).perform(click());
        onView(withId(R.id.comparison_settings_commute_time_weight))
                .perform(clearText(), replaceText("-1"));
        onView(withId(R.id.comparison_settings_yearly_salary))
                .perform(clearText(), replaceText("-2"));
        onView(withId(R.id.comparison_settings_yearly_bonus))
                .perform(clearText(), replaceText("-3"));
        onView(withId(R.id.comparison_settings_retirement_benefits))
                .perform(clearText(), replaceText("-4"));
        onView(withId(R.id.comparison_settings_leave_time))
                .perform(clearText(), replaceText("-5"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.comparison_settings_commute_time_weight))
                .check(matches(hasErrorText("Invalid entry: Must be positive number")));
        onView(withId(R.id.comparison_settings_yearly_salary))
                .check(matches(hasErrorText("Invalid entry: Must be positive number")));
        onView(withId(R.id.comparison_settings_yearly_bonus))
                .check(matches(hasErrorText("Invalid entry: Must be positive number")));
        onView(withId(R.id.comparison_settings_retirement_benefits))
                .check(matches(hasErrorText("Invalid entry: Must be positive number")));
        onView(withId(R.id.comparison_settings_leave_time))
                .check(matches(hasErrorText("Invalid entry: Must be positive number")));
    }

    /**
     * Test Case 52: Enter non-integer value and check for error message
     */
    @Test
    public void testNonInteger() {
        ActivityScenario scenario = main.getScenario();
        onView(withId(R.id.ComparisonSettings)).perform(click());
        onView(withId(R.id.comparison_settings_commute_time_weight))
                .perform(clearText(), replaceText("aBc"));
        onView(withId(R.id.comparison_settings_yearly_salary))
                .perform(clearText(), replaceText("2eF"));
        onView(withId(R.id.comparison_settings_yearly_bonus))
                .perform(clearText(), replaceText("@#$"));
        onView(withId(R.id.comparison_settings_retirement_benefits))
                .perform(clearText(), replaceText("qW@"));
        onView(withId(R.id.comparison_settings_leave_time))
                .perform(clearText(), replaceText("1.23"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.comparison_settings_commute_time_weight))
                .check(matches(hasErrorText("Invalid Number")));
        onView(withId(R.id.comparison_settings_yearly_salary))
                .check(matches(hasErrorText("Invalid Number")));
        onView(withId(R.id.comparison_settings_yearly_bonus))
                .check(matches(hasErrorText("Invalid Number")));
        onView(withId(R.id.comparison_settings_retirement_benefits))
                .check(matches(hasErrorText("Invalid Number")));
        onView(withId(R.id.comparison_settings_leave_time))
                .check(matches(hasErrorText("Invalid Number")));
    }
}
