package com.rex.boilerplate.springboot.security.service;

import com.rex.boilerplate.springboot.service.UserValidationService;
import com.rex.boilerplate.springboot.model.User;
import com.rex.boilerplate.springboot.model.SecurityRole;
import com.rex.boilerplate.springboot.security.dto.AuthenticatedUserDto;
import com.rex.boilerplate.springboot.security.dto.RegistrationRequest;
import com.rex.boilerplate.springboot.security.dto.RegistrationResponse;
import com.rex.boilerplate.springboot.security.mapper.UserMapper;

import com.rex.boilerplate.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final UserValidationService userValidationService;

	@Override
	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	@Override
	public RegistrationResponse registration(RegistrationRequest registrationRequest) {

		userValidationService.validateUser(registrationRequest);

		final User user = UserMapper.INSTANCE.convertToUser(registrationRequest);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setUserRole(SecurityRole.USER);

		userRepository.save(user);

		final String username = registrationRequest.getUsername();
		final String registrationSuccessMessage = "User " + username + " registered successfully!";

		log.info("{} registered successfully!", username);

		return new RegistrationResponse(registrationSuccessMessage);
	}

	@Override
	public AuthenticatedUserDto findAuthenticatedUserByUsername(String username) {

		final User user = findByUsername(username);

		return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
	}
}
