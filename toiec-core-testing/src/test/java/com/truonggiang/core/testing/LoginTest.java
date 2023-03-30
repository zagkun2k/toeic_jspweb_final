//package com.truonggiang.core.testing;
//
//import com.truonggiang.core.dao.UserDao;
//import com.truonggiang.core.daoimpl.UserDaoImpl;
//import com.truonggiang.core.persistence.entity.UserEntity;
//import org.apache.log4j.Logger;
//import org.testng.annotations.Test;
//
//public class LoginTest {
//    private final Logger logger = Logger.getLogger(this.getClass());
//    @Test
//    public void checkisUserExist() {
//
//        UserDao userDao = new UserDaoImpl();
//        String name = "zagkun2k";
//        String password = "123456";
//
//        UserEntity userEntity = userDao.findUserByUsernameAndPassword(name, password);
//        if (userEntity != null) {
//            logger.error("Test Successed");
//        } else {
//            logger.error("Test failed");
//        }
//    }
//
//    @Test
//    public void checkFindRoleByUser() {
//        UserDao userDao = new UserDaoImpl();
//
//        String name = "myle2003";
//        String password = "123456";
//
//        UserEntity userEntity = userDao.findUserByUsernameAndPassword(name, password);
//        logger.error(userEntity.getRole().getRoleId()  + "-" + userEntity.getRole().getName());
//    }
//}
