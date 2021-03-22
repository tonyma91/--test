package com.zr0817.news0817.service.utilsService.imageServiceImpl;

import com.zr0817.news0817.bean.Images;
import com.zr0817.news0817.dao.ImagesDao;
import com.zr0817.news0817.enums.ExceEnum;
import com.zr0817.news0817.service.utilsService.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
  * @description 
  * @author majinming@xiaomi.com
  * @date 2021年 03月10日 19:56
  *@param 
  *@retgurn 
  */
@Service
public class ImageServiceImpl implements ImagesService{

 @Autowired
 private ImagesDao imagesDao;
 /**
  * @param newId
  * @return 新闻的图片列表
  */
 @Override
 public List<String> findNewImags(Long newId) {
  if (null == newId){throw new RuntimeException(ExceEnum.NULL_POINT_EXC.getMsage());};
  List<String> list = new ArrayList<>();

  //TODO 调用Ｍａｐｐｅｒ的查询方法
  for (int i = 0;i< 3;i++){
   StringBuilder stringBuilder = new StringBuilder(UUID.randomUUID().toString()) ;
   list.add(stringBuilder.toString());
  }

  return list;
 }

 @Override
 public boolean saveNewImag(List<String> list) {
  Images images =new Images();
  for (int i = 0;i< 3;i++){
   StringBuilder stringBuilder = new StringBuilder(UUID.randomUUID().toString()) ;
   images.setImageName(stringBuilder.toString());
   imagesDao.save(images);

//   list.add(stringBuilder.toString());
  }
  return false;
 }
}

