package cafepackage;

import userpackage.User;

public class BuildOrder {
	
	private Cafe cafeInfo;
	private User userInfo;
	
	//constructors
	public BuildOrder(Cafe cafeInfo, User userInfo){
		setCafeInfo(cafeInfo);
		setUserInfo(userInfo);
	}

	//setters and getters
	public Cafe getCafeInfo() {
		return cafeInfo;
	}

	public void setCafeInfo(Cafe cafeInfo) {
		this.cafeInfo = cafeInfo;
	}

	public User getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}
	
	
	public void buildOrderSummary(){
		
	}

}
