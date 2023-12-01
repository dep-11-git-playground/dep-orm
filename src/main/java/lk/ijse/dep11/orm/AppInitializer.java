package lk.ijse.dep11.orm;

import lk.ijse.dep11.orm.entity.Customer;
import lk.ijse.dep11.orm.entity.Employee;

import java.sql.Connection;
import java.sql.DriverManager;

public class AppInitializer {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/dep11_annotations",
                        "root", "mysql")) {

            SimpleOrm orm = new SimpleOrm(connection);
            orm.register(Customer.class);
            orm.register(Employee.class);

        }
    }
}
