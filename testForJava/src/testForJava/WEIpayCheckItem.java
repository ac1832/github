package testForJava;

import java.util.Map;

public class WEIpayCheckItem extends DepositCheckItem {
	
	private String Merchants;
	
	private String Type;
	
	private String Status;
	
	private String Amount;
	
	private String OrderNum;
	
	private String Member;
	
	private String Time;
	
	private String sign;
	
	private String Remark;
	
	private Map<String, String> paramMap;
	
	@Override
	public String getKeyword() {
		return Merchants;
	}

	@Override
	public String getThirdPartyId() {
		return OrderNum;
	}

	@Override
	public Boolean getTrueOrderStatus() {
		return ( "SUCCESS" ).equals(Status);
	}

	@Override
	public String getTrueAmount() {
		return Amount;
	}

	@Override
	public Boolean doUpdate() {
		return !Status.equals("0");
	}

	@Override
	public Boolean isResultPage() {
		return false;
	}

	public String getMerchants() {
		return Merchants;
	}

	public void setMerchants(String merchants) {
		Merchants = merchants;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getOrderNum() {
		return OrderNum;
	}

	public void setOrderNum(String orderNum) {
		OrderNum = orderNum;
	}

	public String getMember() {
		return Member;
	}

	public void setMember(String member) {
		Member = member;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public Map<String, String> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}

}
