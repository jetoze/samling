package tzeth.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public final class ZipTest {

    @Test
    public void sameSizedCollections() {
        StringBuilder output = new StringBuilder();
        List<String> a = Arrays.asList("A", "B", "C");
        List<String> b = Arrays.asList("a", "b", "c");
        Zip.zip(a, b, (s1, s2) -> output.append(s1).append(s2));
        
        assertEquals("AaBbCc", output.toString());
    }
    
    @Test
    public void firstCollectionIsSmaller() {
        StringBuilder output = new StringBuilder();
        List<String> a = Arrays.asList("A", "B");
        List<String> b = Arrays.asList("a", "b", "c");
        Zip.zip(a, b, (s1, s2) -> output.append(s1).append(s2));
        
        assertEquals("AaBb", output.toString());
    }
    
    @Test
    public void secondCollectionIsSmaller() {
        StringBuilder output = new StringBuilder();
        List<String> a = Arrays.asList("A", "B");
        List<String> b = Arrays.asList("a");
        Zip.zip(a, b, (s1, s2) -> output.append(s1).append(s2));
        
        assertEquals("Aa", output.toString());
    }
    
    @Test
    public void oneCollectionIsEmpty() {
        StringBuilder output = new StringBuilder();
        List<String> a = Arrays.asList();
        List<String> b = Arrays.asList("a", "b", "c");
        Zip.zip(a, b, (s1, s2) -> fail("The consumer should not have been called"));
        assertEquals("", output.toString());
    }

}
