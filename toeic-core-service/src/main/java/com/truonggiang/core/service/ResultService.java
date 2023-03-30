package com.truonggiang.core.service;

import com.truonggiang.core.dto.ExaminationQuestionDTO;
import com.truonggiang.core.dto.ResultDTO;

import java.util.List;

public interface ResultService {
    ResultDTO saveResult(String userName, Integer examinationId, List<ExaminationQuestionDTO> examinationQuestionDTOS);
}
