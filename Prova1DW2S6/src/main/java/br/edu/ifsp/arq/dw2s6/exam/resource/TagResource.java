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

import br.edu.ifsp.arq.dw2s6.exam.domain.model.Tag;
import br.edu.ifsp.arq.dw2s6.exam.repository.TagRepository;
import br.edu.ifsp.arq.dw2s6.exam.service.TagService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tags")
@Valid
public class TagResource {
	
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private TagService tagService;
	
	//Retorna todas tags
	//GET
	@GetMapping
	public List<Tag> list(){
		return tagRepository.findAll();
	}
	
	//Cria Tag
		//POST
		//Exemplo: 
//		{
//		"name": "Casa"
//	    }
			@PostMapping
			public Tag create(@RequestBody Tag tag, HttpServletResponse response) {
				return tagRepository.save(tag);
			}
			
			//Buscar Tag por Id
			@GetMapping("/{id}")
			public ResponseEntity<Tag> findById(@PathVariable Long id){
				Optional<Tag> tag = tagRepository.findById(id);
				if(tag.isPresent()) {
					return ResponseEntity.ok(tag.get());
				}
				return ResponseEntity.notFound().build();
			}
			
			//Deleta Tag por Id
			@DeleteMapping("/{id}")
			@ResponseStatus(HttpStatus.NO_CONTENT)
			public void remove(@PathVariable Long id) {
				tagRepository.deleteById(id);
			}
			
			
			//Atualiza Tag por Id
			@PutMapping("/{id}")
			public ResponseEntity<Tag> update(@PathVariable Long id, @Valid @RequestBody Tag tag) {
				Tag tagSaved = tagService.update(id, tag);
				return ResponseEntity.ok(tagSaved);
			}

	
}
