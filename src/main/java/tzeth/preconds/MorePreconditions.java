package tzeth.preconds;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * Common preconditions in addition to the ones provided by guava's
 * {@link Preconditions}.
 */
public final class MorePreconditions {

    /**
     * Ensures that the given argument is greater than 0 (zero).
     * 
     * @return {@code n} provided it was positive
     * @throws IllegalArgumentException
     *             if {@code n <= 0}
     */
    public static int checkPositive(int n) {
        checkArgument(n > 0);
        return n;
    }

    /**
     * Ensures that the given argument is not less than 0 (zero).
     * 
     * @return {@code n} provided it was not negative
     * @throws IllegalArgumentException
     *             if {@code n < 0}
     */
    public static int checkNotNegative(int n) {
        checkArgument(n >= 0);
        return n;
    }

    public static int checkInRange(int n, int minInclusive, int maxInclusive) {
        checkArgument(minInclusive <= maxInclusive);
        checkArgument(n >= minInclusive && n <= maxInclusive);
        return n;
    }

    /**
     * Ensures that the given string is not null and not empty.
     * <p>
     * Note that a string consisting entirely of whitespace (i.e. a "blank"
     * string) is accepted by this method. Use {@link #checkNotBlank(String)} to
     * test against this case.
     * 
     * @return the input string provided it was non-null and not empty.
     * @throws IllegalArgumentException
     *             if {@code s == null} or {@code s.length() == 0}.
     */
    public static String checkNotEmpty(String s) {
        checkArgument(!Strings.isNullOrEmpty(s));
        return s;
    }
    
    /**
     * Ensures that the given string is not null, and not empty or consisting entirely
     * of whitespace.
     * 
     * @return the input string provided it was non-null and not blank.
     * @throws IllegalArgumentException
     *             if {@code s == null} or {@code s.trim().length() == 0}.
     */
    public static String checkNotBlank(String s) {
        checkArgument(s != null && s.trim().length() > 0);
        return s;
    }

    private MorePreconditions() {/**/}

}
