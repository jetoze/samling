package tzeth.function;

/**
 * Represents an operation that accepts a single input argument and returns no
 * result, but may throw an Exception. Unlike most other functional interfaces,
 * {@code ThrowingConsumer} is expected to operate via side-effects.
 *
 * @param <T>
 *            the type of the input to the operation
 * 
 */
@FunctionalInterface
public interface ThrowingConsumer<T> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t) throws Exception;

}
