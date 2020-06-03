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
		System.out.print("관심 카테고리 추가  (1) 전자기기 (2) 옷 (3) 가구 (글자를 직접 입력해주세요) >> ");
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
		System.out.print("전화번호 입력 (xxxyyyyzzzz) >> ");
		this.phoneNumber = sc.next();
		System.out.print("비밀번호 입력 (4자리) >> ");
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
					System.out.println("로그인이 완료되었습니다.");
					cnt = 1;
					this.region = check.substring(33, 35);
					this.categoryList.add(check.substring(42));
					System.out.println("지역: " + this.region);
					System.out.println("관심분야: " + categoryList.get(0));
					br.close();
					return true;
				}
				else {
					cnt = 2;
				}
			}
		}
		
		if(cnt == 0) {
			System.out.println("해당하는 전화번호가 없습니다.");
		}
		else if(cnt == 2) {
			System.out.println("비밀번호가 틀렸습니다.");
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
					System.out.println("로그인이 완료되었습니다.");
					cnt = 1;
					this.region = check.substring(33, 35);
					this.categoryList.add(check.substring(42));
					System.out.println("지역: " + this.region);
					System.out.println("관심분야: " + categoryList.get(0));
					br.close();
					return true;
				}
				else {
					cnt = 2;
				}
			}
		}
		
		if(cnt == 0) {
			System.out.println("해당하는 전화번호가 없습니다.");
		}
		else if(cnt == 2) {
			System.out.println("비밀번호가 틀렸습니다.");
		}
		br.close();
		return false;
	}
	
	public boolean signUp() throws IOException {
		System.out.print("전화번호 입력 >> ");
		this.phoneNumber = sc.next();
		System.out.print("비밀번호 입력 >> ");
		this.password = sc.next();
		System.out.print("지역 설정 (1) 일산 (2) 서울 (3) 인천 (숫자를 입력해주세요) >> ");
		int reg = sc.nextInt();
		
		switch(reg) {
		case 1:
			this.region = "일산";
			break;
		case 2:
			this.region = "서울";
			break;
		case 3:
			this.region = "인천";
			break;
		}
		System.out.print("관심분야 설정 (1) 전자기기 (2) 옷 (3) 가구 (숫자를 입력해주세요) >> ");
		int cat = sc.nextInt();
		
		switch(cat) {
		case 1:
			this.category = "전자기기";
			categoryList.add("전자기기");
			break;
		case 2:
			this.category = "옷";
			categoryList.add("옷");
			break;
		case 3:
			this.category = "가구";
			categoryList.add("가구");
			break;
		}
		
		int cnt = 0;
		String fileName = "C:/Java_Lab/Project/src/market/user/user.txt";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String checkPhoneNumber;
		while((checkPhoneNumber = br.readLine()) != null) {
			if(checkPhoneNumber.substring(0, 6).equals("전화번호: ")) {
				if(checkPhoneNumber.substring(6, 17).equals(this.phoneNumber)) {
					System.out.println("이미 가입하셨습니다.");
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
			
			fw.println("전화번호: " + this.phoneNumber + " 비밀번호: " + this.password + " 지역: " + this.region + " 관심목록: " + this.category);
			//fw.println("비밀번호: " + this.password);
			fw.println("========================================================================================================");
			map.put(this.phoneNumber, this.password);
			System.out.println("회원가입이 완료되었습니다.");
			fw.close();
			return true;
		}
		return true;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion() {
		System.out.print("지역을 설정해주세요: (1) 일산 (2) 서울 (3) 인천 (지역을 직접 입력해주세요) >> ");
		this.region = sc.next();
		System.out.println("지역이ㅋㅋㅋㅋㅋ " + this.region + "으로 설정되었습니다.");
	}
	
	public void buyItem() throws IOException {	
		System.out.print("구매하실 상품이 있으신가요? (Y/N)");
		String buy = sc.next();
		String num = null;
		int sel = 0;
		int chk = 0;
		if(buy.equals("y") || buy.equals("Y")){
			System.out.print("구매하실 상품의 카테고리를 선택해주세요 (1) 전자기기 (2) 옷 (3) 가구 (숫자로 입력해주세요) >>");
			sel = sc.nextInt();
			System.out.print("구매하실 상품의 번호를 입력해주세요 >> ");
			num = sc.next();
			
			switch(sel) {
			case 1:
				item = new Electronics();
				try {
					item.deleteItem(num, this);
				}catch(Exception e) {
					System.out.println("해당하는 상품이 없습니다");
					chk = 1;
				}
				break;
			case 2:
				item = new Clothes();
				try {
					item.deleteItem(num, this);
				}catch(Exception e) {
					System.out.println("해당하는 상품이 없습니다");
					chk = 1;
				}
				break;
			case 3:
				item = new Furniture();
				try {
					item.deleteItem(num, this);
				}catch(Exception e) {
					System.out.println("해당하는 상품이 없습니다");
					chk = 1;
				}
				break;
			}
		if(chk == 0) {
			System.out.println("상품이 구매되었습니다.");
		}
		}
	}
	
	public void postItem(User user) throws IOException {
		int sel = 0;
		System.out.print("카테고리를 골라주세요 (1) 전자기기 (2) 옷 (3) 가구 (숫자를 입력해주세요) >> ");
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
			System.out.println("아직 판매한 기록이 없습니다.");
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
			System.out.println("아직 구매한 기록이 없습니다.");
		}
		
	}
	
	public void showCategoryItem() throws FileNotFoundException, IOException {
		int sel = 0;
		System.out.print("카테고리를 골라주세요 (1) 전자기기 (2) 옷 (3) 가구 (숫자를 입력해주세요) >> ");
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
		System.out.println("전화번호: " + this.phoneNumber);
		System.out.println("지역: " + this.region);
		System.out.println("관심 카테고리: " + categoryList.get(0));
		System.out.println("거래 횟수: " + this.count);
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
			if(this.region.equals("일산")) {
				fileName = "C:/Java_Lab/Project/src/market/items/ilsan.txt";
			}
			else if(this.region.equals("서울")) {
				fileName = "C:/Java_Lab/Project/src/market/items/seoul.txt";
			}
			else if(this.region.equals("인천")) {
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
			if(tokens.get(number).equals("전자기기")) {
				item = new Electronics();
				item.showItems();
			}
			else if(tokens.get(number).equals("옷")) {
				item = new Clothes();
				item.showItems();
			}
			else if(tokens.get(number).equals("가구")) {
				item = new Furniture();
				item.showItems();
			}
		}
			
	}
	
}
