package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCExample6 {
	public static void main(String[] args) throws ClassNotFoundException {
		// ���̵� ��й�ȣ�� �Է� �޾�
		// ��ġ�ϴ� ����ڸ� ���� 
		
		// ��, ���̵� �Ǵ� ��й�ȣ�� ��ġ���� ������
		// "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�"���
		
		// ��ġ�ϸ�
		// "���� ���� �Ͻðڽ��ϱ�?(Y/N)" ���
		// -> 'Y' �Է½� ���� (commit) -> "�����Ǿ����ϴ�"
		// -> 'N' ���� ��� (rollback) -> "���� ��ҵ�"
		
		/* DB������ ���� url, userName, password */
		
		String url = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
		String userName = "KH11_PJS"; // ����� ������
		String password = "KH1234"; // ���� ��й�ȣ
		
		/* JDBC Driver�� �޸𸮿� load */
		Class.forName("oracle.jdbc.OracleDriver");
		
		/* SQL �ۼ� */
		String sql = """
				DELETE
				FROM TB_USER 
				WHERE USER_ID = ?
				AND   USER_PW = ? 
				""";
			try (
				Connection conn 
				= DriverManager.getConnection(url, userName, password);
				PreparedStatement pstmt 
				= conn.prepareStatement(sql);
				Scanner sc = new Scanner(System.in);
					) {
				conn.setAutoCommit(false);
				
				// ���̵�, ��й�ȣ �Է� �ޱ�
				System.out.println("=== ����� �����ϱ�===");
				System.out.print("���̵� : ");
				String id = sc.next();
				
				
				System.out.print("��й�ȣ : ");
				String pw = sc.next();
				
				// ? (placeholder)�� �˸��� �� ����
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				
				// sql ���� �� ��� ��ȯ �ޱ�
				int result = pstmt.executeUpdate();
				
				if(result == 0) { // 0�� ����
					System.out.println("���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�");
					
				} else {
					System.out.println("���� ���� �Ͻðڽ��ϱ�(Y/N) ? ");
					String check = sc.next().toUpperCase();
					
					if( check.equals("Y")) {
						conn.commit();
						System.out.println("�����Ǿ����ϴ�");
					} else {
						conn.rollback();
						System.out.println("���� ���");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
}
