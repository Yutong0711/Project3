package test;

import com.yutong.readParse.*;
import junit.framework.TestCase;
import org.junit.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dishaTest extends TestCase
{
    public void test() throws ParseException
    {
        sprint1_Checkout testobj = new sprint1_Checkout();
        boolean output1=testobj.validate("12 asdfgf 1999");
        assertEquals(false,output1);
        boolean output2=testobj.validate("30 FEB 1995");
        assertEquals(false,output2);


    }

}
