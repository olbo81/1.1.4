package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 5);
        userService.saveUser("Ivanka", "Ivankova", (byte) 10);
        userService.saveUser("Fedor", "Sumkin", (byte) 15);
        userService.saveUser("Sidor", "Sidorov", (byte) 20);
        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}






