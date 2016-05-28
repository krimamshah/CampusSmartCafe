package testmain;

import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import cafepackage.Cafe;
import cafepackage.FoodItem;
import userpackage.User;

/**
 * @author Krima
 *
 */
public class Main {
	
	private static DbConnect dbConnect;
    private static User campusCardUser;
    private static Cafe cafeList;
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
		Scanner input = new Scanner(System.in);
		String query = "";
		
		//Prompt for User Id and Password
		System.out.println("User ID: ");
		String userId = input.nextLine();
		System.out.println("Password: ");
		String password = input.nextLine();
		
		//Creating instance of User Class
		campusCardUser = new User(userId,password);
		boolean verified = campusCardUser.verifyAccount ();
		
		
		if(verified){
			System.out.println("1. Cafe\n2. Vending Machine\n3. Diet Profile\n4. Expense Profile\n5. Exit");
		    System.out.println("Select an option: ");
		    while(input.hasNext()){
		    int option = input.nextInt();
		    switch (option) {
			case 1:
				
				//create an instance of class Cafe
				 cafeList = new Cafe();
				 System.out.println(cafeList.displayCafeList());
				 System.out.println("Select the cafe:");
				 if(input.hasNext()){
				    String cafeSelection = input.next();
				    System.out.println("Cafe Menu");
				    System.out.println(cafeList.displayCafeMenu(cafeSelection));
				 }

				 System.out.println("Place an order:");
				    if(input.hasNext()){
				       String orderId = input.next();
				       System.out.println("Order Summary");
				       	System.out.println(cafeList.displayOrderSummary(orderId));			       
				    }
				    System.out.println("Confirm Purchase (Y/N):");
				  while(input.hasNext()){
					   String confirmPuchase = input.next();
					   if(confirmPuchase.equalsIgnoreCase("Y")){
						   campusCardUser.updateUserDietProfile(cafeList.getFoodItemList().get(0).getCalories());
						   campusCardUser.updateUserExpenseProfile(cafeList.getFoodItemList().get(0).getPrice());
						   
					   }
					   else{
						   break;
					   }
					   
				   }
				 
				break;
			case 2:
				
				break;
            case 3:
            	 //query = "select * from userdiet_profile where user_id ="+userId;
				 //dbConnect.setQuery(query);
            	campusCardUser.setDietProfile();
				 System.out.println("User ID: "+campusCardUser.getUserID()+" Diet Profile: "+campusCardUser.getDietProfile().toString());
				break;
            case 4:
            	//query = "select * from userexpense_profile where user_id ="+userId;
				 //dbConnect.setQuery(query);
            	campusCardUser.setExpenseProfile();
				 System.out.println("User ID: "+campusCardUser.getUserID()+" Available Funds: "+campusCardUser.getExpenseProfile().toString());
				break;
            case 5:
				System.exit(0);
				break;
			default:
				break;
			}
		}
		}
		else{
			System.out.println("User does not exist");
		}
		
		//dbConnect.disconnectFromDatabase();
		input.close();
	}

}
