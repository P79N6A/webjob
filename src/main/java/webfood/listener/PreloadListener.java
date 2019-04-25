package webfood.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import webfood.service.ParameterService;

public class PreloadListener implements ServletContextListener
{

	private ApplicationContext app;

	@Override
	public void contextInitialized(ServletContextEvent sce)
	{

		app = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		ParameterService parameterService = (ParameterService) app.getBean("parameterService");

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
		// TODO Auto-generated method stub

	}
}
