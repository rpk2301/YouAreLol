package rocks.zipcode;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpClientAppTest {


    @Test
    void TestString()
    {
        HttpClientApp p = new HttpClientApp();
        String in = "wefwerferth]wefewrgerg}ergergerg}ergerger}";
        String actual = p.delimitByBracket(in);
        Assert.assertEquals(actual," ");
    }







}