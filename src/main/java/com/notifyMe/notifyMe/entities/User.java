package com.notifyMe.notifyMe.entities;

import com.notifyMe.notifyMe.converters.HashMapConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String userId;
    private String userName;
    //converts the hashmap into a JSON using objectMapper.writeValueAsString
    @Convert(converter = HashMapConverter.class)
    private HashMap<String,String> userNotificationTypes;

    //If cascade is not present then ,movie entity must be saved first and then the user entity must be saved.
    @ManyToMany(cascade =CascadeType.ALL)
    @JoinTable(
            name = "userMovieMapping",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "movieId")
    )
    private Set<Movie> movie;
}
