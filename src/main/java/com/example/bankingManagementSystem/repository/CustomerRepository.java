package com.example.bankingManagementSystem.repository;

import com.example.bankingManagementSystem.entity.Customer;
import com.example.bankingManagementSystem.enums.CustomerType;
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
    @Query(value = "UPDATE Customer SET " +
            "name = COALESCE(:name, name), " +
            "address = COALESCE(:address, address), " +
            "phone_number = COALESCE(:phoneNumber, phone_number), " +
            "date_of_birth = COALESCE(:dob, date_of_birth), " +
            "email = COALESCE(:email, email), " +
            "profile_picture = COALESCE(:profilePicture, profile_picture), " +
            "status = COALESCE(:status, status), " +
            "password = COALESCE(:password, password), " +
            "otp = COALESCE(:otp, otp), " +
            "customer_type = COALESCE(:customerType, customer_type) " +
            "WHERE customer_id = :id", nativeQuery = true)
    void updateUser(@Param("name") String name,
                    @Param("address") String address,
                    @Param("phoneNumber") String phoneNumber,
                    @Param("dob") Date dob,
                    @Param("email") String email,
                    @Param("profilePicture") String profilePicture,
                    @Param("status") Status status,
                    @Param("password") String password,
                    @Param("otp") String otp,
                    @Param("customerType")CustomerType customerType,
                    @Param("id") Long id);

}
