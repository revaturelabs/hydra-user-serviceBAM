package com.revature.beans;

import java.sql.Timestamp;


/**
 * @author unknown
 *
 */

public class Batch {
	
	private int batchId;
	private String batchName;
	private Timestamp startDate;
	private Timestamp endDate;
	private BamUser trainer;
	private BatchType type;

	public Batch() {
		// TODO Auto-generated constructor stub
	}

	public Batch(int id, String name, Timestamp startDate, Timestamp endDate, BamUser trainer, BatchType type) {
		super();
		this.batchId = id;
		this.batchName = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.trainer = trainer;
		this.type = type;
	}

	public int getId() {
		return batchId;
	}

	public void setId(int id) {
		this.batchId = id;
	}

	public String getName() {
		return batchName;
	}

	public void setName(String name) {
		this.batchName = name;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public BamUser getTrainer() {
		return trainer;
	}

	public void setTrainer(BamUser trainer) {
		this.trainer = trainer;
	}

	public BatchType getType() {
		return type;
	}

	public void setType(BatchType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Batch [ (Batch ID) /t id = " + batchId
				+ "/n(Batch Name) /t name = " + batchName
				+ "/n(Batch Type) /t type = " + type + "]";
	}
	
}
