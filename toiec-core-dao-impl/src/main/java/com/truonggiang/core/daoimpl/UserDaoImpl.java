package com.truonggiang.core.daoimpl;

//import com.truonggiang.core.dao.RoleDao;
import com.truonggiang.core.common.utils.HibernateUtil;
import com.truonggiang.core.dao.UserDao;
import com.truonggiang.core.data.daoimpl.AbstractDao;
//import com.truonggiang.core.persistence.entity.RoleEntity;
import com.truonggiang.core.persistence.entity.RoleEntity;
import com.truonggiang.core.persistence.entity.UserEntity;
import org.hibernate.HibernateError;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao<Integer, UserEntity> implements UserDao {

    @Override
    public Object[] checkLogin(String name, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean isUserExist = false;
        String roleName = null;
        try {
            transaction = session.beginTransaction();
            StringBuilder sql = new StringBuilder("FROM UserEntity ue WHERE ue.name= :name AND ue.password= :password");
            Query query = session.createQuery(sql.toString());
            query.setParameter("name", name);
            query.setParameter("password", password);

            if (query.list().size() > 0) {
                isUserExist = true;
                UserEntity entity = (UserEntity) query.uniqueResult();
                roleName = entity.getRole().getName();
            }

            transaction.commit();
        } catch (HibernateError e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return new Object[] {isUserExist, roleName};
    }

    @Override
    public List<UserEntity> findUserByName(List<String> names) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<UserEntity> userEntityList = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder( "FROM UserEntity ue WHERE ue.name IN(:names)" );
            Query query = session.createQuery(sql.toString());
            query.setParameterList("names", names);
            userEntityList = query.getResultList();

            transaction.commit();
        } catch (HibernateError e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return userEntityList;
    }
}

