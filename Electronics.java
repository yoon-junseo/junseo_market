package market.items;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;


import market.user.User;

public class Electronics extends Setting implements Items {
	
	public Electronics() {}
	
	// ������ �߰� �޼ҵ�
	@Override
	public void addItem(User user) throws IOException {
		System.out.print("��ǰ���� �Է��ϼ��� >> ");
		String name = sc.next();
		setName(name);
		
		System.out.print("������ �Է��ϼ���(���ڸ� �Է� (ex) 20000�� -> 20000) >> ");
		String price = sc.next();
		setPrice(price);
		
		System.out.print("��ǰ ������ �Է��ϼ���(������ ���� ����) >> ");
		String detail = sc.next();
		setDetail(detail);
		
		System.out.print("������ �����Ͻ� �������� ������ ����Ͻðڽ��ϱ�? (Y/N) >> ");
		String region = null;
		String sel = sc.next();
		if(sel.equals("Y") || sel.equals("y")){
			region = user.getRegion();
		}
		else if(sel.equals("N") || sel.equals("n")) {
			System.out.print("������ �������ּ���: (1) �ϻ� (2) ���� (3) ��õ (������ ���� �Է����ּ���) >> ");
			region = sc.next();
		}
		
		setDate(LocalDate.now()); // ��� ��¥ ����
		setCategory("electronics"); // ī�װ� ����
		
		Random r = new Random(); // ������ȣ�� �������ֱ� ����
		int number = r.nextInt(1000) + 1;
		String num = Integer.toString(number);
		ArrayList<String> numList = new ArrayList<String>();
		
		// �⺻ ��� _user version
		String userFileName = "C:/Java_Lab/Project/src/market/items/electronics_user.txt";
		
		BufferedReader br = new BufferedReader(new FileReader(userFileName));
		String checkNumber = null;
		
		while((checkNumber = br.readLine()) != null){
			if(checkNumber.substring(0, 3).equals("��ȣ: ")) {
				numList.add(checkNumber.substring(4));
			}
		}
		br.close();
		
		// ��ȣ�� �ߺ�����
		for(int i=0; i<numList.size(); i++) {
			for(int j=0; j<=i; j++) {
				if(numList.get(i).equals(numList.get(j))) {
					i--;
					num = Integer.toString(r.nextInt(1000) + 1);
					numList.add(i, num);
				}
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(userFileName, true));
		PrintWriter pw = new PrintWriter(bw, true);
		
		pw.println("��ȣ: " + num);
		pw.println("����: " + region);
		pw.println("ī�װ�: ���ڱ��");
		pw.println("��ǰ��: " + this.getName());
		pw.println("��ǰ����: " + this.getPrice());
		pw.println("��ǰ����: " + this.getDetail());
		pw.println("�����: " + this.getDate());
		pw.println("��ȭ��ȣ: " + user.getPhoneNumber());
		pw.println("=================================================");
		
		System.out.println("��ǰ����� �Ϸ�Ǿ����ϴ�.");
		pw.close();
		
		// �⺻ ���_admin version
		String adminFileName = "C:/Java_Lab/Project/src/market/items/electronics_admin.txt";
		
		BufferedWriter bw2 = new BufferedWriter(new FileWriter(adminFileName, true));
		PrintWriter pw2 = new PrintWriter(bw2, true);
		
		pw2.println("��ȣ: " + num);
		pw2.println("����: " + region);
		pw2.println("ī�װ�: ���ڱ��");
		pw2.println("��ǰ��: " + this.getName());
		pw2.println("��ǰ����: " + this.getPrice());
		pw2.println("��ǰ����: " + this.getDetail());
		pw2.println("�����: " + this.getDate());
		pw2.println("��ȭ��ȣ: " + user.getPhoneNumber());
		pw2.println("��й�ȣ: " + user.getPassword());
		pw2.println("=================================================");
		
		pw2.close();
		
		// ������ ���
		String regionFileName = null;
		if(region.equals("�ϻ�")) {
			regionFileName = "C:/Java_Lab/Project/src/market/items/ilsan.txt";
		}
		else if(region.equals("����")) {
			regionFileName = "C:/Java_Lab/Project/src/market/items/seoul.txt";
		}
		else if(region.equals("��õ")) {
			regionFileName = "C:/Java_Lab/Project/src/market/items/incheon.txt";
		}
		
		BufferedWriter bw3 = new BufferedWriter(new FileWriter(regionFileName, true));
		PrintWriter pw3 = new PrintWriter(bw3, true);
		
		pw3.println("��ȣ: " + num);
		pw3.println("����: " + region);
		pw3.println("ī�װ�: ���ڱ��");
		pw3.println("��ǰ��: " + this.getName());
		pw3.println("��ǰ����: " + this.getPrice());
		pw3.println("��ǰ����: " + this.getDetail());
		pw3.println("�����: " + this.getDate());
		pw3.println("��ȭ��ȣ: " + user.getPhoneNumber());
		pw3.println("=================================================");
		
		pw3.close();
	}

	// �ǸſϷ�� ��ǰ ���ֱ�
	@Override
	public void deleteItem(String num, User buyer) throws IOException {
		HashMap<String, String> map = new HashMap<String, String>();
		String fileName = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		String readBuffer = null;
		String pn = null;
		String pass = null;
		String location = null;
		String itemName = null;
		String itemPrice = null;
		String itemCategory = null;
		String sellerPhone = null;
		String sellerPassword = null;
		
		int chk = 0;
		int chk2 = 0;
		
		User seller = new User();
		
		ArrayList<String> lines = new ArrayList<String>();
		
		fileName = "C:/Java_Lab/Project/src/market/items/electronics_admin.txt";
		br = new BufferedReader(new FileReader(fileName));
		
		while((readBuffer = br.readLine()) != null) {
			if(readBuffer.substring(0, 3).equals("��ȣ:")) {
				if(readBuffer.substring(4, 7).equals(num)) {
					chk = 1;
				}
			}
			if(chk == 0) {
				
				lines.add(readBuffer);
			}
			else if(chk == 1) {
				if(readBuffer.substring(0, 5).equals("��ȭ��ȣ:")) {
					pn = readBuffer.substring(6, 17);
				}
				if(readBuffer.substring(0, 5).equals("��й�ȣ:")) {
					pass = readBuffer.substring(6, 10);
					map.put(pn, pass);
				}
				if(readBuffer.substring(0, 4).contentEquals("��ǰ��:")) {
					itemName = readBuffer.substring(5);
				}
				if(readBuffer.substring(0, 5).equals("��ǰ����:")) {
					itemPrice = readBuffer.substring(6);
				}
				if(readBuffer.substring(0, 5).equals("ī�װ�:")) {
					itemCategory = readBuffer.substring(6);
				}
				if(readBuffer.equals("=================================================")) {
					chk = 0;
				}
			}
			
		}
		br.close();
		
		bw = new BufferedWriter(new FileWriter(fileName, false));
		pw = new PrintWriter(bw, true);
		
		for(int i=0; i<lines.size(); i++) {
			pw.println(lines.get(i));
		}
		
		pw.close();
		
		// user.txt ����
		ArrayList<String> lines2 = new ArrayList<String>();
		fileName = "C:/Java_Lab/Project/src/market/items/electronics_user.txt";
		br = new BufferedReader(new FileReader(fileName));
		
		while((readBuffer = br.readLine()) != null) {
			if(readBuffer.substring(0, 3).equals("��ȣ:")) {
				if(readBuffer.substring(4, 7).equals(num)) {
					chk = 1;
				}
			}
			if(chk == 0) {
				lines2.add(readBuffer);
			}
			else if(chk == 1) {
				if(readBuffer.substring(0,3).equals("����:")) {
					location = readBuffer.substring(4);
				}
				if(readBuffer.equals("=================================================")) {
					chk = 0;
				}
			}
			
		}
		br.close();
		
		bw = new BufferedWriter(new FileWriter(fileName, false));
		pw = new PrintWriter(bw, true);
		for(int i=0; i<lines2.size(); i++) {
			pw.println(lines2.get(i));
		}
		pw.close();

		// �������� ����
		ArrayList<String> lines3 = new ArrayList<String>();
		if(location.equals("�ϻ�")) {
			fileName = "C:/Java_Lab/Project/src/market/items/ilsan.txt";
		}
		else if(location.equals("����")) {
			fileName = "C:/Java_Lab/Project/src/market/items/seoul.txt";
		}
		else if(location.equals("��õ")) {
			fileName = "C:/Java_Lab/Project/src/market/items/incheon.txt";
		}
		
		br = new BufferedReader(new FileReader(fileName));
		
		while((readBuffer = br.readLine()) != null) {
			if(readBuffer.substring(0, 3).equals("��ȣ:")) {
				if(readBuffer.substring(4, 7).equals(num)) {
					chk = 1;
				}
			}
			if(chk == 0) {
				lines3.add(readBuffer);
			}
			else if(chk == 1) {
				if(readBuffer.equals("=================================================")) {
					chk = 0;
				}
			}
			
		}
		br.close();
		
		bw = new BufferedWriter(new FileWriter(fileName, false));
		pw = new PrintWriter(bw, true);
		for(int i=0; i<lines3.size(); i++) {
		//	System.out.println(lines3.get(i));
			pw.println(lines3.get(i));
		}
		pw.close();
		
		Set set = map.keySet();
		Iterator iterator = set.iterator();
		sellerPhone = (String)iterator.next();
		sellerPassword = map.get(sellerPhone);
		
		fileName = "C:/Java_Lab/Project/src/market/user/" + sellerPhone + "selledList.txt";
		
		bw = new BufferedWriter(new FileWriter(fileName, true));
		pw = new PrintWriter(bw, true);
		
		seller.setCount();
		
		
		pw.println("ī�װ�: " + itemCategory);
		pw.println("��ǰ��: " + itemName);
		pw.println("��ǰ����: " + itemPrice);
		pw.println("========================================================================================================");
		pw.close();
		
		fileName = "C:/Java_Lab/Project/src/market/user/" + sellerPhone + "tradeCount.txt";
		bw = new BufferedWriter(new FileWriter(fileName, false));
		pw = new PrintWriter(bw, true);
		pw.println("�ŷ�Ƚ��: " + seller.getCount());
		pw.close();
		
		fileName = "C:/Java_Lab/Project/src/market/user/" + buyer.getPhoneNumber() + "purchasedList.txt";
		bw = new BufferedWriter(new FileWriter(fileName, true));
		pw = new PrintWriter(bw, true);
		
		pw.println("ī�װ�: " + itemCategory);
		pw.println("��ǰ��: " + itemName);
		pw.println("��ǰ����: " + itemPrice);
		pw.println("========================================================================================================");
		pw.close();
		buyer.setCount();
		
		fileName = "C:/Java_Lab/Project/src/market/user/" + buyer.getPhoneNumber() + "tradeCount.txt";
		bw = new BufferedWriter(new FileWriter(fileName, false));
		pw = new PrintWriter(bw, true);
		pw.println("�ŷ�Ƚ��: " + seller.getCount());
		pw.close();
		
	}



	// �������� �����ֱ� O
	@Override
	public void showItemsByRegion() throws IOException {
		String fileName = null;
		String where = null;
		System.out.print("��� ������ ����� ���ðڽ��ϱ�? (�ϻ�, ���� , ��õ �� �ϳ��� �Է��ϼ���) >> ");
		where = sc.next();
		if(where.equals("�ϻ�")) {
			fileName = "C:/Java_Lab/Project/src/market/items/ilsan.txt";
		}
		else if(where.equals("����")) {
			fileName = "C:/Java_Lab/Project/src/market/items/seoul.txt";
		}
		else if(where.equals("��õ")) {
			fileName = "C:/Java_Lab/Project/src/market/items/incheon.txt";
		}
		
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		String readBuffer;
		while((readBuffer = br.readLine()) != null) {
			System.out.println(readBuffer);
		}
		br.close();
	}
	
	// ��� �����ֱ� O
	@Override
	public void showItems() throws IOException {
		String fileName = "C:/Java_Lab/Project/src/market/items/electronics_user.txt";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		String readBuffer;
		while((readBuffer = br.readLine()) != null) {
			System.out.println(readBuffer);
		}
		
		br.close();
	}


}
