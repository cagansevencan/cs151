import static org.junit.jupiter.api.Assertions.*;
import org.testng.annotations.Test;

public class SetTest
{
    @Test public void setAndClear()
    {
        IntSet set = new ArraySet();
        set.set(100);
        set.set(150);
        set.clear(100);
        assertEquals(150, set.min());
        assertEquals(150, set.max());
        assertEquals(1, set.size());
        assertTrue(set.test(150));
        assertFalse(set.test(100));
    }
    @Test public void elementOrderRemove()
    {
        ArraySet set = new ArraySet();
        set.set(40);
        set.set(110);
        set.set(90);
        set.set(70);
        set.set(100);
        set.set(80);
        set.clear(110);
        assertEquals(40, set.elements[0]);
        assertEquals(80, set.elements[1]);
        assertEquals(90, set.elements[2]);
        assertEquals(100, set.largest);
    }

    @Test public void testOneElement()
    {
        IntSet set = new BitSet();
        set.set(100);
        set.set(102);
        //assertEquals(100, set.min());
        //assertEquals(100, set.max());
        //assertEquals(1, set.size());
        //assertTrue(set.test(100));
        //assertFalse(set.test(99));
    }

    @Test public void testBits()
    {
        BitSet set = new BitSet();
        set.set(100);
        set.set(102);
        set.clear(100);
        assertEquals(4, set.elements[0]);
        assertEquals(100, set.start);
    }
}
