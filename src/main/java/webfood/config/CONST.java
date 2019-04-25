package webfood.config;

/* *
 *
 */

public class CONST
{

	public static String remark = "";

	public static final int SUCCESS = 0;

	public static final int PARA_ERROR = -1000;

	/** 未登录 **/
	public static final int RESULT_UNLOGIN = -1002;

	/** 操作成功 **/
	public static final int RESULT_SUCCESS = 0;

	/** 操作数据库出错 **/
	public static final int RESULT_SQL_ERROR = -1003;

	public static final int RESULT_STATE_ERROR = -1004;

	public static final String FILE_PRE = "/upload/";

	/** 项目立项 **/
	public static final Integer PROJECT_STATE_SETUP = 1;
	/** 项目测绘 **/
	public static final Integer PROJECT_STATE_PROSPECT = 2;
	/** 项目预算 **/
	public static final Integer PROJECT_STATE_BUDGET = 3;
	/** 施工单位 **/
	public static final Integer PROJECT_STATE_BUILD = 4;
	/** 监理单位 **/
	public static final Integer PROJECT_STATE_CHECK = 5;
	/** 施工过程信息 **/
	public static final Integer PROJECT_STATE_PROCESS = 6;

	public static final Integer PROJECT_STATE_pay = 7;

	/**
	 * 工程前现场照片
	 */
	public static final Integer PIC_TYPE_BEFORE = 1;
	/**
	 * 施工单位合同
	 */
	public static final Integer PIC_TYPE_BUILD = 21;
	/**
	 * 工程施工过程照片
	 */
	public static final Integer PIC_TYPE_PROCESS = 2;

	/**
	 * 验收相关照片
	 */
	public static final Integer PIC_TYPE_FINISH = 3;

	/**
	 * 合同管理附件
	 */
	public static final Integer PIC_COMPACT = 4;

	/**
	 * 立项工程附件
	 */
	public static final Integer PIC_PROJECT = 5;

	/**
	 * 签收单附件
	 */
	public static final Integer PIC_qsdPics = 6;

	/**
	 * 变更联系单附件
	 */
	public static final Integer PIC_bglxdPics = 7;

	/**
	 * 工程签证单附件
	 */
	public static final Integer PIC_gcqzdPics = 8;

	/**
	 * 验收申请附件
	 */
	public static final Integer PIC_yssqPics = 9;

	/**
	 * 验收记录附件
	 */
	public static final Integer PIC_ysjlPics = 10;

	/**
	 * 支付附件
	 */
	public static final Integer PIC_pays = 11;

	/**
	 * 零星支付附件
	 */
	public static final Integer PIC_scattarpays = 12;
	/**
	 * 合同支付附件
	 */
	public static final Integer PIC_compactpays = 13;

	/**
	 * 未完工
	 */
	public static final Integer PROCESS_UNFINISH = 0;

	/**
	 * 已完工
	 */
	public static final Integer PROCESS_FINISH = 1;

}
