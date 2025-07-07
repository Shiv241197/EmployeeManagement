package com.project.dao;

import com.project.model.LoginAttempt;
import com.project.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginAttemptsDAO {

    // Add a new login attempt
    public boolean addLoginAttempt(LoginAttempt attempt) {
        String sql = "INSERT INTO login_attempts (email, otp_code, attempt_time, success) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, attempt.getEmail());
            stmt.setString(2, attempt.getOtpCode());
            stmt.setTimestamp(3, new Timestamp(attempt.getAttemptTime().getTime()));
            stmt.setBoolean(4, attempt.isSuccess());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all login attempts for a specific email
    public List<LoginAttempt> getAttemptsByEmail(String email) {
        List<LoginAttempt> attempts = new ArrayList<>();
        String sql = "SELECT * FROM login_attempts WHERE email = ? ORDER BY attempt_time DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                LoginAttempt attempt = new LoginAttempt();
                attempt.setEmail(rs.getString("email"));
                attempt.setOtpCode(rs.getString("otp_code"));
                attempt.setAttemptTime(rs.getTimestamp("attempt_time"));
                attempt.setSuccess(rs.getBoolean("success"));
                attempts.add(attempt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attempts;
    }

    // Count failed login attempts for a given email
    public int getFailedAttempts(String email) {
        String sql = "SELECT COUNT(*) FROM login_attempts WHERE email = ? AND success = FALSE AND attempt_time > NOW() - INTERVAL 5 MINUTE";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Clear all login attempts for a specific email (after successful login)
    public boolean clearAttempts(String email) {
        String sql = "DELETE FROM login_attempts WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
