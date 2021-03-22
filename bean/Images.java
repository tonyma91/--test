package com.zr0817.news0817.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
  * @description 保存图片名字
  * @author majinming@xiaomi.com
  * @date 2021年 03月10日 20:26
  *@ｖer 0.0.1 只预留头像的ID暂时不使用
  */
@Data
@Entity
@Table(name = "t_images")
public class Images {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 //头像
 private Long userId;
 //文章图片
 private Long newId;
 //图片名称>仅保留名称不包括路径
 private String imageName;

 @Temporal(TemporalType.TIMESTAMP)
 private Date createdTime;
 //是否已被删除
 private boolean status;


}
