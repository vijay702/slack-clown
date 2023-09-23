package com.example.slackclown.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "app_user")
public class AppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String fullName;

    private LocalDateTime dob;

    private String password;

    private String resetToken;

    private String profilePicture;

    private String statusUpdate;

    private String email;

    private String mobile;

    private String aboutMe;

    private Boolean away;

    private String bio;
}
