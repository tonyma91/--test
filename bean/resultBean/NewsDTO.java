package com.zr0817.news0817.bean.resultBean;

import com.zr0817.news0817.bean.News;
import com.zr0817.news0817.bean.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
  * @description user+images
  * @author majinming@xiaomi.com
  * @date 2021年 03月10日 19:48
  *@param 
  *@retgurn 
  */
@Data
public class NewsDTO {
 private News user;
 private List<String> imagesList ;

 public NewsDTO(News news, List<String> list){
  this.user = news;
  this.imagesList = list;
 }
}
