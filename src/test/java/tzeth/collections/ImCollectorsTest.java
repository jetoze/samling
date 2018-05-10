package tzeth.collections;

import static org.junit.Assert.assertEquals;
import static tzeth.collections.ImCollectors.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class ImCollectorsTest {

    @Test
    public void testToList() {
        List<String> values = Arrays.asList("apple", "banana", "avocado");
        ImmutableList<String> result = values.stream()
                .filter(s -> s.startsWith("a"))
                .collect(toList());
        assertEquals(ImmutableList.of("apple", "avocado"), result);
    }

    @Test
    public void testToSet() {
        List<String> values = Arrays.asList("apple", "banana", "avocado", "apple");
        ImmutableSet<String> result = values.stream()
                .filter(s -> s.startsWith("a"))
                .collect(toSet());
        assertEquals(ImmutableSet.of("apple", "avocado", "apple"), result);
    }
    
    @Test
    public void testToMap() {
        List<String> values = Arrays.asList("a", "bb", "ccc");
        ImmutableMap<Integer, String> result = values.stream()
                .collect(toMap(String::length, String::toUpperCase));
        assertEquals(ImmutableMap.of(1, "A", 2, "BB", 3, "CCC"), result);
    }

}
