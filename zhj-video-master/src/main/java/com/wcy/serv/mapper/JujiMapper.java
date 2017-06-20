package com.wcy.serv.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.wcy.serv.model.Episode;

import java.util.List;

/**
 * Episode 数据库映射
 * Created by Silence on 2016/12/12.
 */
@Mapper
public interface JujiMapper {

  @Select("SELECT * FROM juji WHERE video_id = #{videoId}")
  List<Episode> selectByVideoId(long videoId);

  @Select("SELECT video_id FROM juji WHERE value = #{value} LIMIT 1")
  Long selectVideoIdByValue(String value);

}
