package broker.vo;

/**
 * 부동산 거래 물건 중 월세 물건에 대한 정보를 갖는 VO(Value Object)
 * */
public class MonthlyRent extends RealEstate {
	private int monthlyRent;		//월 임대료
	
	public MonthlyRent(String houseType, int size, String location, int monthlyRent) {
		super(houseType, size, location);
		this.monthlyRent = monthlyRent;
	}
	
	public MonthlyRent(){}

	public int getMonthlyRent() {
		return monthlyRent;
	}

	public void setMonthlyRent(int monthlyRent) {
		this.monthlyRent = monthlyRent;
	}

	@Override
	public String getInfo() {
		return super.getInfo()+String.format(", 월임대료:%d", monthlyRent);
	}
	
	
}
