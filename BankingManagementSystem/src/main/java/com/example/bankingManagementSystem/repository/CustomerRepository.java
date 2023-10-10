package com.example.bankingManagementSystem.repository;

import com.example.bankingManagementSystem.entity.Customer;
import com.example.bankingManagementSystem.enums.Status;
import com.example.bankingManagementSystem.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByOtp(String otp);


    @Transactional
    @Modifying
    @Query("UPDATE Customer SET " +
            "name = COALESCE(:name, name), " +
            "address = COALESCE(:address, address) " +
            "phoneNumber = COALESCE(:phoneNumber, phoneNumber), " +
            "dob = COALESCE(:dob, dob), " +
            "email = COALESCE(:email, email), " +
            "profilePicture = COALESCE(:profilePicture, profilePicture), " +
            "status = COALESCE(:status, status), " +
            "password = COALESCE(:password, password), " +
            "otp = COALESCE(:otp, otp) " +
            "WHERE id = :id")
    void updateUser(@Param("name") String name,
                    @Param("address") String address,
                    @Param("phoneNumber") String phoneNumber,
                    @Param("dob") Date dob,
                    @Param("email") String email,
                    @Param("profilePicture") String profilePicture,
                    @Param("status") String status,
                    @Param("password") String password,
                    @Param("otp") String otp,
                    @Param("id") Long id);

}
