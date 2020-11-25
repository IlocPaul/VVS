import org.junit.*;

import static org.junit.Assert.*;


public class StringConvTest {

    StringConv a = new StringConv();
    StringConv array = new StringConv();
    int[] vector={0,1,2,3};
    int[] vector2 ={array.strToInt("0"),array.strToInt("1"),array.strToInt("2"),array.strToInt("3")};

    @BeforeClass
    public static void beforeClass()
    {
        System.out.println("Only one execution in the beginning - BeforeClass");
    }


    @AfterClass
    public static void afterClass()
    {
        System.out.println("Message at the end - AfterClass");
    }

    @Before
    public  void before()
    {
        System.out.println("Testing");
    }

    @After
    public  void after()
    {
        System.out.println("Done Testing");
    }



    @Test
    public void testAssert() {
        assertEquals("Correct",0,   a.strToInt("0"));
    }

    @Test
    public void testArray() {
        assertArrayEquals("Array correct",vector,  vector2);
    }

    @Test
    public void testSame() {
        assertSame("They are the same",1,   a.strToInt("1"));
    }

    @Test (timeout = 500)
    public void testNotSame() {
        assertNotSame("They are not the same",a.strToInt("8"),   a.strToInt("1"));
    }

    @Ignore
    @Test
    public void testIgnored() {
        assertEquals("We ignore",8,   a.strToInt("asdsadasdsa"));
    }

    @Test
    public void testNotNull() {
        assertNotNull("It's not null",   a.strToInt("0"));
    }

}