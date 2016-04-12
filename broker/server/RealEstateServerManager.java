package broker.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import broker.manager.RealEstateManager;
import broker.vo.BuyingAndSelling;
import broker.vo.Charter;
import broker.vo.MonthlyRent;
import broker.vo.RealEstate;

public class RealEstateServerManager implements RealEstateManager, Serializable {
	private ArrayList<RealEstate> reList = new ArrayList<>();
	private File file = new File("d:/reList.dat");
	private ObjectOutputStream oos; 
	private ObjectInputStream ois;
	
	public RealEstateServerManager() {
		if(file.exists()){
			reList = getFile();
		}else {
			setFile();
		}
		
	}//생성자
	

	@Override
	public boolean addForSales(RealEstate re) {
		boolean result = false;
		reList = getFile();
		for (RealEstate realEstate : reList) {
			if(realEstate.getAddress().equals(re.getAddress())){
				return result;
			}//if
		}//for
		reList.add(re);
		result = true;
		setFile();
		return result;
	}//addForSales()

	@Override
	public RealEstate searchForSalesByAddress(String address) {
		reList = getFile();
		for (RealEstate realEstate : reList) {
			if(realEstate.getAddress().equals(address)){
				return realEstate;
			}//if
		}//for
		return null;
	}//searchForSalesByAddress()

	@Override
	public ArrayList<RealEstate> searchForSalesByTradeType(int type) {
		ArrayList<RealEstate> result = new ArrayList<>();
		reList = getFile();
		switch(type){
		case 1:
			for (RealEstate r : reList) {
				if (r instanceof BuyingAndSelling){
					result.add(r);
				}
			}
			break;
		case 2:
			for (RealEstate r : reList) {
				if (r instanceof Charter){
					result.add(r);
				}
			}
			break;
		case 3:
			for (RealEstate r : reList) {
				if (r instanceof MonthlyRent){
					result.add(r);
				}
			}
			break;
		}
		return result;
	}//searchForSalesByTradeType()

	@Override
	public void updateForSalesInfo(RealEstate re) {
		reList = getFile();
		for(int i = 0; i < reList.size(); i++){
			if(reList.get(i).getAddress().equals(re.getAddress())){
				reList.set(i, re);
				setFile();
				break;
			}//if주소 맞는거 찾기
		}//for 리스트 돌면서
	}//updateForSalesInfo()

	@Override
	public boolean deleteForSaleInfo(String address) {
		boolean result = false;
		reList = getFile();
		for (RealEstate r : reList) {
			if(r.getAddress().equals(address)){
				reList.remove(r);
				result = true;
				setFile();
				break;
			}//if
		}//for
		return result;
	}//deleteForSaleInfo()

	@Override
	public ArrayList<RealEstate> getAllForSalesInfo() {
		reList = getFile();
		return reList;
	}//getAll
	
	public ArrayList<RealEstate> getFile(){
		ArrayList<RealEstate> result = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			result = (ArrayList<RealEstate>) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}//getFile()
	
	public void setFile(){
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(reList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//setFile()

}//class
