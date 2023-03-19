package com.smhrd.model;

public class AdminEexerciseDTO {
	private String rev_machine;
	private int machine_count;
	
	public AdminEexerciseDTO(String rev_machine, int machine_count) {
		super();
		this.rev_machine = rev_machine;
		this.machine_count = machine_count;
	}
	public String getRev_machine() {
		return rev_machine;
	}
	public void setRev_machine(String rev_machine) {
		this.rev_machine = rev_machine;
	}
	public int getMachine_count() {
		return machine_count;
	}
	public void setMachine_count(int machine_count) {
		this.machine_count = machine_count;
	}
	
}
