package model;

public class MonitorResult {
    private final String title;
    private final String[] details;
    private final boolean warning;

    public MonitorResult(String title, boolean warning, String... details) {
        this.title = title;
        this.warning = warning;
        this.details = details.clone();
    }

    public boolean isWarning() {
        return warning;
    }

    public String toHtml() {
        return "<html><b>" + title + "</b><br>"
                + String.join("<br>", details) + "</html>";
    }

    public String toReportLine() {
        return title + ": " + String.join(", ", details)
                + (warning ? " [WARNING]" : "");
    }
}
