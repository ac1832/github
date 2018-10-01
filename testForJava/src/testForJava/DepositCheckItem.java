package testForJava;

import com.alibaba.fastjson.annotation.JSONField;

import testForJava.SortIgnore;

public abstract class DepositCheckItem {
	
	@JSONField(serialize=false)
	@SortIgnore
	private CardItem card;
	
	@JSONField(serialize=false)
	@SortIgnore
	private DepositItem deposit;
	
	@JSONField(serialize=false)
	@SortIgnore
	private String keyword;
	
	@JSONField(serialize=false)
	@SortIgnore
	private String thirdPartyId;
	
	@JSONField(serialize=false)
	@SortIgnore
	private Boolean trueOrderStatus;
	
	@JSONField(serialize=false)
	@SortIgnore
	private String trueAmount;

	@JSONField(serialize=false)
	@SortIgnore
	private Boolean resultPage;
	
	public CardItem getCard(){
		return card;
	}
	
	public void setCard(CardItem card){
		this.card = card;
	}
	
	public DepositItem getDeposit(){
		return deposit;
	}
	
	public void setDeposit(DepositItem deposit){
		this.deposit = deposit;
	}
	
	public abstract String getKeyword(); 
	
	public abstract String getThirdPartyId();
	
	public abstract Boolean getTrueOrderStatus();

	public abstract String getTrueAmount();
	
	public abstract Boolean doUpdate();
	
	public abstract Boolean isResultPage();
	
}
