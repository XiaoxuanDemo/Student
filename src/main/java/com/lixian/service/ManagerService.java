package com.lixian.service;

import java.util.List;

import com.lixian.model.Banji;

public interface ManagerService {
	/**
	 * ����༶
	 * @param bjs
	 * @return
	 */
	public boolean importClass(List<Banji> bjs);
	/**
	 * ��Ӱ༶
	 * @param bj
	 * @return
	 */
	public boolean addClass(Banji bj);
}
