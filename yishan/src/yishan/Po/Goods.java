package yishan.Po;

public class Goods {
	private String id;
	private String name;
	private String type;
	private String discription;
    private User user;
    private String time;
    private String pictureAddress;
    
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPictureAddress() {
		return pictureAddress;
	}
	public void setPictureAddress(String pictureAddress) {
		this.pictureAddress = pictureAddress;
	}
	
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", type=" + type + ", discription=" + discription + ", user="
				+ user + ", time=" + time + ", pictureAddress=" + pictureAddress + "]";
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
    
}
