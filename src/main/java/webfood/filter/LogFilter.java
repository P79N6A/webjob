package webfood.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import webfood.model.User;

public class LogFilter implements Filter
{

	private final static Logger log = Logger.getLogger(LogFilter.class);

	//找回密码，重置密码，注册用户，登录放行
	public final static String[] GreenUrls =
	{ "UserController/resetPassword.do", "UserController/forgetPassword.do", "LoginController/login.do",
			"UserController/register.do" };

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String contextPath = httpServletRequest.getContextPath(); //取得项目当前根目录   例如：/pms
		String currentPath = httpServletRequest.getRequestURI(); //取得当前要访问的页面目录   /pms/pages/login.jsp
		//检查session是否登录过
		if (checkUser(httpServletRequest, httpServletResponse))
		{
			//用户已经登录，放行
			log.info("用户已经登录");
			chain.doFilter(request, response);
		}
		else
		{
			//用户未曾登录，检查访问的Url
			log.info("session为空或者session中用户不存在");
			if (checkUrl(httpServletRequest, httpServletResponse, contextPath, currentPath))
			{
				//是GreenUrls,放行
				log.info("访问路径是GreenUrl");
				chain.doFilter(request, response);
			}
			else
			{
				//不是,跳转login.jsp
				log.info("访问路径不是GreenUrl,跳转登录");
				//        		PrintWriter out = response.getWriter();
				//        		out.print("<script>");
				//        		out.print("window.top.location.href='" + contextPath + "/pages/login.html" + "';");
				//        		out.print("</script>");

				httpServletResponse.sendRedirect(contextPath + "/pages/login.html");
				//request.getRequestDispatcher("/pages/login.html").forward(request, response);
				return;
			}
		}
	}

	@Override
	public void destroy()
	{
	}

	//检查session用户是否存在,如果存在返回true,否则返回false
	public Boolean checkUser(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession(false);
		if (session == null)
		{
			log.info("session为空");
			return false;
		}
		else
		{
			User loger = (User) session.getAttribute("loger");

			if (loger == null || loger.getId() == null)
			{
				log.info("session中用户不存在");
				return false;
			}
		}
		return true;
	}

	//检查Url,如果是GreenUrl 返回true 否则返回false
	public Boolean checkUrl(HttpServletRequest request, HttpServletResponse response, String contextPath,
			String currentPath)
	{

		if (currentPath == null)
		{
			return false;
		}

		for (String green : GreenUrls)
		{
			String url = contextPath + "/" + green;
			log.info("访问路径:" + currentPath);

			if (url.equals(currentPath))
			{
				return true;
			}

		}

		return false;
	}

}
