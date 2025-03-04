package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCExample2 {
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		/* java.sql.PreaparedStatement(�غ�� Statement)
		 * 
		 * - �����Ϸ��� SQL �߰��� ?(placeholder)�� �ۼ��Ͽ�
		 *  ? �ڸ��� java ���� ������ �غ� 
		 *  �Ǿ��ִ� Statement
		 * 
		 * ���� 1 : �� �߰��� ��������
		 * 
		 * ���� 2 : ? �� ���� �� �ڷ����� �´�
		 * 				 ���ͷ� ǥ������� �ڵ� ��ȯ�ȴ�!
		 * 
		 * ex) ?�� int ���� -> NUMBER Ÿ�� ���ͷ� (123)
		 * ex) ?�� String ���� -> CHAR Ÿ�� ���ͷ� ('123')
		 * 												(���ʿ� '' �ڵ� �߰�)
		 * 
		 * ���� 3 : 
		 * 	Statement�� �������� SQL Injection�� ������ �� �ִ�!
		 *  -> Statement�� ���ڿ� ���� ������� SQL�� ����
		 *   -> ���յǴ� ���ڿ��� SQL ��ɾ� �Ǵ� '��' ��� ����
		 * 
		 *  PreparedStatement�� 
		 *  ?�� �̿��ؼ� �Ķ����(��)�� ���� �ϱ� ������
		 *  SQL Injection�� ������ �� �ִ�!
		 */
		
		try{
			// Ŀ�ؼ� ����
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
			String userName = "KH00_TEACHER"; // ����� ������
			String password = "KH1234"; // ���� ��й�ȣ
			conn = DriverManager.getConnection(url, userName, password);
			
			// SQL �ۼ�
			Scanner sc = new Scanner(System.in);
			System.out.print("���̵� �Է� : ");
			String id = sc.next();
			
			System.out.print("��й�ȣ �Է� : ");
			String pw = sc.next();
			
			System.out.print("�̸� �Է� : ");
			String name = sc.next();
			
			String sql 
				= "INSERT INTO TB_USER VALUES(SEQ_USER_NO.NEXTVAL, ?, ?, ?, DEFAULT)";                
			
			/* DML(INSERT, UPDATE, DELETE)�� AutoCommit ����! 
			 * -> Connection�� �����ϸ� �⺻������ true(��������)
			 * -> �Ź� �������� ��
			 *  ��? �����ڰ� Ʈ������� ������� �����ϱ� ���ؼ�
			 * */
			conn.setAutoCommit(false);
			
			/* PreparedStatement ��ü ���� */
			// -> ��ü ������ ���� SQL�� �Ű� ������ �����ؼ� ��Ƶ�
			// -> SQL �ľ��ϱ� ���ؼ�
			pstmt = conn.prepareStatement(sql);
			
			/* ?(placeholder)�� �˸��� �� ���� */
			// pstmt.set�ڷ���(?����, �����Ұ�);
			
			pstmt.setString(1, id); // id ���� ���� '' �߰��Ͽ� sql�� ����
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			
			/* SQL ���� �� ��� ��ȯ �ޱ�
			 * - SELECT : executeQuery() -> ResultSet ��ȯ
			 * - DML    : executeUpdate() -> ���� ����(int) ��ȯ
			 *  */
			
			// DML �������� ���۵� ���� ���� ��ȯ��
			int result = pstmt.executeUpdate();
			
			
			/* result ���� ���� SQL ����/���� ���� ���
			 * + Ʈ����� ����(commit/rollback)
			 * */
			if(result > 0) {
				System.out.println(id + "���� �߰� �Ǿ����ϴ�");
				conn.commit(); // COMMIT ���� -> INSERT ���� DB �ݿ�
			
			} else {
				System.out.println("�߰� ����");
				conn.rollback();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			// JDBC ��ü �ڿ� ��ȯ
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}