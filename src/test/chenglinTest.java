//package test;
//
//import org.junit.Test;
//import org.junit.Ignore;
//import static org.junit.Assert.assertTrue;
///**
// * Created by chenglinwu on 06/10/2017.
// */
//public class chenglinTest {
//    @Test
//    public static void marrigeBeforeDivorce () throws ParseException {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
//        /*String data1 = "24 Sep 1995";
//        String data2 = "26 Sep 1996";*/
//        Date date1 = simpleDateFormat.parse(new String("24 SEP 1995"));
//        Date date2 = simpleDateFormat.parse(new String("22 FEB 2002"));
//        boolean res = com.yutong.readParse.sprint1_Checkout.death_After_Birth(date1, date2);
//        assertTrue("it is false", res == true);
//    }
//    public void divorceBeforeDeath() throws ParseException {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
//        /*String data1 = "24 Sep 1995";
//        String data2 = "26 Sep 1996";*/
//        Date date1 = simpleDateFormat.parse(new String("24 SEP 1995"));
//        Date date2 = simpleDateFormat.parse(new String("22 FEB 2002"));
//        Date date3 = simpleDateFormat.parse(new String("17 SEP 2005"));
//        boolean res = com.yutong.readParse.sprint1_Checkout.marriage_After_Birth(date1, date2, date3);
//        assertTrue("it is false", res == true);
//    }
//
//    @RunWith(Suite.class)
//
//    @Suite.SuiteClasses({
//            TestJunit1.class,
//            TestJunit2.class
//    })
//    public static junit.framework.Test suite() {
//        TestSuite s = new TestSuite();
//        s.addTest(new chenglinTest("marrigeBeforeDivorce"));
//        s.addTest(new chenglinTest("divorceBeforeDeatrh"));
//        return s;
//    }


package test;

import com.yutong.readParse.Indivdual;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class chenglinTest extends TestCase {

    public chenglinTest() {
        super();
    }
    public chenglinTest(String name) {
        super(name);
    }
    public void setUp() throws Exception{

    }
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
    public static junit.framework.Test suite() {
        TestSuite s = new TestSuite();
        s.addTest(new chenglinTest("marrigeBeforeDivorce"));
        s.addTest(new chenglinTest("divorceBeforeDeath"));
        return s;
    }
}