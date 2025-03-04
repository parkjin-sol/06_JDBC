package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCExample3 {
	public static void main(String[] args) {
		
		// ���̵�, ��й�ȣ, �̸��� �Է� �޾�
		// ���̵�, ��й�ȣ�� ��ġ�ϴ� ������� �̸��� ����(UPDATE)
		
		
		/* 1. JDBC ��ü ���� ���� ���� */
		Connection conn = null;
		PreparedStatement pstmt = null; // ? ���� ������ �غ� �Ǿ��ִ�!
		
		// UPDATE�� ������ ���� ������ ��ȯ�� ����
		// -> ResultSet ���ʿ�

		try {
			/* 2. DriverManager ��ü�� �̿��� Connection ��ü �����ϱ� */
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
			String userName = "KH11_TEACHER"; // ����� ������
			String password = "KH1234"; // ���� ��й�ȣ
			conn = DriverManager.getConnection(url, userName, password);
			
			// �ڵ� Ŀ�� ����!!!
			conn.setAutoCommit(false);
			
			/* 3. SQL �ۼ� */
			String sql = """
					UPDATE TB_USER SET
						USER_NAME = ?
					WHERE
					  USER_ID = ?
					AND
						USER_PW = ?
					""";
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("���̵� �Է� : ");
			String id = sc.next();
			
			System.out.print("��й�ȣ �Է� : ");
			String pw = sc.next();
			
			System.out.print("������ �̸� �Է� : ");
			String name = sc.next();
			
			
			/* 4. sql�� �����ϰ� ����� �޾ƿ� 
			 * PreparedStatement ��ü ���� + ? �� ���� */
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			
			/* 5. PreaparedStatement ��ü�� �̿��ؼ� SQL�� DB�� ���� �� ���� 
			 1) SELECT�� : executeQuery() -> ResultSet���� ��ȯ 
			 2) DML��    : executeUpdate() -> ��� ���� ����(int) ��ȯ
			*/
			int result = pstmt.executeUpdate();
			
			/* (DML ���� ��)
			 * 6. SQL ���� ����� ���� ó�� + Ʈ����� ���� */
			
			if(result > 0) { // 1�� ���� 
				System.out.println("���� ����!!");
				conn.commit();
			} else {
				System.out.println("���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�");
				conn.rollback();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			/* 7. ��� �Ϸ�� JDBC ��ü �ڿ� ��ȯ */
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null)  conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}