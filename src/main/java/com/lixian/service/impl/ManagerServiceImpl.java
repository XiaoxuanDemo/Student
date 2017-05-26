package com.lixian.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lixian.mapper.BanjiMapper;
import com.lixian.model.Banji;
import com.lixian.service.ManagerService;
@Service("ManagerService")
public class ManagerServiceImpl implements ManagerService {
	@Resource
	private BanjiMapper bjdao;
	@Transactional
	public boolean importClass(List<Banji> bjs) {
		// TODO Auto-generated method stub
		for (int i = 0; i < bjs.size(); i++) {
			Banji bj = bjs.get(i);
			bjdao.insert(bj);
		}
		return true;
	}
	
	public boolean addClass(Banji bj) {
		// TODO Auto-generated method stub
		int i = bjdao.insert(bj);
		if(i>0){
			return true;
		}
		return false;
	}

}
