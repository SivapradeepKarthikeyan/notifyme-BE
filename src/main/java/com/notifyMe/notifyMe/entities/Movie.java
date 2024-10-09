package com.notifyMe.notifyMe.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "user")  // Exclude user from toString to prevent circular reference
//Used for bi-directional mapping
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "movieId")
public class Movie {
    @Id
    private String movieId;
    private String movieName;
    private String movieUrl;
    //mapping back to users.
    //Telling that one movie can have multiple users.
    @ManyToMany(mappedBy = "movie",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("movie")  // Ignore the movie property when serializing users
    private List<User> user;
}
