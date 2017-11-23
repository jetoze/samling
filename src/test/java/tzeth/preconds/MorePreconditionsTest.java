package tzeth.preconds;

import static tzeth.preconds.MorePreconditions.*;

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
        checkNotEmpty(" ");
        checkNotEmpty("hello");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckNotEmptyFailsForNull() {
        checkNotEmpty(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckNotEmptyFailsForEmptyString() {
        checkNotEmpty("");
    }

}
