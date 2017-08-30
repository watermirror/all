import algo.StringGraph;

public class LearnApp {
    public static void main(String[] args) {
        TestDiamond();
        TestArray();
    }

    private static void TestDiamond() {
        StringGraph.DrawDiamond(7);
        StringGraph.SetForeground('+');
        StringGraph.DrawDiamond(17);
        StringGraph.SetForeground('$');
        StringGraph.DrawDiamond(6);
        StringGraph.DrawDiamond(1);
    }

    private static void TestArray() {
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
    }
}
