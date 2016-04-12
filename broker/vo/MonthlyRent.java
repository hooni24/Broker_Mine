package broker.vo;

/**
 * �ε��� �ŷ� ���� �� ���� ���ǿ� ���� ������ ���� VO(Value Object)
 * */
public class MonthlyRent extends RealEstate {
	private int monthlyRent;		//�� �Ӵ��
	
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
		return super.getInfo()+String.format(", ���Ӵ��:%d", monthlyRent);
	}
	
	
}
