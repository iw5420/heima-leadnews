package com.itheima.mongo.test;


import com.itheima.mongo.MongoApplication;
import com.itheima.mongo.pojo.ApAssociateWords;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest(classes = MongoApplication.class)
@RunWith(SpringRunner.class)
public class MongoTest {


    @Autowired
    private MongoTemplate mongoTemplate;

    //保存
    @Test
    public void saveTest(){
        ApAssociateWords apAssociateWords = new ApAssociateWords();
        apAssociateWords.setAssociateWords("黑馬頭條");
        apAssociateWords.setCreatedTime(new Date());
        mongoTemplate.save(apAssociateWords);
    }

    //查詢一個
    @Test
    public void saveFindOne(){
        ApAssociateWords apAssociateWords = mongoTemplate.findById("675995198f33bc5183e7d80e", ApAssociateWords.class);
        System.out.println(apAssociateWords);
    }

    //條件查詢
    @Test
    public void testQuery(){
        Query query = Query.query(Criteria.where("associateWords").is("黑馬頭條"))
                .with(Sort.by(Sort.Direction.DESC,"createdTime"));
        List<ApAssociateWords> apAssociateWordsList = mongoTemplate.find(query, ApAssociateWords.class);
        System.out.println(apAssociateWordsList);
    }

    @Test
    public void testDel(){
        mongoTemplate.remove(Query.query(Criteria.where("associateWords").is("黑馬頭條")),ApAssociateWords.class);
    }
}
