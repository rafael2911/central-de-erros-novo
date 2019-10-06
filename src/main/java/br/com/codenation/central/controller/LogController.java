package br.com.codenation.central.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.codenation.central.controller.form.LogForm;
import br.com.codenation.central.entity.Log;
import br.com.codenation.central.repository.filtro.LogFilter;
import br.com.codenation.central.service.LogService;

@RestController
@RequestMapping("logs")
public class LogController {
	
	@Autowired
	private LogService logService;
	
	@GetMapping
	@ResponseBody
	public Page<Log> listar(LogFilter filter, @PageableDefault(page = 0, size = 10) Pageable paginacao) {
		
		if(filter.getLevel() != null) {
			return this.logService.findLogByEnvironmentAndLevelContaining(filter.getEnvironment(),
					filter.getLevel(), paginacao);
		}
		
		if(filter.getTitle() != null) {
			return this.logService.findLogByEnvironmentAndTitleContaining(filter.getEnvironment(),
					filter.getTitle(), paginacao);
		}
		
		if(filter.getSource() != null) {
			return this.logService.findLogByEnvironmentAndSourceContaining(filter.getEnvironment(),
					filter.getSource(), paginacao);
		}
		
		if(filter.getEnvironment() != null) {
			return this.logService.findLogByEnvironment(filter.getEnvironment(), paginacao);
		}

		return this.logService.findAll(paginacao);
	}
	
	@PostMapping
	public ResponseEntity<Log> cadastrar(@RequestBody LogForm form, UriComponentsBuilder uriBuilder){
		Log log = form.converter();
		this.logService.toSave(log);
		
		URI location = uriBuilder.path("/logs/{id}")
				.buildAndExpand(log.getId())
				.toUri();
		
		return ResponseEntity.created(location).body(log);
	}
	
	@GetMapping("{id}")
	public Log getLog(@PathVariable("id") Long id) {
		
		return this.logService.findById(id);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> apagar(@PathVariable("id") Long id){
		
		this.logService.toRemove(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("{id}")
	public Log arquivar(@PathVariable("id") Long id){
		
		return this.logService.toFile(id);
	}
	
}
