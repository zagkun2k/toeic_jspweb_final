package com.truonggiang.core.serviceimpl;

import com.truonggiang.core.dto.ExaminationQuestionDTO;
import com.truonggiang.core.dto.ExerciseQuestionDTO;
import com.truonggiang.core.persistence.entity.ExaminationEntity;
import com.truonggiang.core.persistence.entity.ExaminationQuestionEntity;
import com.truonggiang.core.persistence.entity.ExerciseEntity;
import com.truonggiang.core.persistence.entity.ExerciseQuestionEntity;
import com.truonggiang.core.service.ExaminationQuestionService;
import com.truonggiang.core.utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExaminationQuestionServiceImpl implements ExaminationQuestionService {
    @Override
    public Object[] findExaminationQuestionByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit, Integer examinationId) {
        List<ExaminationQuestionDTO> result = new ArrayList<>();
        String whereClause = null;
        if (examinationId != null) {
            whereClause = " AND examinationEntity.examinationId = "+examinationId+"";
        }
        Object[] objects = SingletonDaoUtil.getExaminationQuestionDaoImpl().findByProperty(property, sortExpression, sortDirection, offset, limit, whereClause);
        int count = 1;

        for (ExaminationQuestionEntity entity : (List<ExaminationQuestionEntity>)objects[1]) {
            ExaminationQuestionDTO dto = ExaminationQuestionBeanUtil.entityToDTO(entity);
            ExaminationEntity examinationEntity = entity.getExaminationEntity();

            if (entity.getParagraph() == null) {
                dto.setNumber(count);
                count++;
            }

            dto.setExaminationDTO(ExaminationBeanUtil.entityToDTO(examinationEntity));

            result.add(dto);
        }

        objects[1] = result;
        return objects;
    }
}
