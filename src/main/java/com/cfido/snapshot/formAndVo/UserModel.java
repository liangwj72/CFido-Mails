package com.cfido.snapshot.formAndVo;

import com.cfido.snapshot.domain.Users;

/**
 * <pre>
 * user表的model
 * </pre>
 * 
 * @author <a href="https://github.com/liangwj72">Alex (梁韦江)</a>
 * @date 2016年3月23日
 */
public class UserModel extends BasePoModel<Users> implements Comparable<UserModel> {

	private int rank;

	public UserModel(Users po) {
		super(po);
	}

	@Override
	public int compareTo(UserModel o) {
		// 先按发帖量排序
		int res = o.getPo().getMailNum() - po.getMailNum();
		if (res == 0) {
			// 如果发帖量一样就按名字排序
			res = po.getUserName().compareTo(o.getPo().getUserName());
		}
		return res;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

}
