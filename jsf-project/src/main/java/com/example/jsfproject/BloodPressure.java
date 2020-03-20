package com.example.jsfproject;

import java.math.BigDecimal;
import java.util.Date;

public class BloodPressure {

	protected BpReadingDTO dto;
	
	public BloodPressure() {
		initialize();
	}

	public BloodPressure(BpReadingDTO dto) {
		initialize();
		this.dto = dto;
	}
	private void initialize() {
		this.dto = new BpReadingDTO();
	}

	public BpReadingDTO getDto() {
		return dto;
	}

	public void setDto(BpReadingDTO dto) {
		this.dto = dto;
	}

	/*== delegates ==*/
	public String getDescription() {
		return dto.getDescription();
	}

	public void setDescription(String value) {
		dto.setDescription(value);
	}

	public int getDiastolic() {
		return dto.getDiastolic();
	}

	public void setDiastolic(int value) {
		dto.setDiastolic(value);
	}

	public long getId() {
		return dto.getId();
	}

	public void setId(long value) {
		dto.setId(value);
	}

	public int getPulse() {
		return dto.getPulse();
	}

	public void setPulse(int value) {
		dto.setPulse(value);
	}

	public int getSystolic() {
		return dto.getSystolic();
	}

	public void setSystolic(int value) {
		dto.setSystolic(value);
	}

	public String getTags() {
		return dto.getTags();
	}

	public void setTags(String value) {
		dto.setTags(value);
	}

	public BigDecimal getWeight() {
		return dto.getWeight();
	}

	public void setWeight(BigDecimal value) {
		dto.setWeight(value);
	}

	public String getInfo() {
		return dto.getInfo();
	}

	public Date getDate() {
		return dto.getDate();
	}

	public void setDate(Date value) {
		dto.setDate(value);
	}
	
	
	
}
