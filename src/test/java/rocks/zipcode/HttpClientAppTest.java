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


    @Test
    void extractMessage() {

        String[] incoming = {"wef", "ewrgerg", "ergerg", "'this", "is", "my", "message'", "wefwegf"};
        String expected = "'this is my message'";
        String actual = HttpClientApp.ExtractMessage(incoming);
        Assert.assertEquals(expected,actual);


    }
}