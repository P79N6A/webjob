/**
 * Project Name:farmfood
 * File Name:MenuVo.java
 * Package Name:webfood.vo
 * Date:2017年9月17日上午11:39:26
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package webfood.vo;

public class MenuVo
{
	private Integer id;
	private String menuCode;

	private String title;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	private String url;
	private String remark;

	public String getMenuCode()
	{
		return menuCode;
	}

	public void setMenuCode(String menuCode)
	{
		this.menuCode = menuCode;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

}
