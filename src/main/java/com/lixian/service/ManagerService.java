package com.lixian.service;

import java.util.List;

import com.lixian.model.Banji;

public interface ManagerService {
	/**
	 * 导入班级
	 * @param bjs
	 * @return
	 */
	public boolean importClass(List<Banji> bjs);
	/**
	 * 添加班级
	 * @param bj
	 * @return
	 */
	public boolean addClass(Banji bj);
}
