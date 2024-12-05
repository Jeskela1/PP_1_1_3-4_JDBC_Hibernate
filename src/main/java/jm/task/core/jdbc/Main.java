package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        // Тестирование
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Kirill", "DarkInnocent", (byte) 25);
        userService.saveUser("Alexey", "DarkStalker", (byte) 25);
        userService.saveUser("Ravshan", "DarkDesire", (byte) 28);
        userService.removeUserById(2L);
        userService.cleanUsersTable();

        // Отладка
        System.out.println(userService.getAllUsers());


    }
}
