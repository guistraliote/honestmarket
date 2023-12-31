package com.guilhermeoliveira.honestmarket.dto;

import java.util.Objects;

public class CustomerDTO {

	private Long id;
    private String name;
    private String email;
    
    private Integer occupationId;

    public CustomerDTO() {
    }

	public CustomerDTO(Long id, String name, String email, Integer occupationId) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.occupationId = occupationId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public Integer getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(Integer occupationId) {
		this.occupationId = occupationId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDTO other = (CustomerDTO) obj;
		return Objects.equals(id, other.id);
	}

}
