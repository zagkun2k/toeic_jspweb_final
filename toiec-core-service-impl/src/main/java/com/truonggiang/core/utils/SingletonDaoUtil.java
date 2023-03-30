package com.truonggiang.core.utils;

import com.truonggiang.core.daoimpl.*;

public class SingletonDaoUtil {
    private static UserDaoImpl userDaoImpl = null;
    private static RoleDaoImpl roleDaoImpl = null;
    private static ListenGuidelineDaoImpl listenGuidelineDaoImpl = null;
    private static CommentDaoImpl commentDaoImpl = null;
    private static ExaminationDaoImpl examinationDaoImpl = null;
    private static ExaminationQuestionDaoImpl examinationQuestionDaoImpl = null;
    private static ExerciseDaoImpl exerciseDaoImpl = null;
    private static ExerciseQuestionDaoImpl exerciseQuestionDaoImpl = null;
    private static ResultDaoImpl resultDaoImpl = null;


    public static UserDaoImpl getUserDaoImpl() {
        if (userDaoImpl == null) {
            userDaoImpl = new UserDaoImpl();
        }
        return userDaoImpl;
    }

    public static RoleDaoImpl getRoleDaoImpl() {
        if (roleDaoImpl == null) {
            roleDaoImpl = new RoleDaoImpl();
        }
        return roleDaoImpl;
    }

    public static ListenGuidelineDaoImpl getListenGuidelineDaoImpl() {
        if (listenGuidelineDaoImpl == null) {
            listenGuidelineDaoImpl = new ListenGuidelineDaoImpl();
        }
        return listenGuidelineDaoImpl;
    }

    public static CommentDaoImpl getCommentDaoImpl() {
        if (commentDaoImpl == null) {
            commentDaoImpl = new CommentDaoImpl();
        }
        return commentDaoImpl;
    }

    public static ExaminationDaoImpl getExaminationDaoImpl() {
        if (examinationDaoImpl == null) {
            examinationDaoImpl = new ExaminationDaoImpl();
        }
        return examinationDaoImpl;
    }

    public static ExaminationQuestionDaoImpl getExaminationQuestionDaoImpl() {
        if (examinationQuestionDaoImpl == null) {
            examinationQuestionDaoImpl = new ExaminationQuestionDaoImpl();
        }
        return examinationQuestionDaoImpl;
    }

    public static ExerciseDaoImpl getExerciseDaoImpl() {
        if (exerciseDaoImpl == null) {
            exerciseDaoImpl = new ExerciseDaoImpl();
        }
        return exerciseDaoImpl;
    }

    public static ExerciseQuestionDaoImpl getExerciseQuestionDaoImpl() {
        if (exerciseQuestionDaoImpl == null) {
            exerciseQuestionDaoImpl = new ExerciseQuestionDaoImpl();
        }
        return exerciseQuestionDaoImpl;
    }

    public static ResultDaoImpl getResultDaoImpl() {
        if (resultDaoImpl == null) {
            resultDaoImpl = new ResultDaoImpl();
        }
        return resultDaoImpl;
    }
}
