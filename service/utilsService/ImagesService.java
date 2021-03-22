package com.zr0817.news0817.service.utilsService;

import java.util.List;

/**
  * @description 
  * @author majinming@xiaomi.com
  * @date 2021年 03月10日 19:55
  *@param 
  *@retgurn 
  */
public interface ImagesService {
 /**
  *
  * @param newId
  * @return 新闻的图片列表
  */
 public List<String> findNewImags(Long newId);

 public  boolean saveNewImag(List<String> list);
}
