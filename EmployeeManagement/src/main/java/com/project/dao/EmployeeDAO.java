package com.project.dao;

import com.project.model.Employee;
import com.project.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	private static final String VALIDATE_OTP_SQL =
		    "SELECT COUNT(*) FROM login_attempts WHERE email = ? AND otp = ? AND otp_expiry > NOW() AND success = 0";

		public boolean validateOTP(String email, String otp) {
		    boolean isValid = false;
		    try (Connection conn = DBConnection.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(VALIDATE_OTP_SQL)) {
		        stmt.setString(1, email);
		        stmt.setString(2, otp);
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

		public boolean storeOTP(String email, String otp) {
		    // SQL to insert or update OTP, set expiry time 5 minutes from now.
		    String sql = "INSERT INTO login_attempts (email, otp, otp_expiry, success) " +
		                 "VALUES (?, ?, NOW() + INTERVAL 5 MINUTE, 0) " +
		                 "ON DUPLICATE KEY UPDATE otp = ?, otp_expiry = NOW() + INTERVAL 5 MINUTE, success = 0";

		    try (Connection conn = DBConnection.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {

		        // Set parameters for the query
		        stmt.setString(1, email);
		        stmt.setString(2, otp);
		        stmt.setString(3, otp);  // Set the same OTP value for the update

		        // Execute the update or insert
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

    public String generateEmployeeId() throws SQLException {
        String newId = "JTC-001"; // Default first ID
        String sql = "SELECT employee_id FROM employees ORDER BY id DESC LIMIT 1"; // Fetch the last inserted employee_id

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String lastId = rs.getString("employee_id"); // Example: "JTC-005"
                int number = Integer.parseInt(lastId.substring(4)); // Extracts 5
                newId = String.format("JTC-%03d", number + 1); // Generates "JTC-006"
            }
        }
        return newId;
    }

    // Add Employee
    public boolean addEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (employee_id, employee_name, employee_dept, employee_email, employee_phone, date_of_joining, project_id) VALUES (?, ?, ?, ?, ?, NOW(), ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            String employeeId = generateEmployeeId(); // Generate custom ID

            ps.setString(1, employeeId);
            ps.setString(2, employee.getName());
            ps.setString(3, employee.getDepartment());
            ps.setString(4, employee.getEmail());
            ps.setString(5, employee.getPhone());
            ps.setString(6, employee.getProjectId());

            return ps.executeUpdate() > 0;
            }
                
            
    }
       
            
               

    // Get Employee by ID
    public Employee getEmployeeById(String employeeId) {
        String sql = "SELECT * FROM employees WHERE employee_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getString("employee_id"));
                emp.setName(rs.getString("name"));
                emp.setDepartment(rs.getString("department"));
                emp.setEmail(rs.getString("email"));
                emp.setPhone(rs.getString("phone"));
                emp.setDateOfJoining(rs.getDate("date_of_joining"));
                emp.setProjectId(rs.getString("project_id"));
                return emp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
   

    // Get All Employees
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getString("employee_id"));
                emp.setName(rs.getString("employee_name"));
                emp.setDepartment(rs.getString("employee_dept"));
                emp.setEmail(rs.getString("employee_email"));
                emp.setPhone(rs.getString("employee_phone"));
                emp.setDateOfJoining(rs.getDate("date_of_joining"));
                emp.setProjectId(rs.getString("project_id"));
                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Employee List size: " + (employees != null ? employees.size() : "null"));

        return employees;
    }

    // Update Employee
    public boolean updateEmployee(Employee employee) {
        String query = "UPDATE employees SET employee_name = ?, employee_dept = ?,employee_phone = ? WHERE employee_email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getDepartment());
            ps.setString(3, employee.getPhone());
            ps.setString(4, employee.getEmail()); // Assuming email is the unique identifier
            
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        

    }


    // Delete Employee
    public boolean deleteEmployee(String employeeId) {
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employeeId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Employee getEmployeeByEmail(String email) {
        String sql = "SELECT * FROM employees WHERE employee_email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getString("employee_id"));
                emp.setName(rs.getString("employee_name"));
                emp.setDepartment(rs.getString("employee_dept"));
                emp.setEmail(rs.getString("employee_email"));
                emp.setPhone(rs.getString("employee_phone"));
                emp.setDateOfJoining(rs.getDate("date_of_joining"));
                emp.setProjectId(rs.getString("project_id"));
                return emp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}