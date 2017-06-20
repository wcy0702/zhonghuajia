package com.wcy.API;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcy.dto.Video;
import com.wcy.parse.Parser;
import com.wcy.parse.ParserManager;
import com.wcy.tools.UrlUtils;

import lombok.AllArgsConstructor;

/**
 * Created by Silence on 2016/12/6.
 */
@RestController
@AllArgsConstructor
public class VideoAPI {

	@Resource
	private ParserManager parseManager;

	/**
	 * 解析视频
	 */
	@GetMapping("/api/video")
	public Video play(HttpServletRequest request) {
		String url = request.getParameter("v");
		url = url.replaceAll("\\?(spm|from).*", "");
		return parseManager.parseVideo(url);
	}

	/**
	 * 解析视频相关集数
	 */
	@GetMapping("/api/episode")
	public List episodes(HttpServletRequest request) {
		String url = request.getParameter("v");
		url = url.replaceAll("\\?(spm|from).*", "");
		String key = UrlUtils.getTopDomain(url);
		Parser videoParse = parseManager.getParser(key);
		return videoParse.parseEpisodes(url);
	}

}
