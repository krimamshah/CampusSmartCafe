package cafepackage;

import java.sql.SQLException;

import testmain.DbConnect;

public class FoodItem {
	
	private String itemName;
	private String calories;
	private String price;
	private String ingredients;
	private String comments;
	private DbConnect dbConnect;
	
	
	//constructors
	public FoodItem() {
		dbConnect = new DbConnect();
	}
	public FoodItem(String itemName, String calories, String price, String ingredient){
		setCalories(calories);
		setIngredients(ingredient);
		setItemName(itemName);
		setPrice(price);
		//setComments(comments);
	}
	//setters and getters
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	public DbConnect getDbConnect() {
		return dbConnect;
	}

	public void setDbconnect(DbConnect dbConnect) {
		this.dbConnect = dbConnect;
	}

	
	public String toString(){
		return String.format(getItemName()+" "+getCalories()+" "+getPrice()+" "+getIngredients());
	}
	
	
	

}
