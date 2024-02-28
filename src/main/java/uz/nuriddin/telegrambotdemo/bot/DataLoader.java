package uz.nuriddin.telegrambotdemo.bot;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uz.nuriddin.telegrambotdemo.payload.Person;
import uz.nuriddin.telegrambotdemo.payload.User;

@Component
public class DataLoader {

    private static final String apiUrl1 = "https://jsonplaceholder.typicode.com/users";
    private static final String apiUrl2 = "https://dummyjson.com/users";

    public static User[] users = {};
    public static Person[] people = {};

    @PostConstruct
    public static void getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        users = restTemplate.getForObject(apiUrl1, User[].class);
        people = new RestTemplate().getForObject(apiUrl2, Person[].class);
    }


}
