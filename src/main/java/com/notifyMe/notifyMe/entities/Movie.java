package com.notifyMe.notifyMe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    private String movieId;
    private String movieName;
    private String movieUrl;
    //mapping back to users.
    @ManyToMany(mappedBy = "movie")
    private Set<User> users;
}
