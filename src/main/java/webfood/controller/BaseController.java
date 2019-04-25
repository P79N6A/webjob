package webfood.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import webfood.service.PicService;

@Controller
public class BaseController {

	@Autowired
	PicService picService;

	protected boolean isPicNotEmpty(MultipartFile[] files, String[] strs) {
		return (strs != null && strs.length > 0) || (files != null && files.length > 0 && !files[0].isEmpty());
	}

	protected boolean isAttachNotEmpty(MultipartFile[] files) {
		return files != null && files.length > 0 && !files[0].isEmpty();
	}

	protected boolean isGaopaiyiNotEmpty(String[] base64str_payPics) {
		return base64str_payPics != null && base64str_payPics.length > 0;
	}

	/**
	 * insertTwoPics:插入附件及高拍仪的图片到数据库 <br/>
	 * 2018年2月22日
	 *
	 * @author djy50
	 * @param request
	 * @param id
	 * @param files
	 * @param attrName
	 * @param type
	 * @since JDK 1.6
	 */
	protected void insertTwoPics(HttpServletRequest request, int id, MultipartFile[] files, String attrName, int type) {
		String[] base64str_buildPics = request.getParameterValues(attrName);
		if (isPicNotEmpty(files, base64str_buildPics)) {
			picService.deletePic(id, type);
		}

		if (isGaopaiyiNotEmpty(base64str_buildPics)) {

			picService.insertMultiBase64Pic(request, base64str_buildPics, id, type);
		}

		if (isAttachNotEmpty(files)) {
			picService.insertMultiPic(files, request, id, type);
		}
	}
}
