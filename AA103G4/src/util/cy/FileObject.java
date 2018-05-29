package util.cy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.event_info.model.Event_InfoDAO;

import java.util.Base64;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import static java.nio.file.StandardCopyOption.*;


public class FileObject {
	private String path;
	private File dir;
	private static Logger logger = Logger.getLogger(FileObject.class);
	
	/**
	 * 輸入資料夾路徑已建立物件 
	 * @param path: 資料夾路徑
	 */
	public FileObject(String path){
		this.path = path;
		dir = new File(path);
		if(!dir.exists()){
			//dir.mkdir(); //MAC會失敗，再來找是權限還是路徑問題..
			dir.mkdirs();
		}
	}
	
	/**
	 * 
	 * @return 目錄內檔案清單
	 */
	public ArrayList<String> getFileList(){
		File[] fl = dir.listFiles(); 
		ArrayList<String> list = new ArrayList<>();
		for (File f:fl){
			if (f.isDirectory()){
				list.add("/"+f.getName()+"..");
			}else{
				list.add(f.getName());
			}
		}
		System.out.println(path);
		return list;
	}
	

	/**
	 * 輸入inputstream,建立檔案
	 * @param input: InputStream
	 * @param filename: 檔案名稱
	 */
	public void writeFile(InputStream input,String filename)throws IOException{
		File file = new File(dir,filename);
		try {
			OutputStream out = new FileOutputStream(file);
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = input.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.close();
		}finally{
			input.close();
		}
	}	

	/**
	 * 傳入Base64字串，建立圖片檔案
	 * @param base64Str Base64字串
	 * @param fileType 檔案格式 "png"/"jpg"
	 * @return 檔案名稱
	 */
	public String base64ToImg(String base64Str,String fileType){
		//把字串解回byte
		fileType = fileType.toLowerCase();
		byte[] imageByte = Base64.getDecoder().decode(base64Str);
		String fileName = UUIDGenerator.getUUID()+"."+fileType;
        System.out.println("File save path : " + path+ "\\" +fileName);
        //把Byte用ByteArrayInputStream丟給ImageIO讀成BufferedImage
//        BufferedImage image;
		try {
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageByte));
			//File f = new File(uploadFile);
			File imgFile = new File(path,fileName);
//			f.createNewFile();
	        ImageIO.write(image, fileType, imgFile); //這邊就會把圖片用PNG編碼
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		return fileName;
	}
	
	public String ImgTobase64(String filename){
		String base64Str = null;
		return base64Str;
	}
	
	
	/**
	 * 輸入檔案名稱，將檔案從建構子中的目錄移動到新目錄
	 * @param filename 要移動的檔案名稱
	 * @param newDir 目標資料夾路徑
	 * @return 是否成功
	 * @throws IOException
	 */
	public boolean moveTempFile(String filename,String newDir) throws IOException{
		boolean result = false;
		File srcFile = new File(dir,filename);
		File destDir = new File(newDir);
		logger.info("原始路徑:"+dir);
		logger.info("目標路徑:"+newDir);
		try{
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
			result = true;
		}catch (IOException e){
			throw new IOException("檔案移動失敗："+e.toString());
		}	
		return result;
	}

	/**
	 * 輸入檔案名稱，將檔案從建構子中的目錄刪除
	 * @param filename 檔案名稱
	 * @return 是否成功
	 */
	public boolean delFile(String filename) {
		File delFile = new File(dir,filename);
		return FileUtils.deleteQuietly(delFile);
	}
	
}
