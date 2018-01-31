import algo.StringGraph;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class LearnApp {
    public static void main(String[] args) {
        testDiamond();
        testArray();
        testMap();
        // testSwing();
        testTread();
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

    private static void testSwing() {
        EventQueue.invokeLater(LearnApp::showSwingTestView);
    }

    private static void showSwingTestView() {
        SimpleViewFrame frame = new SimpleViewFrame();
        frame.setVisible(true);
    }

    private static void testTread() {
        DataStore store = new DataStore();
        Runnable r = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Current value : " + store.getData());
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
        Thread t = new Thread(r);
        t.setDaemon(true);
        t.start();

        for (int i = 0; i < 10; ++i) {
            store.setData(store.getData() + 1);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class DataStore {
    private int data;

    int getData() { return data; }

    void setData(int newData) { data = newData; }
}
