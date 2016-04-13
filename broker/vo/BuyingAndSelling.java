package broker.vo;

/**
 * �ε��� �ŷ� ���� �� �Ÿ� ���ǿ� ���� ������ ���� VO(Value Object)
 * */
public class BuyingAndSelling extends RealEstate {
	private int price;	//�ŸŰ���

	public BuyingAndSelling(String houseType, int size, String location, int price) {
		super(houseType, size, location);
		this.price = price;
	}
	
	public BuyingAndSelling(){}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String getInfo() {
		return super.getInfo()+String.format(", �ŸŰ���:%d", price);
	}
	
}
