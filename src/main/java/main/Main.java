package main;

import HES.Systems.EnvironmentalSystem;
import gui.*;
import model.*;
import model.Ward;
import monitors.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// ==================== MAIN ====================

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EnvironmentalSystem system = new EnvironmentalSystem();

            Ward north = new Ward(0, "North Ward", 23.0f);
            Ward central = new Ward(1, "Central Ward", 22.0f);
            Ward south = new Ward(2, "South Ward", 20.0f);

            List<EnvironmentalMonitor> monitors = new ArrayList<>();
            monitors.add(new TemperatureMonitor(system, north));
            monitors.add(new TemperatureMonitor(system, central));
            monitors.add(new TemperatureMonitor(system, south));
            monitors.add(new HumidityMonitor(system));
            monitors.add(new PollutionMonitor(system));

            WardDashboard dashboard = new WardDashboard();
            dashboard.setVisible(true);

            // Initial reading
            refresh(monitors, dashboard);

            final int[] seconds = {0};

            Timer timer = new Timer(1000, e -> {
                List<MonitorResult> results = refresh(monitors, dashboard);

                seconds[0]++;
                if (seconds[0] % 30 == 0) {
                    printReport(results, system);
                }
            });

            timer.start();
        });
    }

    private static List<MonitorResult> refresh(
            List<EnvironmentalMonitor> monitors,
            WardDashboard dashboard) {

        List<MonitorResult> results = new ArrayList<>();

        for (EnvironmentalMonitor monitor : monitors) {
            results.add(monitor.update()); // Polymorphism
        }

        dashboard.display(results);
        return results;
    }

    private static void printReport(
            List<MonitorResult> results,
            EnvironmentalSystem system) {

        System.out.println("\n===== 30-SECOND ENVIRONMENT REPORT =====");

        for (MonitorResult result : results) {
            System.out.println(result.toReportLine());
        }

        System.out.println("CT Scanner: " + system.getCTScannerStatus());
        System.out.println("Ultrasound Scanner: "
                + system.getUltrasoundScannerStatus());
        System.out.println("========================================\n");
    }
}
