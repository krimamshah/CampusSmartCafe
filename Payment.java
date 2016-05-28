package cafepackage;

public class Payment {
	
	private String paymentId;
	private String paymentDate;
	private BuildOrder buildOrder;
	
	//constructor
	public Payment(String paymentId,String PaymentDate, String buildOrder){
		
	}

	//setters and getters
	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BuildOrder getBuildOrder() {
		return buildOrder;
	}

	public void setBuildOrder(BuildOrder buildOrder) {
		this.buildOrder = buildOrder;
	}
	
	

}
