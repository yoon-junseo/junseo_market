package market.items;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import market.user.User;

public interface Items {
	void addItem(User user) throws IOException; // ��ǰ���
	void deleteItem(String num, User buyer) throws IOException; // ��ǰ����
	void showItemsByRegion() throws IOException; // �������� �����ֱ�
	void showItems() throws FileNotFoundException, IOException; // �׳� �����ֱ� -> ī�װ����� ����
}
