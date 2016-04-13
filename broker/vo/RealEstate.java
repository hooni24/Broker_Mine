package broker.vo;

import java.io.Serializable;

/**
 * 부동산 거래 물건의 정보를 갖는 최상위 클래스
 * */
public class RealEstate implements Serializable {
	private String address;		//주소 (중복불가)
	private String houseType;	//주거형태, 아파트, 빌라, 단독주택, 기타 중 하나의 문자열
	private int size;			//크기 (평형)
	
	public RealEstate(String houseType, int size, String location) {
		this.houseType = houseType;
		this.size = size;
		this.address = location;
	}
	
	public RealEstate(){}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String location) {
		this.address = location;
	}
	
	public String getInfo(){
		return String.format("주소:%s, 주거형태:%s, 크기:%d(평)", address, houseType, size);
	}
}
