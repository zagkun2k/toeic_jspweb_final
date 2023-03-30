package com.truonggiang.core.serviceimpl;

import com.truonggiang.core.dto.ExaminationDTO;
import com.truonggiang.core.dto.ExerciseDTO;
import com.truonggiang.core.persistence.entity.ExaminationEntity;
import com.truonggiang.core.persistence.entity.ExerciseEntity;
import com.truonggiang.core.service.ExaminationService;
import com.truonggiang.core.utils.ExaminationBeanUtil;
import com.truonggiang.core.utils.ExerciseBeanUtil;
import com.truonggiang.core.utils.SingletonDaoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExaminationServiceImpl implements ExaminationService {

    @Override
    public Object[] findExaminationByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<ExaminationDTO> result = new ArrayList<>();
        Object[] objects = SingletonDaoUtil.getExaminationDaoImpl().findByProperty(property, sortExpression, sortDirection, offset, limit, null);

        for (ExaminationEntity entity : (List<ExaminationEntity>)objects[1]) {
            ExaminationDTO dto = ExaminationBeanUtil.entityToDTO(entity);
            result.add(dto);
        }

        objects[1] = result;
        return objects;
    }
}
