package webfood.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import webfood.commonDbUtils.Dictionary;
import webfood.commonDbUtils.DictionaryFactory;
import webfood.model.Parameter;
import webfood.model.User;
import webfood.service.ParameterService;
import webfood.service.UserService;
import webfood.utils.MD5Utils;

@Controller
public class LoginController
{

	private final static Logger log = Logger.getLogger(LoginController.class);

	@Autowired
	UserService userService;

	@Autowired
	ParameterService parameterService;

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, String from, HttpSession session,
			HttpServletResponse response)
	{
		log.info("登录....");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		ModelAndView mv = new ModelAndView();

		User user = userService.findUserByUsername(login);
		if (user == null || password == null)
		{
			// 用户不存在,跳转登录
			log.info("用户不存在或者密码为空");
			mv.addObject("message", "name or password blank!");
			mv.setViewName("/admin/index");
			return mv;
		}
		else
		{
			if (MD5Utils.checkPassword(password, user.getPassword()))
			{

				// 密码正确.存入session,跳转首页
				session.setAttribute("loger", user);
				session.setAttribute("username", login);
				Cookie cookieId = new Cookie("id", user.getId().toString());
				cookieId.setPath("/");
				cookieId.setMaxAge(60 * 60 * 24 * 10); // cookie有效期10天
				Cookie cookieUsername = new Cookie("username", user.getUserName());
				cookieUsername.setPath("/");
				cookieUsername.setMaxAge(60 * 60 * 24 * 10);
				response.addCookie(cookieId);
				response.addCookie(cookieUsername);

				List<String> menus = userService.getMenuListByUserId(user.getId());
				session.setAttribute("menu", menus);
				mv.setViewName("/hello");
				if (StringUtils.isNotEmpty(from))
				{
					mv.setViewName("redirect:" + from);
				}

			}
			else
			{

				log.info("密码错误");
				mv.addObject("message", "name or password error!");
				mv.setViewName("/admin/index");
				return mv;

			}
		}

		Dictionary dic = DictionaryFactory.getDictionary(false);
		request.getSession().setAttribute("dic", dic);
		return mv;
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session)
	{
		// 清除session
		session.invalidate();
		log.info("用户退出");
		// 跳转登录
		return "redirect:/index";
	}

	@RequestMapping("/index")
	public ModelAndView index(String from)
	{

		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("from", from);

		return mv;
	}

	@RequestMapping("/hello_user")
	public ModelAndView hello_user()
	{

		ModelAndView mv = new ModelAndView("/hello_user");
		Parameter pat = parameterService.queryBug();
		mv.addObject("content", pat.getRemark());
		return mv;
	}

}
