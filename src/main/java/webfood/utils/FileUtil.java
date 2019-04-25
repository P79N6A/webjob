package webfood.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;
import webfood.config.CONST;

/**
 * 
 * @ClassName: FileUtil
 * @Description: 文件工具
 * @author: MoGuichun
 * @date: 2015年12月29日 下午3:05:21
 * 
 */
public class FileUtil {

	// 文件上传的基本路径
	private static String basePath = PropertiesUtil.getConfigProperty("file_directory");

	// 允许上传的文件类型其中504B0304为office 2007+、D0CF11E0 为office 97~03
	private final static String FILE_TYPE = "504B0304 D0CF11E0";

	/**
	 * 
	 * @Title: saveFile @Description: 将文件保存到特定的目录 @param path @param
	 *         multipartFile @return: void @throws
	 */
	public static void uploadFile(String path, MultipartFile multipartFile) {

		// 构造文件目录
		String absolutePath = basePath + File.separator + path;
		File file = new File(absolutePath);

		// 判断文件所在的目录是否存在，如果不存在则创建新的目录
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		} else {
			// 如果已存在则删除该目录下的文件
			deleteFile(file.getParentFile());
		}

		// 上传
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			if (multipartFile != null) {
				multipartFile.transferTo(file);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: deleteFile @Description: 如果是文件则删除该文件，或者如果是目录则删除该目录下的所有文件 @param
	 *         file @return: void @throws
	 */
	private static void deleteFile(File file) {
		if (file.exists()) {
			// 如果文件则删除
			if (file.isFile()) {

				file.delete();

				// 如果是目录则删除其下的子文件
			} else if (file.isDirectory()) {

				File[] files = file.listFiles();

				if (files != null) {
					for (File subFile : files) {
						subFile.delete();
					}
				}
			}
		}
	}

	/**
	 * 
	 * @Title: getDownloadFileInfo @Description: 获取文件的信息 @param
	 *         path @return @return: HashMap<String,Object> @throws
	 */
	public static HashMap<String, Object> getDownloadFileInfo(String path) {

		HashMap<String, Object> map = new HashMap<String, Object>();

		// 获取下载文件的文件名
		String[] filename = path.split(File.separatorChar == '\\' ? "\\\\" : File.separator);

		// 如果分解的文件名长度大于0则去字符串数组的最后一个元素
		if (filename.length > 0) {
			map.put("FileName", filename[filename.length - 1]);
		} else {
			map.put("FileName", path);
		}

		String absolutePath = basePath + File.separator + path;

		File file = new File(absolutePath);

		if (!file.exists()) {
			return null;
		} else {
			map.put("File", file);
		}
		return map;
	}

	/**
	 * 
	 * @Title: getFileTypeByStream @Description: 根据文件流的前四位获取上传的文件类型 @param
	 *         fileByte @return @return: String @throws
	 */
	public static String getFileTypeByStream(byte[] fileByte) {

		StringBuilder fileType = new StringBuilder();

		if ((fileByte != null) && (fileByte.length > 0)) {
			String hex;
			for (int i = 0; i < fileByte.length; i++) {
				hex = Integer.toHexString(fileByte[i] & 0xFF).toUpperCase();
				if (hex.length() == 1) {
					hex = "0" + hex;
				}
				fileType.append(hex);

				if (i > 2) {
					break;
				}
			}
			return fileType.toString();

		} else {

			return null;
		}
	}

	/**
	 * 
	 * @Title: isAccessType @Description: 验证是否是允许上传的类型 @param
	 *         multipartFile @return @return: boolean @throws
	 */
	public static boolean isAccessType(MultipartFile multipartFile) {
		try {
			return FILE_TYPE.contains(getFileTypeByStream(multipartFile.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description: 将base64编码字符串转换为图片
	 * @Author:
	 * @CreateTime:
	 * @param imgStr
	 *            base64编码字符串
	 * @param path
	 *            图片路径-具体到文件
	 * @return
	 * @throws IOException
	 */
	public static boolean generateImage(String imgStr, String path) throws IOException {
		if (imgStr == null)
			return false;
		BASE64Decoder decoder = new BASE64Decoder();

		byte[] b = decoder.decodeBuffer(imgStr);
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {
				b[i] += 256;
			}
		}
		OutputStream out = new FileOutputStream(path);
		out.write(b);
		out.flush();
		out.close();
		return true;

	}

	public static String upload(HttpServletRequest request, String imgStr) throws IOException {

		ServletContext sc = request.getSession().getServletContext();
		String path = sc.getRealPath("/upload"); // 设定文件保存的目录

		String photoUrl = "";
		if (!imgStr.isEmpty()) {
			String suffix = ".jpg";

			String img1 = UUIDUtil.getUUID(16) + suffix; // 生成随机文件名
			generateImage(imgStr, path + File.separator + img1);
			photoUrl = CONST.FILE_PRE + img1;
		}

		return photoUrl;

	}

}
