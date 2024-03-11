package view;

import config.DatabaseConnection;
import model.Client;
import model.Employee;
import repository.ClientRepository;
import repository.EmployeeRepository;
import repository.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainPrueba2 {
    public static void main(String[] args) throws SQLException {
        try(Connection connection = DatabaseConnection.getInstance()){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Repository<Client> repository = new ClientRepository();
            System.out.println("**** List of clients from the database ****");
            repository.list().stream().forEach(System.out::println);
            Scanner s = new Scanner(System.in);
            System.out.println("**** Create a client ****");
            System.out.println("Escriba el nombre");
            String nombre = s.nextLine();
            System.out.println("Escriba el apellido");
            String apellido = s.nextLine();
            System.out.println("Escriba el email");
            String email = s.nextLine();
            System.out.println("Escriba el user");
            String user = s.nextLine();
            System.out.println("Ingrese fecha de cumple");
            String birthday_date = s.next();
            LocalDate birthVerify = LocalDate.parse(birthday_date,dateTimeFormatter);
            repository.save(Client.builder()
                    .first_name(nombre)
                    .last_name(apellido)
                    .email(email)
                    .user(user)
                    .birthday_date((birthVerify))
                    .build());
            System.out.println("**** Update an existing employee ****");
            System.out.println("Escriba el nombre");
            String nombreUpdate = s.next();
            System.out.println("Escriba el apellido");
            String apellidoUpdate = s.next();
            System.out.println("Escriba el email");
            String emailUpdate = s.next();
            System.out.println("Escriba el user");
            String userUpdate = s.next();
            System.out.println("Ingrese fecha de cumple");
            String birthday_dateUpdate = s.next();
            LocalDate VerifyUpdate = LocalDate.parse(birthday_dateUpdate,dateTimeFormatter);
            System.out.println("Digite el id del cliente a actualizar");
            int id = s.nextInt();
            repository.update(Client.builder()
                    .first_name(nombreUpdate)
                    .last_name(apellidoUpdate)
                    .email(emailUpdate)
                    .user(userUpdate)
                    .birthday_date((VerifyUpdate))
                    .id(id)
                    .build());
        }
    }
}

