package webfood.controller;

import java.io.IOException;
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
import webfood.mapper.BuildMapper;
import webfood.mapper.ScatterPicMapper;
import webfood.model.Build;
import webfood.model.ScatterPic;
import webfood.model.ScatterProject;
import webfood.service.BuildService;
import webfood.service.PicService;
import webfood.service.ProjectService;
import webfood.utils.DateTimeUtil;
import webfood.utils.UUIDUtil;

@Controller
public class ScatterProjectController extends BaseController {

	@Autowired
	ProjectService projectService;
	@Autowired
	BuildService buildService;
	@Autowired
	ScatterPicMapper scatterPicMapper;
	@Autowired
	private BuildMapper buildMapper;

	@Autowired
	PicService picService;

	@RequestMapping("/sproject_add")
	public ModelAndView projectAdd() {

		ModelAndView mv = new ModelAndView("/scatterProject/project_add");
		return mv;
	}

	@RequestMapping("/sproject_list")
	public ModelAndView sprojectList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/scatterProject/project_list");

		List<ScatterProject> list = projectService.queryAllSp();
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping("/sproject_view")
	public ModelAndView projectView(Integer id) {
		ScatterProject project = projectService.querySprojectById(id);
		ModelAndView mv = new ModelAndView("/scatterProject/project_view");
		mv.addObject("project", project);
		showSavedPic(mv, project, CONST.PIC_TYPE_BEFORE);
		picService.showSavedPic("buildList", mv, project.getId(), CONST.PIC_TYPE_BUILD);
		picService.showSavedPic("processList", mv, project.getId(), CONST.PIC_TYPE_PROCESS);
		picService.showSavedPic("finishList", mv, project.getId(), CONST.PIC_TYPE_FINISH);
		picService.showSavedPic("payList", mv, project.getId(), CONST.PIC_scattarpays);
		return mv;
	}

	@RequestMapping("/sproject_query")
	public ModelAndView sproject_query(HttpServletRequest request, String ft_begin_date, String ft_end_date,
			String project) {
		ModelAndView mv = new ModelAndView("/scatterProject/project_list");

		StringBuffer sqlWhere = new StringBuffer("1=1 ");
		if (StringUtils.isNotBlank(ft_begin_date)) {
			sqlWhere.append(" and create_time >'" + ft_begin_date + "'");
			mv.addObject("ft_begin_date", ft_begin_date);
		}
		if (StringUtils.isNotBlank(ft_end_date)) {
			sqlWhere.append(" and create_time <'" + ft_end_date + "'");
			mv.addObject("ft_end_date", ft_end_date);
		}
		if (StringUtils.isNotBlank(project)) {
			sqlWhere.append(" and project like '%" + project + "%'");
			mv.addObject("project", project);
		}
		List<ScatterProject> list = projectService.querySp(sqlWhere);
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping("/sproject_modify")
	public ModelAndView projectModify(Integer id) {
		ModelAndView mv = new ModelAndView("/scatterProject/project_modify");
		ScatterProject project = projectService.querySprojectById(id);
		mv.addObject("project", project);
		showSavedPic(mv, project, CONST.PIC_TYPE_BEFORE);
		return mv;
	}

	private void showSavedPic(ModelAndView mv, ScatterProject project, int type) {
		List<ScatterPic> list = scatterPicMapper.queryByPidAndType(project.getId(), type);
		for (int i = 0; i < list.size(); i++) {
			ScatterPic sp = list.get(i);
			mv.addObject("pic" + (i + 1), sp.getPic());
			mv.addObject("id" + (i + 1), sp.getId());
		}
	}

	private void showSavedPic1(ModelAndView mv, ScatterProject project, int type) {
		List<ScatterPic> list = scatterPicMapper.queryByPidAndType(project.getId(), type);
		for (int i = 0; i < list.size(); i++) {
			ScatterPic sp = list.get(i);
			mv.addObject("ppic" + (i + 1), sp.getPic());
			mv.addObject("pid" + (i + 1), sp.getId());
		}
	}

	@RequestMapping("/sproject_add1")
	public ModelAndView projectAdd1(HttpServletRequest request, ScatterProject project,
			@RequestParam("files") MultipartFile[] files) throws IOException {
		ModelAndView mv = new ModelAndView();
		project.setState(CONST.PROJECT_STATE_SETUP);
		mv.addObject("project", project);
		project.setCreateTime(DateTimeUtil.getDateTimeNow());
		projectService.insertSp(project);
		project.setProjectCode(UUIDUtil.genProjectCode(project.getId(), "LX"));
		projectService.updateSp(project);
		mv.setViewName("redirect:sproject_modify?id=" + project.getId());
		String[] base64str_beforePics = request.getParameterValues("base64str_beforePics");

		if (isPicNotEmpty(files, base64str_beforePics)) {
			picService.deletePic(project.getId(), CONST.PIC_TYPE_BEFORE);

		}

		if (isGaopaiyiNotEmpty(base64str_beforePics)) {

			picService.insertMultiBase64Pic(request, base64str_beforePics, project.getId(), CONST.PIC_TYPE_BEFORE);
		}

		if (isAttachNotEmpty(files)) {
			picService.insertMultiPic(files, request, project.getId(), CONST.PIC_TYPE_BEFORE);
		}

		return mv;
	}

	@RequestMapping("/update_satter")
	public ModelAndView update_satter(HttpServletRequest request, ScatterProject project, String from,
			@RequestParam("files") MultipartFile[] files) {
		ModelAndView mv = new ModelAndView("redirect:/" + from + "?id=" + project.getId());

		if ("sproject_modify".equals(from)) {
			insertTwoPics(request, project.getId(), files, "base64str_beforePics", CONST.PIC_TYPE_BEFORE);
		}
		if ("update_satter_build".equals(from)) {
			insertTwoPics(request, project.getId(), files, "base64str_buildPics", CONST.PIC_TYPE_BUILD);
		}
		if ("update_satter_process".equals(from)) {
			insertTwoPics(request, project.getId(), files, "base64str_processPics", CONST.PIC_TYPE_PROCESS);
		}
		if ("update_satter_finish".equals(from)) {
			insertTwoPics(request, project.getId(), files, "base64str_finishPics", CONST.PIC_TYPE_FINISH);
		}
		project.setUpdateTime(DateTimeUtil.getDateTimeNow());
		projectService.updateSp(project);
		return mv;
	}

	@RequestMapping("/update_satter_payinfo1")
	public ModelAndView update_satter_payinfo1(HttpServletRequest request, ScatterProject project, String from,
			@RequestParam("files") MultipartFile[] files) {
		ModelAndView mv = new ModelAndView("redirect:/" + from + "?id=" + project.getId());

		insertTwoPics(request, project.getId(), files, "base64str_scpayPics", CONST.PIC_scattarpays);

		project.setUpdateTime(DateTimeUtil.getDateTimeNow());
		projectService.updateSp(project);
		return mv;
	}

	@RequestMapping("/update_satter_build")
	public ModelAndView update_satter_build(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/scatterProject/update_build");
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			ScatterProject project = projectService.querySprojectById(Integer.parseInt(id));
			mv.addObject("project", project);
			List<Build> list = buildMapper.queryAll();
			mv.addObject("list", list);
		}

		return mv;
	}

	@RequestMapping("/update_satter_process")
	public ModelAndView update_satter_process(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/scatterProject/update_process");
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			ScatterProject project = projectService.querySprojectById(Integer.parseInt(id));
			mv.addObject("project", project);
			showSavedPic(mv, project, CONST.PIC_TYPE_PROCESS);
		}

		return mv;
	}

	@RequestMapping("/update_satter_finish")
	public ModelAndView update_satter_finish(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/scatterProject/update_finish");
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			ScatterProject project = projectService.querySprojectById(Integer.parseInt(id));
			mv.addObject("project", project);
			showSavedPic(mv, project, CONST.PIC_TYPE_FINISH);
		}

		return mv;
	}

	@RequestMapping("/update_satter_payinfo")
	public ModelAndView update_satter_payinfo(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/scatterProject/update_payinfo");
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			ScatterProject project = projectService.querySprojectById(Integer.parseInt(id));
			mv.addObject("project", project);
		}

		return mv;
	}

	@RequestMapping("/sproject_delete")
	public ModelAndView projectDelete(Integer id) {

		ModelAndView mv = new ModelAndView("redirect:/sproject_list");
		projectService.deleteSById(id);
		return mv;
	}
}
