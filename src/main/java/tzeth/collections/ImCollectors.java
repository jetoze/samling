package tzeth.collections;

import static java.util.Objects.requireNonNull;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public final class ImCollectors {

    public static <T> Collector<T, ?, ImmutableList<T>> toList() {
        Supplier<ImmutableList.Builder<T>> supplier = ImmutableList.Builder::new;
        BiConsumer<ImmutableList.Builder<T>, T> accumulator = (b, e) -> b.add(e);
        BinaryOperator<ImmutableList.Builder<T>> combiner = (l, r) -> l.addAll(r.build());
        Function<ImmutableList.Builder<T>, ImmutableList<T>> finisher = ImmutableList.Builder::build;
        return Collector.of(supplier, accumulator, combiner, finisher);
    }

    public static <T> Collector<T, ?, ImmutableSet<T>> toSet() {
        Supplier<ImmutableSet.Builder<T>> supplier = ImmutableSet.Builder::new;
        BiConsumer<ImmutableSet.Builder<T>, T> accumulator = (b, e) -> b.add(e);
        BinaryOperator<ImmutableSet.Builder<T>> combiner = (l, r) -> l.addAll(r.build());
        Function<ImmutableSet.Builder<T>, ImmutableSet<T>> finisher = ImmutableSet.Builder::build;
        return Collector.of(supplier, accumulator, combiner, finisher);
    }

    public static <K, V> Collector<V, ?, ImmutableMap<K, V>> toMap(Function<? super V, ? extends K> keyMapper) {
        return toMap(keyMapper, t -> t);
    }
    
    public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toMap(Function<? super T, ? extends K> keyMapper,
            Function<? super T, ? extends V> valueMapper) {
        requireNonNull(keyMapper);
        requireNonNull(valueMapper);
        Supplier<ImmutableMap.Builder<K, V>> supplier = ImmutableMap.Builder::new;
        BiConsumer<ImmutableMap.Builder<K, V>, T> accumulator = (b, t) -> 
            b.put(keyMapper.apply(t), valueMapper.apply(t));
        BinaryOperator<ImmutableMap.Builder<K, V>> combiner = (l, r) -> l.putAll(r.build());
        Function<ImmutableMap.Builder<K, V>, ImmutableMap<K, V>> finisher = ImmutableMap.Builder::build;
        return Collector.of(supplier, accumulator, combiner, finisher);
    }

    private ImCollectors() {/**/}

}
