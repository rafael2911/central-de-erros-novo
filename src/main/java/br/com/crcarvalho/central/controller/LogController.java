package br.com.crcarvalho.central.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.crcarvalho.central.controller.form.LogForm;
import br.com.crcarvalho.central.entity.Log;
import br.com.crcarvalho.central.service.LogService;

@RestController
@RequestMapping("logs")
public class LogController {
	
	@Autowired
	private LogService logService;
	
	@GetMapping
	@ResponseBody
	public Page<Log> listar(@PageableDefault(page = 0, size = 10) Pageable paginacao) {
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
	
}
