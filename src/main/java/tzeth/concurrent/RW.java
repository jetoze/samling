package tzeth.concurrent;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

/**
 * Utilities around using a read-write lock.
 */
public final class RW {
    private final Lock lock;

    public RW(Lock lock) {
        this.lock = checkNotNull(lock);
    }
    
    public static RW read(ReadWriteLock rw) {
        return new RW(rw.readLock());
    }
    
    public static RW write(ReadWriteLock rw) {
        return new RW(rw.writeLock());
    }

    public void run(Runnable r) {
        checkNotNull(r);
        try (Block b = Block.lock(lock)) {
            r.run();
        }
    }

    public <T> T get(Supplier<T> s) {
        checkNotNull(s);
        try (Block b = Block.lock(lock)) {
            return s.get();
        }
    }
    
    public <T> boolean test(@Nullable T value, Predicate<? super T> filter) {
        checkNotNull(filter);
        try (Block b = Block.lock(lock)) {
            return filter.apply(value);
        }
    }
    
    public <T> void consume(@Nullable T value, Consumer<? super T> consumer) {
        checkNotNull(consumer);
        try (Block b = Block.lock(lock)) {
            consumer.accept(value);
        }
    }
    
    public <R, T> R apply(@Nullable T value, Function<? super T, R> function) {
        checkNotNull(function);
        try (Block b = Block.lock(lock)) {
            return function.apply(value);
        }
    }
    
    public <T> T call(Callable<T> c) throws Exception {
        checkNotNull(c);
        try (Block b = Block.lock(lock)) {
            return c.call();
        }
    }

    public void doOp(Operation op) throws Exception {
        checkNotNull(op);
        try (Block b = Block.lock(lock)) {
            op.perform();
        }
    }

    
    private static final class Block implements AutoCloseable {
        private final Lock lock;

        public static Block lock(Lock lock) {
            lock.lock();
            return new Block(lock);
        }

        public Block(Lock lock) {
            this.lock = lock;
        }

        public void close() {
            lock.unlock();
        }
    }

    @FunctionalInterface
    public static interface Operation {
        public void perform() throws Exception;
    }

}
