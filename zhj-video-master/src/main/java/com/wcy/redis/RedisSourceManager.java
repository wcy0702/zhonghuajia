package com.wcy.redis;

import com.alibaba.fastjson.JSONObject;
import com.wcy.dto.VideoDTO;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.annotation.Resource;

/**
 * 视频资源管理器
 * Created by Silence on 2017/2/12.
 */
@Component
@AllArgsConstructor
public class RedisSourceManager {

  public final String VIDEO_PREFIX_HOME_CAROUSEL_KEY = "HOME_VIDEO_CAROUSEL";
  public final String VIDEO_PREFIX_HOME_RECOMMEND_KEY = "HOME_VIDEO_RECOMMEND";
  public final String VIDEO_PREFIX_HOME_TV_KEY = "HOME_VIDEO_TV";
  public final String VIDEO_PREFIX_HOME_TV_HOT_KEY = "HOME_VIDEO_TV_HOT";
  public final String VIDEO_PREFIX_HOME_MOVIE_KEY = "HOME_VIDEO_MOVIE";
  public final String VIDEO_PREFIX_HOME_CARTOON_KEY = "HOME_VIDEO_CARTOON";
  public final String VIDEO_PREFIx_HOME_LIVE_KEY = "HOME_LIVE";

  @Resource
  private StringRedisTemplate stringRedisTemplate;

  /**
   *  保存视频信息到 Redis
   */
  public void saveVideos(String key, List<VideoDTO> videos){
    String value = JSONObject.toJSONString(videos);
    stringRedisTemplate.opsForValue().set(key, value);
  }

  /**
   *  得到视频信息
   */
  public List<VideoDTO> getVideosByKeyAndTag(String key, String tag){
    key = key + "_" + tag;
    String cacheValue = stringRedisTemplate.opsForValue().get(key);
    return JSONObject.parseArray(cacheValue, VideoDTO.class);
  }

}
