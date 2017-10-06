package test;

import com.yutong.readParse.Indivdual;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class xuDongTest extends TestCase {

    public xuDongTest() {
        super();
    }
    public xuDongTest(String name) {
        super(name);
    }
    public void setUp() throws Exception{

    }
    public void Deathafterbirth () throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        /*String data1 = "24 Sep 1995";
        String data2 = "26 Sep 1996";*/
        Date date1 = simpleDateFormat.parse(new String("24 SEP 1995"));
        Date date2 = simpleDateFormat.parse(new String("22 FEB 2002"));
        boolean res = com.yutong.readParse.sprint1_Checkout.death_After_Birth(date1, date2);
        Assert.assertTrue("it is false", res == true);
        return;
    }
    public void marriage_After_Birth() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        /*String data1 = "24 Sep 1995";
        String data2 = "26 Sep 1996";*/
        Date date1 = simpleDateFormat.parse(new String("24 SEP 1995"));
        Date date2 = simpleDateFormat.parse(new String("22 FEB 2002"));
        Date date3 = simpleDateFormat.parse(new String("17 SEP 2005"));
        boolean res = com.yutong.readParse.sprint1_Checkout.marriage_After_Birth(date1, date2, date3);
        Assert.assertTrue("it is false", res == true);
    }
    public static junit.framework.Test suite() {
        TestSuite s = new TestSuite();
        s.addTest(new xuDongTest("Deathafterbirth"));
        s.addTest(new xuDongTest("marriage_After_Birth"));
        return s;
    }
}
