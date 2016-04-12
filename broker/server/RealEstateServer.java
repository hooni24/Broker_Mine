package broker.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import broker.vo.RealEstate;

public class RealEstateServer {
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	

	public static void main(String[] args) {
		RealEstateServerManager serverMgr = new RealEstateServerManager();
		
		try {
			ServerSocket server = new ServerSocket(1799);
			System.out.println("서버가 열렸습니다.");
			System.out.println("클라이언트를 기다리는 중입니다.");
			while(true){
				Socket client = server.accept();
				System.out.println("client가 접속했습니다 IP: " + client.getInetAddress());
				
				oos = new ObjectOutputStream(client.getOutputStream());
				ois = new ObjectInputStream(client.getInputStream());
				
				while(client.isConnected()){
				
					Object[] request = (Object[]) ois.readObject();
					String cmdValue = (String) request[0];
	
					switch(cmdValue){
					case "addFS":
						boolean addFSResult = serverMgr.addForSales((RealEstate) request[1]);
						oos.writeObject(addFSResult);
						break;
					case "searchFSBA":
						RealEstate searchFSBAResult = serverMgr.searchForSalesByAddress((String) request[1]);
						oos.writeObject(searchFSBAResult);
						break;
					case "searchFSBTT":
						ArrayList<RealEstate> searchFSBTTResult = serverMgr.searchForSalesByTradeType((int) request[1]);
						oos.writeObject(searchFSBTTResult);
						break;
					case "updateFSI":
						serverMgr.updateForSalesInfo((RealEstate) request[1]);
						oos.writeObject(null);
						break;
					case "deleteFSI":
						boolean deleteFSIResult = serverMgr.deleteForSaleInfo((String) request[1]);
						oos.writeObject(deleteFSIResult);
						break;
					case "getAFSI":
						ArrayList<RealEstate> getAFSIResult = serverMgr.getAllForSalesInfo();
						oos.writeObject(getAFSIResult);
						break;
					default:
					}//switch
				
				}//while 연결시 작동
			}//outter while
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//try-catch
		
	}//main

}//class
