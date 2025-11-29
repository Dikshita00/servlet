package com.demo.bean;

public class User {
	 private int id;
	    private String name;
	    private String email;
	    private String gender;
	    private String hobbies;
	    private String city;
	    private String passwordHash;
	    private String passwordSalt;
		public User() {
			super();
		}
		public User(int id, String name, String email, String gender, String hobbies, String city, String passwordHash,
				String passwordSalt) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.gender = gender;
			this.hobbies = hobbies;
			this.city = city;
			this.passwordHash = passwordHash;
			this.passwordSalt = passwordSalt;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
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
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getHobbies() {
			return hobbies;
		}
		public void setHobbies(String hobbies) {
			this.hobbies = hobbies;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getPasswordHash() {
			return passwordHash;
		}
		public void setPasswordHash(String passwordHash) {
			this.passwordHash = passwordHash;
		}
		public String getPasswordSalt() {
			return passwordSalt;
		}
		public void setPasswordSalt(String passwordSalt) {
			this.passwordSalt = passwordSalt;
		}
		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", email=" + email + ", gender=" + gender + ", hobbies="
					+ hobbies + ", city=" + city + ", passwordHash=" + passwordHash + ", passwordSalt=" + passwordSalt
					+ "]";
		}

	    
	    
}
