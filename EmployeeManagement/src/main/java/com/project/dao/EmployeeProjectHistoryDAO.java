package com.project.dao;

import com.project.model.EmployeeProjectHistory;
import com.project.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeProjectHistoryDAO {

    // Add a new employee project history record
    public boolean addEmployeeProjectHistory(EmployeeProjectHistory history) {
        String sql = "INSERT INTO employee_project_history (employee_id, project_id, start_date, end_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, history.getEmployeeId());
            stmt.setString(2, history.getProjectId());
            stmt.setDate(3, new Date(history.getStartDate().getTime()));

            if (history.getEndDate() != null) {
                stmt.setDate(4, new Date(history.getEndDate().getTime()));
            } else {
                stmt.setNull(4, Types.DATE);
            }

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve all project history for a given employee
    public List<EmployeeProjectHistory> getHistoryByEmployeeId(String employeeId) {
        List<EmployeeProjectHistory> historyList = new ArrayList<>();
        String sql = "SELECT * FROM employee_project_history WHERE employee_id = ? ORDER BY start_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EmployeeProjectHistory history = new EmployeeProjectHistory();
                history.setEmployeeId(rs.getString("employee_id"));
                history.setProjectId(rs.getString("project_id"));
                history.setStartDate(rs.getDate("start_date"));
                history.setEndDate(rs.getDate("end_date"));
                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historyList;
    }

    // Retrieve all project history for a given project
    public List<EmployeeProjectHistory> getHistoryByProjectId(String projectId) {
        List<EmployeeProjectHistory> historyList = new ArrayList<>();
        String sql = "SELECT * FROM employee_project_history WHERE project_id = ? ORDER BY start_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, projectId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EmployeeProjectHistory history = new EmployeeProjectHistory();
                history.setEmployeeId(rs.getString("employee_id"));
                history.setProjectId(rs.getString("project_id"));
                history.setStartDate(rs.getDate("start_date"));
                history.setEndDate(rs.getDate("end_date"));
                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historyList;
    }
}
