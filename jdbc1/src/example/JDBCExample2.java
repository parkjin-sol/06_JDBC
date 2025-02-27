package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample2 {
	public static void main(String[] args) {
		
		// EMPLOYEE ���̺��� 
		// ��� ����� ���, �̸�, �޿��� ��
		// ��� ������������ ��ȸ
		
		/* 1. JDBC ��ü ���� ���� ���� */
		Connection conn = null; // DB ���� ���� ����, �����ϴ� ��ü
		Statement stmt = null; // SQL ����, ��� ��ȯ�޴� ��ü 
		ResultSet rs = null;   // SELECT ���� ��� ���� ��ü
		
		try {
			/* 2. DriverManager ��ü�� �̿��� Connection ��ü �����ϱ�*/
			
			// Oracle JDBC Driver�� �޸𸮿� �ε�(����) == ��ü�� ����
			Class.forName("oracle.jdbc.OracleDriver");
			
			String type = "jdbc:oracle:thin:@";
			String host = "112.221.156.34";
			String port = ":12345";
			String dbName = ":XE";
			String userName = "KH00_TEACHER";
			String password = "KH1234";
			
			
			// Connection ��ü�� �����ؼ� ����
			conn = DriverManager.getConnection(
					type + host + port + dbName,
					userName,
					password
					);
			
			/* 3. SQL �ۼ� */
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT EMP_ID, EMP_NAME, SALARY ");
			sb.append("FROM EMPLOYEE ");
			sb.append("ORDER BY SALARY ASC  ");
			
			String sql = sb.toString();

			/* 4. sql�� �����ϰ� ����� �޾ƿ� Satement ��ü ���� */
			stmt = conn.createStatement();
			

			/* 5. Statement ��ü�� �̿��ؼ� SQL�� DB�� ���� �� ����
			*  1) SELECT�� : executeQuery() -> ResultSet���� ��ȯ
			* 2) DML��    : executeUpdatd() -> ��� ���� ����(int) ��ȯ
			*/
			rs = stmt.executeQuery(sql);

			/* (5�� SQL�� SELECT�� ��쿡��)
			* 6. ��ȸ ����� ����� ResultSet�� 
			* 1�྿ �����Ͽ� �� �࿡ ��ϵ� �÷� �� ������ 
			*  - 1�྿ ���� �� �� �ڵ����� Cursor�� �̿��� */
			
			while(rs.next()) {
				// rs.next() : ResultSet�� Cursor�� ���� ������ �̵�
				// ���� ���� ������ true, ������ false
				
				String empId = rs.getNString("EMP_ID");
				String empName = rs.getNString("EMP_NAME");
				int salary = rs.getInt("SALARY");
				
				System.out.printf(
						"%s / %s / %d \n", empId, empName, salary
						);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				// ���� �������� close �ϴ� ���� ����!
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			} catch(Exception e) {
				e.printStackTrace();			}
		}

		

		/* 7. ��� �Ϸ�� JDBC ��ü �ڿ� ��ȯ */
		
	}
}
