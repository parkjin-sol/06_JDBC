package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample1 {

	public static void main(String[] args) {
		
		/* JDBC(Java DataBase Connectivity)
		 * 
		 * - Java���� DB�� ������ �� �ְ� ���ִ�
		 *   Java ���� API
		 *   -> Java���� �ִ� �ڵ� ���� DB�� ������ ���־� 
		 * 
		 * - java.sql ��Ű���� ������
		 */
		
		/* JDBC�� �����ϴ� �������̽�
		 * 
		 * 1) Connection
		 *  - Ư�� DB�� �����ϱ� ���� ������ ������ ��ü
		 *  - DB�����ּ�, ��Ʈ, DB�̸�, ����ڸ�, ��й�ȣ
		 *  
 		 *  - Connection�� ������ ��ü�� ���ؼ�
 		 *    SQL�� �����ϰ� ����� ��ȯ ���� �� �ִ�!

 		 * 2) Statement 
 		 * - SQL�� DB�� ����
 		 * - DB�� SQL ���� ����� ��ȯ �޴� ����
 		 * 
 		 * 3) ResultSet
 		 * - SELECT ��ȸ ����� �����ϰ� �ٷ�� ��ü
 		 * - �ٷ� �� CURSOR�� �̿��� 1�� �� ����!
 		 * 
 		 * 4) DriverManager Ŭ����
 		 * - DB ���� ������ JDBC ����̹��� �̿��ؼ�
 		 *   ���ϴ� DB�� ������ �� �ִ� 
 		 *   Connection ��ü�� �����ϴ� ������ ��ü
 		 * 
		 */
		
		
		/* 1. JDBC ��ü ���� ���� ���� */
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs   = null;
		try {
			
			/* 2. DriverManager ��ü�� �̿��� 
			 *   Connection ��ü �����ϱ�
			 * */
			
			/* 2-1. Oracle JDBC Driver�� �޸𸮿� Load �ϱ� */
			Class.forName("oracle.jdbc.OracleDriver");
			
			// Class.forName("Ŭ������")
			// - �ش� Ŭ������ �̿��ؼ� ��ü ����
			//  --> ��ü ���� == �޸𸮿� �Ҵ�(���� == Load)
			//  --> Connection ��ü�� ���� �� �ڵ����� �����
			
			
			/* 2-2. DB ���� ���� �ۼ� */
			String type = "jdbc:oracle:thin:@";
			String host = "112.221.156.34";
			String port = ":12345";
			String dbName = ":XE";
			String userName = "KH00_TEACHER";
			String password = "KH1234";

			/* 2-3. DriverManager�� �̿��ؼ� Connection ��ü ���� */
			conn = DriverManager.getConnection(
				type + host + port + dbName, // DB URL
				userName,  // ����� ������
				password   // ��й�ȣ
			);
			// -> 2-1���� �ε�� JDBC ����̹� + ���� ������ �̿���
			//   DB�� ����� Connection�� �����ؼ� ����
			
			// System.out.println(conn);  --> conn ��ü ���� Ȯ��
			
			/* 3. SQL �ۼ� 
			 * 
			 * !! ���� ���� !! 
			 * - JDBC �ڵ忡�� SQL �ۼ� ��
			 *   �������� �����ݷ� �ۼ� X
			 *   -> �ۼ� �� :
			 *   "SQL ��ɾ �ùٸ��� ������� �ʾҽ��ϴ�." ���� �߻�
			 *   (��? JDBC�� �� ���� SQL�� 1�� ���� ���� 
			 *    => DB�� SQL ���� �� �ڵ����� ���� ; �ٿ��� )
			 * 
			 * 
			 * 
			 * 
			 * */
			
			String sql = "SELECT * FROM DEPARTMENT";
			
			/* 4. sql�� �����ϰ� ����� �޾ƿ� Satement ��ü ���� */
			stmt = conn.createStatement();
			
			/* 5. Statement ��ü�� �̿��ؼ� SQL�� DB�� ���� �� ���� */
			rs = stmt.executeQuery(sql);
			// stmt.executeQurey(sql)
			// - SELECT���� �ۼ��� sql�� DB�� ������ ���� ��
			//   DB���� ��ȯ�� ����� ResultSet ���·� ��ȯ �� return
			
			/* 6. ��ȸ ����� ����� ResultSet��
			 *    1�྿ �����Ͽ� �� �࿡ ��ϵ� �÷� �� ������ 
			 *    - 1�྿ ���� �� �� �ڵ����� Cursor�� �̿��� 
			 * */
			
			// rs.next() : Cursor�� ���� ������ �̵� ��
			//             ���� ������ true, ������ false ��ȯ
			//             ��, ù ȣ�� �ÿ��� 1������ �̵�
			while(rs.next()) {
				
				// rs.get �ڷ��� (�÷��� || ����)
				// - ���� Cursor�� ����Ű�� ���� �÷� ���� ����
				// - �ڷ��� �ڸ����� DB���� �о���� ��
				//   Java���� �����ϱ� ������ �ڷ����� �ۼ�
				
				String deptId= rs.getString("DEPT_ID");
				String deptTitle = rs.getString("DEPT_TITLE");
				String locationId = rs.getString("LOCATION_ID");
				
				System.out.printf("�μ��ڵ� : %s / �μ��� : %s "
						+ "/ �����ڵ� : %s \n", deptId, deptTitle, locationId);
			}
			
		}catch (SQLException e) {
			// SQLException : DB ����� ���õ� ���� �� �ֻ��� ����
			e.printStackTrace();
		
		}catch (ClassNotFoundException e) {
			// ojdbc10.jar���� �����ϴ�
			// OracleDriver Ŭ������ ���� ��� �߻��ϴ� ����
			e.printStackTrace();
		
		} finally {
			// JDBC ��ü�� �ܺ� �ڿ�(DB)�� ����� ���¶�
			// Java ���α׷� ���� �Ŀ��� ������ �����ǰ� �ִ�!
			// -> �������� �� �ݾ��༭ �޸� ��ȯ�ؾ���!
			try {
				if(rs   != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
	}
	
}