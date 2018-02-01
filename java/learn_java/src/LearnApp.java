import algo.StringGraph;

import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LearnApp {
    public static void main(String[] args) {
        // testDiamond();
        // testArray();
        // testMap();
        // testSwing();
        // testTread();
        // testTime();
        //testLambda();
        testEqual();
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
        Arrays.sort(integerArray);

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
        Map<Integer, String> names = new HashMap<>();
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

    private static void testTime() {
        System.out.println("*************************\n" +
                           "  Test Time\n" +
                           "*************************");

        Instant now = Instant.now();
        System.out.println("Now : " + now);

        Instant lastInstant = Instant.MAX;
        System.out.println("The last instant of world : " + lastInstant);

        System.out.println("Local date of now : " + LocalDate.now());

        System.out.println("Local time of now : " + LocalTime.now());

        System.out.println("Local date-time of now : " + LocalDateTime.now());

        LocalDate[] birthDays = {
                LocalDate.of(1954, 9, 13),
                LocalDate.of(1960, 9, 6),
                LocalDate.of(1985, 7, 31),
                LocalDate.of(1989, 11, 28) };

        for (LocalDate birthDay : birthDays) {
            Period age = birthDay.until(LocalDate.now());
            System.out.printf("Age is %d years %d months and %d days.\n",
                    age.getYears(),
                    age.getMonths(),
                    age.getDays());
        }

        Set<String> zones = ZoneId.getAvailableZoneIds();
        System.out.println("Count of time zones : " + zones.size());
        System.out.println(LocalDateTime.now().atZone(ZoneId.of("PRC")));

        String[] zoneNames = {"PRC", "Etc/GMT+8", "GMT"};
        ArrayList<ZoneId> zoneIds = new ArrayList<>();

        for (String zoneName : zoneNames) {
            zoneIds.add(ZoneId.of(zoneName));
        }
        zoneIds.add(ZoneId.ofOffset("GMT", ZoneOffset.ofHours(8)));

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss O");
        for (ZoneId zoneId : zoneIds) {
            ZonedDateTime zonedNow = now.atZone(zoneId);
            System.out.println(zonedNow.format(fmt));
        }
    }

    private static void testLambda() {
        // Prepare callback.
        SomeCallback callback = (result) -> onTaskDone(2, result);

        // Post a task.
        postTask(() -> doTask(30, callback));
        postTask(() -> doTask(40, LearnApp::onTaskDone));
    }

    private static void doTask(int p, SomeCallback callback) {
        System.out.println("Do task with " + p + ".");
        callback.call(p * 2);
    }

    private static void onTaskDone(int id, int twice) {
        System.out.println("Callback with Id=" + id + ", twice=" + twice + ".");
    }

    private static void onTaskDone(int twice) {
        System.out.println("Callback with twice=" + twice + ".");
    }

    private static void postTask(Runnable task) {
        task.run();
    }

    private static void testEqual() {
        // String a = new String("Hello");
        // String b = new String("Hello");
        DataStore a = new DataStore(25);
        DataStore b = new DataStore(25);
        System.out.println("a == b : " + (a == b));
        System.out.println("a.equals(b) : " + a.equals(b));
    }
}

class DataStore {
    private int data;

    DataStore() {}

    DataStore(int init) { data = init; }

    int getData() { return data; }

    void setData(int newData) { data = newData; }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        DataStore otherObj = (DataStore) other;
        return data == otherObj.data;
    }
}

interface SomeCallback {
    void call(int twice);
}
