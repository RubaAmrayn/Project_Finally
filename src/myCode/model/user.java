package myCode.model;

public class user {
	private String first_name, last_name,username,Email;
	
	public user(String firstName, String lastName,String user,String email) {
		this.first_name=firstName;
		this.last_name=lastName;
		this.username=user;
		this.Email=email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String user) {
		this.username= user;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		this.Email= email;
	}
	
}


