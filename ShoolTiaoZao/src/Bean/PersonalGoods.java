package Bean;

public class PersonalGoods {
     private String goodsname;
     private String goodsprice;
     private String time;
     private String goodsPictureAddress;
     private String goodsID;
     private String username;
     private String userID;
     public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	private int NumOfGoodsInshopCart;//单个商品在购物车中的数量
	public int getNumOfGoodsInshopCart() {
		return NumOfGoodsInshopCart;
	}
	public void setNumOfGoodsInshopCart(int numOfGoodsInshopCart) {
		NumOfGoodsInshopCart = numOfGoodsInshopCart;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getGoodsprice() {
		return goodsprice;
	}
	public void setGoodsprice(String goodsprice) {
		this.goodsprice = goodsprice;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getGoodsPictureAddress() {
		return goodsPictureAddress;
	}
	public void setGoodsPictureAddress(String goodsPictureAddress) {
		this.goodsPictureAddress = goodsPictureAddress;
	}
     
}
