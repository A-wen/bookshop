package com.track.model;

import java.util.List;

public class TrackService {
	private TrackDAO_interface dao;

	public TrackService() {
		dao = new TrackDAO();
	}

	/**
	 * 新增追蹤 接會員跟書籍編號
	 **/
	public TrackVO addTrack(Integer mem_no, Integer book_no) {
		TrackVO trackVO = new TrackVO();
		trackVO.setMem_no(mem_no);
		trackVO.setBook_no(book_no);
		dao.insert(trackVO);
		return trackVO;
	}

	/**
	 * 追蹤沒有修改 需要就新增，不用就刪除
	 **/

	/** 刪除追蹤 **/
	public void deleteTrack(Integer mem_no, Integer book_no) {
		dao.delete(mem_no, book_no);
	}

	/** 查詢某個追蹤 **/
	public TrackVO getOneTrack(Integer mem_no, Integer book_no) {
		return dao.findByPrimaryKey(mem_no, book_no);
	}

	/** 查詢該會員追蹤的所有書 **/
	public List<TrackVO> getTrackByMem(Integer mem_no) {
		return dao.getByMember(mem_no);
	}

	/** 查詢追蹤某書的所有會員 **/
	public List<TrackVO> getTrackByBook(Integer book_no) {
		return dao.getByBook(book_no);
	}

	/** 列出所有追蹤 **/
	public List<TrackVO> getAllTrack() {
		return dao.getAll();
	}
}
