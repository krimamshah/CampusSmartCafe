/**
 * 
 */
package userpackage;

public class ExpenseProfile {
	
	private String availableFunds;
	
	//constructors
	public ExpenseProfile(){
		
	}
	public ExpenseProfile(String availableFunds){
		setAvailableFunds(availableFunds);
	}
	
	//getters and setters

	public String getAvailableFunds() {
		return availableFunds;
	}
	public void setAvailableFunds(String availableFunds) {
		this.availableFunds = availableFunds;
	}
	
	public String toString(){
		return String.format(getAvailableFunds());
	}
	

}
