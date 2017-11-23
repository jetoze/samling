package tzeth.preconds;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Strings;

public final class MorePreconditions {
    
	public static int checkPositive(int n) {
		checkArgument(n > 0);
		return n;
	}

	public static int checkNotNegative(int n) {
		checkArgument(n >= 0);
		return n;
	}
	
	public static int checkInRange(int n, int minInclusive, int maxInclusive) {
	    checkArgument(minInclusive <= maxInclusive);
	    checkArgument(n >= minInclusive && n <= maxInclusive);
	    return n;
	}
	
	public static String checkNotEmpty(String s) {
		checkArgument(!Strings.isNullOrEmpty(s));
		return s;
	}
	
	private MorePreconditions() {/**/}

}
