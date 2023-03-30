package com.truonggiang.core.web.utils;

import com.truonggiang.core.daoimpl.ExerciseQuestionDaoImpl;
import com.truonggiang.core.serviceimpl.*;

public class SingletonServiceUtil {
    private static UserServiceImpl userServiceImpl = null;
    private static RoleServiceImpl roleServiceImpl = null;
    private static ListenGuidelineServiceImpl listenGuidelineServiceImpl = null;
    private static CommentServiceImpl commentServiceImpl = null;
    private static ExaminationServiceImpl examinationServiceImpl = null;
    private static ExaminationQuestionServiceImpl examinationQuestionServiceImpl = null;
    private static ExerciseServiceImpl exerciseServiceImpl = null;
    private static ExerciseQuestionServiceImpl exerciseQuestionServiceImpl = null;
    private static ResultServiceImpl resultServiceImpl = null;


    public static UserServiceImpl getUserServiceImpl() {
        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }

    public static RoleServiceImpl getRoleServiceImpl() {
        if (roleServiceImpl == null) {
            roleServiceImpl = new RoleServiceImpl();
        }
        return roleServiceImpl;
    }

    public static ListenGuidelineServiceImpl getListenGuidelineServiceImpl() {
        if (listenGuidelineServiceImpl == null) {
            listenGuidelineServiceImpl = new ListenGuidelineServiceImpl();
        }
        return listenGuidelineServiceImpl;
    }

    public static CommentServiceImpl getCommentServiceImpl() {
        if (commentServiceImpl == null) {
            commentServiceImpl = new CommentServiceImpl();
        }
        return commentServiceImpl;
    }

    public static ExaminationServiceImpl getExaminationServiceImpl() {
        if (examinationServiceImpl == null) {
            examinationServiceImpl = new ExaminationServiceImpl();
        }
        return examinationServiceImpl;
    }

    public static ExaminationQuestionServiceImpl getExaminationQuestionServiceImpl() {
        if (examinationQuestionServiceImpl == null) {
            examinationQuestionServiceImpl = new ExaminationQuestionServiceImpl();
        }
        return examinationQuestionServiceImpl;
    }

    public static ExerciseServiceImpl getExerciseServiceImpl() {
        if (exerciseServiceImpl == null) {
            exerciseServiceImpl = new ExerciseServiceImpl();
        }
        return exerciseServiceImpl;
    }

    public static ExerciseQuestionServiceImpl getExerciseQuestionServiceImpl() {
        if (exerciseQuestionServiceImpl == null) {
            exerciseQuestionServiceImpl = new ExerciseQuestionServiceImpl();
        }
        return exerciseQuestionServiceImpl;
    }

    public static ResultServiceImpl getResultServiceImpl() {
        if (resultServiceImpl == null) {
            resultServiceImpl = new ResultServiceImpl();
        }
        return resultServiceImpl;
    }
}
