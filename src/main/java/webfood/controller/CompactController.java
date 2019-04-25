package webfood.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import webfood.config.CONST;
import webfood.mapper.CompactMapper;
import webfood.mapper.CompactPayinfoMapper;
import webfood.mapper.ScatterPicMapper;
import webfood.model.Compact;
import webfood.model.CompactPayinfo;
import webfood.model.ScatterPic;
import webfood.service.PicService;
import webfood.utils.DateTimeUtil;

@Controller
public class CompactController extends BaseController {

	@Autowired
	private CompactMapper compactMapper;

	@Autowired
	ScatterPicMapper scatterPicMapper;
	@Autowired
	CompactPayinfoMapper compactPayinfoMapper;

	@Autowired
	PicService picService;

	@RequestMapping("/cproject_add")
	public ModelAndView projectAdd() {

		ModelAndView mv = new ModelAndView("/compact/project_add");
		return mv;
	}

	@RequestMapping("/compact_list")
	public ModelAndView cproject_list() throws InterruptedException {

		ModelAndView mv = new ModelAndView("/compact/project_list");
		Thread.sleep(1000);
		List<Compact> list = compactMapper.queryAllCp();
		mv.addObject("list", list);
		return mv;

	}

	@RequestMapping("/cproject_add1")
	public ModelAndView projectAdd1(HttpServletRequest request, Compact project,
			@RequestParam("files") MultipartFile[] files) throws IOException {
		ModelAndView mv = new ModelAndView();
		mv.addObject("project", project);
		project.setCreateTime(DateTimeUtil.getDateTimeNow());
		compactMapper.insertSelective(project);
		insertTwoPics(request, project.getId(), files, "base64str_compactPics", CONST.PIC_COMPACT);
		mv.setViewName("redirect:/cproject_modify?id=" + project.getId());
		return mv;
	}

	@RequestMapping("/cproject_modify1")
	public ModelAndView cproject_modify1(HttpServletRequest request, Compact project,
			@RequestParam("files") MultipartFile[] files) throws IOException {
		ModelAndView mv = new ModelAndView("redirect:/cproject_modify?id=" + project.getId());
		mv.addObject("project", project);
		project.setUpdateTime(DateTimeUtil.getDateTimeNow());
		compactMapper.updateByPrimaryKeySelective(project);
		insertTwoPics(request, project.getId(), files, "base64str_compactPics", CONST.PIC_COMPACT);
		return mv;
	}

	@RequestMapping("/cproject_modify")
	public ModelAndView projectModify(Integer id) throws InterruptedException {
		ModelAndView mv = new ModelAndView("/compact/project_modify");
		Compact project = compactMapper.selectByPrimaryKey(id);
		mv.addObject("project", project);
		showSavedPic("picList", mv, project.getId(), CONST.PIC_COMPACT);
		return mv;
	}

	@RequestMapping("/cproject_last_modify")
	public ModelAndView cproject_last_modify() throws InterruptedException {

		Compact project = compactMapper.queryLast();
		Thread.sleep(1000);
		ModelAndView mv = new ModelAndView("/compact/project_modify");
		mv.addObject("project", project);
		showSavedPic("picList", mv, project.getId(), CONST.PIC_COMPACT);
		return mv;
	}

	private void showSavedPic(String stype, ModelAndView mv, int id, int type) {
		List<ScatterPic> list = scatterPicMapper.queryByPidAndType(id, type);
		List<String> picList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			ScatterPic sp = list.get(i);
			picList.add(sp.getPic());

		}
		mv.addObject(stype, picList);
	}

	@RequestMapping("/update_compact_payinfo")
	public ModelAndView update_compact_payinfo(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/compact/update_payinfo");
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			Compact project = compactMapper.selectByPrimaryKey(Integer.parseInt(id));
			mv.addObject("project", project);

			List<CompactPayinfo> list = compactPayinfoMapper.queryByCpid(project.getId());
			mv.addObject("list", list);

		}

		return mv;
	}

	@RequestMapping("/compact_payinfo_add")
	public ModelAndView compact_payinfo_add(HttpServletRequest request, Integer id) {
		ModelAndView mv = new ModelAndView("/compact/payinfo_add");
		Compact project = compactMapper.selectByPrimaryKey(id);
		mv.addObject("project", project);
		return mv;
	}

	@RequestMapping("/compact_delete")
	public ModelAndView projectDelete(Integer id) {

		ModelAndView mv = new ModelAndView("redirect:/compact_list");
		compactMapper.deleteByPrimaryKey(id);
		return mv;
	}

	@RequestMapping("/compact_payinfo_delete")
	public ModelAndView compact_payinfo_delete(Integer id) throws Exception {

		CompactPayinfo pf = compactPayinfoMapper.selectByPrimaryKey(id);
		if (pf == null)
			throw new Exception("CompactPayinfo Not Exist!Pf Id =" + id);
		ModelAndView mv = new ModelAndView("redirect:/update_compact_payinfo?id=" + pf.getProjectId());
		compactPayinfoMapper.deleteByPrimaryKey(id);
		return mv;
	}

	@RequestMapping("/compact_payInfo_add1")
	public ModelAndView compact_payInfo_add1(HttpServletRequest request, CompactPayinfo pf,
			@RequestParam("files") MultipartFile[] files) {

		ModelAndView mv = new ModelAndView("redirect:/update_compact_payinfo?id=" + request.getParameter("projectId"));

		compactPayinfoMapper.insertSelective(pf);
		insertTwoPics(request, pf.getId(), files, "base64str_compactPayPics", CONST.PIC_compactpays);
		return mv;
	}

	@RequestMapping("/compact_view")
	public ModelAndView compact_view(Integer id) {
		Compact project = compactMapper.selectByPrimaryKey(id);
		ModelAndView mv = new ModelAndView("/compact/project_view");
		mv.addObject("project", project);

		showSavedPic("compactPicList", mv, project.getId(), CONST.PIC_COMPACT);
		List<CompactPayinfo> list = compactPayinfoMapper.queryByCpid(project.getId());
		for (CompactPayinfo pf : list) {
			pf.setPicList(picService.getSavedPic(pf.getId(), CONST.PIC_compactpays));
		}
		mv.addObject("list", list);
		return mv;
	}
}
