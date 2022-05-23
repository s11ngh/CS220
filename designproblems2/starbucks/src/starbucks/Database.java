package starbucks;


import java.util.HashMap;



public class Database {
	
	
	HashMap<Integer, Member> barista = new HashMap<Integer, Member>(); //this stores the info a barista would know while registering a new member 
	HashMap<String, Automatic> backend = new HashMap<String, Automatic>(); // this stores info that would be handled automatically by system
		
	
	
	public void add(Member a) {
		if(!emailExists(a)) {
			Automatic standby = new Automatic(a.email, false, 0, 0);
			barista.put(barista.size() + 1, a); //assigns an ID number in a serial order so the Barista doesn't have to work with backend code
			backend.put(a.email, standby); //assigns values to the backend
		}
		else {
			throw new IllegalArgumentException(); 
		}
	}
	
	public Automatic fetcher(int ID) { //fetch backend info on user based on ID
		String email = barista.get(ID).email;
		Automatic info = backend.get(email);
		return info;
	}
	
	public String emailFetcher(int ID) { //fetch email from backend using ID
		return barista.get(ID).email;
	}
	
	public boolean emailExists(Member a) { //returns true if email already exists as a member of Starbucks
		if(backend.containsKey(a.email)) {
			return true;
		}
		return false;
	}
	
	public boolean couponStatus(int ID) { //returns true if customer has a coupon available
		
		return fetcher(ID).statusCoupon;
		
	}
	
	public void newOrder(int ID) { //Function will be called any time an order is made
		if(fetcher(ID).countCoupon==2) { //if the customer has 2 orders previously, this order will be their 3rd, hence, they will be rewarded a coupon
			Automatic customer = new Automatic(emailFetcher(ID), true, fetcher(ID).countCoupon + 1, 0); // a coupon is added, their order count is reset to 0
			backend.put(emailFetcher(ID), customer);
		}
		else {
			Automatic customer = new Automatic(emailFetcher(ID), true, fetcher(ID).countCoupon, fetcher(ID).orders +1); // if the customer has 0 or 1 orders, their order count is incremented by 1
			backend.put(emailFetcher(ID), customer);
		}
	}
	
	public int couponCount(int ID) { //returns the number of coupons in the customer's account
	
		return fetcher(ID).countCoupon;
	}
	
	public String swipe(int ID) { //when the barista swipes the membership card, they get information about the customer's favorite order, their order count and name
		String name = barista.get(ID).firstName; //We print the name as well, so the barista doesn't get the spelling wrong
		String prefrence = barista.get(ID).favorite;
		String purchases =Integer.toString(fetcher(ID).orders);
		
		String info = "Name: " + name + "\nFavorite Order: " + prefrence + "\nOrders: " + purchases;
		return info;
		
	}
	
	
	public void couponRedeem(int ID) { //helps redeem coupons
		if(fetcher(ID).statusCoupon) { //if the customer has 2 orders previously, this order will be their 3rd, hence, they will be rewarded a coupon
			Automatic customer = new Automatic(emailFetcher(ID), true, fetcher(ID).countCoupon -1 , 0); // a coupon is added, the coupon status is unchanged
			
			if(customer.countCoupon>=1) { //if there's any unredeemed coupons still left, it will keep the status at true
				
				backend.put(emailFetcher(ID), customer);
			}
			else { // if there's 0 coupons left, it will change the coupon status to false
				customer = new Automatic(emailFetcher(ID), false, fetcher(ID).countCoupon -1 , 0);
				backend.put(emailFetcher(ID), customer);
			}
			
		}
		
	}
	
	
	
}	
	


	

