package cafepackage;

import java.sql.SQLException;
import java.util.ArrayList;
import testmain.DbConnect;

public class Cafe {
	
	private String cafeName;
	private String cafeId;
	private ArrayList<FoodItem> foodItemList;
	private DbConnect dbConnect;
	
	

	//constructors
	public Cafe(){
		
	}
	public Cafe(String cafeId){
		setCafeId(cafeId);
	}
	public Cafe(String cafeName,String cafeId){
		setCafeId(cafeId);
		setCafeName(cafeName);
	}

	//setters and getters
	public String getCafeName() {
		return cafeName;
	}

	public void setCafeName(String cafeName) {
		this.cafeName = cafeName;
	}

	public String getCafeId() {
		return cafeId;
	}

	public void setCafeId(String cafeId) {
		this.cafeId = cafeId;
	}

	public ArrayList<FoodItem> getFoodItemList() {
		return foodItemList;
	}

	public void setFoodItemList(ArrayList<FoodItem> foodItemList) {
		this.foodItemList = foodItemList;
	}

	public DbConnect getDbConnect() {
		return dbConnect;
	}

	public void setDbConnect() {
		this.dbConnect = new DbConnect();
	}
	
	/**
	 * Displays the list of cafes in Campus Smart Cafe
	 * @return
	 * @throws IllegalStateException
	 * @throws SQLException
	 */
    public String displayCafeList() throws IllegalStateException, SQLException{
    	
    	String query = "select * from cafe";
    	setDbConnect();
    	getDbConnect().setQuery(query);
    	return  getDbConnect().displayData();
    }
    

	/**
	 * Displays menu of the cafe
	 * @param cafeSelection
	 * @throws IllegalStateException
	 * @throws SQLException
	 */
	public String displayCafeMenu(String cafeSelection) throws IllegalStateException, SQLException{
		String query =  "(select * from food_item where fooditem_id in(select item_id from cafe_menu cm where cafe_id = '"+cafeSelection+"'))"
                + "union (select * from drink_item where drinkitem_id in(select item_id from cafe_menu cm where cafe_id = '"+cafeSelection+"'))";
		    getDbConnect().setQuery(query);
		    return String.format(getDbConnect().displayData());
		 }
	
	/**
	 * Display the order summary of the user
	 * @param orderId
	 * @return
	 * @throws IllegalStateException
	 * @throws SQLException
	 */
	public String displayOrderSummary(String orderId) throws IllegalStateException, SQLException{
		String query = "";
		foodItemList = new ArrayList<FoodItem>();
		if(orderId.startsWith("F")){
		     query =  "select fooditem_name,calories,price,ingredients from food_item where fooditem_id = '"+orderId+"'";
		}
		else if(orderId.startsWith("D")){
			query =  "select drinkitem_name,calories,price,ingredients from drink_item where drinkitem_id = '"+orderId+"'";
		}
	        getDbConnect().setQuery(query);
		    String result =  getDbConnect().displayData();
		    String[] resultSplit = result.split(" ");
		    FoodItem foodItem = new FoodItem(resultSplit[0],resultSplit[1],resultSplit[2],resultSplit[3]);
		    foodItemList.add(foodItem);
		    return foodItemList.toString();
	}

}
