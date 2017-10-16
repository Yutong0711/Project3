package test;

import com.yutong.readParse.Indivdual;
import com.yutong.readParse.sprint1_Checkout;
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
    //done xudong;
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
    //done xudong;
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
    //done by chenlin
    public void marrigeBeforeDivorce () throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        /*String data1 = "24 Sep 1995";
        String data2 = "26 Sep 1996";*/
        Date marriage = simpleDateFormat.parse(new String("24 SEP 1995"));
        Date divorce = simpleDateFormat.parse(new String("22 FEB 2002"));
        boolean res = com.yutong.readParse.sprint1_Checkout.marrigeBeforeDivorce(marriage, divorce);
        Assert.assertTrue("it is false", res == true);
        return;
    }
    //done by chenlin
    public void divorceBeforeDeath() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        /*String data1 = "24 Sep 1995";
        String data2 = "26 Sep 1996";*/
        Date divorce = simpleDateFormat.parse(new String("24 SEP 1995"));
        Date husband = simpleDateFormat.parse(new String("22 FEB 2002"));
        Date wife = simpleDateFormat.parse(new String("17 SEP 2005"));
        boolean res1 = com.yutong.readParse.sprint1_Checkout.divorceBeforeDeath(divorce,husband);
        boolean res2 = com.yutong.readParse.sprint1_Checkout.divorceBeforeDeath(divorce,wife);
        boolean res = res1 == true || res2 == true;
        Assert.assertTrue("it is false", res);
    }
    //done by yutong
    public void dates_before_current_date() throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date date = simpleDateFormat.parse(new String("24 SEP 1937"));
        boolean res = com.yutong.readParse.sprint1_Checkout.dates_Before_Current_Date(date);
        System.out.println("test");
        Assert.assertTrue("Alert: Dates (birth, death, marriage, divorce) should NOT be after the current date!",res==true);
    }
    //done by yutong
    public void birth_before_marriage() throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date birth = simpleDateFormat.parse(new String("24 SEP 1993"));
        Date marriage = simpleDateFormat.parse(new String("22 FEB 2002"));
        boolean res = com.yutong.readParse.sprint1_Checkout.birth_Before_Marriage(birth, marriage);
        Assert.assertTrue("Alert: Birth should occur before marriage of an individual!",res==true);
    }
    public void test() throws ParseException
    {
        sprint1_Checkout testobj = new sprint1_Checkout();
        boolean output1=testobj.validate("12 asdfgf 1999");
        assertEquals(false,output1);
        boolean output2=testobj.validate("30 FEB 1995");
        assertEquals(false,output2);

    }
    public static junit.framework.Test suite() {
        TestSuite s = new TestSuite();
        s.addTest(new xuDongTest("Deathafterbirth"));
        s.addTest(new xuDongTest("marriage_After_Birth"));
        s.addTest(new chenglinTest("marrigeBeforeDivorce"));
        s.addTest(new chenglinTest("divorceBeforeDeath"));
        s.addTest(new YutongTest("dates_before_current_date"));
        s.addTest(new YutongTest("birth_before_marriage"));
        return s;
    }
}
