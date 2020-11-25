import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.*;


import static org.junit.jupiter.api.Assertions.*;

public class VVSDateTest {

    VVSDate data1 = new VVSDate(1,1,2019);
    VVSDate data2 = new VVSDate(1,3,2019);

    VVSDate data3 = new VVSDate(1,1,2020);
    VVSDate data4 = new VVSDate(1,3,2020);

    VVSDate data5 = new VVSDate(1,1,2020);
    VVSDate data6 = new VVSDate(31,12,2020);

    VVSDate data7 = new VVSDate(2,4,2020);
    VVSDate data8 = new VVSDate(10,3,2021);


    @Test
    public void testFebruarieNonBisect()
    {
        assertEquals("Correct",59 ,  data2.getDays(data1));
    }

    @Test
    public void testFebruarieBisect()
    {
        assertEquals("Correct",60 ,  data4.getDays(data3));
    }

    @Test
    public void testAn()
    {
        assertEquals("Correct",365,  data6.getDays(data5));
    }

    @Test
    public void testDoiAni()
    {
        assertEquals("Correct",342,  data8.getDays(data7));
    }


}