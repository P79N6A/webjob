package webfood.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import webfood.config.CONST;
import webfood.utils.UUIDUtil;

public class CookieService {
	private final static Logger log = Logger.getLogger(CookieService.class);

	public void removeCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			Cookie _cookie = new Cookie(cookie.getName(), null);
			_cookie.setMaxAge(0);
			_cookie.setPath(cookie.getPath());// 根据你创建cookie的路径进行填写
			response.addCookie(_cookie);
		}

	}

	public static String upload(HttpServletRequest request, MultipartFile file) throws IOException {

		ServletContext sc = request.getSession().getServletContext();
		String path = sc.getRealPath("/upload"); // 设定文件保存的目录

		String photoUrl = "";
		if (!file.isEmpty() && file.getSize() > 0) {
			String suffix = "";
			/**
			 * 获取后缀
			 */
			String originalFilename = file.getOriginalFilename();
			if (originalFilename != null && !"".equals(originalFilename)) {
				int index = originalFilename.indexOf(".");
				if (index > 0) {
					suffix = originalFilename.substring(index, originalFilename.length());
				}
			}
			if (!".jpg.png.gif".contains(suffix)) {
				return null;
			}

			String img1 = UUIDUtil.getUUID(16) + suffix; // 生成随机文件名
			FileUtils.writeByteArrayToFile(new File(path, img1), file.getBytes());
			photoUrl = CONST.FILE_PRE + img1;
		}

		return photoUrl;

	}

	public String getCookie(HttpServletRequest request, String id) {

		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (id.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		log.info("未在cookie中找到用户ID，未登陆！");
		return null;
	}

}
