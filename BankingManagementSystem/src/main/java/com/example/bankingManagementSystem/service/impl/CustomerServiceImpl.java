package com.example.bankingManagementSystem.service.impl;

import com.example.bankingManagementSystem.auth.JwtTokenValidation;
import com.example.bankingManagementSystem.converter.CustomerDtoConverter;
import com.example.bankingManagementSystem.dto.EmailDto;
import com.example.bankingManagementSystem.dto.ResetPasswordDto;
import com.example.bankingManagementSystem.dto.CustomerDto;
import com.example.bankingManagementSystem.email.EmailSender;
import com.example.bankingManagementSystem.entity.Customer;
import com.example.bankingManagementSystem.repository.CustomerRepository;
import com.example.bankingManagementSystem.response.Response;
import com.example.bankingManagementSystem.service.CustomerService;
import com.example.bankingManagementSystem.util.ImageUploaderUtill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

/**
 * this service class is for creating slack
 * users and register purpose and user operations
 */
@Service
public class CustomerServiceImpl implements CustomerService {


    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private static final String USER = "user";

    @Autowired
    CustomerRepository customerRepository;


    @Autowired
    CustomerDtoConverter customerDtoConverter;

    @Autowired
    ImageUploaderUtill imageUploaderUtill;

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    JwtTokenValidation jwtTokenValidation;


    @Override
    public Object createCustomer(CustomerDto customerDto) {
        return customerRepository.findByEmail(customerDto.getEmail())
                .map(existingUser -> Response.BAD_REQUEST("Customer already exist"))
                .orElseGet(() -> {
                    String profilePictureData = imageUploaderUtill.imageUploader(customerDto.getProfilePictureData(),
                            USER);
                    Customer customer = customerDtoConverter.dtoToEntity(customerDto);
                    customer.setProfilePicture(profilePictureData);
                    customerRepository.save(customer);
                    return Response.OK();
                });
    }

    @Override
    public Object customerLogin(CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(customerDto.getEmail());
        if (optionalCustomer.isEmpty()) {
            return Response.NOT_FOUND("Customer not found");
        }
        Customer customer = optionalCustomer.get();
        if (!customer.getPassword().equals(customerDto.getPassword())) {
            return HttpStatus.UNAUTHORIZED;
        }
        return jwtTokenValidation.generateToken(customerDto.getEmail());
    }


    @Override
    public Object updateCustomer(Long id, CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()) {
            return Response.NOT_FOUND("Customer not found");
        }
        customerRepository.updateUser(customerDto.getName(), customerDto.getAddress(), customerDto.getPhoneNumber(),
                customerDto.getDob(), customerDto.getEmail(), customerDto.getProfilePicture(),
                customerDto.getStatus(), customerDto.getPassword(), customerDto.getOtp(), id);
        return Response.OK();
    }

    @Override
    public Object sendOtp(EmailDto emailDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(emailDto.getEmail());
        if (optionalCustomer.isEmpty()) {
            return Response.NOT_FOUND("Customer is not present with this email");
        }
        Customer customer = optionalCustomer.get();
        /// have to replace with update query , all field update query
        customer.setOtp(generateOtp());
        EmailSender.sendNoReplyEmail(mailSender, customer.getEmail(), "VerificationOtp", generateOtp());
        customerRepository.save(customer);
        return ResponseEntity.ok("verification otp send to the customer successfully");
    }

    @Override
    public Object resetPasswordWithOtp(ResetPasswordDto resetPasswordDto) {
        Optional<Customer> optionalCustomerByOtp = customerRepository.findByOtp(resetPasswordDto.getOtp());
        if (optionalCustomerByOtp.isEmpty() &&
                Objects.equals(resetPasswordDto.getNewPassword(), resetPasswordDto.getConfirmPassword())) {
            return Response.NOT_FOUND("otp is not valid Or new password and confirm password are not same");
        }
        /// have to replace with update query , all field update query
        Customer customer = optionalCustomerByOtp.get();
        customer.setPassword(resetPasswordDto.getNewPassword());
        customerRepository.save(customer);
        return Response.OK();
    }

    @Override
    public Object resetPasswordWithOldPassword(ResetPasswordDto resetPasswordDto, String usernameFromJwt) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(usernameFromJwt);
        if (optionalCustomer.isEmpty()) {
            return Response.NOT_FOUND("Customer not found");
        }
        Customer customer = optionalCustomer.get();
        if (!Objects.equals(resetPasswordDto.getOldPassword(), customer.getPassword())){
            return Response.BAD_REQUEST("The Old password does not exist!");
        }
        if (!Objects.equals(resetPasswordDto.getNewPassword(), resetPasswordDto.getConfirmPassword())) {
            return Response.NOT_FOUND("The New password and confirm password are not same");
        }
        customer.setPassword(resetPasswordDto.getNewPassword());
        customerRepository.save(customer);
        return Response.OK();
    }


    private String generateOtp() {
        Random random = new Random();
        // Generate an 8-digit random OTP
        int min = 10000000; // Smallest 8-digit number
        int max = 99999999; // Largest 8-digit number
        int otp = random.nextInt(max - min + 1) + min;
        return String.valueOf(otp);
    }
}
