import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/house_automation";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String device = request.getParameter("device");
        String action = request.getParameter("action");

        if (device != null) {
            try {
                // Simulating toggling device status
                String newStatus = toggleDeviceStatus(device);
                out.println("{ \"status\": \"" + newStatus + "\" }");
            } catch (SQLException e) {
                out.println("{ \"error\": \"" + e.getMessage() + "\" }");
            }
        } else if (action != null) {
            try {
                // Simulating temperature adjustment
                int newTemperature = adjustTemperature(action);
                out.println("{ \"temperature\": " + newTemperature + " }");
            } catch (SQLException e) {
                out.println("{ \"error\": \"" + e.getMessage() + "\" }");
            }
        } else {
            out.println("{ \"error\": \"Invalid request\" }");
        }
    }

    private String toggleDeviceStatus(String device) throws SQLException {
        String newStatus = "Error";  // Default value in case of failure

        // Using try-with-resources to automatically close resources
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT status FROM devices WHERE name = '" + device + "'");

            if (rs.next()) {
                String currentStatus = rs.getString("status");
                newStatus = currentStatus.equals("On") ? "Off" : "On";
                stmt.executeUpdate("UPDATE devices SET status = '" + newStatus + "' WHERE name = '" + device + "'");
            }
        }
        return newStatus;
    }

    private int adjustTemperature(String action) throws SQLException {
        int currentTemp = 22;  // Default temperature if no result found

        // Using try-with-resources to automatically close resources
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT temperature FROM system WHERE id = 1");

            if (rs.next()) {
                currentTemp = rs.getInt("temperature");
                if ("increase".equals(action)) {
                    currentTemp++;
                } else if ("decrease".equals(action)) {
                    currentTemp--;
                }
                stmt.executeUpdate("UPDATE system SET temperature = " + currentTemp + " WHERE id = 1");
            }
        }
        return currentTemp;
    }
}
