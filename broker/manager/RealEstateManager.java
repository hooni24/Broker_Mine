package broker.manager;

import java.util.ArrayList;

import broker.vo.RealEstate;

public interface RealEstateManager {
	
	/**
	 * �ű� �ε��� �Ź��� ����Ѵ�.
	 * �� ��ϵ� �Ź��� ������ �ּ������� ������ ������, ������ �ּ��� �Ź��� �ߺ��Ͽ� ����� �� ����.
	 * @return ��Ͽ� �����ϸ� true��, �����ϸ� false�� ��ȯ�Ѵ�.
	 * @param ����ϰ��� �ϴ� �ε��� �Ź��� ������ ���� RealEstate Ŭ���� ��ü
	 * */
	public boolean addForSales(RealEstate re);
	
	/**
	 * �ּҸ� �������� ��ϵ� �ε��� �Ź��� �˻��Ѵ�.
	 * @return �ּҷ� �˻��� �Ź������� ���� RealEstate Ŭ���� ��ü
	 * @param �˻��ϰ����ϴ� �Ź� ���������� �ּ�
	 * */
	public RealEstate searchForSalesByAddress(String address);
	
	/**
	 * �ŷ�����(�Ÿ�:1, ����:2, ����:3)�� ���� �ε��� �Ź��� �˻��Ѵ�.
	 * @return �ŷ����¿� ���� �Ź������� ���� RealEstate Ŭ���� ��ü�� ���
	 * @param �ŷ����¸� �������� �� (�Ÿ�:1, ����:2, ����:3)
	 * */
	public ArrayList<RealEstate> searchForSalesByTradeType(int type);
	
	/**
	 * ��ϵ� �Ź� ���������� �����Ѵ�. ���� ������ ������ �� �Ź��� ����(�ŸŰ���, �����Ӵ뺸����, ����)������ �ش��Ѵ�.
	 * @param ������ ������ ������ �ִ� RealEstate Ŭ���� ��ü
	 * */
	public void updateForSalesInfo(RealEstate re);
	
	/**
	 * �ŷ��� �Ϸ�� �Ÿ� ������ �ŷ� �Ź� ��Ͽ��� �����Ѵ�.
	 * @param address �����ϰ��� �ϴ� �Ź� ���������� �ּ�
	 * @return ������ �����ϸ� true��, �����ϸ� false�� ��ȯ�Ѵ�.
	 * */
	public boolean deleteForSaleInfo(String address);
	
	/**
	 * ��ϵ� ��� �Ź� ���������� ���� ����� ��ȯ�Ѵ�.
	 * @return �ε��� �߰� ���α׷����� �ŷ��Ǵ� ��� �Ź� ������ ��ϵǾ� �ִ� ArrayList<RealEstate> ��ü
	 * */
	public ArrayList<RealEstate> getAllForSalesInfo();
}
