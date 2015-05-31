package ru.test.vaadin;

/**
 *
 * @author APronchakov <artem.pronchakov@gmail.com>
 */
public class CustomValue {
	private Long id;
	private String name;

	public CustomValue(Long id, String name) {
		this.id = id;
		this.name = name;
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
