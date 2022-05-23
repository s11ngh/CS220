package starbucks;

public class Automatic {
	String email;
	boolean statusCoupon;
	int countCoupon;
	int orders;
	
	public Automatic(String email, boolean statusCoupon, int countCoupon, int orders) {
			
			this.statusCoupon = statusCoupon;
			this.email = email;
			this.countCoupon=countCoupon;
			this.orders = orders;
		}
}
