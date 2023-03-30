package com.truonggiang.core.data.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDao<ID extends Serializable, Entity> {
    List<Entity> findAll();
    Entity update(Entity entity);
    Entity save(Entity entity);
    Entity findById(ID id);
    Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit, String whereClause);
    Integer delete(List<ID> ids);

    Entity findEniqueValue (String property, Object value);

}
