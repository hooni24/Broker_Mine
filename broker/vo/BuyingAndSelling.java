package broker.vo;

/**
 * 부동산 거래 물건 중 매매 물건에 대한 정보를 갖는 VO(Value Object)
 * */
public class BuyingAndSelling extends RealEstate {
	private int price;	//매매가격

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
		return super.getInfo()+String.format(", 매매가격:%d", price);
	}
	
}
