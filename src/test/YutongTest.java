package test;
import com.yutong.readParse.Indivdual;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class YutongTest extends TestCase {
    public YutongTest() {
        super();
    }
    public YutongTest(String name) {
        super(name);
    }
    public void setUp() throws Exception{

    }

    public void dates_before_current_date() throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date date = simpleDateFormat.parse(new String("24 SEP 1937"));
        boolean res = com.yutong.readParse.sprint1_Checkout.dates_Before_Current_Date(date);
        Assert.assertTrue("Alert: Dates (birth, death, marriage, divorce) should NOT be after the current date!",res==true);
    }

    public void birth_before_marriage() throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date birth = simpleDateFormat.parse(new String("24 SEP 1993"));
        Date marriage = simpleDateFormat.parse(new String("22 FEB 2002"));
        boolean res = com.yutong.readParse.sprint1_Checkout.birth_Before_Marriage(birth, marriage);
        Assert.assertTrue("Alert: Birth should occur before marriage of an individual!",res==true);
    }
    public static junit.framework.Test suite() {
        TestSuite s = new TestSuite();
        s.addTest(new YutongTest("dates_before_current_date"));
        s.addTest(new YutongTest("birth_before_marriage"));
        return s;
    }
}
