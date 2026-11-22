package gui;

import model.MonitorResult;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WardDashboard extends JFrame {
    private final MonitorPanel[] panels = new MonitorPanel[5];

    public WardDashboard() {
        super("Hospital Ward Environmental Monitor");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 440);
        setLocationRelativeTo(null);

        JPanel grid = new JPanel(new GridLayout(2, 3, 10, 10));

        grid.setBorder(
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        );

        for (int i = 0; i < panels.length; i++) {
            panels[i] = new MonitorPanel();
            grid.add(panels[i]);
        }

        grid.add(new JPanel());
        add(grid);
    }

    public void display(List<MonitorResult> results) {
        for (int i = 0; i < panels.length; i++) {
            panels[i].display(results.get(i));
        }
    }
}