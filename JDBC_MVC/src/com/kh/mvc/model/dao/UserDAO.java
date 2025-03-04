package com.kh.mvc.model.dao;

import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.model.dto.UserDTO;

/**
 * DAO(Data Access Object)
 * 
 * �����ͺ��̽� ���ñ� �۾�(CRUD)�� ���������� ����ϴ� ��ü
 * DAO�ȿ� ��� �޼ҵ���� �����ͺ��̽��� ���õ� ������� �����
 * 
 * Controller�� ���� ȣ��� ����� ����
 * DB�� ���� ������ �� SQL���� �����ϰ� ��� �ޱ� (JDBC)
 * 
 */
public class UserDAO {
	
	/*
	 * JDBC �� ��ü 
	 * 
	 * - Connection :
	 *   DB���� ���������� ��� �ִ� ��ü
	 *   (IP�ּ�, Port, ����ڸ�, ���)
	 * 
	 * - Statement : 
	 * 	 Connection�� ������ �ִ� �������� DB�� 
	 *   SQL���� �����ϰ� �����ϰ� ����� �޾ƿ��� ��ü
	 *   
	 * - ResultSet :
	 *   ������ SQL���� SELECT�� �� ��� ��ȸ�� ����� ó�� ���� ��ü 
	 * 	          
	 * - PreparedStatement : SQL ���� �̸� �غ��ϴ� ����
	 * 	                     ?(��ġȦ��)�� Ȯ���س��� ������
	 *                       ����ڰ� �Է��� ����� ���ε��ؼ� SQL���� ����
	 * 
	 * ** ó�� ����
	 * 
	 * 1) JDBC Driver ��� : �ش� DBMC���� �����ϴ� Ŭ������ �������� ���
	 * 2) Connection ��ü ���� : �����ϰ����ϴ� DB�� ������ �Է��ؼ� ���� 
	 *                           (URL, ������̸�, ��й�ȣ)
	 * 3_1) PreparedStatement ��ü ���� : 
	 *      Connection ��ü�� �̿��ؼ� ���� (�̿ϼ��� SQL���� �̸� ����)
	 * 3_2) �̿ϼ��� SQL���� �ϼ����·� ������־����
	 *      => �̿ϼ��� ��쿡�� �ش� / �ϼ��� ��쿡�� ����                       
	 * 4) SQL���� ���� : excuteXXX() => SQL�� ���ڷ� �������� ����! 
	 *                  > SELECT(DQL) : executeQuery()
	 *                  > DML         : executeUpdate()
	 * 
	 * 5) ����ޱ� : 
	 *              > SELECT : ResultSetŸ�� ��ü(��ȸ�����ʹ��)
	 *              > DML    : int(ó���� ���� ����)
	 * 
	 * 6_1) ResultSet�� ����ִ� �����͵��� �ϳ��ϳ��� �̾Ƽ� DTO ��ü 
	 * 	    �ʵ忡 �Űܴ��� �� ��ȸ ����� ���� ���� ��� List�� ����
	 * 
	 * 6_2) Ʈ����� ó�� 
	 * 7) (������ �� ����) �ڿ��ݳ�(close) => ������ �������� 
	 * 8) �����ȯ -> Controller 
	 *	              SELECT > 6_1���� �����
	 *                DML : ó���� ���� ���� 
	 *
	 */
	
	
	public List<UserDTO> findAll() {
		/*
		 * VO / DTO / Entity
		 * 
		 * 1���� ȸ���� ������ 1���� UserDTO��ü�� �ʵ忡 ���� ��ƾ߰ڴ�.
		 * 
		 * ������ : userDTO�� ��� ������ �� �� ����
		 * 
		 */
		
		List<UserDTO> list = new ArrayList();
		
		String sql = "SELECT "
					    + "USER_NO"
						+ ", USER_ID"
					    + ", USER_PW"
						+ ", USER_NAME"
					    + ", ENROLL_DATE "
					+ "FROM "
					     + "TB_USER" 
					+ "ORDER "
					    + "BY "
					    	+ "ENROLL_DATE DESC";
		return list;
						
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
