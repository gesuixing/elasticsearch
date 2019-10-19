package com.itheima.repository;

import com.itheima.pojo.Cluster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ClusterRepository extends ElasticsearchRepository<Cluster,Long> {
    //自定义命名规则查询
    List<Cluster> findByTitleOrContent(String title,String content);
    List<Cluster> findByTitleOrContent(String title, String content, Pageable pageable);
}
