package webfood.utils;

import java.util.HashMap;

/**
 * 
 * @ClassName: ProjectUserRole
 * @Description: 用户在项目中的角色
 * @author: MoGuichun
 * @date: 2015年12月16日 上午10:13:17
 * 
 */
public interface ProjectUserRole {

	// 项目经理
	public static final int MANAGER = 0;

	// 程序员
	public static final int PROGRAMMER = 1;
	
	// ued设计师
	public static final int DESIGNER = 2;
	
	// 测试工程师
	public static final int TESTER = 3;
	
	// 项目监管者
	public static final int REGULATOR = 4;
	
	// 项目管理员
	public static final int PROJECT_MANAGER = 5;
	
	public static final HashMap<Integer, String> ROLE_MAP = new HashMap<Integer, String>(){{
		put(PROJECT_MANAGER, "项目经理");
		put(PROGRAMMER, "程序员");
		put(DESIGNER, "UED设计师");
		put(TESTER, "测试工程师");
		put(REGULATOR, "项目监管者");
		put(PROJECT_MANAGER, "项目管理员");
	}};
}
