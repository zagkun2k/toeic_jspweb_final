package com.truonggiang.core.serviceimpl;

import com.truonggiang.core.dto.ExaminationQuestionDTO;
import com.truonggiang.core.dto.ResultDTO;
import com.truonggiang.core.persistence.entity.ExaminationEntity;
import com.truonggiang.core.persistence.entity.ResultEntity;
import com.truonggiang.core.persistence.entity.UserEntity;
import com.truonggiang.core.service.ResultService;
import com.truonggiang.core.utils.ResultBeanUtil;
import com.truonggiang.core.utils.SingletonDaoUtil;

import java.sql.Timestamp;
import java.util.List;

public class ResultServiceImpl implements ResultService {
    @Override
    public ResultDTO saveResult(String userName, Integer examinationId, List<ExaminationQuestionDTO> examinationQuestionDTOS) {
        ResultDTO result = new ResultDTO();
        if (userName != null && examinationId != null && examinationQuestionDTOS != null) {

            UserEntity user = SingletonDaoUtil.getUserDaoImpl().findEniqueValue("name", userName);
            ExaminationEntity examination = SingletonDaoUtil.getExaminationDaoImpl().findById(examinationId);

            ResultEntity resultEntity = new ResultEntity();
            calculateListenAndReadScore(resultEntity, examinationQuestionDTOS);
            initDataToResultEntity(resultEntity, user, examination);

            resultEntity = SingletonDaoUtil.getResultDaoImpl().save(resultEntity);
            result = ResultBeanUtil.entityToDTO(resultEntity);
        }
        return result;
    }

    private void initDataToResultEntity(ResultEntity resultEntity, UserEntity user, ExaminationEntity examination) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        resultEntity.setExaminationEntity(examination);
        resultEntity.setUserEntity(user);
        resultEntity.setCreatedDate(timestamp);
    }

    private void calculateListenAndReadScore(ResultEntity resultEntity, List<ExaminationQuestionDTO> examinationQuestionDTOS) {
        int listenScore = 0;
        int readingScore = 0;
        for (ExaminationQuestionDTO item: examinationQuestionDTOS) {
            if (item.getAnswerUser() != null) {
                if (item.getAnswerUser().equals(item.getCorrectAnswer())) {
                    if (item.getNumber() <= 4) {
                        listenScore++;
                    } else {
                        readingScore++;
                    }
                }
            }
        }
        resultEntity.setListenScore(listenScore);
        resultEntity.setReadingScore(readingScore);
    }
}
