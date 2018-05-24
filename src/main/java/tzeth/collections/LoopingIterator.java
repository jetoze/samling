package tzeth.collections;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.google.common.collect.ImmutableList;

/**
 * Iterator that loops endlessly over a given sequence of values, starting over
 * from the beginning of the sequence once it's reached the end. {@code null} values are not supported.
 */
public abstract class LoopingIterator<T> implements Iterator<T> {

    private LoopingIterator() {/**/}

    public static <T> LoopingIterator<T> of(Collection<? extends T> values) {
        checkArgument(values.size() > 0, "Must provide at least one value");
        return new Sequence<>(values);
    }
    
    @SafeVarargs
    public static <T> LoopingIterator<T> of(T... values) {
        switch (values.length) {
        case 0:
            throw new IllegalArgumentException("Must provide at least one value");
        case 1:
            return new Singleton<>(values[0]);
        case 2:
            return new Alternating<>(values[0], values[1]);
        default:
            return new Sequence<>(values);
        }
    }
    
    /**
     * Always returns {@code true}.
     */
    @Override
    public final boolean hasNext() {
        return true;
    }
    
    /**
     * Returns a Stream representation of this iterator.
     * <p>
     * <strong>Note</strong>: Since the iterator is endless, this stream will be too.
     * It should typically be {@link Stream#limit(long) limited} when used.
     */
    public Stream<T> toStream() {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(this, Spliterator.ORDERED),
                false);
    }
    
    
    private static class Singleton<T> extends LoopingIterator<T> {
        private final T value;
        
        public Singleton(T value) {
            this.value = requireNonNull(value);
        }

        @Override
        public T next() {
            return this.value;
        }
    }
    
    
    private static class Alternating<T> extends LoopingIterator<T> {
        private final T a;
        private final T b;
        private T next;
        
        public Alternating(T a, T b) {
            this.a = requireNonNull(a);
            this.b = requireNonNull(b);
            this.next = a;
        }
        
        @Override
        public T next() {
            T t = this.next;
            this.next = (this.next == a)
                    ? b
                    : a;
            return t;
        }
    }
    
    
    private static class Sequence<T> extends LoopingIterator<T> {
        private final ImmutableList<T> values;
        private int index;
        
        public Sequence(Collection<? extends T> values) {
            this.values = ImmutableList.copyOf(values);
        }
        
        @SafeVarargs
        public Sequence(T... values) {
            this.values = ImmutableList.copyOf(values);
        }
        
        @Override
        public T next() {
            T t = this.values.get(index);
            ++index;
            if (index == this.values.size()) {
                index = 0;
            }
            return t;
        }
    }
    
}
