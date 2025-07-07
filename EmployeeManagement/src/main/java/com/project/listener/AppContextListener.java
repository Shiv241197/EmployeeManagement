package com.project.listener;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // No action needed during initialization
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            // Unregister the JDBC driver
            java.sql.DriverManager.deregisterDriver(java.sql.DriverManager.getDriver("jdbc:mysql://localhost:3306/yourdb"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Stop the AbandonedConnectionCleanupThread to prevent memory leaks
        try {
            AbandonedConnectionCleanupThread.checkedShutdown();
        } catch (Exception e) {
            e.printStackTrace();  // You can catch a generic Exception here, since checkedShutdown() does not throw InterruptedException
        }
    }
}
