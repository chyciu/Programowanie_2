import org.junit.Assert;
import org.junit.Test;

public class MainSMSDwaTest {

    @Test
    public void smsPojedynczaSpacjaTest () {

        String expected = "AlaMaKota";
        String actual = mainSMSDwa.skrocSMS("ala ma kota");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public  void smsPodwojaSpacjaTest () {

        String expected = "JasiuJestDuzymChlopcem";
        String actual = mainSMSDwa.skrocSMS("Jasiu  jest  duzym chlopcem");
        Assert.assertEquals(expected, actual);
    }



}
