package webfood.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import webfood.config.CONST;
import webfood.mapper.ProjectBuildMapper;
import webfood.model.Project;
import webfood.model.ProjectBuild;
import webfood.service.PicService;
import webfood.service.ProjectService;

@Controller
public class ProjectFinishController extends BaseController {

	@Autowired
	ProjectBuildMapper projectBuildMapper;

	@Autowired
	ProjectService projectService;

	@Autowired
	PicService picService;

	@RequestMapping("/finish_modify")
	public ModelAndView builedModify(HttpServletRequest request, Integer id, Integer bid) {

		ModelAndView mv = new ModelAndView("/project/finish_add");
		mv.addObject("id", id);
		Project project = projectService.queryById(id);
		mv.addObject("project", project);
		ProjectBuild pb = projectBuildMapper.selectByPrimaryKey(bid);
		mv.addObject("pb", pb);

		String msg = request.getParameter("msg");
		if (StringUtils.isNotBlank(msg)) {
			mv.addObject("msg", "只能上传图片格式！");
		}
		return mv;
	}

	@RequestMapping("/finish_add1")
	public ModelAndView builedAdd1(HttpServletRequest request, ProjectBuild build,
			@RequestParam("yssqPics") MultipartFile[] yssqPics, @RequestParam("ysjlPics") MultipartFile[] ysjlPics) {

		ModelAndView mv = new ModelAndView("redirect:/update_finish?id=" + build.getProjectId());

		String[] base64str_yssqPics = request.getParameterValues("base64str_yssqPics");
		String[] base64str_ysjlPics = request.getParameterValues("base64str_ysjlPics");
		if (isPicNotEmpty(yssqPics, base64str_yssqPics)) {
			picService.deletePic(build.getId(), CONST.PIC_yssqPics);

		}
		if (isPicNotEmpty(ysjlPics, base64str_ysjlPics)) {
			picService.deletePic(build.getId(), CONST.PIC_ysjlPics);
		}

		if (yssqPics != null && yssqPics.length > 0) {
			picService.insertMultiPic(yssqPics, request, build.getId(), CONST.PIC_yssqPics);
		}
		if (ysjlPics != null && ysjlPics.length > 0) {
			picService.insertMultiPic(ysjlPics, request, build.getId(), CONST.PIC_ysjlPics);
		}
		if (base64str_yssqPics != null && base64str_yssqPics.length > 0) {
			picService.insertMultiBase64Pic(request, base64str_yssqPics, build.getId(), CONST.PIC_yssqPics);
		}
		if (base64str_ysjlPics != null && base64str_ysjlPics.length > 0) {
			picService.insertMultiBase64Pic(request, base64str_ysjlPics, build.getId(), CONST.PIC_ysjlPics);
		}

		projectBuildMapper.updateByPrimaryKeySelective(build);
		return mv;

	}

}
