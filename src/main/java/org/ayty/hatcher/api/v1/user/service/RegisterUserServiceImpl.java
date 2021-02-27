package org.ayty.hatcher.api.v1.user.service;

import javax.transaction.Transactional;

import org.ayty.hatcher.api.v1.user.dto.OutRegisterDTO;
import org.ayty.hatcher.api.v1.user.dto.RegisterUserDTO;
import org.ayty.hatcher.api.v1.user.entity.User;
import org.ayty.hatcher.api.v1.user.exception.EmailNotValidException;
import org.ayty.hatcher.api.v1.user.exception.InvalidDataException;
import org.ayty.hatcher.api.v1.user.exception.UserAlreadyExistsException;
import org.ayty.hatcher.api.v1.user.jpa.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class RegisterUserServiceImpl implements RegisterUserService {
	
	private final UserRepository userDB;
	
	private final PasswordEncoder passwordEncoder;	

	
	@Transactional
	public OutRegisterDTO save(RegisterUserDTO user) {	
		
		if(userDB.existsByLogin(user.getLogin())==true){
			throw new UserAlreadyExistsException();	
		}
		if(userDB.existsByEmail(user.getEmail())==true) {
			throw new UserAlreadyExistsException();
		}
		if(!user.getEmail().contains("@")) {
			throw new EmailNotValidException();
		}
		if(user.getLogin().matches("[a-zA-Z.]*")) {
			String EncryptedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(EncryptedPassword);
			User userImpl = User.to(user);
			userDB.save(userImpl);
			return new OutRegisterDTO(userImpl);
		}
		
		else {
			throw new InvalidDataException();
		}
	}
	
}
		
	

