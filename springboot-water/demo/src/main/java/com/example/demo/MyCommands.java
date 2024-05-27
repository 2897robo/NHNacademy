package com.example.demo;

import com.example.demo.parse.FileParser;
import com.example.demo.parse.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
@Slf4j
public class MyCommands {

    private final FileParser fileParser;
    private final List<User> users;

    public MyCommands(FileParser fileParser) {
        this.fileParser = fileParser;
        this.users = fileParser.parseUsers();
    }

    @ShellMethod(key = "login", value = "Input = id pw")
    public String login(int id, int password) {
        for (User user : users) {
            if (user.authenticate(id, password)) {
                log.info("User {} logged in successfully", user.getName());
                return "Login successful";
            }
        }
        log.warn("Login failed for user with ID {}", id);
        return "Login failed";
    }

    @ShellMethod(key = "logout")
    public String logout() {
        return "good bye";
    }

    @ShellMethod(key = "city")
    public String city() {
        return null;
    }

    @ShellMethod(key = "bill-total")
    public String billTotal(String city, String sector, int use) {
        return null;
    }

    @ShellMethod(key = "sector")
    public String sector(String city) {
        return null;
    }

    @ShellMethod(key = "current-user")
    public String currentUser() {
        return null;
    }

    @ShellMethod (key = "price")
    public String price(String city, String sector) {
        return null;
    }
}