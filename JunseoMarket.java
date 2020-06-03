package market.main;
import java.io.IOException;
import java.util.Scanner;

import market.items.Clothes;
import market.items.Electronics;
import market.items.Furniture;
import market.items.Items;
import market.user.User;

public class JunseoMarket {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		User user = new User();
		Electronics electronics = new Electronics();
		Clothes clothes = new Clothes();
		Furniture furniture = new Furniture();
		Items item = new Electronics();
		boolean isSuccessed = false;
		boolean run = true;
		int sel = 0;
		int sel2 = 0;
		int chk = 0;
		
		while(run) {
			if(chk == 0) {
				System.out.println("메뉴를 선택해주세요 (숫자만 입력해주세요, 회원가입이나 로그인 후 다음 메뉴로 넘어갑니다.)");
				System.out.print("(1) 회원가입 (2) 로그인 (3) 종료  >> ");
				sel = sc.nextInt();
				while(sel < 1 || sel > 3) {
					System.out.println("메뉴는 1번 ~ 3번까지만 있습니다.");
					System.out.print("(1) 회원가입 (2) 로그인 (3) 종료 >> ");
					sel = sc.nextInt();
				}
				
				switch(sel) {
				case 1:
					if(user.signUp() == true) {
						chk = 1;
					}
					break;
				case 2:
					if(user.login() == true) {
						chk = 1;
					}
					break;
				case 3:
					run = false;
					System.exit(0);
					break;
				}
			
			}
			if(chk == 1) {
				System.out.println("메뉴를 선택해주세요 (숫자만 입력해주세요) >> ");
				System.out.print("(1) 판매 상품 등록  (2) 상품 구매 (3) 추천 상품 목록 보기 (4) 카테고리별 목록 보기 (5) 지역별 목록 보기 (6) 사용자 정보 보기  (7) 판매했던 목록 보기  (8) 구매했던 목록 보기 (9) 종료 >> ");
				sel2 = sc.nextInt();
				while(sel2 < 1 || sel2 > 9) {
					System.out.println("메뉴는 1번 ~ 9번까지만 있습니다.");
					System.out.print("(1) 판매 상품 등록  (2) 상품 구매 (3) 추천 상품 목록 보기 (4) 카테고리별 목록 보기 (5) 지역별 목록 보기 (6) 사용자 정보 보기 (7) 판매했던 목록 보기  (8) 구매했던 목록 보기 (9) 종료 >> ");
					sel2 = sc.nextInt();
				}
				
				switch(sel2) {
				case 1:
					user.postItem(user);
					break;
				case 2:
					user.buyItem();
					break;
				case 3:
					user.showRecommendedItem();
					break;
				case 4:
					user.showCategoryItem();
					break;
				case 5:
					item.showItemsByRegion();
					break;
				case 6:
					user.showInfo();
					break;
				case 7:
					user.showSelledList();
					break;
				case 8:
					user.showPurchasedList();
					break;
				case 9:
					run = false;
					System.exit(0);
					break;
				}
			}
	
		}
		
	}

}
