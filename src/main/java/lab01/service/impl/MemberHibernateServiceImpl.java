package lab01.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lab01.dao.MemberDao;
import lab01.model.MemberBean;
import lab01.service.MemberService;

//The functions in this class encapsulate business logic
@Service
@Transactional
public class MemberHibernateServiceImpl implements MemberService {
	
	private MemberDao memberDao;
	private SessionFactory factory;

	public MemberHibernateServiceImpl(MemberDao memberDao, SessionFactory factory) {
		this.memberDao = memberDao;
		this.factory = factory;
	}

	public MemberBean findById(Integer id) {
		return memberDao.findById(id);
	}
	
	public List<MemberBean> findAll() {
		return memberDao.findAll();
	}

	public void save(MemberBean bean) {
		memberDao.save(bean);
	}


	public void deleteById(Integer id) {
		memberDao.deleteById(id);
	}

	@Override
	public boolean existsByMemberId(String memberId) {
		return memberDao.existsByMemberId(memberId);
	}

	@Override
	public void update(MemberBean memberBean) {
		memberDao.update(memberBean);
	}
}