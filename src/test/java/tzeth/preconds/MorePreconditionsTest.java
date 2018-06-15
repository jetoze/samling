package tzeth.preconds;

import static org.junit.Assert.assertEquals;
import static tzeth.preconds.MorePreconditions.*;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public final class MorePreconditionsTest {

    @Test
    public void testCheckPositivePass() {
        checkPositive(1);
        checkPositive(2);
        checkPositive(1_000_000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckPositiveFailsForZero() {
        checkPositive(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckPositiveFailsForNegativeNumber() {
        checkPositive(-1);
    }

    @Test
    public void testCheckNotNegativePass() {
        checkNotNegative(0);
        checkNotNegative(1);
        checkNotNegative(100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckNotNegativeFailsForNegativeNumber() {
        checkNotNegative(-1);
    }

    @Test
    public void testCheckInRangeForValidArgument() {
        checkInRange(10, 0, 10);
        checkInRange(10, 10, 20);
        checkInRange(10, 0, 20);
        checkInRange(10, 10, 10);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCheckInRangeForInvalidArgument() {
        checkInRange(10, 0, 5);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCheckInRangeForInvalidRange() {
        checkInRange(10, 20, 0);
    }
    
    @Test
    public void testCheckNotEmptyPass() {
        Arrays.asList(" ", "hello").stream().forEach(this::checkNotEmptyPassImpl);
    }

    private void checkNotEmptyPassImpl(String s) {
        assertEquals(s, checkNotEmpty(s));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCheckNotEmptyFailsForNull() {
        checkNotEmpty(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckNotEmptyFailsForEmptyString() {
        checkNotEmpty("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckNotBlankFailsForNull() {
        checkNotBlank(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCheckNotBlankFailsForEmptyString() {
        checkNotBlank("");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCheckNotBlankFailsForOneSpace() {
        checkNotBlank(" ");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCheckNotBlankFailsForSpaces() {
        checkNotBlank("    ");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCheckNotBlankFailsForOneTab() {
        checkNotBlank("\t");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCheckNotBlankFailsForVariousWhitespaces() {
        checkNotBlank("\n  \t    \n\n");
    }
    
    @Test
    public void testCheckNotBlankPass() {
        Arrays.asList("a", " a", "a ", "\ta  \n").forEach(this::checkNotBlankPassImpl);
    }
    
    private void checkNotBlankPassImpl(String s) {
        assertEquals(s, checkNotBlank(s));
    }

    @Test
    public void testOneOfPassWithSingleOption() {
        checkOneOf(TimeUnit.SECONDS, TimeUnit.SECONDS);
    }

    @Test
    public void testOneOfPassWithTwoOptions() {
        checkOneOf(TimeUnit.SECONDS, TimeUnit.SECONDS, TimeUnit.MINUTES);
    }

    @Test
    public void testOneOfPassWithSeveralOptions() {
        checkOneOf(TimeUnit.SECONDS, TimeUnit.SECONDS, TimeUnit.MINUTES, TimeUnit.HOURS, TimeUnit.DAYS);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testOneOfFailWithOneOption() {
        checkOneOf(TimeUnit.MILLISECONDS, TimeUnit.SECONDS);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testOneOfFailWithTwoOption() {
        checkOneOf(TimeUnit.MILLISECONDS, TimeUnit.SECONDS, TimeUnit.MINUTES);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testOneOfFailWithSeveralOption() {
        checkOneOf(TimeUnit.MILLISECONDS, TimeUnit.SECONDS, TimeUnit.MINUTES, TimeUnit.HOURS, TimeUnit.DAYS);
    }
    
}
