package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {

    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        userService.createUsersTable();
        User user1 = new User("Павел", "Трифанов", (byte) 34);
        User user2 = new User("Глеб", "Чучвич", (byte) 32);
        User user3 = new User("Иван", "Иванов", (byte) 77);
        User user4 = new User("Заур", "Трегулов", (byte) 35);
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            System.out.println(user.toString());
        }

        userService.removeUserById(3);
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}

