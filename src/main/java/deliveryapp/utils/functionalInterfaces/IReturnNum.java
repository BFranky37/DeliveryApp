package deliveryapp.utils.functionalInterfaces;

@FunctionalInterface
public interface IReturnNum<T> {
    int getItem(T item);
}
