package market.items;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import market.user.User;

public interface Items {
	void addItem(User user) throws IOException; // 상품등록
	void deleteItem(String num, User buyer) throws IOException; // 상품삭제
	void showItemsByRegion() throws IOException; // 지역별로 보여주기
	void showItems() throws FileNotFoundException, IOException; // 그냥 보여주기 -> 카테고리별로 나옴
}
