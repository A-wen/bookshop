package com.track.model;

import java.util.*;

public interface TrackDAO_interface {

	/** 新增追蹤 **/
	public void insert(TrackVO trackVO);

	/** 追蹤沒有修改 需要就新增，不用就刪除 **/
	
	/** 刪除追蹤 **/
	public void delete(Integer mem_no, Integer book_no);

	/** 查詢某個追蹤 **/
	public TrackVO findByPrimaryKey(Integer mem_no, Integer book_no);

	/** 查詢該會員追蹤的所有書 **/
	public List<TrackVO> getByMember(Integer mem_no);// 查會員追蹤的書

	/** 查詢追蹤某書的所有會員 **/
	public List<TrackVO> getByBook(Integer book_no);// 查追蹤此書的會員

	/** 列出所有追蹤 **/
	public List<TrackVO> getAll();
}
