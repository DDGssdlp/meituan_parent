package com.ddg.meituan.search.service.impl;

import com.ddg.meituan.search.dao.ElasticSearchDao;
import com.ddg.meituan.search.entity.DocEntity;
import com.ddg.meituan.search.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/12/21 13:58
 * @email: wangzhijie0908@gmail.com
 */
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    private final ElasticsearchRestTemplate elasticsearchTemplate;
    private final ElasticSearchDao elasticSearchDao;

    private Pageable pageable = PageRequest.of(0,10);

    @Autowired
    public ElasticSearchServiceImpl(ElasticsearchRestTemplate elasticsearchTemplate, ElasticSearchDao elasticSearchDao) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.elasticSearchDao = elasticSearchDao;
    }


    @Override
    public void createIndex() {
        elasticsearchTemplate.createIndex(DocEntity.class);
    }

    @Override
    public void deleteIndex(String index) {
        elasticsearchTemplate.deleteIndex(index);
    }

    @Override
    public void save(DocEntity docBean) {
        elasticSearchDao.save(docBean);
    }

    @Override
    public void saveAll(List<DocEntity> list) {
        elasticSearchDao.saveAll(list);
    }

    @Override
    public Iterator<DocEntity> findAll() {
        return elasticSearchDao.findAll().iterator();
    }

    @Override
    public Page<DocEntity> findByContent(String content) {
        return elasticSearchDao.findByContent(content,pageable);
    }

    @Override
    public Page<DocEntity> findByFirstCode(String firstCode) {
        return elasticSearchDao.findByFirstCode(firstCode,pageable);
    }

    @Override
    public Page<DocEntity> findBySecordCode(String secordCode) {
        return elasticSearchDao.findBySecordCode(secordCode,pageable);
    }

    @Override
    public Page<DocEntity> query(String key) {
        return elasticSearchDao.findByContent(key,pageable);
    }

}
