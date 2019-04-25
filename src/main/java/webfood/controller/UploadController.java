package webfood.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import webfood.config.CONST;
import webfood.utils.UUIDUtil;

/**
 * @author shixw
 */
@Controller
public class UploadController
{

	private final static Logger log = Logger.getLogger(UploadController.class);

	/**
	 * 上传图片
	 *
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/upload_image", produces =
	{ "application/json;charset=UTF-8" })
	public void upload_image(HttpServletRequest request, @RequestParam("upload") MultipartFile file1,
			HttpSession session, HttpServletResponse response) throws IOException
	{
		ServletContext sc = request.getSession().getServletContext();
		String dir = sc.getRealPath("/upload"); // 设定文件保存的目录
		String photoUrl = "";
		if (!file1.isEmpty())
		{
			String suffix = "";
			/**
			 * 获取后缀
			 */
			String originalFilename = file1.getOriginalFilename();
			if (originalFilename != null && !"".equals(originalFilename))
			{
				int index = originalFilename.indexOf(".");
				if (index > 0)
				{
					suffix = originalFilename.substring(index, originalFilename.length());
				}
			}

			String img1 = UUIDUtil.getUUID(16) + "." + suffix; // 生成随机文件名
			FileUtils.writeByteArrayToFile(new File(dir, img1), file1.getBytes());
			photoUrl = CONST.FILE_PRE + img1;
			log.info("upload over. " + img1);
		}

		String callback = request.getParameter("CKEditorFuncNum");
		response.getOutputStream().println("<script type=\"text/javascript\">");
		response.getOutputStream()
				.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + photoUrl + "','')");
		response.getOutputStream().println("</script>");
	}
}
