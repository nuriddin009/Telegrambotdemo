package uz.nuriddin.telegrambotdemo.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Company {
    private String name;
    @JsonProperty("catchPhrase")
    private String catchPhrase;
    private String bs;
}
