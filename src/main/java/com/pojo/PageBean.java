package com.pojo;

public class PageBean {

	@SuppressWarnings("unused")
	private Integer pageStart; //实际的数据库从第几行�?始查�?
	private Integer rows; //easyui传入的每页多少行
	private Long total;//总的数据库的数据条数
	private Integer page;//easyui传入的第几页

	public PageBean(Integer page, Integer rows) {
		super();
		this.page = page;
		this.rows = rows;

	}

	public Integer getPageStart() {
		return (page - 1) * rows;
	}

	public void setPageStart(Integer pageStart) {
		this.pageStart = pageStart;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	// public Integer getTotal() {
	// return total;
	// }
	// public void setTotal(Integer total) {
	// this.total = total;
	// }
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
