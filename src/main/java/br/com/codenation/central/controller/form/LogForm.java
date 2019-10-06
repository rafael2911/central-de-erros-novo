package br.com.codenation.central.controller.form;

import br.com.codenation.central.entity.Log;

public class LogForm {

	private String environment;
	private String level;
	private String title;
	private String details;
	private String source;

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Log converter() {
		Log log = new Log();
		log.setEnvironment(this.environment);
		log.setLevel(this.level);
		log.setTitle(this.title);
		log.setDetails(this.details);
		log.setSource(this.source);
		
		return log;
	}

}
