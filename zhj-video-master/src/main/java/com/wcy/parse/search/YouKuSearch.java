package com.wcy.parse.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.wcy.dto.Video;

/**
 * 优酷视频搜索
 * Created by Silence on 2017/3/27.
 */
@Component
public class YouKuSearch {

  private static final String ku = "http://www.youku.com/";
  private static final String api = "http://www.soku.com/m/y/video";
  private static final String ua = "Mozilla/5.0 (Linux; Android 4.4.2; Nexus 4 Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.23 Mobile Safari/537.36";
  //private static final String ua="Mozilla/5.0 (Windows NT 6.4; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0";
    /**
     * 搜索视频
     * @param keyword
     * @return
     */
  public List<Video> searchVideos(String keyword){
    List<Video> videos = new ArrayList<>();
    Document document = requestAPI(keyword);
    if (document==null)
      return videos;
    Elements elements = document.select("div#direct_touch");
    for (Element element : elements){
      Video video = createVideo(element);
      videos.add(video);
    }
    return videos;
  }

  private Document requestAPI(String keyword){
    try {
      return Jsoup.connect(api).userAgent(ua).ignoreContentType(true).data("q", keyword).get();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  
  private Video createVideo(Element element){
	    Video video = new Video();
	    String url = element.select("a.card_link").attr("href");
	    if (url.startsWith("http")){
	      video.setValue(url);
	    }else {
	      video.setValue(ku + url);
	    }
	    String image = element.select("div.v_img").select("img").attr("src");
	    String title = element.select("h2.v_title").text();
	    String provider = element.select("div.v_pos span").text();
	    video.setTitle(title);
	    video.setProvider(provider);
	    video.setImage(image);
	    return video;
	  }
  
//  private Video createVideo(Element element){
//    Video video = new Video();
//    String url = element.select("div.v-title").select("a").attr("href");
//    //if (url.startsWith("http")){
//      video.setValue(url);
//    //}else {
//   //   video.setValue(le + url);
//   // }
//    String image = element.select("div.v-pic").select("img").attr("src");
//    String title = element.select("a.v-link-box").attr("title");
//    String provider = element.select("a.a_cnt_icon").text();
//    video.setTitle(title);
//    video.setProvider(provider);
//    video.setImage(image);
//    return video;
//  }

}
