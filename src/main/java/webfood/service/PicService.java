package webfood.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public interface PicService {

	void insertMultiPic(MultipartFile[] files, HttpServletRequest request, Integer id, Integer picProject);

	void insertPic(MultipartFile file, HttpServletRequest request, Integer id, Integer type);

	void showSavedPic(String string, ModelAndView mv, Integer id, Integer picCompact);

	List<String> getSavedPic(Integer id, Integer picCompactpays);

	void deletePic(Integer id, Integer picProject);

	void insertMultiBase64Pic(HttpServletRequest request, String[] strs, Integer id, Integer type);

	void insertBase64Pic(HttpServletRequest request, String str, Integer id, Integer type);

}
