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
				case 1: //등록
					insertForSalesInfo();
					break;
				case 2: //검색
					searchForSalesInfo();
					break;
				case 3: //수정
					updateMenu();
					break;
				case 4: //삭제
					deleteMenu();
					break;
				case 5: //전체출력
					showAll();
					break;
				case 9: System.exit(0);
					break;
				default: System.out.println("메뉴번호 선택 오류!");
			}//switch
		}//while
	}//constructor
	
	/**
	 * 부동산 중계 관리 프로그램의 메인 메뉴를 출력한다.
	 * */
	public void printMainMenu(){
		System.out.println("--------------------------");
		System.out.println("     부동산 중계 관리 시스템              ");
		System.out.println("--------------------------");
		System.out.println(" 1.등록 ");	
		System.out.println(" 2.검색 ");	
		System.out.println(" 3.수정 ");	
		System.out.println(" 4.삭제(거래완료) ");	
		System.out.println(" 5.전체출력 ");
		System.out.println(" 9.종료 ");
		System.out.println("--------------------------");
		System.out.print(" 메뉴 번호를 선택하세요=> ");	
	}
	
	
	/**
	 * 부동산 중계 관리 프로그램의 매물 정보 등록 메뉴를 출력한다.
	 * */
	public void printInsertForSalesInfoMenu(){
		System.out.println("--------------------------");
		System.out.println("     부동산 매물 정보 등록                 ");
		System.out.println("--------------------------");
		System.out.println(" 1.매매 ");	
		System.out.println(" 2.전세 ");	
		System.out.println(" 3.월세 ");	
		System.out.println(" 4.상위메뉴 ");	
		System.out.println("--------------------------");
		System.out.print(" 메뉴 번호를 선택하세요=> ");	
	}
	
	/**
	 * 부동산 중계 관리 프로그램의 매물 정보 검색 메뉴를 출력한다.
	 * */
	public void printSearchForSalesInfoMenu(){
		System.out.println("--------------------------");
		System.out.println("     부동산 매물 정보 검색                 ");
		System.out.println("--------------------------");
		System.out.println(" 0.주소로 검색 ");	
		System.out.println(" 1.매매 물건 검색 ");	
		System.out.println(" 2.전세 물건 검색 ");	
		System.out.println(" 3.월세 물건 검색 ");	
		System.out.println("--------------------------");
		System.out.print(" 메뉴 번호를 선택하세요=> ");	
	}
	
	/**
	 * 부동산 중계 프로그램의 매물 검색 메뉴에 대한 처리를 담당한다.
	 * 주소검색, 매매 물건 검색, 전세 물건 검색, 월세 물건 검색으로 분기처리 하여 검색한다. 
	 * */
	public void searchForSalesInfo(){
		printSearchForSalesInfoMenu();
		int no = sc.nextInt();
		
		switch (no){
		case 0:
			System.out.print("찾으실 주소를 입력하세요 >");
			String address = sc.nextLine();
			address = sc.nextLine();
			RealEstate found = clientMgr.searchForSalesByAddress(address);
				if(found != null){
					System.out.println(found.getInfo());
				}else {
					System.out.println("찾으시는 물건이 없습니다.");
				}//if-else 주소값을 가진 매물이 있는지 없는지
			break;
		case 1: case 2: case 3:															
			ArrayList<RealEstate> result = clientMgr.searchForSalesByTradeType(no);
			for (RealEstate r : result) {
				System.out.println(r.getInfo());
			}
		}//switch
	}//searchForSalesInfo()
	
	/**
	 * 부동산 중계 프로그램의 매물 등록 메뉴에 대한 처리를 담당한다.
	 * 매매, 전세, 월세로 구분하여 매물 정보를 입력받아 등록한다.
	 * */
	public void insertForSalesInfo(){
		printInsertForSalesInfoMenu();
		int no = sc.nextInt();
		RealEstate made = makeForSaleInfo(no);
		if(made != null){
			boolean result = clientMgr.addForSales(made);
			if(result){
				System.out.println("등록에 성공했습니다.");
			}else {
				System.out.println("등록에 실패했습니다.(주소중복)");
			}
		}
	}//method
	
	
	//공통 : 주소, 주거형태(아파트, 빌라, 단독주택, 기타 중 하나의 문자열), 크기(평형) / 매매:매매가격, 전세:전세보증금, 월세:월임대료
	public RealEstate makeForSaleInfo(int type){
		
			if(type == 1 || type == 2 || type == 3){
			RealEstate result = null;
				System.out.println("===매매등록진행===");
				System.out.print("매물의 주소를 입력하세요 >");
				String location = sc.nextLine();
				location = sc.nextLine();
	
				String houseType = null;
				while(houseType == null){
				System.out.print("매물의 주거형태를 선택하세요(1.아파트, 2.빌라, 3.단독주택, 4.기타) >");
				String input = sc.nextLine();
					switch(input){
					case "1": houseType = "아파트";
						break;
					case "2": houseType = "빌라";
						break;
					case "3":houseType = "단독주택";
						break;
					case "4":houseType = "기타";
						break;
					default:
						System.out.println("1~4만 입력하세요.");
					}//switch
				}//while
				
				System.out.print("매물의 크기를 입력하세요(평형) >");
				int size = sc.nextInt(); 
					
				switch(type){
					case 1: 
						System.out.print("매매가격을 입력하세요 >");
						int price = sc.nextInt();
						result = new BuyingAndSelling(houseType, size, location, price);
						return result;
					case 2:
						System.out.print("전세보증금을 입력하세요 >");
						int depositMoney = sc.nextInt();
						result = new Charter(houseType, size, location, depositMoney);
						return result;
					case 3:
						System.out.print("월임대료를 입력하세요 >");
						int monthlyRent = sc.nextInt();
						result = new MonthlyRent(houseType, size, location, monthlyRent);
						return result;
					case 4:
						System.out.println("상위메뉴로 돌아갑니다.");
					}
				return null;
			}else if(type == 4){
				System.out.println("상위메뉴로 돌아갑니다.");
				return null;
			}//if(type분별)
			return null;
	}//makeForSaleInfo()
	
	
	public void updateMenu(){
		System.out.print("|수정할 물건의 주소| ");
		String address = sc.nextLine();
		address = sc.nextLine();
		RealEstate target = clientMgr.searchForSalesByAddress(address);
		
		if(target != null){
			System.out.println("==============================");
			System.out.println(target.getInfo());
			System.out.println("==============================");
			if(target instanceof BuyingAndSelling){
				System.out.print("|매매가격|");
				int price = sc.nextInt();
				BuyingAndSelling updatedInfo = new BuyingAndSelling(target.getHouseType(), target.getSize(), target.getAddress(), price);
				clientMgr.updateForSalesInfo(updatedInfo);
			}else if (target instanceof Charter){
				System.out.print("|임대 보증금|");
				int depositMoney = sc.nextInt();
				Charter updatedInfo = new Charter(target.getHouseType(), target.getSize(), target.getAddress(), depositMoney);
				clientMgr.updateForSalesInfo(updatedInfo);
			}else {
				System.out.println("|월임대료|");
				int MonthlyRent = sc.nextInt();
				MonthlyRent updatedInfo = new MonthlyRent (target.getHouseType(), target.getSize(), target.getAddress(), MonthlyRent);
				clientMgr.updateForSalesInfo(updatedInfo);
				
				System.out.println("성공적으로 변경되었습니다.");
			}//if-else
		}else {
			System.out.println("주소를 정확히 입력하세요");
		}//if문 target이 있는지.
		
	}//updateMenu()
	
	
	public void deleteMenu(){
		System.out.print("|삭제 물건의 주소| ");
		String address = sc.nextLine();
		address = sc.nextLine();
		boolean result = clientMgr.deleteForSaleInfo(address);
		if(result){
			System.out.println("정상 삭제 되었습니다.");
		}else {
			System.out.println("찾으시는 물건이 없습니다.");
		}//if-else
	}//deleteMenu()
	
	
	public void showAll(){													
		ArrayList<RealEstate> result = clientMgr.getAllForSalesInfo();
		for (RealEstate r : result) {
			System.out.println(r.getInfo());
		}
	}
	
}//class



