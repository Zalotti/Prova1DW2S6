package br.edu.ifsp.arq.dw2s6.exam.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.arq.dw2s6.exam.domain.model.Task;
import br.edu.ifsp.arq.dw2s6.exam.repository.TaskRepository;
import br.edu.ifsp.arq.dw2s6.exam.service.TaskService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
@Valid
public class TaskResource {

	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private TaskService taskService;
	
	//Retorna todas tarefas
	//GET
	@GetMapping
	public List<Task> list(){
		return taskRepository.findAll();
	}
	
	//Cria tarefa
	//POST
	//Exemplo: 
//	{
//		"description": "Devo limpar a casa",	
//		"task_date": "2023-09-01",
//		"observation": "Terei visitas em 30 dias",
//		"user":{
//		"id": 1
//		},
//		"tag":{
//		"id": 1  
//		}
//		}
		@PostMapping
		public Task create(@RequestBody Task task, HttpServletResponse response) {
			return taskRepository.save(task);
		}
		
		//Buscar Tarefa por Id
		@GetMapping("/{id}")
		public ResponseEntity<Task> findById(@PathVariable Long id){
			Optional<Task> task = taskRepository.findById(id);
			if(task.isPresent()) {
				return ResponseEntity.ok(task.get());
			}
			return ResponseEntity.notFound().build();
		}
		
		//Deleta Tarefa por Id
		@DeleteMapping("/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void remove(@PathVariable Long id) {
			taskRepository.deleteById(id);
		}
		
		
		//Atualiza Tarefa por Id
		@PutMapping("/{id}")
		public ResponseEntity<Task> update(@PathVariable Long id, @Valid @RequestBody Task task) {
			Task taskSaved = taskService.update(id, task);
			return ResponseEntity.ok(taskSaved);
		}

}
