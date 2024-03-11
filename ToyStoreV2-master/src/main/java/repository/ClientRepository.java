package repository;

import config.DatabaseConnection;
import model.Client;
import model.Employee;
import model.Rol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository implements Repository<Client>{
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }
    private Client createClient(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getInt("id"));
        client.setFirst_name(resultSet.getString("First_name"));
        client.setLast_name(resultSet.getString("last_name"));
        client.setEmail(resultSet.getString("email"));
        client.setUser(resultSet.getString("user"));
        client.setBirthday_date(resultSet.getDate("birthday_date").toLocalDate());
        return client;
    }
    @Override
    public List<Client> list() {
        List<Client> clientList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     """
                        SELECT c.*, c.id as client_id, c.first_name as client_name, c.last_name as client_lastName, 
                        c.user as client_user, c.email as email, c.birthday_date as birthday 
                        FROM clients AS c;
                      """
             ))
        {
            while (resultSet.next()) {
                Client client = createClient(resultSet);
                clientList.add(client);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return clientList;
    }

    @Override
    public Client byId(int id) {
        return null;
    }

    @Override
    public void save(Client client) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      INSERT INTO clients(first_name, last_name, email, user, birthday_date) VALUES (?,?,?,?,?)
                                      """)
        ){
            preparedStatement.setString(1, client.getFirst_name());
            preparedStatement.setString(2, client.getLast_name());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.setString(4, client.getUser());
            preparedStatement.setDate(5, Date.valueOf(client.getBirthday_date()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Client client) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                  UPDATE clients SET first_name = ?, last_name = ?, email = ?, birthday_date = ? WHERE id = ?;
                                      """)
        ){
            preparedStatement.setString(1, client.getFirst_name());
            preparedStatement.setString(2, client.getLast_name());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.setDate(4, Date.valueOf(client.getBirthday_date()));
            preparedStatement.setInt(5,client.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
