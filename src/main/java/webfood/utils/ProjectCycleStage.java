package webfood.utils;

import java.util.HashMap;

/**
 * 
 * @ClassName: ProjectCycleStage
 * @Description: 项目周期阶段常量接口
 * @author: MoGuichun
 * @date: 2015年12月16日 上午10:03:19
 * 
 */
public interface ProjectCycleStage {

	// 立项阶段
	public static final int FOUND_STAGE = 0;

	// 需求分析阶段
	public static final int REQUIRE_STAGE = 10;

	// 概要设计阶段
	public static final int DESIGN_STAGE = 20;

	// 编码阶段
	public static final int CODING_STAGE = 30;

	// 测试阶段
	public static final int TEST_STAGE = 40;

	// 结项阶段
	public static final int KNOT_STAGE = 50;

	public static final HashMap<Integer, String> PROJECT_STAGE = new HashMap<Integer, String>() {
		{
			put(FOUND_STAGE, "立项阶段");
			put(REQUIRE_STAGE, "需求分析阶段");
			put(DESIGN_STAGE, "概要设计阶段");
			put(CODING_STAGE, "编码阶段");
			put(TEST_STAGE, "测试阶段");
			put(KNOT_STAGE, "结项阶段");
		}
	};
}
