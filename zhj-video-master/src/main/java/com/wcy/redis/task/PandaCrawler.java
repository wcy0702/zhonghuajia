package com.wcy.redis.task;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wcy.dto.VideoDTO;
import com.wcy.redis.RedisSourceManager;
import com.wcy.tools.JsoupUtils;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * 熊猫TV 爬虫 Created by Silence on 2017/4/30.
 */
@Component
@Log4j2
@AllArgsConstructor
public class PandaCrawler {
	private static final String PANDA = "http://www.panda.tv/";
	private static final String PANDA_ALL = "http://www.panda.tv/all";
	private static final String TAG = "PANDA";

	@Resource
	private RedisSourceManager redisSourceManager;

	/**
	 * 每隔20分钟，爬一次熊猫TV
	 */
	@Scheduled(fixedRate = 20 * 60 * 1000)
	public void start() {
		Document document = JsoupUtils.getDocWithPC(PANDA_ALL);
		savePandaLivesToRedis(document);
	}

	private void savePandaLivesToRedis(Document document) {
		List<VideoDTO> lives = new ArrayList<>();
		Elements elements = document.select("li.video-list-item.video-no-tag");
		for (Element element : elements) {
			VideoDTO videoDTO = new VideoDTO();
			String title = "[" + element.select("div.video-info span.video-cate").text() + "] "
					+ element.select("div.video-info span.video-nickname").text();
			String image = element.select("img.video-img").attr("data-original");
			String url = PANDA + element.attr("data-id");
			videoDTO.setAvailable(true);
			videoDTO.setTitle(title);
			videoDTO.setImage(image);
			videoDTO.setValue(url);
			lives.add(videoDTO);
			if (lives.size() > 48) {
				break;
			}
		}
		String key = redisSourceManager.VIDEO_PREFIx_HOME_LIVE_KEY + "_" + TAG;
		redisSourceManager.saveVideos(key, lives);
	}
}
