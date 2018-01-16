import algo.StringGraph;

import java.util.*;

public class LearnApp {
    public static void main(String[] args) {
        testDiamond();
        testArray();
        testMap();
    }

    private static void testDiamond() {
        StringGraph.drawDiamond(7);
        StringGraph.setForeground('+');
        StringGraph.drawDiamond(17);
        StringGraph.setForeground('$');
        StringGraph.drawDiamond(6);
        StringGraph.drawDiamond(1);
    }

    private static void testArray() {
        // Initialize a integer array.
        final int ARRAY_SIZE = 10;
        int[] integerArray = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; ++i) {
            integerArray[i] = (int)Math.round(Math.random() * 20);
        }

        // Sort array.
        java.util.Arrays.sort(integerArray);

        // Reverse array.
        for (int i = 0; i < integerArray.length / 2; ++i) {
            int temp = integerArray[i];
            integerArray[i] = integerArray[integerArray.length - 1 - i];
            integerArray[integerArray.length - 1 - i] = temp;
        }

        // Output the array.
        for (int item : integerArray) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(item);
            stringBuilder.append(" ");
            System.out.print(stringBuilder);
        }
        System.out.println();

        // Find the max number.
        int max = Integer.MIN_VALUE;
        for (int item : integerArray) {
            max = Math.max(max, item);
        }
        System.out.printf("The maximum is %d.\n", max);
    }

    private static void testMap() {
        Map<Integer, String> names = new HashMap<Integer, String>();
        names.put(1, "Michael che");
        names.put(1, "Michael Che");
        names.put(2, "Angy Yang");
        names.put(3, "Hello Kitty");
        for (int i = 1; i <= 3; ++i) {
            System.out.printf("No.%d is %s.\n", i, names.get(i));
        }
    }
}
