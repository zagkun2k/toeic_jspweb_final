package com.truonggiang.core.data.daoimpl;

import com.truonggiang.core.common.constant.CoreConstant;
import com.truonggiang.core.common.utils.HibernateUtil;
import com.truonggiang.core.data.dao.GenericDao;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.exception.ConstraintViolationException;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbstractDao<ID extends Serializable, Entity> implements GenericDao<ID, Entity> {

    private Class<Entity> persistenceClass;
    private final Logger logger = Logger.getLogger(this.getClass());

    public AbstractDao() {
        this.persistenceClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public String getPersistenceClassName() {
        return persistenceClass.getSimpleName();
    }

    @Override
    public List<Entity> findAll() {
        List<Entity> list = new ArrayList<>();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            StringBuilder sql = new StringBuilder("from ");
            sql.append(this.getPersistenceClassName());
            Query query = session.createQuery(sql.toString());
            list = query.list();
            transaction.commit();
        } catch (HibernateError e) {
            transaction.rollback();
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public Entity update(Entity entity) {
        Entity result = null;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Object object = session.merge(entity);
            result = (Entity) object;
            transaction.commit();
        } catch (HibernateError e) {
            transaction.rollback();
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Entity save(Entity entity) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public Entity findById(ID id) {
        Entity result = null;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            result = (Entity) session.get(this.persistenceClass, id);
            if (result == null) {
                throw new ObjectNotFoundException(" NOT FOUND " + id, null);
            }
            transaction.commit();
        } catch (HibernateError e) {
            transaction.rollback();
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit, String whereClause) {
        //Khoi tao List chua cac Entity
        List<Entity> list = new ArrayList<>();

        //Khoi tao session
        Session session = HibernateUtil.getSessionFactory().openSession();
        //Khoi tao transaction
        Transaction transaction = session.beginTransaction();;

        Object totalItem = 0;
        Object[] nameQuery = HibernateUtil.buildNameQuery(property);
        try {


            //Truy van List Entity
            StringBuilder sqlList = new StringBuilder("FROM ");
            sqlList.append(this.getPersistenceClassName()).append(" WHERE 1=1 ").append(nameQuery[0]);

            if (sortExpression != null && sortDirection != null) {
                sqlList.append(" order by ").append(sortExpression).append(" " + (sortDirection.equals(CoreConstant.SORT_ASC) ? "asc" : "desc"));
            }

            if (whereClause != null) {
                sqlList.append(whereClause);
            }

            Query queryList = session.createQuery(sqlList.toString());
            setParameterToQuery(nameQuery, queryList);

            //Phan trang
            if (offset != null && offset >= 0) {
                queryList.setFirstResult(offset);//vi tri dau trang
            }
            if (limit != null && limit > 0) {
                queryList.setMaxResults(limit);//so luong max cua trang
            }

            //Tra ve list
            list = queryList.list();

            //Truy van size List Entity
            StringBuilder sqlSize = new StringBuilder("SELECT COUNT(*) FROM ");
            sqlSize.append(this.getPersistenceClassName()).append(" WHERE 1=1 ").append(nameQuery[0]);

            if (whereClause != null) {
                sqlSize.append(whereClause);
            }

            Query querySize = session.createQuery(sqlSize.toString());
            setParameterToQuery(nameQuery, querySize);

            //Tra ve kieu Object , dung de lay ra size list
            totalItem = querySize.list().get(0);

            //Xac nhan giao dich
            transaction.commit();

        } catch (HibernateError e) {

            //Giao dich lai
            transaction.rollback();
            logger.error(e.getMessage(), e);
        } finally {

            //Dong session
            session.close();
        }

        // Return ve mang object
        return new Object[]{totalItem, list};
    }

    private void setParameterToQuery(Object[] nameQuery, Query query) {
        if (nameQuery.length == 3) {
            String[] params = (String[]) nameQuery[1];
            Object[] values = (Object[]) nameQuery[2];
            for (int i2 = 0; i2 < params.length ; i2++) {
                query.setParameter(params[i2], values[i2]);
            }
        }
    }

    @Override
    public Integer delete(List<ID> ids) {
        Integer countDelete = 0;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            for (ID item : ids) {
                Entity entity = (Entity) session.get(this.persistenceClass, item);
                session.delete(entity);
                countDelete++;
            }
            transaction.commit();
        } catch (HibernateError e) {
            transaction.rollback();
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
            return countDelete;
        }
    }

    @Override
    public Entity findEniqueValue(String property, Object value) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Entity result = null;
        try {
            transaction = session.beginTransaction();
            StringBuilder sql = new StringBuilder("FROM ").append(getPersistenceClassName()).append(" model WHERE model.").append(property).append("= :value");
            Query query = session.createQuery(sql.toString());
            query.setParameter("value", value);
            result = (Entity) query.uniqueResult();

            transaction.commit();
        } catch (HibernateError e) {
            transaction.rollback();
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return result;
    }
}
