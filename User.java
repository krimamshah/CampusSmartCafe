/**
 * 
 */
package userpackage;

import java.sql.SQLException;

import testmain.DbConnect;

/**
 * @author Krima
 *
 */
public class User {
	
	private String userName;
	private String userId;
	private String password;
	private DietProfile dietProfile;
	private ExpenseProfile expenseProfile;
	private DbConnect dbConnect;
	
	//constructors
	
	public User(){
		
	}
	
    public User(String userId, String password){
		
		
		setUserId(userId);
		setPassword(password);
	} 
    public User(String userName, String userId, String password, DietProfile dietProfile, ExpenseProfile expenseProfile) throws IllegalStateException, SQLException{
    	setUserName(userName);
		setUserId(userId);
		setPassword(password);
		//setDietProfile();
		setExpenseProfile();
    }
    public User(DietProfile dietProfile) throws IllegalStateException, SQLException{
    	setDietProfile();
    }
    public User(ExpenseProfile expenseProfile) throws IllegalStateException, SQLException{
    	setExpenseProfile();
    }
	
	
	//Setters and Getters
	
	public DietProfile getDietProfile() {
		return dietProfile;
	}


	public void setDietProfile() throws IllegalStateException, SQLException {
		if(verifyAccount()){
		 // this.dietProfile = dietProfile;
		 // dietProfile = new DietProfile();
		  dbConnect = new DbConnect();
		  setUserDietProfile();
		}
	}


	public ExpenseProfile getExpenseProfile() {
		return expenseProfile;
	}


	public void setExpenseProfile() throws IllegalStateException, SQLException {
		if(verifyAccount()){
			 // this.dietProfile = dietProfile;
			 // dietProfile = new DietProfile();
			  dbConnect = new DbConnect();
			  setUserExpenseProfile();
			 
			}
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserID() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	public DbConnect getDbConnect() {
		return dbConnect;
	}

	public void setDbConnect() {
		this.dbConnect = new DbConnect();
	}
	
    
	/**
	 * Verifies the user account
	 * @param username
	 * @param password
	 * @return boolean result
	 * @throws IllegalStateException
	 * @throws SQLException
	 */
	public  boolean verifyAccount() throws IllegalStateException, SQLException{
		boolean result = false;
		// dbConnect = new DbConnect();
		 setDbConnect();
		 String query = "select user_id from campus_card where user_id = '"+getUserID()+"' and password = '"+getPassword()+"'";
		 getDbConnect().setQuery(query);
		 int count = getDbConnect().getRowCount();
		 if(count==1)
			 result = true;
		return result;
	}
	
	
	/**
	 * @throws SQLException 
	 * @throws IllegalStateException 
	 * 
	 */
	private void setUserDietProfile() throws IllegalStateException, SQLException{
		String selectQuery = "select dailycalorie_intake,diet_preference from userdiet_profile where user_id= '"+userId+"'";
		getDbConnect().setQuery(selectQuery);
		String result = getDbConnect().displayData();
		String[] resultSplit = result.split(" ");
		dietProfile = new DietProfile(resultSplit[0],resultSplit[1]);
		
	}
	
	private void setUserExpenseProfile() throws IllegalStateException, SQLException{
		String selectQuery = "select available_funds from userexpense_profile where user_id= '"+userId+"'";
		getDbConnect().setQuery(selectQuery);
		String result = getDbConnect().displayData();
		String[] resultSplit = result.split(" ");
		expenseProfile = new ExpenseProfile(resultSplit[0]);
	}
	
	/**
	 * Updates the diet profile of the user
	 * @param itemCalories
	 * @param userId
	 * @throws IllegalStateException
	 * @throws SQLException
	 */
	public void updateUserDietProfile(String itemCalories) throws IllegalStateException, SQLException{
		
		setDietProfile();
		String newCalorieValue = calorieDifference(dietProfile.getDailyCalorieIntake(), itemCalories);
		String updateQuery =  "update userdiet_profile set dailycalorie_intake = '"+newCalorieValue+"' where user_id = '"+getUserID()+"'";
	        getDbConnect().updateQuery(updateQuery);
	     System.out.println("Daily calorie intake updated");
	}
	
	/**
	 * computes the calories consumed
	 * @param oldCalorieValue
	 * @param itemCalories
	 * @return
	 */
	private  String calorieDifference(String oldCalorieValue, String itemCalories){
		String calorieDifference = "";
		int oldCalories = Integer.parseInt(oldCalorieValue);
		int itemCal = Integer.parseInt(itemCalories);
		if(oldCalories>=itemCal){
			int calorieDiff = oldCalories - itemCal;
			calorieDifference = Integer.toString(calorieDiff);
		}
		else{
			 System.out.println("Exceeds the daily calorie preference");
		}
		return calorieDifference;
	}
	
	/**
	 * Updates the expense profile of the user
	 * @param itemPrice
	 * @param userId
	 * @throws IllegalStateException
	 * @throws SQLException
	 */
	public void updateUserExpenseProfile(String itemPrice) throws IllegalStateException, SQLException{
		setExpenseProfile();
		//dbConnect.setQuery(selectQuery);
		//String temp = dbConnect.displayData();
		//String oldFunds = temp.substring(0, temp.length()-2);
		String newfunds = expenseDifference(getExpenseProfile().getAvailableFunds(), itemPrice);
		String updateQuery =  "update userexpense_profile set available_funds = '"+newfunds+"' where user_id = '"+getUserID()+"'";
	        getDbConnect().updateQuery(updateQuery);
	     System.out.println("Available funds updated");  
		
	}
	
	/**
	 * Computes the available funds for the user
	 * @param oldFunds
	 * @param itemPrice
	 * @return
	 */
	private String expenseDifference(String oldFunds, String itemPrice){
		String expenseDifference = "";
		double preFunds = Double.parseDouble(oldFunds);
		double itemPri = Double.parseDouble(itemPrice);
		if(preFunds>=itemPri){
			double expDiff = preFunds - itemPri;
			//expenseDifference = Double.toString(expDiff);
			expenseDifference = String.format("%.2f", expDiff);
		}
		else{
			System.out.println("Insufficient Funds");
		}
		return expenseDifference;
	}
	
	
}
