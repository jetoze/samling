package tzeth.preconds;

import static org.junit.Assert.assertEquals;
import static tzeth.preconds.MorePreconditions.checkInRange;
import static tzeth.preconds.MorePreconditions.checkNotBlank;
import static tzeth.preconds.MorePreconditions.checkNotEmpty;
import static tzeth.preconds.MorePreconditions.checkNotNegative;
import static tzeth.preconds.MorePreconditions.checkPositive;

import java.util.Arrays;

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
    
}
