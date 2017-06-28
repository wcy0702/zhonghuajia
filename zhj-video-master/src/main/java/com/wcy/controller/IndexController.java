package com.wcy.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wcy.dto.Video;
import com.wcy.dto.VideoDTO;
import com.wcy.parse.search.VideoSearch;
import com.wcy.parse.search.YouKuSearch;
import com.wcy.redis.RedisSourceManager;
import com.wcy.serv.model.Sponsor;
import com.wcy.serv.service.FriendLinkService;
import com.wcy.serv.service.SponsorService;

import lombok.AllArgsConstructor;

/**
 * Created by Silence on 2016/11/11.
 */
@Controller
@AllArgsConstructor
public class IndexController {

	private final static String[] TAGS = { "LETV", "PANDA" };

	@Resource
	private RedisSourceManager redisSourceManager;
	@Resource
	private FriendLinkService friendLinkService;
	@Resource
	private SponsorService sponsorService;
	@Resource
	private VideoSearch videoSearch;
	
	@Resource
	private YouKuSearch youKuSearch;

	/**
	 * 首页
	 */
	@GetMapping("/")
	public String home(Model model) {
		List<VideoDTO> carouselPics = redisSourceManager
				.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIX_HOME_CAROUSEL_KEY, TAGS[0]);
		List<VideoDTO> recommends = redisSourceManager
				.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIX_HOME_RECOMMEND_KEY, TAGS[0]);
		List<VideoDTO> tvHots = redisSourceManager.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIX_HOME_TV_KEY,
				TAGS[0]);
		List<VideoDTO> animeHots = redisSourceManager
				.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIX_HOME_CARTOON_KEY, TAGS[0]);
		List<VideoDTO> movies = redisSourceManager.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIX_HOME_MOVIE_KEY,
				TAGS[0]);
		List<VideoDTO> tvTops = redisSourceManager.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIX_HOME_TV_HOT_KEY,
				TAGS[0]);
		List<VideoDTO> lives = redisSourceManager.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIx_HOME_LIVE_KEY,
				TAGS[1]);
		model.addAttribute("carouselPics", carouselPics);
		model.addAttribute("recommends", recommends);
		model.addAttribute("tvHots", tvHots);
		model.addAttribute("animeHots", animeHots);
		model.addAttribute("tvTops", tvTops);
		model.addAttribute("lives", lives);
		model.addAttribute("movies", movies);
		model.addAttribute("navIndex", -1);
		return "home";
	}

	/**
	 * 搜索
	 */
	@GetMapping(value = "/s")
	public String search(HttpServletRequest request, Model model) {
		model.addAttribute("navIndex", 1);
		String keyword = request.getParameter("wd");
		if (StringUtils.isNotEmpty(keyword)) {
			List<Video> videos = videoSearch.searchVideos(keyword);
			//List<Video> videos = youKuSearch.searchVideos(keyword);
			model.addAttribute("videos", videos);
			model.addAttribute("hasResult", true);
			model.addAttribute("size", videos.size());
		}
		return "search";
	}

	/**
	 * 高级
	 */
	@GetMapping("/video")
	public String video(Model model) {
		model.addAttribute("navIndex", 2);
		return "video";
	}

	/**
	 * 捐助
	 */
	@GetMapping("/sponsor")
	public String sponsor(Model model) {
		List<Sponsor> sponsors = sponsorService.list();
		model.addAttribute("navIndex", 3);
		model.addAttribute("sponsors", sponsors);
		return "sponsor";
	}

	/**
	 * 解析
	 */
	@GetMapping("/author")
	public String author(Model model) {
		model.addAttribute("navIndex", 4);
		return "author";
	}

	/**
	 * 友情链接
	 */
	@GetMapping("/friend")
	public String friend(Model model) {
		model.addAttribute("appName", friendLinkService.getAppName());
		model.addAttribute("appDomain", friendLinkService.getAppDomain());
		return "friend-link";
	}

	/**
	 * 登录
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * 注册
	 */
	@GetMapping("/register")
	public String register() {
		return "register";
	}

}
