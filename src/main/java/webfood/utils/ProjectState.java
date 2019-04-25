package webfood.utils;

import java.util.HashMap;

/**
 * 
 * @ClassName: ProjectState
 * @Description: 项目状态常量接口
 * @author: MoGuichun
 * @date: 2015年12月16日 上午9:58:35
 * 
 */
public interface ProjectState {
	// 进行中
	public static final int CONDUCTING_STATE = 0;

	// 暂停
	public static final int SUSPEND_STATE = 1;

	// 废弃
	public static final int SCRAP_STATE = 2;
	
	// 结项
	public static final int KNOT_STATE = 3;
	
	public static final HashMap<Integer, String> PROJECT_STATE = new HashMap<Integer, String>(){{
		put(CONDUCTING_STATE, "进行中");
		put(SUSPEND_STATE, "暂停");
		put(SCRAP_STATE, "废弃");
		put(KNOT_STATE, "结项");
	}};
}
