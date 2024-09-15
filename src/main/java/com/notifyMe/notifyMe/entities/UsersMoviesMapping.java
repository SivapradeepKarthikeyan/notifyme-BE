package com.notifyMe.notifyMe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "usersMoviesMapping")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersMoviesMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userMovieMappingID;
    String userId;
    String movieId;
}
