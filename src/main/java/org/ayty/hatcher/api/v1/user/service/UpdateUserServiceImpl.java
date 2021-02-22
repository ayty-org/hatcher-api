package org.ayty.hatcher.api.v1.user.service;

import java.util.Optional;

import org.ayty.hatcher.api.v1.user.dto.UpdateUserDTO;
import org.ayty.hatcher.api.v1.user.entity.User;
import org.ayty.hatcher.api.v1.user.exception.InvalidDataException;
import org.ayty.hatcher.api.v1.user.jpa.UserRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UpdateUserServiceImpl implements UpdateUserService{
	
	
	private final UserRepository userDB;
	
	public UpdateUserDTO updateUser(Long id, User update) {
		Optional<User> user = userDB.findById(id);
		if (user.isPresent()) {
			User userUpdate = user.get();
			userUpdate.setLogin(update.getLogin());
			userUpdate.setEmail(update.getEmail());
			userUpdate.setFullname(update.getFullname());
			userUpdate.setImage(update.getImage());
			userDB.save(userUpdate);
			return new UpdateUserDTO(user.get());
		} else {
			throw new InvalidDataException();
		}
		
	}
}