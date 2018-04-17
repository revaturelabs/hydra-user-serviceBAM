package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Last updated by: (1802-Matt)
 * @author Unknown
 * 
 */

@Entity
@Table(name = "USERS")
public class BamUser {

	@Id
	@Column(name = "USER_ID")
	@SequenceGenerator(name = "USERID_SEQ", sequenceName = "USERID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERID_SEQ")
	private int userId;

	@Column(name = "FIRST_NAME")
	@NotNull(message = "First name cannot be empty")
	private String firstName;

	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Column(name = "LAST_NAME")
	@NotNull(message = "Last name cannot be empty")
	private String lastName;

	@Column(name = "EMAIL", unique = true)
	@NotNull(message = "e-mail address cannot be empty")
	private String email;

	@Column(name = "PASSWORD")
	@NotNull(message = "Password cannot be empty")
	private String password;

	@Column(name = "ROLE")
	private Role role;

	@Column(name = "BATCH_ID")
	private Integer batchId;

	@Column(name = "MAIN_PHONE")
	@NotNull(message = "Primary phone cannot be empty")
	private String phone;

	@Column(name = "SECONDARY_PHONE")
	private String phone2;

	@Column(name = "SKYPE_ID")
	private String skype;

	@Column(name = "PASSWORD_BAK") // This is a backup password that will be
									// used when the user needs to reset their password.
	private String temporaryPassword; //should be a better way to do this

	@Column(name = "ASSIGNFORCE_ID")
	private Integer assignForceID;

	public BamUser() {
		super();
	}

	public BamUser(String fName, String mName, String lName, String email, String pwd, Role role, int batch,
			String phone, String phone2, String skype, String pwd2) {
		super();
		this.firstName = fName;
		this.middleName = mName;
		this.lastName = lName;
		this.email = email;
		this.password = pwd;
		this.role = role;
		this.batchId = batch;
		this.phone = phone;
		this.phone2 = phone2;
		this.skype = skype;
		this.temporaryPassword = pwd2;
	}


	public BamUser(String fName, String mName, String lName, String email, String pwd, Role role, int batch,
			String phone, String phone2, String skype, String pwd2, Integer AssignForceID) {

		this(fName, mName, lName, email, pwd, role, batch, phone, phone2, skype, pwd2);
		this.assignForceID = AssignForceID;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setfirstName(String fName) {
		this.firstName = fName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String mName) {
		this.middleName = mName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lName) {
		this.lastName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pwd) {
		this.password = pwd;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role i) {
		this.role = i;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getTemporaryPassword() {
		return temporaryPassword;
	}

	public void setTemporaryPassword(String pwd2) {
		this.temporaryPassword = pwd2;
	}

	public Integer getAssignForceID() {
		return assignForceID;
	}

	public void setAssignForceID(Integer assignForceID) {
		this.assignForceID = assignForceID;
	}

	public int getBatch() {
		return batchId;
	}

	public void setBatch(Integer invalid) {
		this.batchId = invalid;
	}

	/**
	 * Last updated by: (1802-Matt)
	 * @author Unknown
	 * @return String
	 * 
	 */	
	@Override
	public String toString() {
		return "BamUser [\n(User Id) \t userId = " + userId
				+ "\n(First Name) \t firstName = " + firstName
				+ "\n(Middle Name) \t middleName = " + middleName
				+ "\n(Last Name) \t lastName = " + lastName
				+ "\n(Email) \t email = " + email
				+ "\n(Password) \t password = " + password
				+ "\n(Role) \t role = " + role
				+ "\n(Batch) \t batchId = " + batchId
				+ "\n(Phone Number) \t phone = " + phone
				+ "\n(2nd Phone Number) \t phone2 = " + phone2
				+ "\n(Skype ID) \t skype = " + skype
				+ "\n(Back up Password) \t temporaryPassword = " + temporaryPassword
				+ "\n(Forcefully assigns an ID) \t assignForceID = " + assignForceID + "]";
	}
}