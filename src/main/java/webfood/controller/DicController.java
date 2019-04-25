package webfood.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import webfood.mapper.DicClassMapper;
import webfood.mapper.DicItemMapper;
import webfood.model.DicClass;
import webfood.model.DicItem;
import webfood.vo.ItemVo;

@Controller
public class DicController {

	@Autowired
	private DicItemMapper dicItemMapper;

	@Autowired
	private DicClassMapper dicClassMapper;

	@RequestMapping("/dic_list")
	public ModelAndView dic_list(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("/admin/dic_list");
		List<ItemVo> list = dicItemMapper.queryItemAll();
		request.setAttribute("list", list);
		return mv;
	}

	@RequestMapping("/dic_class_add")
	public ModelAndView dic_class_add(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("/admin/dic_class_add");
		return mv;
	}

	@RequestMapping("/dic_class_add1")
	public ModelAndView dic_class_add1(HttpServletRequest request, DicClass dc) {

		ModelAndView mv = new ModelAndView("redirect:/dic_list");
		dicClassMapper.insertSelective(dc);
		return mv;
	}

	@RequestMapping("/dic_item_add")
	public ModelAndView dic_item_add(HttpServletRequest request, Integer id) {

		ModelAndView mv = new ModelAndView("/admin/dic_item_add");
		DicClass dc = dicClassMapper.selectByPrimaryKey(id);
		mv.addObject("dclass", dc);
		return mv;
	}

	@RequestMapping("/dic_item_delete")
	public ModelAndView dic_item_delete(HttpServletRequest request, Integer id) {

		ModelAndView mv = new ModelAndView("redirect:/dic_list");
		dicItemMapper.deleteByPrimaryKey(id);
		return mv;
	}

	@RequestMapping("/dic_item_add1")
	public ModelAndView dic_item_add1(HttpServletRequest request, DicItem item) {

		ModelAndView mv = new ModelAndView("redirect:/dic_list");
		dicItemMapper.insertSelective(item);
		return mv;
	}

}
