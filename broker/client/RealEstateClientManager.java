package broker.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import broker.manager.RealEstateManager;
import broker.vo.RealEstate;

public class RealEstateClientManager implements RealEstateManager {
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public RealEstateClientManager() {
		try {
			Socket client = new Socket("127.0.0.1", 1799);
			ois = new ObjectInputStream(client.getInputStream());
			oos = new ObjectOutputStream(client.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//try-catch
	}//»ý¼ºÀÚ
	

	@Override
	public boolean addForSales(RealEstate re) {
		Object[] request = {"addFS", re};
		boolean result = (boolean) sendReturn(request);
		return result;
	}

	@Override
	public RealEstate searchForSalesByAddress(String address) {
		Object[] request = {"searchFSBA", address};
		RealEstate result = (RealEstate) sendReturn(request);
		return result;
	}

	@Override
	public ArrayList<RealEstate> searchForSalesByTradeType(int type) {
		Object[] request = {"searchFSBTT", type};
		ArrayList<RealEstate> result = (ArrayList<RealEstate>) sendReturn(request);
		return result;
	}

	@Override
	public void updateForSalesInfo(RealEstate re) {
		Object[] request = {"updateFSI", re};
		sendReturn(request);
	}

	@Override
	public boolean deleteForSaleInfo(String address) {
		Object[] request = {"deleteFSI", address};
		boolean result = (boolean) sendReturn(request);
		return result;
	}

	@Override
	public ArrayList<RealEstate> getAllForSalesInfo() {
		Object[] request = {"getAFSI"};
		ArrayList<RealEstate> result = (ArrayList<RealEstate>) sendReturn(request);
		return result;
	}
	
	public Object sendReturn(Object[] o){
		Object result = null;
		try {
			oos.writeObject(o);
			result = ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}//sendReturn()

}//class
