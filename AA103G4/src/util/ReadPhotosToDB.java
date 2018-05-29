package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReadPhotosToDB {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        }catch (Exception e) {
        	e.printStackTrace();
        	}
	}

	public static void main(String argv[]) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:oracle:thin:@aa103.ddns.net:1521:XE";
		String userid = "AA103G4";
		String passwd = "XAqzP8mLkNQti6C";
			
		//會員圖片
		String memPics[] = new String[12];
		for(int i = 0; i<memPics.length ; i++){//陣列長度決定迴圈次數
			memPics[i]=""+(101+i)+".jpg";
	    }
	    //書籍圖片
		String bookPics[] = new String[100];
		for(int i = 0; i<bookPics.length ; i++){//陣列長度決定迴圈次數
	    	bookPics[i]=""+(1001+i)+".jpg";
	    }
	    //廣告圖片
		String cmPics[] = new String[3];
		for(int i = 0; i<cmPics.length ; i++){//陣列長度決定迴圈次數
	    	cmPics[i]=""+(3001+i)+".jpg";
	    }
        try {
            con = DriverManager.getConnection(url, userid, passwd);
            InputStream fin = null;
            System.out.println("=====寫入會員圖片=====");
            for(int i = 0; i<memPics.length ; i++){
            	File pic = new File("pic/member_pic",memPics[i]);
	            long flen = pic.length();
	            fin = new FileInputStream(pic);  			
	            pstmt = con.prepareStatement(
					"update mem set mem_photo=? where mem_no="+memPics[i].substring(0,3));//SQL-update指令
	            pstmt.setBinaryStream(1, fin, (int)flen);
	            int rowsUpdated = pstmt.executeUpdate();
	            if (1 == rowsUpdated){
	            	System.out.println("UPDATE "+memPics[i].substring(0,4)+" SUCCESS");
	            }else{
	              	System.out.println("updated 'book_no"+memPics[i].substring(0,4)+"' is fail");
	            }
            }
            System.out.println("=====寫入書籍圖片=====");
            for(int i = 0; i<bookPics.length ; i++){//陣列長度決定迴圈次數
            	File pic = new File("pic/book_pic",bookPics[i]);
         			
                long flen = pic.length();
                fin = new FileInputStream(pic);  
								
                pstmt = con.prepareStatement(
									"update book set book_pic=? where book_no="+bookPics[i].substring(0,4));//SQL-update指令
                pstmt.setBinaryStream(1, fin, (int)flen);
                int rowsUpdated = pstmt.executeUpdate();
                if (1 == rowsUpdated){
                	System.out.println("UPDATE "+bookPics[i].substring(0,4)+" SUCCESS");
                }else{
                    System.out.println("updated 'book_no"+bookPics[i].substring(0,4)+"' is fail");
                }
            }
            System.out.println("=====寫入廣告圖片=====");
            for(int i = 0; i<cmPics.length ; i++){//陣列長度決定迴圈次數
            	File pic = new File("pic/cm_pic",cmPics[i]);
         			
                long flen = pic.length();
                fin = new FileInputStream(pic);  
								
                pstmt = con.prepareStatement(
									"update cm set cm_pic=? where cm_no="+cmPics[i].substring(0,4));//SQL-update指令
                pstmt.setBinaryStream(1, fin, (int)flen);
                int rowsUpdated = pstmt.executeUpdate();
                if (1 == rowsUpdated){
                	System.out.println("UPDATE "+cmPics[i].substring(0,4)+" SUCCESS");
                }else{
                    System.out.println("updated 'cm_no"+cmPics[i].substring(0,4)+"' is fail");
                }
            }
	    }catch (Exception e) {
            e.printStackTrace();
        }finally {
          	try {
              	con.close();
          	}catch (SQLException e) {
         		e.printStackTrace();
           	}
        }
    }
}