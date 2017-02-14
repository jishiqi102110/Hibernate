package yishan.Po;

public class User {
	private String name;
	private String password;
	private String tel;
	private String email;
	private String id;
	
	public User(){
		
	}
	public User(String name,String password){
		this.name=name;
		this.password=password;
	}
	public User(String name, String password, String tel, String email) {
	
		this.name = name;
		this.password = password;
		this.tel = tel;
		this.email = email;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
