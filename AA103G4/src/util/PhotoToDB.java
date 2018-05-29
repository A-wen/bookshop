package util;

import java.sql.*;
import java.io.*;

class PhotoToDB {

        static {
            try {
                 Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }

        public static void main(String argv[]) {
              Connection con = null;
              PreparedStatement pstmt = null;
              String url = "jdbc:oracle:thin:@localhost:1521:XE";
              String userid = "AA103Brian";
              String passwd = "bbbb1204";
							String pics[] = new String[100];
							for(int i = 0; i<pics.length ; i++){//陣列長度決定迴圈次數
	        				pics[i]=""+(1001+i)+".jpg";
	        			}
              try {
                con = DriverManager.getConnection(url, userid, passwd);
                InputStream fin = null;
                // �s�i�Ϥ�
                for(int i = 0; i<pics.length ; i++){//陣列長度決定迴圈次數
                	File pic = new File("pic\\book_pic",pics[i]);
             			
	                long flen = pic.length();
	                fin = new FileInputStream(pic);  
									
	                pstmt = con.prepareStatement(
										"update book set book_pic=? where book_no="+pics[i].substring(0,4));//SQL-update指令
	                pstmt.setBinaryStream(1, fin, (int)flen);
	                int rowsUpdated = pstmt.executeUpdate();
				
	                if (1 == rowsUpdated){
	                	System.out.println("UPDATE "+pics[i].substring(0,4)+" SUCCESS");
	                }else{
	                    System.out.println("updated 'book_no"+pics[i].substring(0,4)+"' is fail");
	                }
                }
	                } catch (Exception e) {
                    e.printStackTrace();
              } finally {
                    try {
                      con.close();
                    } catch (SQLException e) {
                    }
             }
      }
}
