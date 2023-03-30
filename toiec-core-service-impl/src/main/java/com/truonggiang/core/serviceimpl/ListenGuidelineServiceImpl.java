package com.truonggiang.core.serviceimpl;

import com.truonggiang.core.dao.ListenGuidelineDao;
import com.truonggiang.core.daoimpl.ListenGuidelineDaoImpl;
import com.truonggiang.core.dto.ListenGuidelineDTO;
import com.truonggiang.core.persistence.entity.ListenGuidelineEntity;
import com.truonggiang.core.service.ListenGuidelineService;
import com.truonggiang.core.utils.ListenGuidelineBeanUtil;
import com.truonggiang.core.utils.SingletonDaoUtil;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListenGuidelineServiceImpl implements ListenGuidelineService {

    @Override
    public Object[] findListenGuidelineByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {

        List<ListenGuidelineDTO> result = new ArrayList<>();
        Object[] objects = SingletonDaoUtil.getListenGuidelineDaoImpl().findByProperty(property, sortExpression, sortDirection, offset, limit, null);

        for (ListenGuidelineEntity entity : (List<ListenGuidelineEntity>)objects[1]) {
            ListenGuidelineDTO dto = ListenGuidelineBeanUtil.entityToDTO(entity);
            result.add(dto);
        }

        objects[1] = result;
        return objects;
    }

    @Override
    public ListenGuidelineDTO findEniqueValue(String property, Object value) {
        ListenGuidelineEntity entity = SingletonDaoUtil.getListenGuidelineDaoImpl().findEniqueValue(property, value);
        ListenGuidelineDTO dto = ListenGuidelineBeanUtil.entityToDTO(entity);
        return dto;
    }

    @Override
    public void saveListenGuideline(ListenGuidelineDTO listenGuidelineDTO) throws RuntimeException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        listenGuidelineDTO.setCreatedDate(timestamp);
        ListenGuidelineEntity entity = ListenGuidelineBeanUtil.dtoToEntity(listenGuidelineDTO);
        SingletonDaoUtil.getListenGuidelineDaoImpl().save(entity);
    }

    @Override
    public ListenGuidelineDTO updateListenGuideline(ListenGuidelineDTO listenGuidelineDTO) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        listenGuidelineDTO.setModifiedDate(timestamp);

        ListenGuidelineEntity entity = ListenGuidelineBeanUtil.dtoToEntity(listenGuidelineDTO);
        entity = SingletonDaoUtil.getListenGuidelineDaoImpl().update(entity);

        listenGuidelineDTO = ListenGuidelineBeanUtil.entityToDTO(entity);
        return listenGuidelineDTO;
    }

    @Override
    public Integer deleteListenGuideline(List<Integer> ids) {
        Integer result = SingletonDaoUtil.getListenGuidelineDaoImpl().delete(ids);
        return result;
    }
}
