package deliveryapp.utils.functionalInterfaces;

@FunctionalInterface
public interface IEditString<T> {
    T append(T object1, T object2);
}
