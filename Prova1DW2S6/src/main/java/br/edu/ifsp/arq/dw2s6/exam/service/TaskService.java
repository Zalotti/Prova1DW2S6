package br.edu.ifsp.arq.dw2s6.exam.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.dw2s6.exam.domain.model.Task;
import br.edu.ifsp.arq.dw2s6.exam.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	public Task update(Long id, Task task) {
		Task taskSaved = taskRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(task, taskSaved, "id");
		return taskRepository.save(taskSaved);
	}

}
