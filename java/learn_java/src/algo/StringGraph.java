package algo;

public class StringGraph {
    private static char backChar = ' ';
    private static char foreChar = '*';

    public static void SetBackground(char backChar) {
        StringGraph.backChar = backChar;
    }

    public static void SetForeground(char foreChar) {
        StringGraph.foreChar = foreChar;
    }

    public static boolean DrawDiamond(int size) {
        if (size <= 0) {
            return false;
        }
        boolean oddness = (size % 2 != 0);
        int half_size = size / 2;
        DrawTriangle(half_size, oddness ? 1 : 0, true);
        if (oddness) {
            DrawHorizontalLine(0, size);
        }
        DrawTriangle(half_size, oddness ? 1 : 0, false);
        return true;
    }

    private static boolean DrawTriangle(int height, int leftMargin, boolean upIsUp) {
        // Check legality of parameters.
        if (height < 1 || leftMargin < 0) {
            return false;
        }

        // Prepare variables.
        int backCount = upIsUp ? height - 1 : 0;
        int backStep = upIsUp ? -1 : 1;
        int foreCount = upIsUp ? 1 : height * 2 - 1;
        int foreStep = upIsUp ? 2 : -2;

        // Draw triangle.
        for (int row = 0; row < height; ++row) {
            // Draw margin first.
            if (leftMargin > 0) {
                for (int i = 0; i < leftMargin; ++i) {
                    System.out.print(StringGraph.backChar);
                }
            }

            // Draw background chars.
            for (int i = 0; i < backCount; ++i) {
                System.out.print(StringGraph.backChar);
            }

            // Draw foreground chars.
            for (int i = 0; i < foreCount; ++i) {
                System.out.print(StringGraph.foreChar);
            }

            // Change row.
            System.out.print('\n');

            // Increase variables.
            backCount += backStep;
            foreCount += foreStep;
        }

        return true;
    }

    private static boolean DrawHorizontalLine(int startFrom, int length) {
        if (startFrom < 0 || length < 0) {
            return false;
        }
        for (int i = 0; i < startFrom; ++i) {
            System.out.print(StringGraph.backChar);
        }
        for (int i = 0; i < length; ++i) {
            System.out.print(StringGraph.foreChar);
        }
        System.out.print('\n');
        return true;
    }
}
