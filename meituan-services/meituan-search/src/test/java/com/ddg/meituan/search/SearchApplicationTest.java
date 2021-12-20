package com.ddg.meituan.search;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/1/28 17:15
 * @email:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchApplicationTest {

    @Test
    public void eSTest(){

        try( RestHighLevelClient http = new RestHighLevelClient(RestClient.builder(new HttpHost("101.200.140.80", 9200,
                "http")))){

            CreateIndexResponse user = http.indices().create(new CreateIndexRequest("user2"), RequestOptions.DEFAULT);
            System.out.println(user.isAcknowledged());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
