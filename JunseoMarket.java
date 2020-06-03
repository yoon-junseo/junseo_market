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
				System.out.println("�޴��� �������ּ��� (���ڸ� �Է����ּ���, ȸ�������̳� �α��� �� ���� �޴��� �Ѿ�ϴ�.)");
				System.out.print("(1) ȸ������ (2) �α��� (3) ����  >> ");
				sel = sc.nextInt();
				while(sel < 1 || sel > 3) {
					System.out.println("�޴��� 1�� ~ 3�������� �ֽ��ϴ�.");
					System.out.print("(1) ȸ������ (2) �α��� (3) ���� >> ");
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
				System.out.println("�޴��� �������ּ��� (���ڸ� �Է����ּ���) >> ");
				System.out.print("(1) �Ǹ� ��ǰ ���  (2) ��ǰ ���� (3) ��õ ��ǰ ��� ���� (4) ī�װ��� ��� ���� (5) ������ ��� ���� (6) ����� ���� ����  (7) �Ǹ��ߴ� ��� ����  (8) �����ߴ� ��� ���� (9) ���� >> ");
				sel2 = sc.nextInt();
				while(sel2 < 1 || sel2 > 9) {
					System.out.println("�޴��� 1�� ~ 9�������� �ֽ��ϴ�.");
					System.out.print("(1) �Ǹ� ��ǰ ���  (2) ��ǰ ���� (3) ��õ ��ǰ ��� ���� (4) ī�װ��� ��� ���� (5) ������ ��� ���� (6) ����� ���� ���� (7) �Ǹ��ߴ� ��� ����  (8) �����ߴ� ��� ���� (9) ���� >> ");
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
