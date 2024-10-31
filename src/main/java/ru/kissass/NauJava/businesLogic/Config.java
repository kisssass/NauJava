package ru.kissass.NauJava.businesLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import ru.kissass.NauJava.CommandProcessor;
import ru.kissass.NauJava.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Configuration
public class Config
{
    @Autowired
    @Lazy
    private CommandProcessor commandProcessor;
    @Bean
    public CommandLineRunner commandScanner()
    {
        return args ->
        {
            try (Scanner scanner = new Scanner(System.in))
            {
                System.out.println("Enter command 'exit' to exit.");
                while (true)
                {
                    System.out.print("> ");
                    String input = scanner.nextLine();
                    if ("exit".equalsIgnoreCase(input.trim()))
                    {
                        System.out.println("Exiting the program...");
                        break;
                    }
                    commandProcessor.processCommand(input);
                }
            }
        };
    }
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<User> userContainer() {
        return new ArrayList<>();
    }


}