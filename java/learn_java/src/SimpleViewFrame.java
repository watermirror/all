import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

class SimpleViewFrame extends JFrame {
    SimpleViewFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setTitle("Swing Test Window");

        SolarView solarView = new SolarView();
        getContentPane().add(solarView);
        pack();
    }
}

class SolarView extends JComponent {
    private static final int DEFAULT_SIZE = 700;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_SIZE, DEFAULT_SIZE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D canvas = (Graphics2D)g;
        canvas.draw(new Ellipse2D.Double(10, 10, 30, 30));
    }
}
