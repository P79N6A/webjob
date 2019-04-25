package webfood.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import webfood.mapper.BuildMapper;
import webfood.model.Build;
import webfood.page.PageContext;
import webfood.service.CookieService;
import webfood.utils.DateTimeUtil;

@Controller
public class BuildController {

	@Autowired
	private BuildMapper buildMapper;

	@RequestMapping("/build_add")
	public ModelAndView buildAdd() {

		ModelAndView mv = new ModelAndView("/baseinfo/build_add");
		return mv;
	}

	@RequestMapping("/build_view")
	public ModelAndView build_view(Integer id) {

		ModelAndView mv = new ModelAndView("/baseinfo/build_view");
		Build build = buildMapper.selectByPrimaryKey(id);
		mv.addObject("build", build);
		return mv;
	}

	@RequestMapping("/build_modify")
	public ModelAndView buildModify(Integer id) {

		ModelAndView mv = new ModelAndView("/baseinfo/build_modify");
		Build build = buildMapper.selectByPrimaryKey(id);
		mv.addObject("build", build);
		return mv;
	}

	@RequestMapping("/build_delete")
	public ModelAndView build_delete(Integer id) {

		ModelAndView mv = new ModelAndView("redirect:/build_list");
		buildMapper.deleteByPrimaryKey(id);
		return mv;
	}

	@RequestMapping("/build_add1")
	public ModelAndView builedAdd1(HttpServletRequest request, Build build, @RequestParam("bpic") MultipartFile file)
			throws IOException {

		ModelAndView mv = new ModelAndView("redirect:/build_list");
		build.setPic(CookieService.upload(request, file));
		build.setCreateTime(DateTimeUtil.getDateTimeNow());
		buildMapper.insertSelective(build);
		return mv;
	}

	@RequestMapping("/build_modify1")
	public ModelAndView buildModify1(HttpServletRequest request, Build build, @RequestParam("bpic") MultipartFile file)
			throws IOException {

		ModelAndView mv = new ModelAndView("redirect:/build_list");
		if (file != null)
			build.setPic(CookieService.upload(request, file));
		buildMapper.updateByPrimaryKeySelective(build);
		return mv;
	}

	@RequestMapping("/build_list")
	public ModelAndView builedList(HttpServletRequest request, String pageSize, String totalPages, String totalRows) {

		String ft_page = request.getParameter("ft_page_size");

		ModelAndView mv = new ModelAndView("/baseinfo/build_list");
		if (!StringUtils.isEmpty(ft_page)) {
			pageSize = ft_page;
			mv.addObject("ft_page", ft_page);
		}
		List<Build> list = null;
		String pagec = request.getParameter("page");
		PageContext page = PageContext.getContext();
		// 请自行验证
		if (null == pagec) {
			page.setCurrentPage(1);
			page.setPageSize(10);
		} else {
			page.setCurrentPage(Integer.parseInt(pagec));
			page.setPageSize(Integer.parseInt(pageSize));
			page.setTotalPages(Integer.parseInt(totalPages));
			page.setTotalRows(Integer.parseInt(totalRows));
		}
		page.setPagination(true);
		String buildUnit = request.getParameter("buildUnit");
		if (StringUtils.isEmpty(buildUnit)) {
			list = buildMapper.queryAll();
		} else {
			list = buildMapper.queryAllByUnit(buildUnit);
			mv.addObject("buildUnit", buildUnit);
		}
		PageContext.removeContext();
		mv.addObject("list", list);
		mv.addObject("page", page);
		return mv;
	}

}
