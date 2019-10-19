package com.itheima.test;

import com.itheima.pojo.Cluster;
import com.itheima.repository.ClusterRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringdataElasticsearchTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ClusterRepository clusterRepository;

    @Test
    public void createIndex(){
        //创建索引的同时配置映射
        elasticsearchTemplate.createIndex(Cluster.class);
    }

    @Test
    public void deleteIndex(){
        elasticsearchTemplate.deleteIndex(Cluster.class);
    }

    @Test
    public void addOrUpdateDocument(){
        Cluster cluster = new Cluster();
        cluster.setId(6L);
        cluster.setTitle("666月饼市场升温：\"哪吒表情包\"月饼、蒜泥月饼都来了");
        cluster.setContent("666\"神州北极\"漠河迎来首场霜冻,商贩穿羽绒服卖菜");

        clusterRepository.save(cluster);
    }

    @Test
    public void deleteDocument(){
        clusterRepository.deleteById(1L);
    }

    @Test
    public void findAll(){
        Iterable<Cluster> clusters = clusterRepository.findAll();
        clusters.forEach(cluster -> {
            System.out.println(cluster);
        });
    }

    @Test
    public void findById(){
        Cluster cluster = clusterRepository.findById(2L).get();
        System.out.println(cluster);
    }

    @Test
    public void findByTitleOrContent(){
        String title = "不获全胜";
        String content = "2019全国中秋";
        List<Cluster> clusterList = clusterRepository.findByTitleOrContent(title,content);
        clusterList.stream().forEach(cluster -> {
            System.out.println(cluster);
        });
    }

    @Test
    public void findByTitleOrContentForPage(){
        String title = "555555月饼";
        String content = "55555神州北极";
        Pageable pageable = PageRequest.of(0,1);
        List<Cluster> clusterList = clusterRepository.findByTitleOrContent(title, content, pageable);
        clusterList.stream().forEach(cluster -> {
            System.out.println(cluster);
        });
    }

    @Test
    public void testNativeSearchQuery(){
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery("月饼市场升温").defaultField("title"))
                .withPageable(PageRequest.of(0, 2))
                .build();
        List<Cluster> clusterList = elasticsearchTemplate.queryForList(nativeSearchQuery, Cluster.class);
        clusterList.stream().forEach(cluster -> {
            System.out.println(cluster);
        });
    }
}
