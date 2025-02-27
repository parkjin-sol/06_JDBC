package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Exception;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample4 {
	public static void main(String[] args) {
		
		// EMPLOYEE ���̺��� 
		// �޿��� "�ּ�" �̻� "�ִ�" ���Ϸ� �޴� ����� 
		// ���, �̸�, �μ��ڵ�, �޿���
		// �޿� ������������ ���
		
		Scanner sc = new Scanner(System.in);
	
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
			
			
			
			
			
			conn = DriverManager.getConnection(
					type + host + port + dbName, userName, password 
					);
			
			// ���� �Է� �ޱ�
			System.out.println("== ���� �� �޿� �޴� ���� ��ȸ ==");
			System.out.print("�ּҰ� �Է�");
			int min = sc.nextInt();
			
			System.out.print("�ִ밪 �Է�");
			int max = sc.nextInt();
			
			
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY ");
			sb.append("FROM EMPLOYEE ");
			sb.append("WHERE SALARY BETWEEN ");
			sb.append(min);
			sb.append(" AND ");
			sb.append(max);
			sb.append(" ORDER BY SALARY DESC ");
		
			String sql = sb.toString();
			
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

		
	}
}
