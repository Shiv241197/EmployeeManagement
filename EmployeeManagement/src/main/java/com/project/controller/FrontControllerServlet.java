package com.project.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.project.dao.*;
import com.project.model.*;
import com.project.util.DBConnection;
import com.project.util.EmailUtil;
import java.text.ParseException;

public class FrontControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDAO employeeDAO;
    private ProjectDAO projectDAO;
    private ClientDAO clientDAO;
    private AdminUserDAO adminUserDAO;

    public void init() {
        employeeDAO = new EmployeeDAO();
        projectDAO = new ProjectDAO();
        clientDAO = new ClientDAO();
        adminUserDAO = new AdminUserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        

        
    

        if (action != null) {
            switch (action) {
                case "updateEmployeeProfile":
                    updateEmployeeProfile(request, response);
                    break;
                case "adminLogin":
                    adminLogin(request, response);
                    break;
                case "addEmployee":
				try {
					try {
						addEmployee(request, response);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    break;
                case "editEmployee":
                    editEmployee(request, response);
                    break;
                case "deleteEmployee":
                    deleteEmployee(request, response);
                    break;
                case "addProject":
                    addProject(request, response);
                    break;
                case "addClient":
                    addClient(request, response);
                    break;

                // Other cases...
            }
        }
    }

        
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

       
        if (action == null || action.isEmpty()) {
            System.out.println("No action received, redirecting...");
            response.sendRedirect("error.jsp"); // Redirect to an error or dashboard page
            return;
        }
        System.out.println("Received action: " + action);
        try {
            switch (action) {
                // Employee Operations
                case "viewEmployees":
                    viewEmployees(request, response);
                    break;

                // Project Operations
                case "listProjects":
                    listProjects(request, response);
                    break;
                case "addProject":
                    addProject(request, response);
                    break;

                // Client Operations
                case "viewClients":
                    viewClients(request, response);
                    break;
                case "addClient":
                    addClient(request, response);
                    break;

                // OTP-Based Authentication & Verification
                case "sendClientOTP":
                    sendClientOTP(request, response);
                    break;
                case "sendEmployeeOTP":
                    sendEmployeeOTP(request, response);
                    break;
                case "verifyOTP":
                    verifyOTP(request, response);
                    break;

                // Admin Login & Logout
                case "adminLogin":
                    adminLogin(request, response);
                    break;
                case "logout":
                    logout(request, response);
                    break;

                default:
                    response.sendRedirect("error.jsp");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Something went wrong! Please try again.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }


    // ---------------------------------
    // ✅ Employee Management Methods
    // ---------------------------------
 // Method to add a new employee
    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException {
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        
        // Generate employee ID automatically (example: JTC-001)
        String employeeId = generateEmployeeId();
        
        // Set up the Employee object
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setName(name);
        employee.setDepartment(department);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setDateOfJoining(new Date()); // or any default value you want
        
        // Call the EmployeeDAO to add the employee to the database
        boolean added = employeeDAO.addEmployee(employee);
        
        // Redirect based on success/failure
        if (added) {
            response.sendRedirect("FrontControllerServlet?action=viewEmployees"); // Show the list of employees
        } else {
            response.sendRedirect("addEmployee.jsp?error=Failed to add employee"); // Handle failure case
        }
    }

    // Helper method to generate employee ID (JTC-001, JTC-002, etc.)
    private String generateEmployeeId() {
        // Logic to generate the employee ID, this can be based on current count or a random number.
        // For simplicity, returning a static value here.
        return "JTC-" + String.format("%03d", new Random().nextInt(1000));
    }
    
 //UPDATE EMPLOYEE
    private void updateEmployeeProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("employeeEmail") == null) {
            response.sendRedirect("employeeLogin.jsp");
            return;
        }

        String email = (String) session.getAttribute("employeeEmail"); // Get employee email from session
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee = employeeDAO.getEmployeeByEmail(email); // Fetch existing employee data using the email

        // Check if employee exists
        if (employee == null) {
            response.sendRedirect("employeeLogin.jsp");
            return;
        }

        // Retrieve updated data from the form
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        String phone = request.getParameter("phone");

        // Update only the fields that were changed
        if (name != null && !name.isEmpty()) {
            employee.setName(name);
        }
        if (department != null && !department.isEmpty()) {
            employee.setDepartment(department);
        }
        if (phone != null && !phone.isEmpty()) {
            employee.setPhone(phone);
        }

        // Update employee in the database
        boolean updated = employeeDAO.updateEmployee(employee);

        // Redirect based on update result
        if (updated) {
            // Optionally, update employee details in the session as well
            session.setAttribute("employeeName", employee.getName());
            response.sendRedirect("employeeDashboard.jsp?success=Profile updated successfully");
        } else {
            response.sendRedirect("updateEmployeeProfile.jsp?error=Update failed");
        }
    }


    private void viewEmployees(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    // Retrieve the list of employees from the DAO
    List<Employee> employees = employeeDAO.getAllEmployees();

    // Debugging: Log the size of the retrieved employee list
    if (employees != null) {
        System.out.println("Employees retrieved: " + employees.size());
    } else {
        System.out.println("Employees retrieved: null");
    }

    // Set the list as a request attribute to be accessed in the JSP
    request.setAttribute("employees", employees);

    // Forward the request to the viewEmployees.jsp for rendering
    request.getRequestDispatcher("viewEmployees.jsp").forward(request, response);
}
    
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        String query = "SELECT * FROM employees";  // Assuming 'employees' is your table name

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getString("employee_id"));
                emp.setName(rs.getString("employee_name"));
                emp.setDepartment(rs.getString("employee_dept"));
                emp.setEmail(rs.getString("employee_email"));  // Corrected column name
                emp.setPhone(rs.getString("employee_phone"));
                emp.setDateOfJoining(rs.getDate("date_of_joining"));
                emp.setProjectId(rs.getString("project_id"));
                employeeList.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }



    private void editEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        Employee employee = new Employee();
        employee.setEmployeeId(id);
        employee.setName(name);
        employee.setDepartment(department);
        employee.setEmail(email);
        employee.setPhone(phone);

        employeeDAO.updateEmployee(employee);
        response.sendRedirect("FrontControllerServlet?action=listEmployees");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        employeeDAO.deleteEmployee(id);
        response.sendRedirect("FrontControllerServlet?action=listEmployees");
    }

    // ---------------------------------
    // ✅ Project Management Methods
    // ---------------------------------
    private void listProjects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Project> projects = projectDAO.getAllProjects();
        request.setAttribute("projects", projects);
        request.getRequestDispatcher("projectList.jsp").forward(request, response);
    }

    private void addProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String clientId = request.getParameter("clientId");

        Project project = new Project();
        project.setName(name);
        project.setClientId(clientId);

        projectDAO.addProject(project);
        response.sendRedirect("FrontControllerServlet?action=listProjects");
    }

    // ---------------------------------
    // ✅ Client Management Methods
    // ---------------------------------
    private void viewClients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Client> clients = clientDAO.getAllClients();
        request.setAttribute("clients", clients);
        request.getRequestDispatcher("viewClients.jsp").forward(request, response);
    }
    private void addClient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String companyName = request.getParameter("companyName");
        String relationshipDateStr = request.getParameter("relationshipDate");

        Client client = new Client();
        client.setCompanyName(companyName);

        if (relationshipDateStr != null && !relationshipDateStr.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date relationshipDate = sdf.parse(relationshipDateStr);
                client.setRelationshipDate(relationshipDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            client.setRelationshipDate(new Date()); // Default to current date if not provided
        }

        clientDAO.addClient(client);
        response.sendRedirect("FrontControllerServlet?action=viewClients");
    }

    

    // ---------------------------------
    // ✅ OTP-Based Authentication
    // ---------------------------------
    // When sending OTP, include userType to indicate client or employee.
    private void sendClientOTP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String otp = String.format("%06d", new Random().nextInt(999999));

        if (clientDAO.storeOTP(email, otp)) {
            EmailUtil.sendEmail(email, "Your OTP Code", "Your OTP is: " + otp);
            // Redirect to a common verify OTP page with email and userType=client
            response.sendRedirect("verifyOTP.jsp?email=" + email + "&userType=client");
        } else {
            response.sendRedirect("clientLogin.jsp?error=Invalid Email");
        }
    }

    private void sendEmployeeOTP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String otp = String.format("%06d", new Random().nextInt(999999));
        System.out.println("Generated OTP: " + otp);
        System.out.println("Email: " + email);

        if (employeeDAO.storeOTP(email, otp)) {
        	HttpSession session = request.getSession();
            session.setAttribute("email", email);
            session.setAttribute("userType", "employee");
            EmailUtil.sendEmail(email, "Your OTP Code", "Your OTP is: " + otp);
            // Redirect to a common verify OTP page with email and userType=employee
            response.sendRedirect("verifyOTP.jsp?email=" + email + "&userType=employee");
        } else {
            response.sendRedirect("employeeLogin.jsp?error=Invalid Email");
        }
    }

    // Unified OTP Verification for both clients and employees
    private void verifyOTP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String otp = request.getParameter("otp");
        String userType = request.getParameter("userType"); // Expected "client" or "employee"
        boolean isValid = false;
        
        if ("client".equalsIgnoreCase(userType)) {
            isValid = clientDAO.validateOTP(email, otp);
        } else if ("employee".equalsIgnoreCase(userType)) {
            isValid = employeeDAO.validateOTP(email, otp);
        }
        
        if (isValid) {
            // Reset failed attempts and set session attribute, then redirect
            if ("client".equalsIgnoreCase(userType)) {
                clientDAO.resetFailedAttempts(email);
                session.setAttribute("clientEmail", email);
                response.sendRedirect("clientDashboard.jsp");
            } else if ("employee".equalsIgnoreCase(userType)) {
                employeeDAO.resetFailedAttempts(email);
                session.setAttribute("employeeEmail", email);
                response.sendRedirect("employeeDashboard.jsp");
            }
        } else {
            // Increment failed attempts and forward back to verifyOTP.jsp with error
            if ("client".equalsIgnoreCase(userType)) {
                clientDAO.incrementFailedAttempts(email);
            } else if ("employee".equalsIgnoreCase(userType)) {
                employeeDAO.incrementFailedAttempts(email);
            }
            request.setAttribute("error", "Invalid OTP. Please try again.");
            request.getRequestDispatcher("verifyOTP.jsp").forward(request, response);
        }
    }

    // ---------------------------------
    // ✅ Admin Login & Logout
    // ---------------------------------
    private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (adminUserDAO.validateAdmin(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("userRole", "admin");
            session.setAttribute("userEmail", email);
            response.sendRedirect("adminDashboard.jsp");
        } else {
            response.sendRedirect("adminLogin.jsp?error=Invalid Credentials");
        }
    }
    
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get session if exists
        if (session != null) {
            session.invalidate(); // Destroy session
        }
        response.sendRedirect("adminLogin.jsp"); // Redirect to login page (adjust as needed)
    }
    
    public void showEmployeeDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("employeeEmail") == null) {
            response.sendRedirect("employeeLogin.jsp");
            return;
        }

        // Get employee's email from session
        String email = (String) session.getAttribute("employeeEmail");

        // Fetch employee details from database
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee = employeeDAO.getEmployeeByEmail(email);

        if (employee != null) {
            // Set employee object as a request attribute to be accessed in the JSP
            request.setAttribute("employee", employee);
            // Forward the request to the employee dashboard JSP
            request.getRequestDispatcher("employeeDashboard.jsp").forward(request, response);
        } else {
            // If employee is not found, redirect to error page or show an error message
            response.sendRedirect("error.jsp");
        }
    }
    public void showClientDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("clientEmail") == null) {
            response.sendRedirect("clientLogin.jsp");
            return;
        }

        // Get client's email from session
        String email = (String) session.getAttribute("clientEmail");

        // Fetch client details from database
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.getClientByEmail(email);

        if (client != null) {
            // Set client object as a request attribute to be accessed in the JSP
            request.setAttribute("client", client);
            // Retrieve projects related to this client
            ProjectDAO projectDAO = new ProjectDAO();
            List<Project> projects = projectDAO.getProjectsByClientId(client.getClientId());
            request.setAttribute("projects", projects);

            // Forward the request to the client dashboard JSP
            request.getRequestDispatcher("clientDashboard.jsp").forward(request, response);
        } else {
            // If client is not found, redirect to error page or show an error message
            response.sendRedirect("error.jsp");
        }
    }


}
