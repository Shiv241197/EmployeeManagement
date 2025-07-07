package com.project.dao;

import com.project.model.AdminUser;
import com.project.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminUserDAO {
	

	private static final String VALIDATE_ADMIN_SQL = "SELECT COUNT(*) FROM admin_users WHERE admin_email = ? AND admin_password = ?";

    public boolean validateAdmin(String email, String password) {
        boolean isValid = false;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(VALIDATE_ADMIN_SQL)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    isValid = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }
    // Method to fetch admin details by email
    public AdminUser getAdminByEmail(String adminEmail) {
        AdminUser admin = null;
        String sql = "SELECT * FROM admin_users WHERE admin_email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, adminEmail);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new AdminUser();
                admin.setAdminEmail(rs.getString("admin_email"));
                admin.setAdminPassword(rs.getString("admin_password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    // Method to create a new admin user
    public boolean addAdmin(AdminUser admin) {
        String sql = "INSERT INTO admin_users (admin_email, admin_password) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, admin.getAdminEmail());
            stmt.setString(2, admin.getAdminPassword());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
