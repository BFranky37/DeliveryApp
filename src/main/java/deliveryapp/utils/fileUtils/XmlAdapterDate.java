package deliveryapp.utils.fileUtils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class XmlAdapterDate implements XmlAdapter<Date, String> {
    @Override
    public Date unmarshall(String date) throws ParseException {
        Date sqlDate= (Date) new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date);
        return sqlDate;
    }

    @Override
    public String marshall(Date element) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(element);
        return strDate;
    }
}
