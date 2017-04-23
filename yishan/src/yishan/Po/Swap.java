package yishan.Po;

import java.util.Date;

public class Swap {
	private String swapID;//交换ID
	private String pic;
	private String dpic;	
	private String distributor;//发布者的ID
	private String getter;//寻求交换的人的ID
	private String state;//交换状态
	private String evaluate;
	private Date time;
	private String disname;//发布者名字
	private String getname;//寻求交易人名字
	private String disgname;//发布的物品名称
	private String getgname;//作为交换的物品名称
	private String disgID;//发布的物品名称
	private String getgID;//作为交换的物品ID
	public String getSwapID() {
		return swapID;
	}
	public String getDpic() {
		return dpic;
	}
	public void setDpic(String dpic) {
		this.dpic = dpic;
	}

	public void setSwapID(String swapID) {
		this.swapID = swapID;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getDistributor() {
		return distributor;
	}
	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	public String getGetter() {
		return getter;
	}
	public void setGetter(String getter) {
		this.getter = getter;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getDisname() {
		return disname;
	}
	public void setDisname(String disname) {
		this.disname = disname;
	}
	public String getGetname() {
		return getname;
	}
	public void setGetname(String getname) {
		this.getname = getname;
	}
	public String getDisgname() {
		return disgname;
	}
	public void setDisgname(String disgname) {
		this.disgname = disgname;
	}
	public String getGetgname() {
		return getgname;
	}
	public void setGetgname(String getgname) {
		this.getgname = getgname;
	}
	public String getDisgID() {
		return disgID;
	}
	public void setDisgID(String disgID) {
		this.disgID = disgID;
	}
	public String getGetgID() {
		return getgID;
	}
	public void setGetgID(String getgID) {
		this.getgID = getgID;
	}
	
	

	
}
