package tzeth.function;

/**
 * Represents a function that takes one argument and produces a result, or
 * throws an Exception.
 * 
 * @param <T>
 *            the type of the input to the function
 * @param <R>
 *            the type of the result of the function
 */
@FunctionalInterface
public interface ThrowingFunction<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t
     *            the function argument
     * @return the function result
     */
    R apply(T t) throws Exception;

}
