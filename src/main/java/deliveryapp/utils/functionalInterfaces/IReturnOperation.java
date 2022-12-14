package deliveryapp.utils.functionalInterfaces;

@FunctionalInterface
public interface IReturnOperation<T> {
    T returnResult(T object1, T object2);
}
