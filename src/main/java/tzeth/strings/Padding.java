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
    
    public String padLeft(String s) {
        requireNonNull(s);
        return Strings.padStart(s, width, ' ');
    }
    
    public String padRight(String s) {
        requireNonNull(s);
        return Strings.padEnd(s, width, ' ');
    }
    
    public String padLeft(int i) {
        return padLeft(Integer.toString(i));
    }
    
    public String padRight(int i) {
        return padRight(Integer.toString(i));
    }

}
