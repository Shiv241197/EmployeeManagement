package com.project.dao;

import com.project.model.ClientContactPerson;
import com.project.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientContactPersonDAO {

    // Add a new contact person for a client
    public boolean addContactPerson(ClientContactPerson contact) {
        String sql = "INSERT INTO client_contact_persons (client_id, name, email, phone, designation) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contact.getClientId());
            stmt.setString(2, contact.getName());
            stmt.setString(3, contact.getEmail());
            stmt.setString(4, contact.getPhone());
            stmt.setString(5, contact.getDesignation());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve all contact persons for a given client ID
    public List<ClientContactPerson> getContactsByClientId(String clientId) {
        List<ClientContactPerson> contactList = new ArrayList<>();
        String sql = "SELECT * FROM client_contact_persons WHERE client_id = ? ORDER BY name";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, clientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ClientContactPerson contact = new ClientContactPerson();
                contact.setContactId(rs.getInt("contact_id"));
                contact.setClientId(rs.getString("client_id"));
                contact.setName(rs.getString("name"));
                contact.setEmail(rs.getString("email"));
                contact.setPhone(rs.getString("phone"));
                contact.setDesignation(rs.getString("designation"));
                contactList.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactList;
    }

    // Delete a contact person by ID
    public boolean deleteContactPerson(int contactId) {
        String sql = "DELETE FROM client_contact_persons WHERE contact_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contactId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
