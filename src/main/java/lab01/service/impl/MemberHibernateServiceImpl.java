package lab01.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import lab01.dao.MemberDao;
import lab01.dao.impl.MemberHibernateDaoImpl;
import lab01.model.MemberBean;
import lab01.service.MemberService;
import lab01.utils.HibernateUtils;

//The functions in this class encapsulate business logic
public class MemberHibernateServiceImpl implements MemberService {
	
	private MemberDao memberDao;
	private SessionFactory factory;
	
	public MemberHibernateServiceImpl() {
		memberDao = new MemberHibernateDaoImpl();
		factory = HibernateUtils.getSessionFactory();
	}	

	public MemberBean findById(Integer id) {
		MemberBean memberBean;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			memberBean = memberDao.findById(id);
			tx.commit();
			
		}catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return memberBean;
	}
	
	public List<MemberBean> findAll() {
		List<MemberBean> memberBeans = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			memberBeans = memberDao.findAll();
			tx.commit();
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return memberBeans;
	}

	public void save(MemberBean bean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			memberDao.save(bean);
			tx.commit();
			
		}catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}


	public void deleteById(Integer id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			memberDao.deleteById(id);
			tx.commit();
			
		}catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public boolean existsByMemberId(String memberId) {
		boolean exist = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			exist = memberDao.existsByMemberId(memberId);
			tx.commit();
			
		}catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return exist;
	}

	@Override
	public void update(MemberBean memberBean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			memberDao.update(memberBean);
			tx.commit();
			
		}catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
}