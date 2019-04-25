package webfood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import webfood.config.CONST;
import webfood.mapper.BuildMapper;
import webfood.mapper.ProjectBuildMapper;
import webfood.model.Build;
import webfood.model.Project;
import webfood.model.ProjectBuild;
import webfood.service.ProjectService;

@Controller
public class ProjectBuildController {

	@Autowired
	ProjectBuildMapper projectBuildMapper;

	@Autowired
	ProjectService projectService;

	@Autowired
	private BuildMapper buildMapper;

	@RequestMapping("/project_build_add")
	public ModelAndView builedAdd(Integer id) {

		ModelAndView mv = new ModelAndView("/project/project_build_add");
		mv.addObject("id", id);
		Project project = projectService.queryById(id);
		mv.addObject("project", project);

		return mv;
	}

	@RequestMapping("/project_build_modify")
	public ModelAndView builedModify(Integer id, Integer bid) {

		ModelAndView mv = new ModelAndView("/project/project_build_add");
		mv.addObject("id", id);
		Project project = projectService.queryById(id);
		mv.addObject("project", project);
		ProjectBuild pb = projectBuildMapper.selectByPrimaryKey(bid);
		mv.addObject("pb", pb);
		List<Build> list = buildMapper.queryAll();
		mv.addObject("list", list);

		return mv;
	}

	@RequestMapping("/project_build_add1")
	public ModelAndView builedAdd1(ProjectBuild build) {

		ModelAndView mv = new ModelAndView("redirect:/update_build?id=" + build.getProjectId());
		Project project = projectService.queryById(build.getProjectId());
		ProjectBuild oldBuild = projectBuildMapper.selectByPrimaryKey(build.getId());
		if (project.getAgreeAmount() != null) {
			if (oldBuild.getBuildContractAmount() == null || oldBuild.getBuildContractAmount() <= 0)
				project.setAgreeAmount(project.getAgreeAmount() + build.getBuildContractAmount().longValue());
			project.setState(CONST.PROJECT_STATE_BUILD);
			projectService.update(project);
		}
		build.setState(null);// 不改变完工状态
		projectBuildMapper.updateByPrimaryKeySelective(build);

		return mv;
	}

	@RequestMapping("/audit1")
	public ModelAndView audit1(Integer id, String auditAmount, String projectId) {

		ModelAndView mv = new ModelAndView("redirect:/audit?id=" + projectId + "&bid=" + id);
		ProjectBuild oldBuild = projectBuildMapper.selectByPrimaryKey(id);

		oldBuild.setAuditAmount(Double.parseDouble(auditAmount));
		projectBuildMapper.updateByPrimaryKeySelective(oldBuild);

		return mv;
	}

}
