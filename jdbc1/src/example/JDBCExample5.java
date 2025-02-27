package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample5 {
	public static void main(String[] args) {
		// 부서명을 입력 받아
		// 해당 부서의 근무하는 사원의 
		// 사번, 이름, 부서명, 직급명을 
		// 직급코드 내림차순으로 조회
		
		Scanner sc = new Scanner(System.in); 
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
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
			// 범위 입력 받기
			
			System.out.println("== 범위 내 급여 받는 직원 조회 ==");
			System.out.print("최소값 입력");
			int min = sc.nextInt();
						
			System.out.print("최대값 입력");
			int max = sc.nextInt();
						
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT E.EMP_ID, E.EMP_NAME, D.DEPT_TITLE , J.JOB_NAME ");
			sb.append("FROM EMPLOYEE E ");
			sb.append("JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID ) ");
			sb.append("JOIN JOB J ON (E.JOB_CODE = J.JOB_CODE) ");
			sb.append("ORDER BY E.JOB_CODE DESC ");
			
			String sql = sb.toString();
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next() ) {
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String deptTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				
				System.out.printf(
						"%s / %s / %s / %s \n",
						empId, empName, deptTitle, jobName);
			}

			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
