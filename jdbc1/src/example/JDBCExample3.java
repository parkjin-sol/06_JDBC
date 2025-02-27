package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Exception;
import java.sql.Statement;

public class JDBCExample3 {
	public static void main(String[] args) {
		
		// EMPLOYEE ���̺��� 
		// �޿��� 300�� �̻� 500�� ���Ϸ� �޴� ����� 
		// ���, �̸�, �μ��ڵ�, �޿���
		// �޿� ������������ ���
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// Oracle JDBC Driver�� �޸𸮿� �ε�(����) == ��ü�� ����
			Class.forName("oracle.jdbc.OracleDriver");
						
			String type = "jdbc:oracle:thin:@";
			String host = "112.221.156.34";
			String port = ":12345";
			String dbName = ":XE";
			String userName = "KH00_TEACHER";
			String password = "KH1234";
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT EMP_ID");
			
			
			
			conn = DriverManager.getConnection( 
					type + host + port + dbName + userName + password 
					);
		
			
			String sql = "SELECT * FROM DEPARTMENT";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int empId = rs.getInt("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String deptCode = rs.getString("DEPT_CODE");
				int salary = rs.getInt("SALARY");
				
				System.out.printf(
						"%s / %s / %s / %d \n",
						empId, empName, deptCode, salary
						);
			}
			
			
		} catch(Exception e) { // ����ó���� ������ ����
			
		}
		
	}
}
