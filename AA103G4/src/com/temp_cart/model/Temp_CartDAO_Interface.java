/*
 * 8.29 更新  BY CY
 * 1. 加入取得特定會員的全部暫存購物車方法
 * 2. 新增/修改/刪除購物車方法的回傳設定
 */
package com.temp_cart.model;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

import com.shopping_cart.model.Shopping_CartVO;

public interface Temp_CartDAO_Interface {

	/**
	 * 新增暫存購物車內容
	 * @param temp_cartVO 暫存購物車物件
	 * @return 是否成功
	 */
	public boolean insert (Temp_cartVO temp_cartVO);
	
	/**
	 * 更新購物車內容
	 * @param temp_cartVO 暫存購物車物件
	 * @return 是否成功
	 * @throws SQLException 
	 */
	public boolean update (Temp_cartVO temp_cartVO);
	
	/**
	 * 傳入購物車物件陣列，更新購物車 (使用同insert的CallableStatement)
	 * @param mem_no 會員編號
	 * @param bookList 購物車物件陣列
	 * @return 是否成功
	 * @throws SQLException
	 */
	public boolean update(Integer mem_no,List<Temp_cartVO> bookList);
	
	/**
	 * 刪除會員購物車全部內容
	 * @param mem_no 會員編號
	 * @return 是否成功
	 * @throws SQLException 
	 */
	public int delAll (Integer mem_no);
	
	/**
	 * 刪除會員購物車內所選的書籍項目
	 * @param mem_no 會員編號
	 * @param books 要刪除的書籍陣列
	 * @return 是否成功
	 * @throws SQLException 
	 */
	public int delBooks(Integer mem_no,Integer[] books);
	
	/**
	 * 使用會員編號,書籍編號找到該會員在購物車內的書籍數量
	 * @param mem_no 會員編號
	 * @param book_no 書籍編號
	 * @return 購物車項目物件
	 */
	public Temp_cartVO findByPrimaryKey (Integer mem_no,Integer book_no);
	
	
	public List<Temp_cartVO> getAll();
	
	/**
	 * 取得會員暫存購物車清單
	 * @param mem_no 會員編號
	 * @return 會員購物車項目
	 */
	public List<Shopping_CartVO> getAllByMem(Integer mem_no);
	
}
