package market.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import market.items.Clothes;
import market.items.Electronics;
import market.items.Furniture;
import market.items.Items;

public class User {
	int count;
	Items item;
	private String phoneNumber;
	private String password;
	private String region;
	private String category;
	private ArrayList<HashMap<String, String>> userList;
	private HashMap<String, String> map;
	private ArrayList<String> categoryList;
	//private HashMap<String, String> sellList = new HashMap<String, String>();
	
	Scanner sc = new Scanner(System.in);
	
	public User() {
		this.count = 0;
		this.userList = new ArrayList<HashMap<String, String>>();
		this.map = new HashMap<String, String>();
		this.categoryList = new ArrayList<String>();
	}
	
	public void setCategory() throws IOException {
		System.out.print("���� ī�װ� �߰�  (1) ���ڱ�� (2) �� (3) ���� (���ڸ� ���� �Է����ּ���) >> ");
		this.category = sc.next();
		
		String fileName = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		String readBuffer = null;
		ArrayList<String> lines = new ArrayList<String>();
		
		fileName = "C:/Java_Lab/Project/src/market/user/user.txt";
		br = new BufferedReader(new FileReader(fileName));
		while((readBuffer = br.readLine()) != null) {
			if(readBuffer.substring(6, 17).equals(this.phoneNumber)) {
				lines.add(readBuffer + ", " + this.category);
			}
			else {
				lines.add(readBuffer);
			}
		}
		br.close();
		
		bw = new BufferedWriter(new FileWriter(fileName, false));
		pw = new PrintWriter(bw, false);
		
		for(int i=0; i<lines.size(); i++) {
			pw.println(lines.get(i));
		}
		pw.close();
		
	}
	
	public void setMap(HashMap<String, String> map) {
		this.map = map;
		Set set = map.keySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) {
			this.phoneNumber = (String)iterator.next();
		}
		this.password = map.get(phoneNumber);
	}
	
	public String getPhoneNumber() {
		Set set = map.keySet();
		Iterator iterator = set.iterator();
		String key = null;
		while(iterator.hasNext()) {
			key = (String)iterator.next();
		}
		return key;
	}
	
	public void setCount() {
		this.count++;
	}
	public String getPassword() {
		return map.get(phoneNumber);
	}
	
	public void inputInfo() {
		System.out.print("��ȭ��ȣ �Է� (xxxyyyyzzzz) >> ");
		this.phoneNumber = sc.next();
		System.out.print("��й�ȣ �Է� (4�ڸ�) >> ");
		this.password = sc.next();
	}
	public boolean login(String phoneNumber, String Password) throws IOException {
		this.phoneNumber = phoneNumber;
		this.password = password;
		String fileName = "C:/Java_Lab/Project/src/market/user/user.txt";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String check;
		int cnt = 0;
		while((check = br.readLine()) != null) {
			if(check.substring(6, 17).equals(this.phoneNumber)) {
				if(check.substring(24, 28).equals(this.password)){
					map.put(this.phoneNumber, this.password);
					System.out.println("�α����� �Ϸ�Ǿ����ϴ�.");
					cnt = 1;
					this.region = check.substring(33, 35);
					this.categoryList.add(check.substring(42));
					System.out.println("����: " + this.region);
					System.out.println("���ɺо�: " + categoryList.get(0));
					br.close();
					return true;
				}
				else {
					cnt = 2;
				}
			}
		}
		
		if(cnt == 0) {
			System.out.println("�ش��ϴ� ��ȭ��ȣ�� �����ϴ�.");
		}
		else if(cnt == 2) {
			System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
		}
		br.close();
		return false;
	
	}
	public boolean login() throws IOException {
		inputInfo();
		this.phoneNumber = phoneNumber;
		this.password = password;
		String fileName = "C:/Java_Lab/Project/src/market/user/user.txt";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String check;
		int cnt = 0;
		while((check = br.readLine()) != null) {
			if(check.substring(6, 17).equals(this.phoneNumber)) {
				if(check.substring(24, 28).equals(this.password)){
					map.put(this.phoneNumber, this.password);
					System.out.println("�α����� �Ϸ�Ǿ����ϴ�.");
					cnt = 1;
					this.region = check.substring(33, 35);
					this.categoryList.add(check.substring(42));
					System.out.println("����: " + this.region);
					System.out.println("���ɺо�: " + categoryList.get(0));
					br.close();
					return true;
				}
				else {
					cnt = 2;
				}
			}
		}
		
		if(cnt == 0) {
			System.out.println("�ش��ϴ� ��ȭ��ȣ�� �����ϴ�.");
		}
		else if(cnt == 2) {
			System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
		}
		br.close();
		return false;
	}
	
	public boolean signUp() throws IOException {
		System.out.print("��ȭ��ȣ �Է� >> ");
		this.phoneNumber = sc.next();
		System.out.print("��й�ȣ �Է� >> ");
		this.password = sc.next();
		System.out.print("���� ���� (1) �ϻ� (2) ���� (3) ��õ (���ڸ� �Է����ּ���) >> ");
		int reg = sc.nextInt();
		
		switch(reg) {
		case 1:
			this.region = "�ϻ�";
			break;
		case 2:
			this.region = "����";
			break;
		case 3:
			this.region = "��õ";
			break;
		}
		System.out.print("���ɺо� ���� (1) ���ڱ�� (2) �� (3) ���� (���ڸ� �Է����ּ���) >> ");
		int cat = sc.nextInt();
		
		switch(cat) {
		case 1:
			this.category = "���ڱ��";
			categoryList.add("���ڱ��");
			break;
		case 2:
			this.category = "��";
			categoryList.add("��");
			break;
		case 3:
			this.category = "����";
			categoryList.add("����");
			break;
		}
		
		int cnt = 0;
		String fileName = "C:/Java_Lab/Project/src/market/user/user.txt";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String checkPhoneNumber;
		while((checkPhoneNumber = br.readLine()) != null) {
			if(checkPhoneNumber.substring(0, 6).equals("��ȭ��ȣ: ")) {
				if(checkPhoneNumber.substring(6, 17).equals(this.phoneNumber)) {
					System.out.println("�̹� �����ϼ̽��ϴ�.");
					cnt++;
					br.close();
					return false;
				}
			}
		}
		br.close();
		
		if(cnt == 0) {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
			PrintWriter fw = new PrintWriter(bw, true);
			
			fw.println("��ȭ��ȣ: " + this.phoneNumber + " ��й�ȣ: " + this.password + " ����: " + this.region + " ���ɸ��: " + this.category);
			//fw.println("��й�ȣ: " + this.password);
			fw.println("========================================================================================================");
			map.put(this.phoneNumber, this.password);
			System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
			fw.close();
			return true;
		}
		return true;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion() {
		System.out.print("������ �������ּ���: (1) �ϻ� (2) ���� (3) ��õ (������ ���� �Է����ּ���) >> ");
		this.region = sc.next();
		System.out.println("�����̤��������� " + this.region + "���� �����Ǿ����ϴ�.");
	}
	
	public void buyItem() throws IOException {	
		System.out.print("�����Ͻ� ��ǰ�� �����Ű���? (Y/N)");
		String buy = sc.next();
		String num = null;
		int sel = 0;
		int chk = 0;
		if(buy.equals("y") || buy.equals("Y")){
			System.out.print("�����Ͻ� ��ǰ�� ī�װ��� �������ּ��� (1) ���ڱ�� (2) �� (3) ���� (���ڷ� �Է����ּ���) >>");
			sel = sc.nextInt();
			System.out.print("�����Ͻ� ��ǰ�� ��ȣ�� �Է����ּ��� >> ");
			num = sc.next();
			
			switch(sel) {
			case 1:
				item = new Electronics();
				try {
					item.deleteItem(num, this);
				}catch(Exception e) {
					System.out.println("�ش��ϴ� ��ǰ�� �����ϴ�");
					chk = 1;
				}
				break;
			case 2:
				item = new Clothes();
				try {
					item.deleteItem(num, this);
				}catch(Exception e) {
					System.out.println("�ش��ϴ� ��ǰ�� �����ϴ�");
					chk = 1;
				}
				break;
			case 3:
				item = new Furniture();
				try {
					item.deleteItem(num, this);
				}catch(Exception e) {
					System.out.println("�ش��ϴ� ��ǰ�� �����ϴ�");
					chk = 1;
				}
				break;
			}
		if(chk == 0) {
			System.out.println("��ǰ�� ���ŵǾ����ϴ�.");
		}
		}
	}
	
	public void postItem(User user) throws IOException {
		int sel = 0;
		System.out.print("ī�װ��� ����ּ��� (1) ���ڱ�� (2) �� (3) ���� (���ڸ� �Է����ּ���) >> ");
		sel = sc.nextInt();
		
		switch(sel) {
		case 1:
			item = new Electronics();
			item.addItem(user);
			break;
		case 2:
			item = new Clothes();
			item.addItem(user);
			break;
		case 3:
			item = new Furniture();
			item.addItem(user);
			break;
		}
	}
	
	public void showSelledList() throws IOException {
		String fileName = "C:/Java_Lab/Project/src/market/user/" + this.phoneNumber + "selledList.txt";
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String readBuffer = null;
			while((readBuffer = br.readLine()) != null) {
				System.out.println(readBuffer);
			}
			br.close();
		} catch(Exception e) {
			System.out.println("���� �Ǹ��� ����� �����ϴ�.");
		}
	}
	
	public void showPurchasedList() throws IOException {
		String fileName = "C:/Java_Lab/Project/src/market/user/" + this.phoneNumber + "purchasedList.txt";
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String readBuffer = null;
			while((readBuffer = br.readLine()) != null) {
				System.out.println(readBuffer);
			}
			br.close();
		} catch(Exception e) {
			System.out.println("���� ������ ����� �����ϴ�.");
		}
		
	}
	
	public void showCategoryItem() throws FileNotFoundException, IOException {
		int sel = 0;
		System.out.print("ī�װ��� ����ּ��� (1) ���ڱ�� (2) �� (3) ���� (���ڸ� �Է����ּ���) >> ");
		sel = sc.nextInt();
		
		switch(sel) {
		case 1:
			item = new Electronics();
			item.showItems();
			break;
		case 2:
			item = new Clothes();
			item.showItems();
			break;
		case 3:
			item = new Furniture();
			item.showItems();
			break;
		}
	}
	
	public void showInfo() {
		System.out.println("��ȭ��ȣ: " + this.phoneNumber);
		System.out.println("����: " + this.region);
		System.out.println("���� ī�װ�: " + categoryList.get(0));
		System.out.println("�ŷ� Ƚ��: " + this.count);
	}
	
	
	public void showRecommendedItem() throws IOException {
		Random r = new Random();
		int number = r.nextInt(10) + 1;
		BufferedReader br = null;
		String fileName = null;
		String readBuffer = null;
		ArrayList<String> tokens = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(this.categoryList.get(0), ", ");
		
		while(st.hasMoreTokens()) {
			tokens.add(st.nextToken());
		}
		
		if(number % 2 == 1) {
			if(this.region.equals("�ϻ�")) {
				fileName = "C:/Java_Lab/Project/src/market/items/ilsan.txt";
			}
			else if(this.region.equals("����")) {
				fileName = "C:/Java_Lab/Project/src/market/items/seoul.txt";
			}
			else if(this.region.equals("��õ")) {
				fileName = "C:/Java_Lab/Project/src/market/items/seoul.txt";
			}
			br = new BufferedReader(new FileReader(fileName));
			while((readBuffer = br.readLine()) != null) {
				System.out.println(readBuffer);
			}
			br.close();
		}
		else if(number % 2 == 0) {
			number = r.nextInt(tokens.size());
			System.out.println(number);
			if(tokens.get(number).equals("���ڱ��")) {
				item = new Electronics();
				item.showItems();
			}
			else if(tokens.get(number).equals("��")) {
				item = new Clothes();
				item.showItems();
			}
			else if(tokens.get(number).equals("����")) {
				item = new Furniture();
				item.showItems();
			}
		}
			
	}
	
}
