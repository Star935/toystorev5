package repository;

import config.DatabaseConnection;
import model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DetailsRepository implements Repository<Details>{
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    private Details createDetails(ResultSet resultSet) throws SQLException {
        Details details = new Details();
        details.setId(resultSet.getInt("id"));

        Client client = new Client();
        client.setId(resultSet.getInt("id"));
        client.setFirst_name(resultSet.getString("First_name"));
        client.setLast_name(resultSet.getString("last_name"));
        client.setEmail(resultSet.getString("email"));
        client.setUser(resultSet.getString("user"));
        client.setBirthday_date(resultSet.getDate("birthday_date").toLocalDate());
        details.setClient(client);

        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setFirst_name(resultSet.getString("First_name"));
        employee.setLast_name(resultSet.getString("last_name"));
        employee.setRol(new Rol(
                resultSet.getInt("id_rol"),
                resultSet.getString("role_name"),
                resultSet.getInt("role_salary")
        ));
        details.setEmployee(employee);

        Toy toy = new Toy();
        toy.setToy_name(resultSet.getString("toy_name"));
        toy.setType(new TypeToy(
                resultSet.getInt("id_type"),
                resultSet.getString("type_name")
        ));
        toy.setAmount(resultSet.getInt("amount"));
        toy.setPrice_unit(resultSet.getInt("price_unit"));
        toy.setPrice_total(resultSet.getInt("price_total"));
        details.setToy(toy);

        Sales sale= new Sales();
        sale.setInvoice_number(resultSet.getInt("invoice_number"));
        sale.setDate(resultSet.getDate("date").toLocalDate());
        details.setSales(sale);

        return details;
    }

    @Override
    public List<Details> list() {
        List<Details> detailsList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     """
                       SELECT d.*, c.id as client_id, e.id as employee_id, t.id as toys_id 
                       FROM details AS d 
                       INNER JOIN clients AS c ON d.client_id = c.id 
                       INNER JOIN employees AS e ON d.employee_id = e.id 
                       INNER JOIN toys AS t ON d.toy_id = t.id 
                       INNER JOIN sales AS s ON d.sales_id = s.invoice_number;
                      """
             ))
        {
            while (resultSet.next()) {
                Details details = createDetails(resultSet);
                detailsList.add(details);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return detailsList;
    }

    @Override
    public Details byId(int id) {
        return null;
    }

    @Override
    public void save(Details details) {

    }

    @Override
    public void update(Details details) {

    }
}
