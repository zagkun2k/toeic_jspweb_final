package com.truonggiang.core.service;

import com.truonggiang.core.dto.ListenGuidelineDTO;
import com.truonggiang.core.dto.UserDTO;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;
import java.util.Map;

public interface ListenGuidelineService {
    Object[] findListenGuidelineByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    ListenGuidelineDTO findEniqueValue(String property, Object value);

    void saveListenGuideline (ListenGuidelineDTO listenGuidelineDTO) throws RuntimeException;

    ListenGuidelineDTO updateListenGuideline(ListenGuidelineDTO listenGuidelineDTO);
    Integer deleteListenGuideline(List<Integer> ids);
}
