package com.notifyMe.notifyMe.entities;

import com.fasterxml.jackson.annotation.*;
import com.notifyMe.notifyMe.converters.HashMapConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "movie")  // Exclude movie from toString to prevent circular reference
//Used for bi-directional mapping
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User {
    @Id
    private String userId;
    private String userName;
    //converts the hashmap into a JSON using objectMapper.writeValueAsString
    @Convert(converter = HashMapConverter.class)
    private HashMap<String,String> userNotificationTypes;

    //If cascade is not present then ,movie entity must be saved first and then the user entity must be saved.
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "usersMoviesMapping",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "movieId")
    )
    //Telling that one user can have multiple movies
    @JsonIgnoreProperties("user")  // Ignore the user property when serializing movies
    private List<Movie> movie;
}
