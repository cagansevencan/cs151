import java.util.Arrays;

public class BitSet implements IntSet {
    // These are left package visible so they can be accessed in a unit test
    int[] elements;
    int start;
    int elementCount;

    public BitSet() {
        elementCount =0;
        elements = new int[10];
        start = 0;
    }

    public boolean test(int n) {
        for (int i = 0; i <= elements.length; i++)
            if (test(i,n)) {
                return true;
            }
        return false;

    }

    public void set(int n) {
        int number;
        int exp =0;
        int exp2 = 0;
        int bitLocation;
        if (elementCount == 0) {

            elements[0] += 1;
            elementCount++;
        }

        else
        {


            int index;
            int bitValue;
            index =  (n-start)/32;
            bitLocation = (n-start) % 32;
            bitValue = n - start - (index*32);
            exp = (int) Math.pow(2, bitValue);

           elements[index] += bitValue;
            elementCount++;



        }


    }

    public void clear(int n) {
        int index;
        int bitValue;
        index =  (n-start)/32;
        bitValue = (n-start) % 32;
        elements[index] =- bitValue;
        elementCount--;
    }

    public int min() {
        return Integer.MIN_VALUE;
    }

    public int max() {
        return Integer.MAX_VALUE;
    }

    // Don't change any of these (but add javadoc)

    public int size() {
        return elementCount;
    }

    private static boolean test(int n, int i) {
        assert 0 <= i && i < 32;
        return (n & (1 << i)) != 0;
    }

    private static int set(int n, int i) {
        assert 0 <= i && i < 32;
        return n | (1 << i);
    }

    private static int clear(int n, int i) {
        assert 0 <= i && i < 32;
        return n & ~(1 << i);
    }


}