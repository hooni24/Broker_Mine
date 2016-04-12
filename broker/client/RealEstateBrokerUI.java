package broker.client;

import java.util.ArrayList;
import java.util.Scanner;

import broker.manager.RealEstateManager;
import broker.vo.BuyingAndSelling;
import broker.vo.Charter;
import broker.vo.MonthlyRent;
import broker.vo.RealEstate;

public class RealEstateBrokerUI {
	private RealEstateManager clientMgr = new RealEstateClientManager();
	private Scanner sc = new Scanner(System.in);
	
	public RealEstateBrokerUI(){
		while(true){
			printMainMenu();
			int no = sc.nextInt();
			switch(no){
				case 1: //���
					insertForSalesInfo();
					break;
				case 2: //�˻�
					searchForSalesInfo();
					break;
				case 3: //����
					updateMenu();
					break;
				case 4: //����
					deleteMenu();
					break;
				case 5: //��ü���
					showAll();
					break;
				case 9: System.exit(0);
					break;
				default: System.out.println("�޴���ȣ ���� ����!");
			}//switch
		}//while
	}//constructor
	
	/**
	 * �ε��� �߰� ���� ���α׷��� ���� �޴��� ����Ѵ�.
	 * */
	public void printMainMenu(){
		System.out.println("--------------------------");
		System.out.println("     �ε��� �߰� ���� �ý���              ");
		System.out.println("--------------------------");
		System.out.println(" 1.��� ");	
		System.out.println(" 2.�˻� ");	
		System.out.println(" 3.���� ");	
		System.out.println(" 4.����(�ŷ��Ϸ�) ");	
		System.out.println(" 5.��ü��� ");
		System.out.println(" 9.���� ");
		System.out.println("--------------------------");
		System.out.print(" �޴� ��ȣ�� �����ϼ���=> ");	
	}
	
	
	/**
	 * �ε��� �߰� ���� ���α׷��� �Ź� ���� ��� �޴��� ����Ѵ�.
	 * */
	public void printInsertForSalesInfoMenu(){
		System.out.println("--------------------------");
		System.out.println("     �ε��� �Ź� ���� ���                 ");
		System.out.println("--------------------------");
		System.out.println(" 1.�Ÿ� ");	
		System.out.println(" 2.���� ");	
		System.out.println(" 3.���� ");	
		System.out.println(" 4.�����޴� ");	
		System.out.println("--------------------------");
		System.out.print(" �޴� ��ȣ�� �����ϼ���=> ");	
	}
	
	/**
	 * �ε��� �߰� ���� ���α׷��� �Ź� ���� �˻� �޴��� ����Ѵ�.
	 * */
	public void printSearchForSalesInfoMenu(){
		System.out.println("--------------------------");
		System.out.println("     �ε��� �Ź� ���� �˻�                 ");
		System.out.println("--------------------------");
		System.out.println(" 0.�ּҷ� �˻� ");	
		System.out.println(" 1.�Ÿ� ���� �˻� ");	
		System.out.println(" 2.���� ���� �˻� ");	
		System.out.println(" 3.���� ���� �˻� ");	
		System.out.println("--------------------------");
		System.out.print(" �޴� ��ȣ�� �����ϼ���=> ");	
	}
	
	/**
	 * �ε��� �߰� ���α׷��� �Ź� �˻� �޴��� ���� ó���� ����Ѵ�.
	 * �ּҰ˻�, �Ÿ� ���� �˻�, ���� ���� �˻�, ���� ���� �˻����� �б�ó�� �Ͽ� �˻��Ѵ�. 
	 * */
	public void searchForSalesInfo(){
		printSearchForSalesInfoMenu();
		int no = sc.nextInt();
		
		switch (no){
		case 0:
			System.out.print("ã���� �ּҸ� �Է��ϼ��� >");
			String address = sc.nextLine();
			address = sc.nextLine();
			RealEstate found = clientMgr.searchForSalesByAddress(address);
				if(found != null){
					System.out.println(found.getInfo());
				}else {
					System.out.println("ã���ô� ������ �����ϴ�.");
				}//if-else �ּҰ��� ���� �Ź��� �ִ��� ������
			break;
		case 1: case 2: case 3:															
			ArrayList<RealEstate> result = clientMgr.searchForSalesByTradeType(no);
			for (RealEstate r : result) {
				System.out.println(r.getInfo());
			}
		}//switch
	}//searchForSalesInfo()
	
	/**
	 * �ε��� �߰� ���α׷��� �Ź� ��� �޴��� ���� ó���� ����Ѵ�.
	 * �Ÿ�, ����, ������ �����Ͽ� �Ź� ������ �Է¹޾� ����Ѵ�.
	 * */
	public void insertForSalesInfo(){
		printInsertForSalesInfoMenu();
		int no = sc.nextInt();
		RealEstate made = makeForSaleInfo(no);
		if(made != null){
			boolean result = clientMgr.addForSales(made);
			if(result){
				System.out.println("��Ͽ� �����߽��ϴ�.");
			}else {
				System.out.println("��Ͽ� �����߽��ϴ�.(�ּ��ߺ�)");
			}
		}
	}//method
	
	
	//���� : �ּ�, �ְ�����(����Ʈ, ����, �ܵ�����, ��Ÿ �� �ϳ��� ���ڿ�), ũ��(����) / �Ÿ�:�ŸŰ���, ����:����������, ����:���Ӵ��
	public RealEstate makeForSaleInfo(int type){
		
			if(type == 1 || type == 2 || type == 3){
			RealEstate result = null;
				System.out.println("===�Ÿŵ������===");
				System.out.print("�Ź��� �ּҸ� �Է��ϼ��� >");
				String location = sc.nextLine();
				location = sc.nextLine();
	
				String houseType = null;
				while(houseType == null){
				System.out.print("�Ź��� �ְ����¸� �����ϼ���(1.����Ʈ, 2.����, 3.�ܵ�����, 4.��Ÿ) >");
				String input = sc.nextLine();
					switch(input){
					case "1": houseType = "����Ʈ";
						break;
					case "2": houseType = "����";
						break;
					case "3":houseType = "�ܵ�����";
						break;
					case "4":houseType = "��Ÿ";
						break;
					default:
						System.out.println("1~4�� �Է��ϼ���.");
					}//switch
				}//while
				
				System.out.print("�Ź��� ũ�⸦ �Է��ϼ���(����) >");
				int size = sc.nextInt(); 
					
				switch(type){
					case 1: 
						System.out.print("�ŸŰ����� �Է��ϼ��� >");
						int price = sc.nextInt();
						result = new BuyingAndSelling(houseType, size, location, price);
						return result;
					case 2:
						System.out.print("������������ �Է��ϼ��� >");
						int depositMoney = sc.nextInt();
						result = new Charter(houseType, size, location, depositMoney);
						return result;
					case 3:
						System.out.print("���Ӵ�Ḧ �Է��ϼ��� >");
						int monthlyRent = sc.nextInt();
						result = new MonthlyRent(houseType, size, location, monthlyRent);
						return result;
					case 4:
						System.out.println("�����޴��� ���ư��ϴ�.");
					}
				return null;
			}else if(type == 4){
				System.out.println("�����޴��� ���ư��ϴ�.");
				return null;
			}//if(type�к�)
			return null;
	}//makeForSaleInfo()
	
	
	public void updateMenu(){
		System.out.print("|������ ������ �ּ�| ");
		String address = sc.nextLine();
		address = sc.nextLine();
		RealEstate target = clientMgr.searchForSalesByAddress(address);
		
		if(target != null){
			System.out.println("==============================");
			System.out.println(target.getInfo());
			System.out.println("==============================");
			if(target instanceof BuyingAndSelling){
				System.out.print("|�ŸŰ���|");
				int price = sc.nextInt();
				BuyingAndSelling updatedInfo = new BuyingAndSelling(target.getHouseType(), target.getSize(), target.getAddress(), price);
				clientMgr.updateForSalesInfo(updatedInfo);
			}else if (target instanceof Charter){
				System.out.print("|�Ӵ� ������|");
				int depositMoney = sc.nextInt();
				Charter updatedInfo = new Charter(target.getHouseType(), target.getSize(), target.getAddress(), depositMoney);
				clientMgr.updateForSalesInfo(updatedInfo);
			}else {
				System.out.println("|���Ӵ��|");
				int MonthlyRent = sc.nextInt();
				MonthlyRent updatedInfo = new MonthlyRent (target.getHouseType(), target.getSize(), target.getAddress(), MonthlyRent);
				clientMgr.updateForSalesInfo(updatedInfo);
				
				System.out.println("���������� ����Ǿ����ϴ�.");
			}//if-else
		}else {
			System.out.println("�ּҸ� ��Ȯ�� �Է��ϼ���");
		}//if�� target�� �ִ���.
		
	}//updateMenu()
	
	
	public void deleteMenu(){
		System.out.print("|���� ������ �ּ�| ");
		String address = sc.nextLine();
		address = sc.nextLine();
		boolean result = clientMgr.deleteForSaleInfo(address);
		if(result){
			System.out.println("���� ���� �Ǿ����ϴ�.");
		}else {
			System.out.println("ã���ô� ������ �����ϴ�.");
		}//if-else
	}//deleteMenu()
	
	
	public void showAll(){													
		ArrayList<RealEstate> result = clientMgr.getAllForSalesInfo();
		for (RealEstate r : result) {
			System.out.println(r.getInfo());
		}
	}
	
}//class



