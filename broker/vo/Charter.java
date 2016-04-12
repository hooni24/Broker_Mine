package broker.vo;

/**
 * 부동산 거래 물건 중 전세 물건에 대한 정보를 갖는 VO(Value Object)
 * */
public class Charter extends RealEstate {
	private int depositMoney;		//전세보증금
	
	public Charter(String houseType, int size, String location, int depositMoney) {
		super(houseType, size, location);
		this.depositMoney = depositMoney;
	}
	
	public Charter(){}

	public int getDepositMoney() {
		return depositMoney;
	}

	public void setDepositMoney(int depositMoney) {
		this.depositMoney = depositMoney;
	}

	@Override
	public String getInfo() {
		return super.getInfo()+String.format(", 전세보증금:%d", depositMoney);
	}
	
}
