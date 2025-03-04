package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCExample5 {
	public static void main(String[] args) throws ClassNotFoundException {

		// ���̵�, ��й�ȣ, �� ��й�ȣ�� �Է� �޾�
		// ���̵�, ��й�ȣ�� ��ġ�ϴ� ȸ���� ��й�ȣ ����
		
		
		
		/* *** try-with-resources ***
		 * - try ���� �� () ��ȣ ���� 
		 *   try �������� ����ϰ� ��ȯ�� �ڿ��� �����ϸ� 
		 *   ���� �� �ڵ����� �ش� �ڿ��� 
		 *   ��ȯ(close())�ϴ� �ڵ带 ����
		 * 	  --> �޸� ���� ���� ȿ��
		 * 	 
		 *   (���� : AutoCloseable�� ������ ��ü�� �ڵ� ��ȯ ����)
		 *   
		 *   - finally�� �̿��� ��ü �ڿ� ��ȯ ���� ���� ����!
		 */
		
		
		
		String url = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
		String userName = "KH11_TEACHER"; // ����� ������
		String password = "KH1234"; // ���� ��й�ȣ
		
		/* 2. DriverManager ��ü�� �̿��� Connection ��ü �����ϱ� */
		Class.forName("oracle.jdbc.OracleDriver");
		
		/* 3. SQL �ۼ� */
		String sql = """
				UPDATE TB USER SET 
				USER_PW = ?
				WHERE 
					USER_ID = ?
				AND 
					USER_PW = ?
				""";
		
		/* 1. JDBC ��ü ���� ���� ���� */
		try(Connection conn = DriverManager.getConnection(url, userName, password);
		    PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			// �ڵ� Ŀ�� ����
			conn.setAutoCommit(false);
			
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("���̵� : ");
			String id = sc.next();	
			
			System.out.print("��й�ȣ : ");
			String pw = sc.next();	
			
			System.out.print("�� ��й�ȣ : ");
			String newPw = sc.next();	
			
			
			pstmt.setString(1, newPw);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			
			int result = pstmt.executeUpdate();
			
			/* (DML ���� ��)
			 * 6. SQL ���� ����� ���� ó�� + Ʈ����� ���� */
			
			if(result > 0) { // 1�� ���� 
				System.out.println("��й�ȣ�� ����Ǿ�������.");
				conn.commit();
			} else {
				System.out.println("���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�");
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}