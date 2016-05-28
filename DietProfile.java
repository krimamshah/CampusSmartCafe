/**
 * 
 */
package userpackage;

/**
 * @author Krima
 *
 */
public class DietProfile {
	
	private String dailyCalorieIntake;
	private String diet_preferences;
	
	//constructors
	public DietProfile(){
		
	}
	public DietProfile(String dailyCalorieIntake){
		setDailyCalorieIntake(dailyCalorieIntake);
	}
	public DietProfile(String dailyCalorieIntake,String diet_preferences){
		setDailyCalorieIntake(dailyCalorieIntake);
		setDiet_preferences(diet_preferences);
	}
	
	//setters and getters
	public String getDailyCalorieIntake() {
		return dailyCalorieIntake;
	}
	public void setDailyCalorieIntake(String dailyCalorieIntake) {
		this.dailyCalorieIntake = dailyCalorieIntake;
	}
	public String getDiet_preferences() {
		return diet_preferences;
	}
	public void setDiet_preferences(String diet_preferences) {
		this.diet_preferences = diet_preferences;
	}
	
	
	public String toString(){
		return String.format(getDailyCalorieIntake()+" "+getDiet_preferences());
	}
	

}
