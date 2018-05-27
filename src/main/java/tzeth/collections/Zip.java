package tzeth.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.BiConsumer;

public final class Zip {
    public static <T, U> void zip(Collection<T> c1, Collection<U> c2,
            BiConsumer<? super T, ? super U> consumer) {
        zip(c1.iterator(), c2.iterator(), consumer);
    }
    
    public static <T, U> void zip(Iterator<T> it1, Iterator<U> it2,
            BiConsumer<? super T, ? super U> consumer) {
        while (it1.hasNext() && it2.hasNext()) {
            consumer.accept(it1.next(), it2.next());
        }
    }
    
    private Zip() {/**/}

}
