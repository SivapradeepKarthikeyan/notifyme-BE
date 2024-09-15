package com.notifyMe.notifyMe.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    String userId;
    String userName;
    HashMap<String,String> userNotificationTypes;
    String userMovieId;
    String userMovieName;
    String userMovieUrl;
}
