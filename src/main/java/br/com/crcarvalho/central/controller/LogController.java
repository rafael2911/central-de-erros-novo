package br.com.crcarvalho.central.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	
}
