package tzeth.strings;

import static java.util.Objects.requireNonNull;
import static tzeth.preconds.MorePreconditions.checkNotNegative;

import com.google.common.base.Strings;

public final class Padding {
    private final int width;
    
    private Padding(int width) {
        this.width = checkNotNegative(width);
    }
    
    public static Padding of(int width) {
        return new Padding(width);
    }
    
    public String left(String s) {
        requireNonNull(s);
        return Strings.padStart(s, width, ' ');
    }
    
    public String right(String s) {
        requireNonNull(s);
        return Strings.padEnd(s, width, ' ');
    }
    
    public String left(Object o) {
        return left(o.toString());
    }

    public String right(Object o) {
        return right(o.toString());
    }
    
    public String left(int i) {
        return left(Integer.toString(i));
    }
    
    public String right(int i) {
        return right(Integer.toString(i));
    }

}
