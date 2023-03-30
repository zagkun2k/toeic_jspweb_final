package com.truonggiang.core.serviceimpl;

import com.truonggiang.core.dto.ExerciseDTO;
import com.truonggiang.core.dto.ListenGuidelineDTO;
import com.truonggiang.core.persistence.entity.ExerciseEntity;
import com.truonggiang.core.persistence.entity.ListenGuidelineEntity;
import com.truonggiang.core.service.ExerciseService;
import com.truonggiang.core.utils.ExerciseBeanUtil;
import com.truonggiang.core.utils.ListenGuidelineBeanUtil;
import com.truonggiang.core.utils.SingletonDaoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExerciseServiceImpl implements ExerciseService {

    @Override
    public Object[] findExerciseByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<ExerciseDTO> result = new ArrayList<>();
        Object[] objects = SingletonDaoUtil.getExerciseDaoImpl().findByProperty(property, sortExpression, sortDirection, offset, limit, null);

        for (ExerciseEntity entity : (List<ExerciseEntity>)objects[1]) {
            ExerciseDTO dto = ExerciseBeanUtil.entityToDTO(entity);
            result.add(dto);
        }

        objects[1] = result;
        return objects;
    }
}
