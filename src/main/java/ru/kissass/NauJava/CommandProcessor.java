package ru.kissass.NauJava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kissass.NauJava.businesLogic.UserService;

@Component
public class CommandProcessor
{
    private final UserService userService;
    @Autowired
    public CommandProcessor(UserService userService)
    {
        this.userService = userService;
    }
    public void processCommand(String input)
    {
        String[] cmd = input.split(" ");
        try{
            switch (cmd[0])
            {
                case "create" -> {
                    userService.createUser(Long.valueOf(cmd[1]), cmd[2]);
                    System.out.println("User added");
                }
                case "delete" -> {
                    userService.deleteById(Long.valueOf(cmd[1]));
                    System.out.println("User deleted");
                }
                case "update" -> {
                    userService.updateLogin(Long.valueOf(cmd[1]), cmd[2]);
                    System.out.println("User login updated");
                }
                case "read" -> System.out.println(userService.findById(Long.valueOf(cmd[1])));
                default -> System.out.println("Entered unknown command...");
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid number of parameters");
        }
    }
}