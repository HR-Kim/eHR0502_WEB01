package kr.co.ehr.user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ehr.cmn.DTO;
import kr.co.ehr.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	Logger  LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserDaoImpl userDaoImpl;
	
	@Override
	public DTO get_selectOne(DTO dto) {
		LOG.debug("=============================");
		LOG.debug("=@Service========="+dto);
		LOG.debug("=============================");
		return userDaoImpl.get_selectOne(dto);
	}

}
