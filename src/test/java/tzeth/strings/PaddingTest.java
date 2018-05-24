package tzeth.strings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.base.Strings;

/**
 * Unit tests for Padding.
 */
public final class PaddingTest {

    @Test
    public void testLeftPad() {
        String s = "hello";
        int n = 20;
        
        String padded = Padding.of(n).padLeft(s);

        String expected = Strings.repeat(" ", n - s.length()) + s;
        assertEquals(expected, padded);
    }
    
    @Test
    public void testLeftPadOfEmptyString() {
        int n = 20;
        
        String padded = Padding.of(n).padLeft("");
        
        assertEquals(Strings.repeat(" ", n), padded);
    }
    
    @Test
    public void testLeftPadOfLongerString() {
        String s = "hello";
        int n = 2;
        
        String padded = Padding.of(n).padLeft(s);
        
        assertEquals(s, padded);
    }
    

    @Test
    public void testRightPad() {
        String s = "hello";
        int n = 20;
        
        String padded = Padding.of(n).padRight(s);

        String expected = s + Strings.repeat(" ", n - s.length());
        assertEquals(expected, padded);
    }
    
    @Test
    public void testRightPadOfEmptyString() {
        int n = 20;
        
        String padded = Padding.of(n).padRight("");
        
        assertEquals(Strings.repeat(" ", n), padded);
    }
    
    @Test
    public void testRightPadOfLongerString() {
        String s = "hello";
        int n = 2;
        
        String padded = Padding.of(n).padRight(s);
        
        assertEquals(s, padded);
    }
}
