package com.guilhermeoliveira.honestmarket.dto;

import java.util.Objects;

public class OccupationDTO {

	private Integer id;
	private String occupation;
	private String description;

    public OccupationDTO() {
    }

	public OccupationDTO(Integer id, String occupation, String description) {
		super();
		this.id = id;
		this.occupation = occupation;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		OccupationDTO other = (OccupationDTO) obj;
		return Objects.equals(id, other.id);
	}
}
