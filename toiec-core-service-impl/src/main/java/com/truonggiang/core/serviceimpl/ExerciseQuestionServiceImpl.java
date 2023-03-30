package com.truonggiang.core.serviceimpl;

import com.truonggiang.core.dto.ExerciseDTO;
import com.truonggiang.core.dto.ExerciseQuestionDTO;
import com.truonggiang.core.persistence.entity.ExerciseEntity;
import com.truonggiang.core.persistence.entity.ExerciseQuestionEntity;
import com.truonggiang.core.service.ExerciseQuestionService;
import com.truonggiang.core.utils.ExerciseBeanUtil;
import com.truonggiang.core.utils.ExerciseQuestionBeanUtil;
import com.truonggiang.core.utils.SingletonDaoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExerciseQuestionServiceImpl implements ExerciseQuestionService {
    @Override
    public Object[] findExerciseQuestionByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit, Integer exerciseId) {
        List<ExerciseQuestionDTO> result = new ArrayList<>();
        String whereClause = null;
        if (exerciseId != null) {
            whereClause = " AND exerciseEntity.exerciseId = "+exerciseId+"";
        }
        Object[] objects = SingletonDaoUtil.getExerciseQuestionDaoImpl().findByProperty(property, sortExpression, sortDirection, offset, limit, whereClause);

        for (ExerciseQuestionEntity entity : (List<ExerciseQuestionEntity>)objects[1]) {
            ExerciseQuestionDTO dto = ExerciseQuestionBeanUtil.entityToDTO(entity);
            ExerciseEntity exerciseEntity = entity.getExerciseEntity();

            dto.setExerciseDTO(ExerciseBeanUtil.entityToDTO(exerciseEntity));

            result.add(dto);
        }

        objects[1] = result;
        return objects;
    }
}
