package webfood.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import webfood.service.LogService;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: GameController
 * @Description: 优惠策略
 * @author wangxb
 * @date 2016年10月4日 上午10:49:00
 *
 */
@Controller
public class LogController {

	private final static Logger log = Logger.getLogger(LogController.class);

	@Autowired
	LogService logService;

	@RequestMapping("/log_list")
	public ModelAndView logList(HttpServletRequest request, String pageSize, String totalPages, String totalRows) {
		ModelAndView mv = new ModelAndView("/admin/log_list");

		// List<OpLog> list = logService.queryAll();

		mv.addObject("list", null);
		return mv;
	}

}
