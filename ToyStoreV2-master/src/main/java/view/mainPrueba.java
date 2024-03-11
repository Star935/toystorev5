package view;

import config.DatabaseConnection;
import model.Employee;
import model.Rol;
import repository.EmployeeRepository;
import repository.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class mainPrueba {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DatabaseConnection.getInstance()){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            List<Rol> rol = new ArrayList<>();
                rol.add(Rol.builder()
                        .id(1)
                        .name("Store Manager")
                        .salary(500)
                        .build());
                rol.add(Rol.builder()
                        .id(2)
                        .name("Assistant Manager")
                        .salary(350)
                        .build());
                rol.add(Rol.builder()
                        .id(3)
                        .name("Salesperson")
                        .salary(300)
                        .build());
                rol.add(Rol.builder()
                        .id(4)
                        .name("Cashier")
                        .salary(280)
                        .build());
                rol.add(Rol.builder()
                        .id(5)
                        .name("Restocker")
                        .salary(250)
                        .build());

            Repository<Employee> repository = new EmployeeRepository();
            System.out.println("**** List of employees from the database ****");
            repository.list().stream().forEach(System.out::println);
            Scanner s = new Scanner(System.in);
            System.out.println("**** Create an employee ****");
            System.out.println("Escriba el nombre");
            String nombre = s.nextLine();
            System.out.println("Escriba el apellido");
            String apellido = s.nextLine();
            System.out.println("Escriba el id");
            int id_rol = s.nextInt();
            System.out.println("Ingrese fecha de cumple");
            String birthday_date = s.next();
            LocalDate birthVerify = LocalDate.parse(birthday_date,dateTimeFormatter);
            repository.save(Employee.builder()
                    .first_name(nombre)
                    .last_name(apellido)
                    .rol(rol.get(id_rol))
                    .birthday_date((birthVerify))
                    .build());
            System.out.println("**** Update an existing employee ****");
            System.out.println("Actualizar el nombre");
            String nombreUpdate = s.nextLine();
            System.out.println("Actualizar el apellido");
            String apellidoUpdate = s.nextLine();
            System.out.println("Actualizar el rol");
            int id_rolUpdate = s.nextInt();
            System.out.println("Actualice fecha de cumple");
            String birthday_dateUpdate = s.next();
            LocalDate birthVerifyUpdate = LocalDate.parse(birthday_dateUpdate,dateTimeFormatter);
            System.out.println("Indique el id");
            int id = s.nextInt();
            repository.update(Employee.builder()
                    .first_name(nombreUpdate)
                    .last_name(apellidoUpdate)
                    .rol(rol.get(id_rolUpdate))
                    .birthday_date((birthVerifyUpdate))
                    .id(id)
                    .build());
        }
    }
}
