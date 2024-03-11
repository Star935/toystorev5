package repository;

import config.DatabaseConnection;
import mapping.dtos.ToyDto;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ToyRepository implements ToysRepository<Toy>{
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    private Toy createToy(ResultSet resultSet) throws SQLException {
        Toy toy = new Toy();
        toy.setToy_name(resultSet.getString("toy_name"));
        toy.setType(new TypeToy(
                resultSet.getInt("id_type"),
                resultSet.getString("type_name")
        ));
        toy.setAmount(resultSet.getInt("amount"));
        toy.setPrice_unit(resultSet.getInt("price_unit"));
        toy.setPrice_total(resultSet.getInt("price_total"));

        return toy;
    }


    @Override
    public void save(Toy toy) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      INSERT INTO toys (toy_name, id_type, amount, price_unit, price_total) VALUES (?,?,?,?,?)
                                      """)
        ){
            preparedStatement.setString(1, toy.getToy_name());
            preparedStatement.setInt(2, toy.getType().getId());
            preparedStatement.setInt(3, toy.getAmount());
            preparedStatement.setInt(4, toy.getPrice_unit());
            preparedStatement.setInt(5,toy.getPrice_total());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Toy> listToys() {
        List<Toy> toyList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery
                     (
                     """
                        SELECT t.*, t.toy_name as toy_name, t.id as toy_id, t.price_unit as toy_price ,tToys.type as Type_name
                        FROM toys AS t 
                        INNER JOIN type_toys AS tToys ON t.id_type = tToys.id;
                      """
             ))
        {
            while (resultSet.next()) {
                Toy toy = createToy(resultSet);
                toyList.add(toy);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return toyList;
    }

    @Override
    public Map.Entry<TypeToy, Integer> maxToy() throws Exception {
        return null;
    }

    @Override
    public Map.Entry<TypeToy, Integer> minToy() throws Exception {
        return null;
    }

    @Override
    public Map<TypeToy, Integer> sort() throws Exception {
        return null;
    }

    @Override
    public List<ToyDto> increase(ToyDto toyDto, int amount) throws Exception {
        return null;
    }

    @Override
    public List<ToyDto> decrease(ToyDto toyDto, int amount) throws Exception {
        return null;
    }

    @Override
    public Map<TypeToy, Integer> showByType() throws Exception {
        return null;
    }

    @Override
    public List<ToyDto> showLargerThan(double value) throws Exception {
        return null;
    }

    @Override
    public Boolean verifyExist(String name) {

        return listToys().stream()
                // anyMatch() recorre  anyMatch() recorre los elementos del flujo hasta que encuentra un elemento que cumple con la condición.
                .anyMatch(e -> e.getToy_name().equalsIgnoreCase(name));
    }

    @Override
    public Integer totalToys() throws Exception {
        Integer count = 0;
        for (Toy toy : listToys()){
            count += toy.getAmount();
        }
        return count;
    }

    @Override
    public void delete(int id) {

    }
}
