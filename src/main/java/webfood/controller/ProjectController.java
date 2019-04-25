package webfood.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import webfood.config.CONST;
import webfood.mapper.BdstMapper;
import webfood.mapper.PayInfoMapper;
import webfood.mapper.ProcessMapper;
import webfood.mapper.ProjectBuildMapper;
import webfood.mapper.ScatterPicMapper;
import webfood.model.PayInfo;
import webfood.model.Project;
import webfood.model.ProjectBuild;
import webfood.service.BuildService;
import webfood.service.CookieService;
import webfood.service.PicService;
import webfood.service.ProjectService;
import webfood.utils.DateTimeUtil;
import webfood.utils.StringUtil;
import webfood.utils.UUIDUtil;

@Controller
public class ProjectController extends BaseController {

	private final static Logger log = Logger.getLogger(ProjectController.class);

	@Autowired
	ProjectService projectService;

	@Autowired
	BuildService buildService;

	@Autowired
	BdstMapper bdstMapper;

	@Autowired
	ProjectBuildMapper projectBuildMapper;

	@Autowired
	ProcessMapper processMapper;

	@Autowired
	ScatterPicMapper scatterPicMapper;

	@Autowired
	PicService picService;

	@Autowired
	PayInfoMapper payInfoMapper;

	@RequestMapping("/project_add")
	public ModelAndView projectAdd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/project/project_add");
		return mv;
	}

	@RequestMapping("/update_finish")
	public ModelAndView updateFinish(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/project/update_finish");

		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			Project project = projectService.queryById(Integer.parseInt(id));
			mv.addObject("project", project);
			if (project.getState() < 3) {
				mv.addObject("msg", "请先填写预算！");
			}
			List<ProjectBuild> list = buildService.queryByPid(Integer.parseInt(id));
			mv.addObject("list", list);
		}

		return mv;
	}

	@RequestMapping("/project_modify")
	public ModelAndView projectModify(Integer id) {
		ModelAndView mv = new ModelAndView("/project/project_add");
		Project project = projectService.queryById(id);
		mv.addObject("project", project);
		return mv;
	}

	@RequestMapping("/update_project")
	public ModelAndView updateProject(HttpServletRequest request, Project project, String from, Integer state)
			throws IOException {
		ModelAndView mv = new ModelAndView("redirect:/" + from + "?id=" + project.getId());

		project.setUpdateTime(DateTimeUtil.getDateTimeNow());
		// 如果说 竣工按钮，更新竣工时间
		if (state != null && state == 6) {
			project.setCompleteCheckTime(DateTimeUtil.getDateTimeNow());
		}
		Project oldproject = projectService.queryById(project.getId());
		if ("update_budget".equals(from)) {

			if (oldproject.getBdstNum() != null && oldproject.getBdstNum() > 0) {
				for (int i = 0; i < oldproject.getBdstNum(); i++) {
					String id = request.getParameter("id_" + i);
					if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
						ProjectBuild pb = projectBuildMapper.selectByPrimaryKey(Integer.parseInt(id));
						String budgetAmount = request.getParameter("budgetAmount" + i);
						pb.setBudgetAmount(Double.parseDouble(budgetAmount));
						projectBuildMapper.updateByPrimaryKey(pb);
					}
				}
			}
		}

		if (oldproject.getState() < state) {
			project.setState(state);
		}

		projectService.update(project);
		return mv;
	}

	@RequestMapping("/update_project_finish")
	public ModelAndView update_project_finish(HttpServletRequest request, Project project, String from,
			@RequestParam("doc1") MultipartFile doc1, @RequestParam("doc2") MultipartFile doc2) {
		ModelAndView mv = new ModelAndView("redirect:/" + from + "?id=" + project.getId());
		try {
			String sdoc1 = CookieService.upload(request, doc1);
			String sdoc2 = CookieService.upload(request, doc2);
			project.setFinishApplyDoc(sdoc1);
			project.setFinishRecordDoc(sdoc2);
		} catch (IOException e) {
			e.printStackTrace();
		}

		projectService.update(project);

		return mv;
	}

	@RequestMapping("/project_view")
	public ModelAndView projectView(Integer id) {
		Project project = projectService.queryById(id);
		ModelAndView mv = new ModelAndView("/project/project_view");
		mv.addObject("project", project);

		picService.showSavedPic("picList", mv, project.getId(), CONST.PIC_PROJECT);
		List<ProjectBuild> list = buildService.queryByPid(id);

		for (ProjectBuild pb : list) {
			pb.setQsdList(picService.getSavedPic(pb.getId(), CONST.PIC_qsdPics));
			pb.setBgdList(picService.getSavedPic(pb.getId(), CONST.PIC_bglxdPics));
			pb.setQzdList(picService.getSavedPic(pb.getId(), CONST.PIC_gcqzdPics));
			pb.setYsjlList(picService.getSavedPic(pb.getId(), CONST.PIC_yssqPics));
			pb.setJgysList(picService.getSavedPic(pb.getId(), CONST.PIC_ysjlPics));
			List<PayInfo> plist = payInfoMapper.queryByBid(pb.getId());
			for (PayInfo pf : plist) {
				pf.setPstrlist(picService.getSavedPic(pf.getId(), CONST.PIC_pays));

			}
			pb.setPlist(plist);
		}
		mv.addObject("list", list);
		System.out.println("list" + list);
		return mv;
	}

	@ResponseBody
	@RequestMapping("/project_view1")
	public Map<String, Object> projectView1(Integer id) {
		Project project = projectService.queryById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("project", project);

		List<ProjectBuild> list = buildService.queryByPid(id);

		for (ProjectBuild pb : list) {
			pb.setQsdList(picService.getSavedPic(pb.getId(), CONST.PIC_qsdPics));
			pb.setBgdList(picService.getSavedPic(pb.getId(), CONST.PIC_bglxdPics));
			pb.setQzdList(picService.getSavedPic(pb.getId(), CONST.PIC_gcqzdPics));
			pb.setYsjlList(picService.getSavedPic(pb.getId(), CONST.PIC_yssqPics));
			pb.setJgysList(picService.getSavedPic(pb.getId(), CONST.PIC_ysjlPics));
			List<PayInfo> plist = payInfoMapper.queryByBid(pb.getId());
			for (PayInfo pf : plist) {
				pf.setPstrlist(picService.getSavedPic(pf.getId(), CONST.PIC_pays));

			}
			pb.setPlist(plist);
		}

		map.put("list", list);
		System.out.println("list" + list);
		return map;
	}

	@RequestMapping("/update_prospect")
	public ModelAndView updateProspect(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/project/update_prospect");
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			Project project = projectService.queryById(Integer.parseInt(id));
			mv.addObject("project", project);
		}

		return mv;
	}

	@RequestMapping("/update_budget")
	public ModelAndView updateBudget(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/project/update_budget");
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			Project project = projectService.queryById(Integer.parseInt(id));
			mv.addObject("project", project);
			if (project.getBdstNum() != null) {
				List<ProjectBuild> list = projectBuildMapper.queryByPid(project.getId());
				mv.addObject("list", list);
			}

		}
		return mv;
	}

	@RequestMapping("/update_design")
	public ModelAndView updateDesign(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/project/update_design");
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			Project project = projectService.queryById(Integer.parseInt(id));
			mv.addObject("project", project);
		}
		return mv;
	}

	@RequestMapping("/update_build")
	public ModelAndView updateBuild(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/project/update_build");
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			Project project = projectService.queryById(Integer.parseInt(id));
			mv.addObject("project", project);
			if (project.getState() < 3) {
				mv.addObject("msg", "请先填写预算！");
			}
			List<ProjectBuild> list = buildService.queryByPid(Integer.parseInt(id));
			mv.addObject("list", list);
		}

		return mv;
	}

	@RequestMapping("/update_build_process")
	public ModelAndView updateBuildProcess(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/project/update_build_process");
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			Project project = projectService.queryById(Integer.parseInt(id));
			mv.addObject("project", project);
			List<ProjectBuild> list = buildService.queryByPid(Integer.parseInt(id));
			mv.addObject("list", list);
			if (project.getState() < 4) {
				mv.addObject("msg", "请先填写施工单位信息！");
			}
			if (project.getState() < 3) {
				mv.addObject("msg", "请先填写预算信息！");
			}
			String msg = request.getParameter("msg");
			if (StringUtils.isNotBlank(msg)) {
				mv.addObject("msg", "至少上传一种附件！");
			}
			mv.addObject("state", project.getState());
		}

		return mv;
	}

	@RequestMapping("/update_payinfo")
	public ModelAndView updatePayinfo(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/project/update_payinfo");

		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			Project project = projectService.queryById(Integer.parseInt(id));
			mv.addObject("project", project);
			List<ProjectBuild> list = buildService.queryByPid(Integer.parseInt(id));
			for (ProjectBuild pb : list) {
				List<PayInfo> plist = pb.getPlist();
				if (plist != null && plist.size() > 0) {
					double tmp = 0.0;
					for (PayInfo pf : plist) {
						tmp += pf.getPayedAmount();
					}
					pb.setTotalPay(tmp);
				}
			}

			mv.addObject("list", list);
			if ("2".equals(project.getProjectBody())) {
				mv.setViewName("/project/update_county_payinfo");
			}
			if (project.getState() < 4) {
				mv.addObject("msg", "请先填写施工单位信息！");
			}
			if (project.getState() < 3) {
				mv.addObject("msg", "请先填写预算信息！");
			}
			String msg = request.getParameter("msg");
			if ("1".equals(msg)) {
				mv.addObject("msg", "未选择支付方式，不能录入支付信息！");
			}
			if ("2".equals(msg)) {
				mv.addObject("msg", "未填写进度或者所有进度已支付完成，请直接修改");
			}
		}

		return mv;
	}

	@RequestMapping("/project_delete")
	public ModelAndView projectDelete(Integer id) {

		ModelAndView mv = new ModelAndView("redirect:/project_list");
		projectService.deleteById(id);
		return mv;
	}

	@RequestMapping("/gaopaiyi")
	public ModelAndView gaopaiyi() {

		ModelAndView mv = new ModelAndView("/project/gaopaiyi");
		return mv;
	}

	@RequestMapping("/project_add1")
	public ModelAndView projectAdd1(HttpServletRequest request, Project project,
			@RequestParam("files") MultipartFile[] files) throws IOException {
		ModelAndView mv = new ModelAndView();

		String id = request.getParameter("id");
		project.setProjectPic("");
		project.setState(CONST.PROJECT_STATE_SETUP);
		project.setProspectUnit("义乌市勘测设计研究院");
		project.setDesiginUnit("义乌市城市规划设计研究院");
		project.setCreateTime(DateTimeUtil.getDateTimeNow());
		project.setAgreeAmount(0L);
		project.setAuditAmount(0.0);

		mv.addObject("project", project);

		// form里图片的name
		String[] strs = request.getParameterValues("projectPic");

		// 如果是列表页面，通过修改进入编辑页面，则修改信息
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			Project oldProject = projectService.queryById(project.getId());
			if (oldProject.getBdstNum() != project.getBdstNum()) {
				// 标段数修改，清空原标段信息，重新生成
				projectBuildMapper.deleteByPid(project.getId());
				int bdst_num = project.getBdstNum();
				for (int i = 0; i < bdst_num; i++) {
					ProjectBuild pb = new ProjectBuild();
					pb.setProjectId(project.getId());
					pb.setRemark("标段" + StringUtil.NumberToString(i + 1));
					projectBuildMapper.insertSelective(pb);
				}
			}
			projectService.update(project);
			if (isPicNotEmpty(files, strs)) {
				picService.deletePic(project.getId(), CONST.PIC_PROJECT);

			}
			if (strs != null && strs.length > 0) {

				picService.insertMultiBase64Pic(request, strs, project.getId(), CONST.PIC_PROJECT);
			}
			if (files != null && files.length > 0) {
				picService.insertMultiPic(files, request, project.getId(), CONST.PIC_PROJECT);
			}
			mv.setViewName("redirect:/project_modify?id=" + project.getId());
			return mv;
		}
		projectService.insert(project);

		picService.insertMultiBase64Pic(request, strs, project.getId(), CONST.PIC_PROJECT);
		project.setProjectCode(UUIDUtil.genProjectCode(project.getId(), "GHJS"));
		int bdst_num = project.getBdstNum();
		for (int i = 0; i < bdst_num; i++) {
			ProjectBuild pb = new ProjectBuild();
			pb.setProjectId(project.getId());
			pb.setRemark("标段" + StringUtil.NumberToString(i + 1));
			projectBuildMapper.insertSelective(pb);
		}
		projectService.update(project);
		mv.setViewName("redirect:/project_modify?id=" + project.getId());
		return mv;
	}

	@RequestMapping("/project_list")
	public ModelAndView projectList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/project/project_list");

		List<Project> list = projectService.queryAll();
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping("/project_query")
	public ModelAndView project_query(HttpServletRequest request, String ft_begin_date, String ft_end_date,
			String project) {
		ModelAndView mv = new ModelAndView("/project/project_list");

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
		List<Project> list = projectService.query(sqlWhere);
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping("/finishPrint")
	public ModelAndView finishPrint(HttpServletRequest request) {
		String id = request.getParameter("id");
		ModelAndView mv = new ModelAndView("/print/finish_print");
		mv.addObject("id", id);
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			Project project = projectService.queryById(Integer.parseInt(id));
			mv.addObject("project", project);
		}
		return mv;
	}

}
