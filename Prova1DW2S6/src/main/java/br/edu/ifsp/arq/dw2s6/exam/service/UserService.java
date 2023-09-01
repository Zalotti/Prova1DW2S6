package br.edu.ifsp.arq.dw2s6.exam.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.dw2s6.exam.domain.model.User;
import br.edu.ifsp.arq.dw2s6.exam.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User update(Long id, User user) {
		User userSaved = userRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(user, userSaved, "id");
		return userRepository.save(userSaved);
	}
}
