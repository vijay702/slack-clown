package com.example.bankingManagementSystem.auth;

import com.example.bankingManagementSystem.entity.Customer;
import com.example.bankingManagementSystem.enums.Status;
import com.example.bankingManagementSystem.enums.UserRole;
import com.example.bankingManagementSystem.repository.CustomerRepository;
import com.example.bankingManagementSystem.response.Response;
import com.google.common.base.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Aspect
@Configuration
public class JwtAspect {

    @Autowired
    CustomerRepository userRepository;

    @Autowired
    JwtTokenValidation jwtAuthorizationImpl;

    @Autowired
    HttpServletRequest request;

    @Around("@annotation(authorizer) && args(..)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, JwtAuthorizer authorizer) throws Throwable {
        String authorizationToken = request.getHeader("Authorization");
        if (!authorizer.required() && Strings.isNullOrEmpty(authorizationToken)) {
            return proceedingJoinPoint.proceed();
        }
        Long userId = validateToken(authorizationToken);
        if (Objects.nonNull(userId)) {
            Optional<UserRole> validAdminOptional = getAdminUser(userId, authorizer.authorized());
            if (validAdminOptional.isEmpty()) {
                return Response.UNAUTHORIZED("Admin does not have enough privileges!");
            }
            List<Object> methodArgs = Arrays.asList(proceedingJoinPoint.getArgs());
            methodArgs = methodArgs.stream()
                    .map(obj -> obj instanceof Customer ? validAdminOptional.get() : obj)
                    .collect(Collectors.toList());
            return proceedingJoinPoint.proceed(methodArgs.toArray());
        }
        return Response.UNAUTHORIZED("LOGOUT");
    }

    private Long validateToken(String authorizationHeader) {
        Long userId = jwtAuthorizationImpl.validateToken(authorizationHeader);
        request.setAttribute("user_id", userId);
        return userId;
    }

    private Optional<UserRole> getAdminUser(Long userId, UserRole[] authorizedRoles) {
        return null;
    }

}
