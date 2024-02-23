package uz.nuriddin.telegrambotdemo.bot;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uz.nuriddin.telegrambotdemo.payload.User;

@Component
public class DataLoader {

    private static final String apiUrl = "https://jsonplaceholder.typicode.com/users";

    public static User[] users = {};

    @PostConstruct
    public static void getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        users = restTemplate.getForObject(apiUrl, User[].class);
    }


}
