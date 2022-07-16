package com.joinus.persistence;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.joinus.domain.MembersVo;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Slf4j
public class MemberDaoTest {
	
	@Autowired
	MemberDao memberDao;
	
	@Test
	public void getMemberAll() {
		
//		List<MembersVo> members = memberDao.getMemberAll();
		
//		log.info("members : {}", members);
		
	}

}
