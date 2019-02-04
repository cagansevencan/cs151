import java.util.ArrayList;

public class Lists {
    public static void swapLargestAndSecondSmallest(ArrayList<Integer> a) {
        int maxIndex = 0;
        int minIndex = 0;
        int secondIndex = 1;
        if (a.size() > 1) {
            if (a.get(0) > a.get(1)) {
                minIndex = 1;
                secondIndex = 0;
                if (a.size() < 3) {
                    maxIndex = secondIndex;
                }
            }

            for (int i = 2; i < a.size(); i++) {
                if (a.get(i) < a.get(minIndex)) {
                    secondIndex = minIndex;
                    minIndex = i;

                } else if (a.get(secondIndex) > a.get(i) && a.get(minIndex) != a.get(i)) {
                    secondIndex = i;

                }

                if (a.get(i) > a.get(maxIndex)) {
                    maxIndex = i;
                }
            }

            int m;

            if (maxIndex != minIndex) {
                m = a.get(secondIndex);
                a.set(secondIndex, a.get(maxIndex));
                a.set(maxIndex, m);
            }
        }

    }
}

