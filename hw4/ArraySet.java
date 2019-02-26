import java.util.Arrays;

public class ArraySet implements IntSet {
    // These are left package visible so they can be accessed in a unit test
    int smallest = Integer.MAX_VALUE;
    int largest = Integer.MIN_VALUE;
    int[] elements;
    int elementCount;

    IntSet set = new BitSet();


    public boolean test(int n) {
        for (int i = 0; i <= elements.length-1; i++)
            if (elements[i] == n) {
                return true;
            }
        return false;
    }

    public void clear(int n) {
       for(int i = elements.length-1; i >= 0; i--)
       {

           if(elements[i] == n)
           {
               elementCount--;
               elements[i] = elements[elements.length-1];
           }
           if(elements[i] > largest)
           {
               largest = elements[i];
           }
           if(elements[i] == largest)
           {
               smallest = largest;
           }
           if(elements[i] < smallest && elements[i] != 0)
           {
               smallest = elements[i];
           }

       }




    }

    public void set(int n) {
        if (elementCount == 0) {
            elements = new int[10];
            elements[elementCount] = n;
            elementCount++;
            smallest = n;
            largest = n;

        } else if (elementCount < elements.length) {
            elements[elementCount] = n;
            elementCount++;
            if (n < smallest) {
                smallest = n;
            } else if (n > largest) {
                largest = n;
            }
        } else {
            elements = Arrays.copyOf(elements, elements.length * 2);
            elements[elementCount] = n;
            elementCount++;
        }

    }

    // Don't change any of these (but add javadoc)

    public int min() {
        return smallest;
    }

    public int max() {
        return largest;
    }

    public int size() {
        return elementCount;
    }


}