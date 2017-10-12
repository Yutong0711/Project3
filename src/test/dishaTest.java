package test;

import com.yutong.readParse.Parse;
import junit.framework.TestCase;
import org.junit.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dishaTest extends TestCase
{
    public void test() throws ParseException
    {
        Parse testobj = new Parse();
        boolean output1=testobj.validate("12 asdfgf 1999");
        assertEquals(false,output1);
        boolean output2=testobj.validate("30 FEB 1995");
        assertEquals(false,output2);


    }

}
