package com.heima.es;

import com.alibaba.fastjson.JSON;
import com.heima.es.mapper.ApArticleMapper;
import com.heima.es.pojo.SearchArticleVo;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ApArticleTest {

    @Autowired
    private ApArticleMapper apArticleMapper;

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    /**
     * 注意：數據量的導入，如果數據量過大，需要分頁導入
     * @throws Exception
     */
    @Test
    public void init() throws Exception {

        //1.查詢所有符合條件的文章數據
        List<SearchArticleVo> searchArticleVos = apArticleMapper.loadArticleList();

        //2.批量導入到es索引庫

        BulkRequest bulkRequest = new BulkRequest("app_info_article");

        for (SearchArticleVo searchArticleVo : searchArticleVos) {

            IndexRequest indexRequest = new IndexRequest().id(searchArticleVo.getId().toString())
                    .source(JSON.toJSONString(searchArticleVo), XContentType.JSON);

            //批量添加數據
            bulkRequest.add(indexRequest);

        }
        restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

    }

}