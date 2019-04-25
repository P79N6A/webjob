package webfood.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import webfood.model.Parameter;
import webfood.service.ParameterService;

@Controller
public class ParameterController
{

	private final static Logger log = Logger.getLogger(ParameterController.class);

	@Autowired
	ParameterService parameterService;

	@RequestMapping("/bug")
	public ModelAndView bug()
	{

		ModelAndView mv = new ModelAndView("/admin/bug");
		Parameter pat = parameterService.queryBug();
		mv.addObject("content", pat.getRemark());
		return mv;
	}

	@RequestMapping("/bug1")
	public ModelAndView bug1(String content)
	{

		ModelAndView mv = new ModelAndView("redirect:/bug");
		parameterService.updateBug(content);
		return mv;
	}

	@RequestMapping("/parameter_add")
	public ModelAndView paraAdd()
	{

		ModelAndView mv = new ModelAndView("/admin/parameter_add");
		return mv;
	}

	@RequestMapping("/parameter_delete")
	public ModelAndView paraDelete(Integer id)
	{

		ModelAndView mv = new ModelAndView("redirect:/parameter_list");
		int result = parameterService.deleteById(id);
		if (result != 1)
		{
			return null;
		}
		return mv;
	}

	@RequestMapping("/parameter_add1")
	public ModelAndView parameterAdd(HttpServletRequest request, @ModelAttribute Parameter para)
	{

		ServletContext sce = request.getSession().getServletContext();
		int result = parameterService.insert(para);
		ModelAndView mv = new ModelAndView("redirect:/parameter_list");

		if (result == 1)
		{
			log.info(String.format("插入参数成功，参数名:%s,参数值%s", para.getParaName(), para.getParaValue()));
			sce.setAttribute(para.getParaName(), para.getParaValue());

		}
		else
		{
			log.info(String.format("插入参数失败，result:%d", result));
			return null;
		}
		return mv;
	}

	@ResponseBody
	@RequestMapping("/parameter_list")
	public ModelAndView parameterList(HttpServletRequest request, String pageSize, String totalPages, String totalRows)
	{
		ModelAndView mv = new ModelAndView("/admin/parameter_list");

		String pagec = request.getParameter("page");

		List<Parameter> list = parameterService.queryAllParameter();
		log.info("list长度：" + list.size());
		mv.addObject("list", list);
		return mv;
	}

}
