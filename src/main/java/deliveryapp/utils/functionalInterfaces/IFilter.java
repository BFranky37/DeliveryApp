package deliveryapp.utils.functionalInterfaces;

@FunctionalInterface
public interface IFilter<T, R> {
    R filter(T item);
}
