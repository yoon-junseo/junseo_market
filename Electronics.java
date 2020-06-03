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
	
	// 아이템 추가 메소드
	@Override
	public void addItem(User user) throws IOException {
		System.out.print("상품명을 입력하세요 >> ");
		String name = sc.next();
		setName(name);
		
		System.out.print("가격을 입력하세요(숫자만 입력 (ex) 20000원 -> 20000) >> ");
		String price = sc.next();
		setPrice(price);
		
		System.out.print("상품 설명을 입력하세요(없으면 없음 기재) >> ");
		String detail = sc.next();
		setDetail(detail);
		
		System.out.print("기존에 설정하신 지역으로 물건을 등록하시겠습니까? (Y/N) >> ");
		String region = null;
		String sel = sc.next();
		if(sel.equals("Y") || sel.equals("y")){
			region = user.getRegion();
		}
		else if(sel.equals("N") || sel.equals("n")) {
			System.out.print("지역을 설정해주세요: (1) 일산 (2) 서울 (3) 인천 (지역을 직접 입력해주세요) >> ");
			region = sc.next();
		}
		
		setDate(LocalDate.now()); // 등록 날짜 설정
		setCategory("electronics"); // 카테고리 설정
		
		Random r = new Random(); // 고유번호를 지정해주기 위함
		int number = r.nextInt(1000) + 1;
		String num = Integer.toString(number);
		ArrayList<String> numList = new ArrayList<String>();
		
		// 기본 목록 _user version
		String userFileName = "C:/Java_Lab/Project/src/market/items/electronics_user.txt";
		
		BufferedReader br = new BufferedReader(new FileReader(userFileName));
		String checkNumber = null;
		
		while((checkNumber = br.readLine()) != null){
			if(checkNumber.substring(0, 3).equals("번호: ")) {
				numList.add(checkNumber.substring(4));
			}
		}
		br.close();
		
		// 번호의 중복제거
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
		
		pw.println("번호: " + num);
		pw.println("지역: " + region);
		pw.println("카테고리: 전자기기");
		pw.println("상품명: " + this.getName());
		pw.println("상품가격: " + this.getPrice());
		pw.println("상품설명: " + this.getDetail());
		pw.println("등록일: " + this.getDate());
		pw.println("전화번호: " + user.getPhoneNumber());
		pw.println("=================================================");
		
		System.out.println("상품등록이 완료되었습니다.");
		pw.close();
		
		// 기본 목록_admin version
		String adminFileName = "C:/Java_Lab/Project/src/market/items/electronics_admin.txt";
		
		BufferedWriter bw2 = new BufferedWriter(new FileWriter(adminFileName, true));
		PrintWriter pw2 = new PrintWriter(bw2, true);
		
		pw2.println("번호: " + num);
		pw2.println("지역: " + region);
		pw2.println("카테고리: 전자기기");
		pw2.println("상품명: " + this.getName());
		pw2.println("상품가격: " + this.getPrice());
		pw2.println("상품설명: " + this.getDetail());
		pw2.println("등록일: " + this.getDate());
		pw2.println("전화번호: " + user.getPhoneNumber());
		pw2.println("비밀번호: " + user.getPassword());
		pw2.println("=================================================");
		
		pw2.close();
		
		// 지역별 목록
		String regionFileName = null;
		if(region.equals("일산")) {
			regionFileName = "C:/Java_Lab/Project/src/market/items/ilsan.txt";
		}
		else if(region.equals("서울")) {
			regionFileName = "C:/Java_Lab/Project/src/market/items/seoul.txt";
		}
		else if(region.equals("인천")) {
			regionFileName = "C:/Java_Lab/Project/src/market/items/incheon.txt";
		}
		
		BufferedWriter bw3 = new BufferedWriter(new FileWriter(regionFileName, true));
		PrintWriter pw3 = new PrintWriter(bw3, true);
		
		pw3.println("번호: " + num);
		pw3.println("지역: " + region);
		pw3.println("카테고리: 전자기기");
		pw3.println("상품명: " + this.getName());
		pw3.println("상품가격: " + this.getPrice());
		pw3.println("상품설명: " + this.getDetail());
		pw3.println("등록일: " + this.getDate());
		pw3.println("전화번호: " + user.getPhoneNumber());
		pw3.println("=================================================");
		
		pw3.close();
	}

	// 판매완료된 상품 없애기
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
			if(readBuffer.substring(0, 3).equals("번호:")) {
				if(readBuffer.substring(4, 7).equals(num)) {
					chk = 1;
				}
			}
			if(chk == 0) {
				
				lines.add(readBuffer);
			}
			else if(chk == 1) {
				if(readBuffer.substring(0, 5).equals("전화번호:")) {
					pn = readBuffer.substring(6, 17);
				}
				if(readBuffer.substring(0, 5).equals("비밀번호:")) {
					pass = readBuffer.substring(6, 10);
					map.put(pn, pass);
				}
				if(readBuffer.substring(0, 4).contentEquals("상품명:")) {
					itemName = readBuffer.substring(5);
				}
				if(readBuffer.substring(0, 5).equals("상품가격:")) {
					itemPrice = readBuffer.substring(6);
				}
				if(readBuffer.substring(0, 5).equals("카테고리:")) {
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
		
		// user.txt 정리
		ArrayList<String> lines2 = new ArrayList<String>();
		fileName = "C:/Java_Lab/Project/src/market/items/electronics_user.txt";
		br = new BufferedReader(new FileReader(fileName));
		
		while((readBuffer = br.readLine()) != null) {
			if(readBuffer.substring(0, 3).equals("번호:")) {
				if(readBuffer.substring(4, 7).equals(num)) {
					chk = 1;
				}
			}
			if(chk == 0) {
				lines2.add(readBuffer);
			}
			else if(chk == 1) {
				if(readBuffer.substring(0,3).equals("지역:")) {
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

		// 지역별로 정리
		ArrayList<String> lines3 = new ArrayList<String>();
		if(location.equals("일산")) {
			fileName = "C:/Java_Lab/Project/src/market/items/ilsan.txt";
		}
		else if(location.equals("서울")) {
			fileName = "C:/Java_Lab/Project/src/market/items/seoul.txt";
		}
		else if(location.equals("인천")) {
			fileName = "C:/Java_Lab/Project/src/market/items/incheon.txt";
		}
		
		br = new BufferedReader(new FileReader(fileName));
		
		while((readBuffer = br.readLine()) != null) {
			if(readBuffer.substring(0, 3).equals("번호:")) {
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
		
		
		pw.println("카테고리: " + itemCategory);
		pw.println("상품명: " + itemName);
		pw.println("상품가격: " + itemPrice);
		pw.println("========================================================================================================");
		pw.close();
		
		fileName = "C:/Java_Lab/Project/src/market/user/" + sellerPhone + "tradeCount.txt";
		bw = new BufferedWriter(new FileWriter(fileName, false));
		pw = new PrintWriter(bw, true);
		pw.println("거래횟수: " + seller.getCount());
		pw.close();
		
		fileName = "C:/Java_Lab/Project/src/market/user/" + buyer.getPhoneNumber() + "purchasedList.txt";
		bw = new BufferedWriter(new FileWriter(fileName, true));
		pw = new PrintWriter(bw, true);
		
		pw.println("카테고리: " + itemCategory);
		pw.println("상품명: " + itemName);
		pw.println("상품가격: " + itemPrice);
		pw.println("========================================================================================================");
		pw.close();
		buyer.setCount();
		
		fileName = "C:/Java_Lab/Project/src/market/user/" + buyer.getPhoneNumber() + "tradeCount.txt";
		bw = new BufferedWriter(new FileWriter(fileName, false));
		pw = new PrintWriter(bw, true);
		pw.println("거래횟수: " + seller.getCount());
		pw.close();
		
	}



	// 지역별로 보여주기 O
	@Override
	public void showItemsByRegion() throws IOException {
		String fileName = null;
		String where = null;
		System.out.print("어느 지역의 목록을 보시겠습니까? (일산, 서울 , 인천 중 하나를 입력하세요) >> ");
		where = sc.next();
		if(where.equals("일산")) {
			fileName = "C:/Java_Lab/Project/src/market/items/ilsan.txt";
		}
		else if(where.equals("서울")) {
			fileName = "C:/Java_Lab/Project/src/market/items/seoul.txt";
		}
		else if(where.equals("인천")) {
			fileName = "C:/Java_Lab/Project/src/market/items/incheon.txt";
		}
		
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		String readBuffer;
		while((readBuffer = br.readLine()) != null) {
			System.out.println(readBuffer);
		}
		br.close();
	}
	
	// 목록 보여주기 O
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
