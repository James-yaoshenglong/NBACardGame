package network.data;

import java.sql.*;

public class DatabaseConnector {
	private static DatabaseConnector instance = new DatabaseConnector();
	
    private DatabaseConnector() {
    	
    }
    
	public static DatabaseConnector getInstance() {
		return instance;
	}
	
	final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    final String DB_URL = "jdbc:mysql://localhost:3306/gamedata?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    
    final String USER = "root";
    final String PASS = "bujiangwude";
    
    public boolean loginCheck(LoginData rawData) {
    	Connection connection = null;
        Statement statement = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
        
            // 打开链接
            System.out.println("连接数据库...");
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            statement = connection.createStatement();
            String sql;
            sql = String.format("SELECT userName, password FROM users WHERE userName= \'%s\' AND password = \'%s\'",
            		rawData.getUserName(),
            		rawData.getPassword());
            ResultSet rs = statement.executeQuery(sql);
        
            // 展开结果集数据库
           if(rs.next()) {
        	   String userName = rs.getString("userName");
        	   System.out.printf("%s login success\n", userName);
        	   return true;
           }
           else {
        	   System.out.printf("%s has not registered\n", rawData.getUserName());
           }
            // 完成后关闭
            rs.close();
            statement.close();
            connection.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(statement!=null) statement.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(connection!=null) connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return true;
    }
    
}
