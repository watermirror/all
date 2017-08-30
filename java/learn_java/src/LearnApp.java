import algo.StringGraph;

public class LearnApp {
    public static void main(String[] args) {
        testDiamond();
        testArray();
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
}
