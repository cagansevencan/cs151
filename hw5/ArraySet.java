import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

public class ArraySet implements IntSet
{
    private int modCount;
    private int nextIndex = 0;
    private boolean afterNext;


    private class ArraySetIterator implements Iterator<Integer>
    {
        public boolean hasNext() {
            if(nextIndex <= elementCount)
            {
                nextIndex++;
                return true;
            }
         else
            return false;
        }

        @Override
        public Integer next() {
            if(hasNext()) {
                return elements[nextIndex];
            }
        }

        @Override
        public void remove() {
        clear(nextIndex);
        }

        @Override
        public void forEachRemaining(Consumer<? super Integer> action) {

        }
    }

    public Iterator<Integer> iterator()
    {

        return new ArraySetIterator();
    }



    public boolean test(int n)
    {
        if (n < smallest || n > largest) return false;
        for (int i = 0; i < elementCount; i++)
            if (elements[i] == n) return true;
        return false;
    }

    public void clear(int n)
    {
        for (int i = 0; i < elementCount; i++)
            if (elements[i] == n)
            {
                elements[i] = elements[elementCount - 1];
                elementCount--;
                if (n == smallest)
                {
                    smallest = Integer.MAX_VALUE;
                    for (int k = 0; k < elementCount; k++)
                        smallest = Math.min(elements[k], smallest);
                }
                if (n == largest)
                {
                    largest = Integer.MIN_VALUE;
                    for (int k = 0; k < elementCount; k++)
                        largest = Math.max(elements[k], largest);
                }
            }
    }

    public void set(int n)
    {
        if (!test(n))
        {
            if (elements == null)
            {
                elements = new int[10];
            }
            else if (elements.length == elementCount)
            {
                elements = Arrays.copyOf(elements, 2 * elements.length);
            }
            elements[elementCount] = n;
            smallest = Math.min(smallest, n);
            largest = Math.max(largest, n);
            elementCount++;
        }
    }

    public int min()
    {
        return smallest;
    }

    public int max()
    {
        return largest;
    }

    public int size()
    {
        return elementCount;
    }

    int smallest = Integer.MAX_VALUE;
    int largest = Integer.MIN_VALUE;
    int[] elements;
    int elementCount;
}
