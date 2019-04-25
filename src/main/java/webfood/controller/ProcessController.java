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
import webfood.mapper.ProcessMapper;
import webfood.mapper.ProjectBuildMapper;
import webfood.model.Process;
import webfood.model.Project;
import webfood.model.ProjectBuild;
import webfood.service.PicService;
import webfood.service.ProjectService;

@Controller
public class ProcessController extends BaseController {

	@Autowired
	ProcessMapper processMapper;

	@Autowired
	ProjectService projectService;

	@Autowired
	ProjectBuildMapper projectBuildMapper;

	@Autowired
	PicService picService;

	@RequestMapping("/process_add")
	public ModelAndView processAdd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/project/process_add");
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			Project project = projectService.queryById(Integer.parseInt(id));
			mv.addObject("project", project);
			String bid = request.getParameter("bid");
			mv.addObject("bid", bid);
		}
		mv.addObject("id", id);

		return mv;
	}

	@RequestMapping("/process_modify")
	public ModelAndView process_modify(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/project/process_modify");
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			Project project = projectService.queryById(Integer.parseInt(id));
			mv.addObject("project", project);
			String bid = request.getParameter("bid");
			mv.addObject("bid", bid);
			String pcid = request.getParameter("pcid");
			Process process = processMapper.selectByPrimaryKey(Integer.parseInt(pcid));
			mv.addObject("process", process);
		}
		mv.addObject("id", id);

		return mv;
	}

	@RequestMapping("/process_add1")
	public ModelAndView processAdd1(HttpServletRequest request, Process process) {

		ModelAndView mv = new ModelAndView("redirect:/update_build_process?id=" + process.getProjectId());

		List<Process> list = processMapper.queryByBid(process.getProjectBuildId());
		if (list.size() > 0) {
			Process lastProcess = list.get(0);
			if (process.getPdate().compareTo(lastProcess.getPdate()) <= 0) {
				mv.addObject("msg", "日期不能早于最近录入的进度日期：" + lastProcess.getPdate());
				mv.setViewName("/project/process_add");
				Project project = projectService.queryById(process.getProjectId());
				mv.addObject("project", project);
				mv.addObject("id", process.getProjectId());
				mv.addObject("bid", process.getProjectBuildId());
				return mv;
			}
			if (process.getProcess() <= lastProcess.getProcess()) {
				mv.addObject("msg", "进度不能小于最近一次的进度：" + lastProcess.getProcess() + "%");
				mv.setViewName("/project/process_add");
				Project project = projectService.queryById(process.getProjectId());
				mv.addObject("project", project);
				mv.addObject("id", process.getProjectId());
				mv.addObject("bid", process.getProjectBuildId());
				return mv;
			}
		}
		ProjectBuild pb = projectBuildMapper.selectByPrimaryKey(process.getProjectBuildId());
		if (process.getProcess().intValue() == 100) {
			pb.setState(CONST.PROCESS_FINISH);
			projectBuildMapper.updateByPrimaryKeySelective(pb);
		}
		if (process.getId() == null) {
			processMapper.insertSelective(process);
		} else {
			processMapper.updateByPrimaryKeySelective(process);
		}
		return mv;
	}

	@RequestMapping("/process_modify1")
	public ModelAndView process_modify1(HttpServletRequest request, Process process) {

		ModelAndView mv = new ModelAndView("redirect:/update_build_process?id=" + process.getProjectId());

		String oldId = request.getParameter("oldId");
		Process oldProcess = processMapper.selectByPrimaryKey(Integer.parseInt(oldId));

		ProjectBuild pb = projectBuildMapper.selectByPrimaryKey(process.getProjectBuildId());
		if (process.getProcess().intValue() == 100) {
			pb.setState(CONST.PROCESS_FINISH);
			projectBuildMapper.updateByPrimaryKeySelective(pb);
		}
		if (oldProcess.getProcess().intValue() == 100 && process.getProcess().intValue() != 100) {
			pb.setState(CONST.PROCESS_UNFINISH);
			projectBuildMapper.updateByPrimaryKeySelective(pb);
		}

		process.setId(oldProcess.getId());
		processMapper.updateByPrimaryKeySelective(process);
		return mv;
	}

	@RequestMapping("/process_attach_add")
	public ModelAndView process_attach_add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/project/process_attach_add");
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			Project project = projectService.queryById(Integer.parseInt(id));
			mv.addObject("project", project);
			String bid = request.getParameter("bid");
			mv.addObject("bid", bid);
		}
		mv.addObject("id", id);

		return mv;
	}

	@RequestMapping("/process_attach_add1")
	public ModelAndView processAttachAdd1(HttpServletRequest request, ProjectBuild build, Integer projectId,
			Integer projectBuildId, @RequestParam("qsdPics") MultipartFile[] qsdPics,
			@RequestParam("bglxdPics") MultipartFile[] bglxdPics, @RequestParam("gcqzdPics") MultipartFile[] gcqzdPics)
			throws IOException {

		ModelAndView mv = new ModelAndView("redirect:/update_build_process?id=" + projectId);

		String[] base64str_qsdPics = request.getParameterValues("base64str_qsdPics");
		String[] base64str_bglxdPics = request.getParameterValues("base64str_bglxdPics");
		String[] base64str_gcqzdPics = request.getParameterValues("base64str_gcqzdPics");

		if (isPicNotEmpty(qsdPics, base64str_qsdPics)) {
			picService.deletePic(projectBuildId, CONST.PIC_qsdPics);
		}
		if (isPicNotEmpty(bglxdPics, base64str_bglxdPics)) {
			picService.deletePic(projectBuildId, CONST.PIC_bglxdPics);
		}
		if (isPicNotEmpty(gcqzdPics, base64str_gcqzdPics)) {
			picService.deletePic(projectBuildId, CONST.PIC_gcqzdPics);
		}

		if (base64str_qsdPics != null && base64str_qsdPics.length > 0) {

			picService.insertMultiBase64Pic(request, base64str_qsdPics, projectBuildId, CONST.PIC_qsdPics);
		}
		if (base64str_bglxdPics != null && base64str_bglxdPics.length > 0) {
			picService.insertMultiBase64Pic(request, base64str_bglxdPics, projectBuildId, CONST.PIC_bglxdPics);
		}
		if (base64str_gcqzdPics != null && base64str_gcqzdPics.length > 0) {
			picService.insertMultiBase64Pic(request, base64str_gcqzdPics, projectBuildId, CONST.PIC_gcqzdPics);
		}

		if (qsdPics != null && qsdPics.length > 0)
			picService.insertMultiPic(qsdPics, request, projectBuildId, CONST.PIC_qsdPics);
		if (bglxdPics != null && bglxdPics.length > 0)
			picService.insertMultiPic(bglxdPics, request, projectBuildId, CONST.PIC_bglxdPics);
		if (gcqzdPics != null && gcqzdPics.length > 0)
			picService.insertMultiPic(gcqzdPics, request, projectBuildId, CONST.PIC_gcqzdPics);

		return mv;
	}

}
