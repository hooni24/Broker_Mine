package broker.vo;

import java.io.Serializable;

/**
 * �ε��� �ŷ� ������ ������ ���� �ֻ��� Ŭ����
 * */
public class RealEstate implements Serializable {
	private String address;		//�ּ� (�ߺ��Ұ�)
	private String houseType;	//�ְ�����, ����Ʈ, ����, �ܵ�����, ��Ÿ �� �ϳ��� ���ڿ�
	private int size;			//ũ�� (����)
	
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
		return String.format("�ּ�:%s, �ְ�����:%s, ũ��:%d(��)", address, houseType, size);
	}
}
