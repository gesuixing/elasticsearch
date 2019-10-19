package com.itheima.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

//实体类对应document对象，需要指定索引名称、type类型
@Document(indexName = "index_cluster",type = "cluster")
public class Cluster {

    @Id
    @Field(type = FieldType.Long,store = true)
    private Long id;

    @Field(type = FieldType.text,store = true,index = true,analyzer = "ik_smart")
    private String title;

    @Field(type = FieldType.text,store = true,index = true,analyzer = "ik_smart")
    private String content;

    @Override
    public String toString() {
        return "Cluster{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
