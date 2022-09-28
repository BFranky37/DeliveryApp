package deliveryapp.utils.fileUtils;

import java.text.ParseException;

public interface XmlAdapter<T, U> {
    public T unmarshall(U element) throws ParseException;

    public U marshall(T element);
}
