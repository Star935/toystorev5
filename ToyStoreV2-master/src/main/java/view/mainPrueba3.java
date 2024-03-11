package view;

import config.DatabaseConnection;
import model.*;
import repository.EmployeeRepository;
import repository.Repository;
import repository.ToyRepository;
import repository.ToysRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mainPrueba3 {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DatabaseConnection.getInstance()) {
            List<TypeToy> type = new ArrayList<>();
            type.add(TypeToy.builder()
                    .id(1)
                    .type("FEMALE")
                    .build());
            type.add(TypeToy.builder()
                    .id(2)
                    .type("MALE")
                    .build());
            type.add(TypeToy.builder()
                    .id(3)
                    .type("UNISEX")
                    .build());
            Scanner s = new Scanner(System.in);
            ToysRepository<Toy> repository = new ToyRepository();
            System.out.println("**** List of toys from the database ****");
            repository.listToys().stream().forEach(System.out::println);
            System.out.println(repository.totalToys());
            System.out.println("**** Create a toy ****");
            System.out.println("Escriba el nombre");
            String nombre = s.nextLine();
            System.out.println("Escriba el genero");
            int gender = s.nextInt();
            System.out.println("Escriba el cantidad");
            int amount = s.nextInt();
            System.out.println("Escriba el precio");
            int price = s.nextInt();
            repository.save(Toy.builder()
                    .toy_name(nombre)
                    .type(type.get(gender))
                    .amount(amount)
                    .price_unit(price)
                    .price_total(amount * price)
                    .build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}