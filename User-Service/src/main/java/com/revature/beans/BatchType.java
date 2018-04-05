package com.revature.beans;

/**
 * Author: Devin Dellamano
 * Purpose: Provides the type for each batch
 */
public class BatchType {

	private int typeId;
	private String typeName;
	// length = 10 for now, this defaults to 10 and can't be changed.
	private int length = 10; // in the future, this field can be editable.

	public BatchType() {
	}

	public BatchType(int id, String name, int length) {
		super();
		this.typeId = id;
		this.typeName = name;
		this.length = length;
	}

	public int getId() {
		return typeId;
	}

	public void setId(int id) {
		this.typeId = id;
	}

	public String getName() {
		return typeName;
	}

	public void setName(String name) {
		this.typeName = name;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "BatchType [/n(Batch Type ID) /t id = " + typeId
				+ "/n(Batch Type Name) /t name = " + typeName
				+ "/n(Batch Type Length) /t length = " + length + "]";
	}

}
