package com.project.dao;

import com.project.model.Client;
import com.project.model.Employee;
import com.project.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
     
	// Validate OTP for Employee Login
    public boolean validateOTP(String email, String otp) {
        boolean isValid = false;
        String sql = "SELECT * FROM login_attempts WHERE email = ? AND otp = ? AND TIMESTAMPDIFF(MINUTE, created_at, NOW()) <= 5";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, otp);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                isValid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return isValid;
    }
    public boolean storeOTP(String email, String otp) {
        // This query inserts a new record into login_attempts with the OTP details.
        // It uses ON DUPLICATE KEY UPDATE to update the OTP if a record for the email already exists.
        String sql = "INSERT INTO login_attempts (email, otp, otp_expiry, success) " +
                     "VALUES (?, ?, NOW() + INTERVAL 5 MINUTE, 0) " +
                     "ON DUPLICATE KEY UPDATE otp = VALUES(otp), otp_expiry = NOW() + INTERVAL 5 MINUTE, success = 0";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, otp);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    

    // Add Client
    public void addClient(Client client) {
        String sql = "INSERT INTO clients (client_id, company_name, relationship_date) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String newClientId = generateClientId(); // ✅ Call the method to generate ID

            pstmt.setString(1, newClientId);
            pstmt.setString(2, client.getCompanyName());

            if (client.getRelationshipDate() != null) {
                pstmt.setDate(3, new java.sql.Date(client.getRelationshipDate().getTime())); // Convert java.util.Date to java.sql.Date
            } else {
                pstmt.setDate(3, null); // Let MySQL handle default CURDATE()
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ **Method to generate unique Client ID**
    public String generateClientId() {
        String lastId = null;
        String sql = "SELECT client_id FROM clients ORDER BY client_id DESC LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                lastId = rs.getString("client_id"); // Get the last inserted client_id
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // ✅ If no clients exist, start from client-001
        if (lastId == null) {
            return "client-001";
        }

        // ✅ Extract the numeric part and increment it
        int num = Integer.parseInt(lastId.split("-")[1]);
        num++; // Increment the number

        return String.format("client-%03d", num); // Format to client-001, client-002, etc.
    }


    // Get Client by ID
    public Client getClientById(String clientId) {
        String sql = "SELECT * FROM clients WHERE client_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, clientId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Client client = new Client();
                client.setClientId(rs.getString("client_id"));
                client.setCompanyName(rs.getString("company_name")); // Updated
                client.setRelationshipDate(rs.getDate("relationship_date"));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get All Clients
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Client client = new Client();
                client.setClientId(rs.getString("client_id"));
                client.setCompanyName(rs.getString("company_name")); // Updated
                client.setRelationshipDate(rs.getDate("relationship_date"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    // Update Client
    public boolean updateClient(Client client) {
        String sql = "UPDATE clients SET company_name=?, relationship_date=? WHERE client_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, client.getCompanyName()); // Updated
            stmt.setDate(2, new java.sql.Date(client.getRelationshipDate().getTime()));
            stmt.setString(3, client.getClientId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete Client
    public boolean deleteClient(String clientId) {
        String sql = "DELETE FROM clients WHERE client_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, clientId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void resetFailedAttempts(String email) {
        String query = "UPDATE login_attempts SET failed_attempts = 0 WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void incrementFailedAttempts(String email) {
        String query = "UPDATE login_attempts SET failed_attempts = failed_attempts + 1, last_failed_attempt = NOW() WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Employee> getEmployeesByProject(String projectId) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT e.employee_id, e.employee_name, e.department " +
                     "FROM employees e WHERE e.project_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, projectId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                employees.add(new Employee(
                    rs.getString("employeeid"),
                    rs.getString("employeename"),
                    rs.getString("department")
                ));
            }
        }
        return employees;
    }

    public Client getClientByEmail(String email) {
        Client client = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Get a database connection
            connection = DBConnection.getConnection();

            // SQL query to fetch client details based on email
            String sql = "SELECT * FROM clients WHERE email = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
            	client.setClientId(resultSet.getString("client_id"));
                client.setCompanyName(resultSet.getString("company_name")); // Corrected from setClientName to setCompanyName
                client.setContactPerson(resultSet.getString("contact_person"));
                client.setEmail(resultSet.getString("email"));
                client.setPhone(resultSet.getString("phone")); // Setting phone number
                client.setRelationshipDate(resultSet.getDate("relationship_date"));  
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
        return client;
    }

}
