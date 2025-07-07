package com.project.dao;

import com.project.model.Project;
import com.project.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    // Add Project
    public boolean addProject(Project project) {
        String sql = "INSERT INTO projects (project_id, name, start_date, end_date, client_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, project.getProjectId());
            stmt.setString(2, project.getName());
            stmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
            stmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
            stmt.setString(5, project.getClientId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get Project by ID
    public Project getProjectById(String projectId) {
        String sql = "SELECT * FROM projects WHERE project_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, projectId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Project project = new Project();
                project.setProjectId(rs.getString("project_id"));
                project.setName(rs.getString("name"));
                project.setStartDate(rs.getDate("start_date"));
                project.setEndDate(rs.getDate("end_date"));
                project.setClientId(rs.getString("client_id"));
                return project;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get All Projects
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM projects";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Project project = new Project();
                project.setProjectId(rs.getString("project_id"));
                project.setName(rs.getString("name"));
                project.setStartDate(rs.getDate("start_date"));
                project.setEndDate(rs.getDate("end_date"));
                project.setClientId(rs.getString("client_id"));
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    // Update Project
    public boolean updateProject(Project project) {
        String sql = "UPDATE projects SET name=?, start_date=?, end_date=?, client_id=? WHERE project_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, project.getName());
            stmt.setDate(2, new java.sql.Date(project.getStartDate().getTime()));
            stmt.setDate(3, new java.sql.Date(project.getEndDate().getTime()));
            stmt.setString(4, project.getClientId());
            stmt.setString(5, project.getProjectId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete Project
    public boolean deleteProject(String projectId) {
        String sql = "DELETE FROM projects WHERE project_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, projectId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<Project> getProjectsByClientId(String clientId) {
        List<Project> projects = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Get a database connection
            connection = DBConnection.getConnection();

            // SQL query to fetch projects for the client
            String sql = "SELECT * FROM projects WHERE client_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, clientId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Project project = new Project();
                project.setProjectId(resultSet.getString("project_id"));
                project.setName(resultSet.getString("project_name"));
                project.setStartDate(resultSet.getDate("start_date"));
                project.setEndDate(resultSet.getDate("end_date"));
                project.setClientId(resultSet.getString("client_id"));
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return projects;
    }

}
