package com.rex.boilerplate.springboot.security.service;

import com.rex.boilerplate.springboot.model.User;
import com.rex.boilerplate.springboot.security.dto.AuthenticatedUserDto;
import com.rex.boilerplate.springboot.security.dto.RegistrationRequest;
import com.rex.boilerplate.springboot.security.dto.RegistrationResponse;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
public interface UserService {

	User findByUsername(String username);

	RegistrationResponse registration(RegistrationRequest registrationRequest);

	AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

}
