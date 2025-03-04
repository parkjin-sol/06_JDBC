package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample1 {
	public static void main(String[] args) {

		/*
		 * �Է� ���� ���̵� ���Ե� ������� ����� ��ȣ, ���̵�, �̸�,��������
		 * ȸ�� ��ȣ ������������ ��ȸ(SELECT)
		 */

		/* 1. JDBC ��ü ���� ���� ���� */
		Connection conn = null; // DB ���� ������ ������ �����ϴ� ��ü
		Statement stmt = null; // SQL ����, ��� ��ȯ �޴� ��ü
		ResultSet rs = null; // SELECT ����� �����ϰ� 1�྿ �����ϴ� ��ü

		try {
			/* 2. DriverManager ��ü�� �̿��� Connection ��ü �����ϱ� */

			Class.forName("oracle.jdbc.OracleDriver");

			// �� ��ǻ�� DB ���� ��
			// jdbc:oracle:thin:@localhost:1521:XE

			// �п� DB ���� URL
			// - jdbc ����̹��� � �����ͺ��̽��� �������� ����
			String url = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
			String userName = "KH00_TEACHER"; // ����� ������
			String password = "KH1234"; // ���� ��й�ȣ

			conn = DriverManager.getConnection(url, userName, password);

			/* 3. SQL �ۼ� */
			Scanner sc = new Scanner(System.in);
			System.out.print("�˻��� ���̵� �Է� : ");
			String input = sc.next();

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT USER_NO, USER_ID, USER_NAME, ENROLL_DATE ");
			sb.append("FROM TB_USER ");
			sb.append("WHERE USER_ID LIKE '%" + input + "%' ");
			sb.append("ORDER BY USER_NO ASC ");

			/* 4. sql�� �����ϰ� ����� �޾ƿ� Statement ��ü ���� */
			stmt = conn.createStatement();

			/*
			 * 5. Statement ��ü�� �̿��ؼ� SQL�� DB�� ���� �� ���� 1) �� : executeQuery() -> ResultSet����
			 * ��ȯ 2) DML�� : executeUpdate() -> ��� ���� ����(int) ��ȯ
			 */
			String sql = sb.toString();
			rs = stmt.executeQuery(sql);

			/*
			 * (5�� SQL�� SELECT�� ��쿡��) 6. ��ȸ ����� ����� ResultSet�� 1�྿ �����Ͽ� �� �࿡ ��ϵ� �÷� �� ������
			 */

			while (rs.next()) { // Ŀ���� ���������� �̵�, �� ������ true
				int no = rs.getInt("USER_NO");
				String id = rs.getString("USER_ID");
				String name = rs.getString("USER_NAME");
				java.sql.Date enrollDate = rs.getDate("ENROLL_DATE");

				// java.sql.Date : DB�� Date Ÿ���� �����ϴ� Ŭ����

				System.out.printf("%d / %s / %s / %s \n", no, id, name, enrollDate.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}