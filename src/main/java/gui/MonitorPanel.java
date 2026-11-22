package gui;

import model.MonitorResult;

import javax.swing.*;
import java.awt.*;

public class MonitorPanel extends JPanel {
    private final JLabel label = new JLabel();

    public MonitorPanel() {
        super(new BorderLayout());

        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        label.setBorder(
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        );

        add(label, BorderLayout.CENTER);
    }

    public void display(MonitorResult result) {
        label.setText(result.toHtml());

        setBackground(result.isWarning() ? Color.RED : Color.WHITE);
        label.setForeground(result.isWarning() ? Color.WHITE : Color.BLACK);
    }
}