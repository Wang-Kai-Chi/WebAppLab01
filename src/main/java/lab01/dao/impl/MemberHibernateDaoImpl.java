package lab01.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import lab01.dao.MemberDao;
import lab01.model.MemberBean;
import lab01.utils.HibernateUtils;

public class MemberHibernateDaoImpl implements MemberDao {

	private SessionFactory factory;

	public MemberHibernateDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	public MemberBean findByMemberId(String id) {
		String hql = "FROM MemberEntity m WHERE m.memberId = :mId";
		MemberBean result = null;
		Session session = factory.getCurrentSession();
		try {
			result = session.createQuery(hql, MemberBean.class)
							.setParameter("mId", id)
							.getSingleResult();			
			
		}catch(NonUniqueResultException e) {
			
		}catch(NoResultException e) {
			
		}
		return result;
	}

	public List<MemberBean> findAll() {
		String hql = "FROM MemberEntity";
		Session session = factory.getCurrentSession();
		
		return session.createQuery(hql, MemberBean.class)
						.getResultList();
	}

	public void save(MemberBean bean) {
		Session session = factory.getCurrentSession();
		session.save(bean);

	}

	public void deleteByMemberId(String memberId) {
		String hql = "DELETE FROM MemberEntity m WHERE m.memberId = :mId";
		Session session = factory.getCurrentSession();
		
		session.createQuery(hql)
				.setParameter("mId", memberId)
				.executeUpdate();		
	}

	@Override
	public boolean existsByMemberId(String memberId) {
		String hql = "FROM MemberEntity m WHERE m.memberId = :mId";
		boolean exist = false;
		
		Session session = factory.getCurrentSession();
		List<MemberBean> memberBeans = session.createQuery(hql, MemberBean.class)
												.setParameter("mId", memberId)
												.getResultList();
		if(memberBeans.isEmpty()) {
			exist = false;
		}else {
			exist = true;
		}
		return exist;
	}

	@Override
	public MemberBean findById(Integer id) {
		Session session = factory.getCurrentSession();
		
		return session.get(MemberBean.class, id);
	}

	@Override
	public void deleteById(Integer id) {
		Session session = factory.getCurrentSession();
		MemberBean memberBean = session.get(MemberBean.class, id);
		
		memberBean.setId(id);
		session.delete(memberBean);
	}

	@Override
	public void update(MemberBean memberBean) {
		MemberBean temp= findById(memberBean.getId());
		memberBean.setRegisterDate(temp.getRegisterDate());
		Session session = factory.getCurrentSession();
		session.evict(temp);
		session.update(memberBean);
	}

}