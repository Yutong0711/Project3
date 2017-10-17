package test;

import com.yutong.readParse.Family;
import com.yutong.readParse.Indivdual;
import com.yutong.readParse.sprint1_Checkout;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        String name = "name";
        boolean res = com.yutong.readParse.sprint1_Checkout.death_After_Birth(date1, date2, name);
        Assert.assertTrue("it is false information of " + name , res == true);
        return;
    }
    //done xudong;
    public void marriage_Before_Death() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        /*String data1 = "24 Sep 1995";
        String data2 = "26 Sep 1996";*/
        Date date1 = simpleDateFormat.parse(new String("24 SEP 2008"));
        Date date2 = simpleDateFormat.parse(new String("22 FEB 2008"));
        Date date3 = simpleDateFormat.parse(new String("17 SEP 2005"));
        String wife_name = "XXX";
        String Husband_name = "xxx";
        boolean res1 = com.yutong.readParse.sprint1_Checkout.marriage_Before_Death(date1, date3,Husband_name);
        boolean res2 = com.yutong.readParse.sprint1_Checkout.marriage_Before_Death(date2, date3,wife_name);
        Assert.assertTrue("it is true", (res1 && res2) == true);
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
        String id = "fdsafd";
        boolean res1 = com.yutong.readParse.sprint1_Checkout.divorceBeforeDeath(divorce,husband,id);
        boolean res2 = com.yutong.readParse.sprint1_Checkout.divorceBeforeDeath(divorce,wife,id);
        boolean res = (res1 == true && res2 == true);
        Assert.assertTrue("it is false", res == false);
    }
    //done by yutong
    public void dates_before_current_date() throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date date = simpleDateFormat.parse(new String("24 SEP 1937"));
        String ID = "hfudilabfd";
        boolean res = com.yutong.readParse.sprint1_Checkout.dates_Before_Current_Date(date, ID);
        System.out.println("test");
        Assert.assertTrue("Alert: Dates (birth, death, marriage, divorce) should NOT be after the current date!",res==true);
    }

    //done by yutong
    public void birth_before_marriage() throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date birth = simpleDateFormat.parse(new String("24 SEP 1993"));
        Date marriage = simpleDateFormat.parse(new String("22 FEB 2002"));
        String ID = "hfudilabfd";
        boolean res = com.yutong.readParse.sprint1_Checkout.birth_Before_Marriage(birth, marriage, ID);
        Assert.assertTrue("Alert: Birth should occur before marriage of an individual!",res==true);
    }


    public void No_bigamy() throws ParseException {
        Family family1 = new Family();
        Family family2 = new Family();
        family1.setHusbandID("IN01");
        family2.setHusbandID("IN02");
        family1.setWifeID("IN03");
        family2.setWifeID("IN04");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date divorce = simpleDateFormat.parse(new String("24 SEP 1991"));
        Date marriage = simpleDateFormat.parse(new String("22 FEB 1990"));
        family1.setMarried(marriage);
        family1.setDivorced(divorce);
        Date divorce1 = simpleDateFormat.parse(new String("24 SEP 2008"));
        Date marriage1 = simpleDateFormat.parse(new String("22 FEB 1992"));
        family1.setMarried(marriage1);
        family1.setDivorced(divorce1);
        List<Family> familyList = new ArrayList<>();
        familyList.add(family1);
        familyList.add(family2);
        boolean res = com.yutong.readParse.sprint1_Checkout.No_bigamy("IN04",familyList);
        Assert.assertTrue("Alert: Birth should occur before marriage of an individual!",res==true);
    }

    public void isValid() throws ParseException
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date birth = simpleDateFormat.parse(new String("24 SEP 1993"));
        Date marriage = simpleDateFormat.parse(new String("22 FEB 2002"));

        boolean output1= com.yutong.readParse.sprint1_Checkout.isValidDate("30 FEB 1995");
        assertEquals(false,output1);

    }
    public static junit.framework.Test suite() {
        TestSuite s = new TestSuite();
        s.addTest(new xuDongTest("Deathafterbirth"));
        s.addTest(new xuDongTest("marriage_Before_Death"));
        s.addTest(new xuDongTest("marrigeBeforeDivorce"));
        s.addTest(new xuDongTest("divorceBeforeDeath"));
        s.addTest(new xuDongTest("dates_before_current_date"));
        s.addTest(new xuDongTest("birth_before_marriage"));
        s.addTest(new xuDongTest("No_bigamy"));
        s.addTest(new xuDongTest("isValid"));
        return s;
    }
}
