package testForJava;


public class DBpayCheckItem extends DepositCheckItem {
	
	private String orderno;
	
	private String usercode;
	
	private String customno;
	
	private String type;
	
	private String bankcode;
	
	private String tjmoney;
	
	private String money;
	
	private String status;
	
	private String refundstatus;
	
	private String currency;
	
	private String sign;
	
	private String resultcode;
	
	private String resultmsg;
	
	private String ordertype;

	@Override
	public String getKeyword() {
		return customno;
	}

	@Override
	public String getThirdPartyId() {
		return orderno;
	}

	@Override
	public Boolean getTrueOrderStatus() {
		return ( "0" ).equals(status)||( "1" ).equals(status);
	}

	@Override
	public String getTrueAmount() {
		return tjmoney;
	}

	@Override
	public Boolean doUpdate() {
		return !status.equals("0");
	}

	@Override
	public Boolean isResultPage() {
		return false;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getCustomno() {
		return customno;
	}

	public void setCustomno(String customno) {
		this.customno = customno;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getTjmoney() {
		return tjmoney;
	}

	public void setTjmoney(String tjmoney) {
		this.tjmoney = tjmoney;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRefundstatus() {
		return refundstatus;
	}

	public void setRefundstatus(String refundstatus) {
		this.refundstatus = refundstatus;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getResultmsg() {
		return resultmsg;
	}

	public void setResultmsg(String resultmsg) {
		this.resultmsg = resultmsg;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

}
