package team.manager.model;

import java.io.Serializable;

public class School implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	
	public School() {}
	
	public School(final String s) {
		this.name = s;
	}
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
