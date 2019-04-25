package webfood.utils;

/**
 * 
 * @ClassName: ProjectUserState
 * @Description: 用户是否还在该项目中
 * @author: MoGuichun
 * @date: 2015年12月16日 上午10:21:52
 *
 */
public interface ProjectUserState {
	
	// 用户还在本项目中
	public static final int PARTICIPATING = 0;
	
	// 用户已退出本项目中
	public static final int SIGN_OUT = 1; 
}
