package tzeth.collections;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import tzeth.collections.ImCollectors;

public class ImCollectorsTest {

	@Test
	public void testToList() {
		List<String> values = Arrays.asList("apple", "banana", "avocado");
		ImmutableList<String> result = values.stream()
				.filter(s -> s.startsWith("a"))
				.collect(ImCollectors.toList());
		assertEquals(ImmutableList.of("apple", "avocado"), result);
	}

	@Test
	public void testToSet() {
		List<String> values = Arrays.asList("apple", "banana", "avocado", "apple");
		ImmutableSet<String> result = values.stream()
				.filter(s -> s.startsWith("a"))
				.collect(ImCollectors.toSet());
		assertEquals(ImmutableSet.of("apple", "avocado", "apple"), result);
	}

}
