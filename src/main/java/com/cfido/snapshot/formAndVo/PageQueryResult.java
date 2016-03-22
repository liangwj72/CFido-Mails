package com.cfido.snapshot.formAndVo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;

/**
 * 用于存储分页查询的结果
 * 
 * @author liangwj
 *
 * @param <T>
 */
public class PageQueryResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2886257609486632909L;

	// 翻页栏上面页码的数量
	private int pageBarLinkCount = 3;

	private final int pageSize;

	private long pageNo = 1;
	private long pageTotal;
	private final long itemTotal;
	private final List<T> list;
	private final PageNumBean prev = new PageNumBean();
	private final PageNumBean next = new PageNumBean();
	private String actionUrl;

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public PageQueryResult(Page<?> page, List<T> list, int pageSize) {
		// Page中第一页是0页

		this.list = list;
		this.pageSize = pageSize;

		if (page.hasContent()) {
			this.itemTotal = page.getTotalElements();
			this.pageTotal = page.getTotalPages();
			this.pageNo = page.getNumber() + 1;

			if (page.hasNext()) {
				this.next.pageNo = page.nextPageable().getPageNumber() + 1;
			} else {
				this.next.setClassForDisable();
			}

			if (page.hasPrevious()) {
				this.prev.pageNo = page.previousPageable().getPageNumber() + 1;
			} else {
				this.prev.setClassForDisable();
			}

		} else {
			this.next.setClassForDisable();
			this.prev.setClassForDisable();
			this.itemTotal = 0;
			this.pageNo = 0;
		}

	}

	public long getItemTotal() {
		return itemTotal;
	}

	public long getPageNo() {
		return pageNo;
	}

	public long getPageTotal() {
		return pageTotal;
	}

	public List<T> getList() {
		return list;
	}

	public List<PageNumBean> getPageList() {
		List<PageNumBean> res = new LinkedList<PageNumBean>();
		if (this.itemTotal > 0) {
			long begin = this.pageNo - this.pageBarLinkCount;
			if (begin < 1) {
				begin = 1;
			}

			long end = begin + 2 * this.pageBarLinkCount;
			if (end > this.pageTotal) {
				end = this.pageTotal;
			}
			for (long i = begin; i <= end; i++) {
				PageNumBean bean = new PageNumBean();
				bean.pageNo = i;
				if (bean.pageNo == this.pageNo) {
					bean.setClassForActive();
				}
				res.add(bean);
			}
		}
		return res;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageBarLinkCount(int pageBarLinkCount) {
		this.pageBarLinkCount = pageBarLinkCount;
	}

	public PageNumBean getPrev() {
		return prev;
	}

	public PageNumBean getNext() {
		return next;
	}

	public int getPageBarLinkCount() {
		return pageBarLinkCount;
	}

	public class PageNumBean implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -3616562967448665501L;
		private long pageNo = 1;
		private String classStr;

		public String getClassStr() {
			return classStr;
		}

		public long getPageNo() {
			return pageNo;
		}

		private void setClassForActive() {
			this.classStr = "class='active'";
		}

		private void setClassForDisable() {
			this.classStr = "disabled";
		}
	}
}
