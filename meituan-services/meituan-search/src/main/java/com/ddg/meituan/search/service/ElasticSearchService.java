package com.ddg.meituan.search.service;

import com.ddg.meituan.search.entity.DocEntity;
import org.springframework.data.domain.Page;

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
 * @date 2021/12/21 13:57
 * @email: wangzhijie0908@gmail.com
 */
public interface ElasticSearchService {

    void createIndex();

    void deleteIndex(String index);

    void save(DocEntity docBean);

    void saveAll(List<DocEntity> list);

    Iterator<DocEntity> findAll();

    Page<DocEntity> findByContent(String content);

    Page<DocEntity> findByFirstCode(String firstCode);

    Page<DocEntity> findBySecordCode(String secordCode);

    Page<DocEntity> query(String key);

}
