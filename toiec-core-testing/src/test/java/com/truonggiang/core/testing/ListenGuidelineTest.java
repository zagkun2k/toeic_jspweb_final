//package com.truonggiang.core.testing;
//
//import com.truonggiang.core.dao.ListenGuidelineDao;
//import com.truonggiang.core.daoimpl.ListenGuidelineDaoImpl;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class ListenGuidelineTest {
//
//    public ListenGuidelineDao listenGuidelineDao;
//    @BeforeTest
//    public void initData() {
//        listenGuidelineDao = new ListenGuidelineDaoImpl();
//    }
//    @Test
//    //Done
//    public void checkFindByProperty () {
//
//        Map<String, Object> property = new HashMap<>();
//        property.put("title","7");
//        property.put("content","content7");
//
//        Object[] objects = listenGuidelineDao.findByProperty(property, null, null, null, null);
//
//
//
////        Object[] objects = listenGuidelineDao.findByProperty(null, null, null, null, 3, 2);
//    }
//}
