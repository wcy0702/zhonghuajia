package com.wcy.parse.video;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSONObject;
import com.wcy.dto.Video;
import com.wcy.parse.Parser;
import com.wcy.serv.model.Episode;
import com.wcy.tools.JsoupUtils;

public class YouKu implements Parser<Video>{
	 private final static String VIDEO_INFO_API = "http://play-ali.youku.com/play/get.json?vid=%s&ct=12";
	  private final static String VIDEO_PLAY = "http://pl.youku.com/playlist/m3u8?vid=%s&type=%s&ts=%s&keyframe=0&ep=%s&sid=%s&token=%s&ctype=12&ev=1&oip=%s";
	  private final static String[] TYPE = {"flv", "mp4", "hd2", "hd3"};

	  private final static String VID_REGX = "id_(.*?)(_|\\.html)";

	  private static int[] e = { 19, 1, 4, 7, 30, 14, 28, 8, 24, 17, 6, 35, 34, 16, 9, 10, 13, 22, 32, 29, 31, 21, 18, 3, 2, 23, 25, 27, 11, 20, 5, 15, 12, 0, 33, 26 };

	  @Override
	  public Video parse(String url) {
	    Video videoInfo = new Video();
	    String vid = getVid(url);
	    String api = String.format(VIDEO_INFO_API, vid);
	    JSONObject get = JSONObject.parseObject(JsoupUtils.getDocWithPhone(api).text());
	    videoInfo.setTitle(get.getJSONObject("data").getJSONObject("video").getString("title"));
	    videoInfo.setImage(get.getJSONObject("data").getJSONObject("video").getString("logo"));
	    JSONObject security = get.getJSONObject("data").getJSONObject("security");
	    String encrypt_string = security.getString("encrypt_string");
	    String ip = security.getString("ip");
	    String result = rc4(translate("b4eto0b4", e), decode64(encrypt_string));
	    String[] param = result.split("_");
	    String sid = param[0];
	    String token = param[1];
	    String ep = encode64(rc4(translate("boa4poz1", e), sid + "_" + vid + "_" + token));
	    //ep = UrlUtils.urlEncodeByUTF8(ep);
	    long ts = new Date().getTime() / 1000;
	    videoInfo.setPlayUrl(String.format(VIDEO_PLAY, vid, TYPE[2], ts, ep, sid, token, ip));
	    return videoInfo;
	  }

	  private String getVid(String url){
	    Matcher matcher = Pattern.compile(VID_REGX).matcher(url);
	    if (matcher.find()){
	      return matcher.group(1);
	    }else {
	      if (url.contains("tudou.com")){
	        Document document = JsoupUtils.getDocWithPhone(url);
	        matcher = Pattern.compile("vcode: '(.*?)'").matcher(document.html());
	        if (matcher.find()){
	          return matcher.group(1);
	        }
	      }
	    }
	    return "";
	  }

	  // 优酷算法来源：unifull_.js
	  private static String decode64(String a) {
	    int b, c, f = 0, g, d, e;
	    String h = "";
	    int[] i = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
	                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54,
	                55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
	                14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33,
	                34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };
	    for (g = a.length(); f < g;) {
	      do {
	        b = i[255 & (int) a.charAt(f++)];
	      } while (f < g && b == -1);
	      if (b == -1)
	        break;
	      do {
	        c = i[255 & (int) a.charAt(f++)];
	      } while (f < g && c == -1);
	      if (c == -1)
	        break;
	      h += (char) (b << 2 | (48 & c) >> 4);
	      do {
	        d = 255 & (int) a.charAt(f++);
	        if (61 == d){
	          return h;
	        }
	        d = i[d];
	      } while (f < g && d == -1);
	      if (d == -1)
	        break;
	      h += (char) ((15 & c) << 4 | (60 & d) >> 2);
	      do {
	        e = 255 & (int) a.charAt(f++);
	        if (61 == e)
	          return h;
	        e = i[e];
	      } while (f < g && e == -1);
	      if (e == -1)
	        break;
	      h += (char) ((3 & d) << 6 | e);
	    }
	    return h;
	  }

	  private static String rc4(String a, String b) {
	    int c, e = 0, g = 0;
	    String f = "";
	    int[] d = new int[256];
	    for (; g < 256; g++) {
	      d[g] = g;
	    }
	    for (g = 0; g < 256; g++) {
	      e = (e + d[g] + a.charAt(g % a.length())) % 256;
	      c = d[g];
	      d[g] = d[e];
	      d[e] = c;
	    }
	    g = 0;
	    e = 0;
	    for (int h = 0; h < b.length(); h++) {
	      g = (g + 1) % 256;
	      e = (e + d[g]) % 256;
	      c = d[g];
	      d[g] = d[e];
	      d[e] = c;
	      f += (char) (b.charAt(h) ^ d[(d[g] + d[e]) % 256]);
	    }
	    return f;
	  }

	  private static String translate(String a, int[] b) {
	    char[] c = new char[26];
	    for (int d = 0; d < a.length(); d++) {
	      int e;
	      boolean flag = (a.charAt(d) >= 'a' && a.charAt(d) <= 'z');
	      if (flag) {
	        e = (int) a.charAt(d) - (int) 'a';
	      } else {
	        e = (int) a.charAt(d) - (int) '0' + 26;
	      }
	      for (int f = 0; f < 36; f++) {
	        if (b[f] == e) {
	          e = f;
	          break;
	        }
	      }
	      if (e > 25) {
	        c[d] = (char) (e + 22);
	      } else {
	        c[d] = (char) (e + 97);
	      }
	    }
	    return new String(c).trim();
	  }

	  private String encode64(String a) {
	    int c = 0, d, e, f, g;
	    String b = "";
	    String h = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	    for (d = a.length(); c < d;) {
	      e = 255 & a.charAt(c++);
	      if (c == d) {
	        b += h.charAt(e >> 2);
	        b += h.charAt((3 & e) << 4);
	        b += "==";
	        break;
	      }
	      f = a.charAt(c++);
	      if (c == d) {
	        b += h.charAt(e >> 2);
	        b += h.charAt((3 & e) << 4 | (240 & f) >> 4);
	        b += h.charAt((15 & f) << 2);
	        b += "=";
	        break;
	      }
	      g = a.charAt(c++);
	      b += h.charAt(e >> 2);
	      b += h.charAt((3 & e) << 4 | (240 & f) >> 4);
	      b += h.charAt((15 & f) << 2 | (192 & g) >> 6);
	      b += h.charAt(63 & g);
	    }
	    return b;
	  }

	@Override
	public List<Episode> parseEpisodes(String url) {
		// TODO Auto-generated method stub
		return null;
	}
}
