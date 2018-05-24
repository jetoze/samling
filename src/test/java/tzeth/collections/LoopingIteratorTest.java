package tzeth.collections;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

/**
 * Unit tests for LoopingIterator.
 */
public final class LoopingIteratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void mustProvideAtLeastOneValueViaVarArg() {
        LoopingIterator.of();
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustProvideAtLeastOneValueViaCollection() {
        LoopingIterator.of(Collections.emptyList());
    }
    
    @Test
    public void singleValueViaVarArg() {
        String value = "hello";
        verifySingleton(LoopingIterator.of(value), value);
    }

    @Test
    public void singleValueViaCollection() {
        String value = "hello";
        verifySingleton(LoopingIterator.of(Collections.singleton(value)), value);
    }
    
    private void verifySingleton(LoopingIterator<String> it, String value) {
        assertEquals(
                Arrays.asList(value, value, value),
                it.toStream().limit(3).collect(toList()));
    }
    
    @Test(expected = NullPointerException.class)
    public void rejectSingleNullValueViaVarArg() {
        LoopingIterator.of((String) null);
    }
    
    @Test(expected = NullPointerException.class)
    public void rejectSingleNullValueViaCollection() {
        LoopingIterator.of(Arrays.asList((String) null));
    }
    
    @Test
    public void twoValuesViaVarArg() {
        String value1 = "hello";
        String value2 = "world";
        verifyTwoValueIterator(LoopingIterator.of(value1, value2), value1, value2);
    }
    
    @Test
    public void twoValuesViaCollection() {
        String value1 = "hello";
        String value2 = "world";
        verifyTwoValueIterator(LoopingIterator.of(Arrays.asList(value1, value2)), value1, value2);
    }
    
    private void verifyTwoValueIterator(LoopingIterator<String> it, String value1, String value2) {
        assertEquals(
                Arrays.asList(value1, value2, value1, value2, value1),
                it.toStream().limit(5).collect(toList()));
    }
    
    @Test(expected = NullPointerException.class)
    public void rejectNullValueViaTwoElementVarArg() {
        LoopingIterator.of("a", (String) null);
    }

    @Test
    public void multipleValuesViaVarArg() {
        String value1 = "once";
        String value2 = "upon";
        String value3 = "a";
        String value4 = "time";
        verifyMultipleValueIterator(LoopingIterator.of(value1, value2, value3, value4), 
                value1, value2, value3, value4);
    }
    
    @Test
    public void multipleValuesViaCollection() {
        String value1 = "once";
        String value2 = "upon";
        String value3 = "a";
        String value4 = "time";
        verifyMultipleValueIterator(LoopingIterator.of(Arrays.asList(value1, value2, value3, value4)), 
                value1, value2, value3, value4);
    }
    
    private void verifyMultipleValueIterator(LoopingIterator<String> it, 
            String value1, String value2, String value3, String value4) {
        assertEquals(
                Arrays.asList(value1, value2, value3, value4, value1, value2, value3, value4, value1),
                it.toStream().limit(9).collect(toList()));
    }

}
