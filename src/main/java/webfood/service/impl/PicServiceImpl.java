package webfood.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import webfood.mapper.ScatterPicMapper;
import webfood.model.ScatterPic;
import webfood.service.CookieService;
import webfood.service.PicService;
import webfood.utils.FileUtil;

@Service("picService")
public class PicServiceImpl implements PicService {

	@Autowired
	ScatterPicMapper scatterPicMapper;

	@Override
	public void insertMultiPic(MultipartFile[] files, HttpServletRequest request, Integer id, Integer type) {

		// 判断file数组不能为空并且长度大于0
		if (files != null && files.length > 0) {
			// 循环获取file数组中得文件
			for (MultipartFile file : files) {
				insertPic(file, request, id, type);
			}
		}
	}

	@Override
	public void insertMultiBase64Pic(HttpServletRequest request, String[] strs, Integer id, Integer type) {

		// 判断file数组不能为空并且长度大于0
		if (strs != null && strs.length > 0) {
			// 循环获取file数组中得文件
			for (String str : strs) {
				str = str.substring(23);
				insertBase64Pic(request, str, id, type);
			}
		}
	}

	@Override
	public void insertBase64Pic(HttpServletRequest request, String str, Integer id, Integer type) {

		if (str != null) {
			String pic;
			try {
				pic = FileUtil.upload(request, str);
				if (StringUtils.isEmpty(pic)) {
					return;
				}
				ScatterPic sp = new ScatterPic();
				sp.setPic(pic);
				sp.setProjectId(id);
				sp.setPtype(type);
				scatterPicMapper.insertSelective(sp);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void insertPic(MultipartFile file, HttpServletRequest request, Integer id, Integer type) {

		if (file != null) {
			String pic;
			try {
				pic = CookieService.upload(request, file);
				if (StringUtils.isEmpty(pic)) {
					return;
				}
				ScatterPic sp = new ScatterPic();
				sp.setPic(pic);
				sp.setProjectId(id);
				sp.setPtype(type);
				scatterPicMapper.insertSelective(sp);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void showSavedPic(String stype, ModelAndView mv, Integer id, Integer type) {

		List<ScatterPic> list = scatterPicMapper.queryByPidAndType(id, type);
		List<String> picList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			ScatterPic sp = list.get(i);
			picList.add(sp.getPic());

		}
		mv.addObject(stype, picList);

	}

	@Override
	public List<String> getSavedPic(Integer id, Integer type) {
		List<ScatterPic> list = scatterPicMapper.queryByPidAndType(id, type);
		List<String> picList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			ScatterPic sp = list.get(i);
			picList.add(sp.getPic());

		}
		return picList;
	}

	@Override
	public void deletePic(Integer id, Integer picProject) {
		scatterPicMapper.deleteByPid(id, picProject);

	}

}
