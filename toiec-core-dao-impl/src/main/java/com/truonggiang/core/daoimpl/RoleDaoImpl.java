package com.truonggiang.core.daoimpl;

import com.truonggiang.core.common.utils.HibernateUtil;
import com.truonggiang.core.dao.RoleDao;
import com.truonggiang.core.data.daoimpl.AbstractDao;
import com.truonggiang.core.persistence.entity.RoleEntity;
import com.truonggiang.core.persistence.entity.UserEntity;
import org.hibernate.HibernateError;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends AbstractDao<Integer, RoleEntity> implements RoleDao {

    @Override
    public List<RoleEntity> findRoleByName(List<String> roles) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<RoleEntity> roleEntityList = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder( "FROM RoleEntity re WHERE re.name IN(:roles)" );
            Query query = session.createQuery(sql.toString());
            query.setParameterList("roles", roles);
            roleEntityList = query.getResultList();

            transaction.commit();
        } catch (HibernateError e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return roleEntityList;
    }
}
