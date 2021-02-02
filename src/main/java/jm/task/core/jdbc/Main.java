package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.createUsersTable();

        service.saveUser("Alex", "Popov", (byte) 22);
        System.out.println("Add Alex in table");
        service.saveUser("Misha", "Papov", (byte) 23);
        System.out.println("Add Misha in table");
        service.saveUser("Ben", "Popof", (byte) 24);
        System.out.println("Add Ben in table");
        service.saveUser("Killreal", "Petrov", (byte) 25);
        System.out.println("Add Killreal in table");

        List<User> users = service.getAllUsers();
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        service.cleanUsersTable();

        service.dropUsersTable();
    }
}
