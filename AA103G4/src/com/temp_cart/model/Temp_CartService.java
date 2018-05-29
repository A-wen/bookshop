package com.temp_cart.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.shopping_cart.model.Shopping_CartVO;

public class Temp_CartService {
	private Temp_CartDAO_Interface DAO;
	
	
	public Temp_CartService(){
		DAO = new Temp_CartDAO();
	}
	
	/**
	 * 傳入會員編號與書籍編號，取得購物車內欲購買的數量
	 * @param mem_no 會員編號
	 * @param book_no 書籍編號
	 * @return 購物車物件
	 */
	public Temp_cartVO findByPrimaryKey(Integer mem_no,Integer book_no){
		return DAO.findByPrimaryKey(mem_no, book_no);
	}
	
	/**
	 * 傳入會員編號，取得該會員的臨時購物車內容(購物車內容已join好)
	 * @param mem_no 會員編號
	 * @return 會員購物車List
	 */
	public List<Shopping_CartVO> getAllShoppingCartVO(Integer mem_no){
		return DAO.getAllByMem(mem_no);
	}
	
	/**
	 * 傳入會員編號，取得該會員的臨時購物車內容(只有
	 * @param mem_no
	 * @return
	 */
	public List<Temp_cartVO> getAllTempCartVO(Integer mem_no){
		return DAO.getAll();
	}

	/**
	 * 新增書籍到購物車
	 * @param mem_no 會員編號
	 * @param book_no 書籍編號
	 * @param b_amount 購買數量
	 * @return 新增是否成功
	 * @throws SQLException 
	 */
	public boolean addItem(Integer mem_no,Integer book_no,Integer b_amount) 
			throws SQLException{
		Temp_cartVO vo = new Temp_cartVO();
		vo.setMem_no(mem_no);
		vo.setBook_no(book_no);
		vo.setB_amount(b_amount);
		return DAO.insert(vo);
	}
	
	/**
	 * 更新商品購買數量
	 * @param mem_no 會員編號
	 * @param book_no 書籍編號
	 * @param b_amount 商品數量
	 * @return 是否成功
	 * @throws SQLException
	 */
	public boolean updateItem(Integer mem_no,Integer book_no,Integer b_amount) throws SQLException{
		Temp_cartVO vo = new Temp_cartVO();
		vo.setMem_no(mem_no);
		vo.setBook_no(book_no);
		vo.setB_amount(b_amount);
		return DAO.update(vo);
	}
	
	/**
	 * 同步購物車內容，傳入會員編號還有從Cookie中取出的購物車資料來更新購物車內容
	 * @param mem_no 會員編號
	 * @param cookieCart 從cookie取出的購物車資料
	 * @return 更新是否成功
	 * @throws SQLException
	 */
	public boolean syncCart(Integer mem_no,Map<String,Integer> cookieCart) throws SQLException{
		//xxx rollback
		List<Temp_cartVO> cart = new ArrayList<>();
		for (Entry<String, Integer> item : cookieCart.entrySet()){
			Temp_cartVO vo = new Temp_cartVO();
			vo.setMem_no(mem_no);
			vo.setBook_no(Integer.parseInt(item.getKey()));
			vo.setB_amount(item.getValue());
			cart.add(vo);
		}
		return DAO.update(mem_no, cart);
	}
	
	/**
	 * 刪除購物車內多筆項目
	 * @param mem_no 會員編號
	 * @param books 要刪除的書籍編號陣列
	 * @return 刪除的數量
	 * @throws SQLException 
	 */
	public int delItems(Integer mem_no,Integer[] books) throws SQLException{
		return DAO.delBooks(mem_no, books);
	}

	/**
	 * 刪除特定會員購物車內全部商品
	 * @param mem_no 會員編號
	 * @return 刪除筆數
	 * @throws SQLException 
	 */
	public int delAll(Integer mem_no) throws SQLException{
		return DAO.delAll(mem_no);
	}
}
