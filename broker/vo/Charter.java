package broker.vo;

/**
 * �ε��� �ŷ� ���� �� ���� ���ǿ� ���� ������ ���� VO(Value Object)
 * */
public class Charter extends RealEstate {
	private int depositMoney;		//����������
	
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
		return super.getInfo()+String.format(", ����������:%d", depositMoney);
	}
	
}
