package util.cy;



import java.util.Base64;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;



public class Base64Util {
	
	/**
	 * 傳入Base64字串，轉圖片後回傳檔案名稱
	 * @param base64Str Base64字串
	 * @return 檔案名稱
	 */
	public static String strToImg(String base64Str,String path){
		//把字串解回byte
		byte[] imageByte = Base64.getDecoder().decode(base64Str);

        String uploadFile = path+"\\"+UUIDGenerator.getUUID()+".png";
        System.out.println("File save path : " + uploadFile);
        //把Byte用ByteArrayInputStream丟給ImageIO讀成BufferedImage
        BufferedImage image;
		try {
			image = ImageIO.read(new ByteArrayInputStream(imageByte));
			//File f = new File(uploadFile);
			File f = new File(path,UUIDGenerator.getUUID());
			f.mkdirs();
			f.createNewFile();
	        if (image == null) {
	        	System.out.println("Buffered Image is null");
	        }
	        ImageIO.write(image, "png", f); //這邊就會把圖片用PNG編碼
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		return null;
	}
}
