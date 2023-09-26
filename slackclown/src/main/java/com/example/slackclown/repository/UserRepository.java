package com.example.slackclown.repository;

import com.example.slackclown.entity.AppUser;
import com.example.slackclown.entity.Status;
import com.example.slackclown.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findByOtp(String otp);

    Optional<UserRole> findByIdAndRoleInAndStatus(Long userId, Iterable<UserRole> adminRoles, Status status);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser SET " +
            "firstName = COALESCE(:firstName, firstName), " +
            "lastName = COALESCE(:lastName, lastName), " +
            "fullName = COALESCE(:fullName, fullName), " +
            "dob = COALESCE(:dob, dob), " +
            "password = COALESCE(:password, password), " +
            "profilePicture = COALESCE(:profilePicture, profilePicture), " +
            "email = COALESCE(:email, email), " +
            "mobile = COALESCE(:mobile, mobile), " +
            "aboutMe = COALESCE(:aboutMe, aboutMe), " +
            "bio = COALESCE(:bio, bio), " +
            "away = COALESCE(:away, away) " +
            "WHERE id = :id")
    void updateUser(@Param("firstName") String firstName,
                    @Param("lastName") String lastName,
                    @Param("fullName") String fullName,
                    @Param("dob") LocalDateTime dob,
                    @Param("password") String password,
                    @Param("profilePicture") String profilePicture,
                    @Param("email") String email,
                    @Param("mobile") String mobile,
                    @Param("aboutMe") String aboutMe,
                    @Param("bio") String bio,
                    @Param("away") Boolean away,
                    @Param("id") Long id);

}
