package usersystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {


    @Autowired
    public ConsoleRunner() {
    }

    @Override
    public void run(String... args) {

    }
}
