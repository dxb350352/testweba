import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFormat {

    public static void main(String[]args){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        try {
            System.out.println(sdf.format(sdf.parse("2018-21-21")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.setLenient(false);
        try {
            System.out.println(sdf.format(sdf.parse("2018-21-21")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
