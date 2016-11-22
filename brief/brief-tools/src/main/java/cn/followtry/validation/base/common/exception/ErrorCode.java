package cn.followtry.validation.base.common.exception;

/**
 * 错误代码； 
 * @author liuxrb
 */
public enum ErrorCode {
	
	/**参数格式不正确*/
	ILLEGAL_PARAMETE(1001, "参数格式不正确！[parma=%s]"),
	
	/**缺少必要参数*/
	MISSING_REQUIRED_PARAMETE(1002, "缺少必要参数！[param=%s]"),
	
	/**没有操作权限*/
	PERFORM_FORBIDDEN(1003, "没有该操作权限！"),
	/**项目不可编辑*/
	PROJECT_NOT_EDITABLE(1004, "项目不可编辑"),
	
	/**截止日期早于开始日期*/
	ENDDATE_BEFORE_BEGINDATE(1005, "截止日期不能早于开始日期"),
	
	TASK_NOTSTARTED(1402, "任务尚未启动，无法完成！"),

	/** 任务已经完成 */
	TASK_ALREADY_COMPLETED(1403, "任务已经完成！"),

	/** 任务完成时间不能早于开始时间 */
	TASK_COMPLETEDATE_ERROR(1404, "任务完成时间不能早于开始时间！"),

	/** 任务计划完成时间不能早于开始时间 */
	TASK_PLANCOMPLETEDATE_ERROR(1405, "任务计划完成时间不能早于开始时间！"),

	/** 未找到任务 */
	TASK_NOT_FOUND(1401, "未找到任务！"),
	
	PROJECT_NOT_EXISTS(10000, "项目不存在"),
	
	PROJECT_BROWSER_NOT_ALLOWED(10001, "不允许浏览项目"),
	
	PROJECT_TASK_NOT＿EXISTS(10002, "任务不存在"),
	
	PROJECT_TASK_ALREADY_STARTED(10003, "任务已经被其他人启动"),
	
	PROJECT_TASK_ALREADY_FINISHED(10004, "任务已经结束了"),
	
	PROJECT_TASK_FORBIDDEN(10005, "无权限操作任务"),
	
	TASK_EXECUTOR_NOT_EXIST(10006, "任务执行人不存在,【value=%s】"),
	
	NULL_VALUE(4001, "空值！"),
	
	NULL_VALUE_PARAM(4001,"参数%s不能为空值！"),
	
	/**项目负责人不存在*/
	PROJECT_MASTER_NOT_EXIST(1006, "项目负责人不存在"),
	
	PROJECT_PARTNER_NOT_EXIST(1007, "项目参与人不存在【value=%s】"),
	
	PROJECT_SHARNER_NOT_EXIST(1008, "项目分享者不存在【value=%s】"),
	
	TASK_CREATION_FAILED(1009, "任务创建失败"),
	
	// organization & member 占用 11000 ~ 11299  号段
	/** 公司不存在 */
	CORPORATION_NOT_EXIST(11000,"公司不存在"),
	/** 组织部门不存在 */
	ORGANIZATION_NOT_EXIST(11001, "部门不存在"),
	/** 组织部门已存在 */
	ORGANIZATION_EXIST(11002, "部门已存在"),
	/** 编号已存在 */
	ORGANIZATION_CODE_EXIST(11003, "部门编号已存在"),
	/** 同级存在同名部门 */
	ORGANIZATION_SUB_NAME_EXIST(11004, "存在同名部门"),
	/** 缺少管理员权限 */
	AUTH_OF＿ADMIN(11005, "缺少管理员权限"),
	/** 需其他管理员设置 */
	AUTH_OF＿OTHER_ADMIN(11006, "需其他管理员设置"),
	/** 组织创建失败 */
	ORGA_CREATE_FAILED(11007,"组织创建失败"),
	/** 组织编辑失败 */
	ORGA_EDITOR_FAILED(11008,"组织编辑失败"),
	/** 组织删除失败 */
	ORGA_DELETE_FAILED(11009,"组织删除失败"),
	/** 组织名称已存在 */
	ORGA_NAME_EXIST(11010,"组织名称已存在"),
	/** 上级组织不存在 */
	ORGA_PID_NOT_EXIST(11011,"上级组织不存在"),
	/** 缺少上级组织参数 */
	ORGA_PID_ADSENCE(11012,"缺少上级组织参数"),
	/** 组织负责人不存在 */
	ORGA_PRINCIPAL_NOT_EXIST(11013,"组织负责人不存在"),
	/** 公司不能存在上级组织 */
	ORGA_CORP_NOT_PID(11014,"公司不能存在上级组织"),
	/** 存在下级组织不可删除 */
	ORGA_DELETE_EXIST_SUB(11015,"存在下级组织不可删除"),
	/** 存在成员不可删除 */
	ORGA_DELETE_EXIST_STAFF(11016,"存在成员不可删除 "),
	/** 组织编号不能为空 */
	ORGA_CODE_EMPTY(11017,"组织编号不能为空"),
	/** 组织名称不能为空 */
	ORGA_NAME_EMPTY(11018,"组织名称不能为空"),
	/** 组织不能作为自身的上级组织 */
	ORGA_PID_SELF(11019,"组织不能作为自身的上级组织"),
	/** 上级部门非法 */
	ORGA_PID_ERROR(11020,"上级部门非法"),
	
	/** 成员编号已存在 */
	MEMBER_CODE_EXIST(11101, "成员编号已存在"),
	/** 成员手机号已存在 */
	MEMBER_TELEPHONE_EXIST(11102, "成员手机号已存在"),
	/** 成员邮箱已存在 */
	MEMBER_EMAIL_EXIST(11103,"成员邮箱已存在"),
	/** 成员没有操作权限 */
	AUTH_OF_MEMBER(11104, "没有操作权限"),
	
	/** 成员不存在 */
	MEMBER_NOT_EXIST(1010, "成员不存在"),
	/** 成员添加失败 */
	MEMBER_ADDED_FAILED(1011, "成员添加失败"),
	/** 成员编辑操作失败 */
	STAFF_EDIT_FAILED(11105,"成员编辑操作失败"),
	/** 缺少编号 */
	VALID_CODE_NOT_EXIST(11201,"缺少编号"),
	/** 缺少名称 */
	VALID_NAME_NOT_EXIST(11202,"缺少名称"),
	/** 缺少手机号 */
	VALID_MOBILE_NOT_EXIST(11203,"缺少手机号"),
	/** 缺少邮箱 */
	VALID_EMAIL_NOT_EXIST(11202,"缺少邮箱"),
	/** 缺少ID */
	VALID_ID_NOT_EXIST(11202,"缺少ID"),
	/** 性別信息有誤*/
	VALID_SEX_ERROR(11203,"性別信息有誤"),
	
	// User Login 占用 11300 ~ 11599
	
	/** I-服务内部错误 */
	IM_ERROR (11301,"I-服务内部错误"),
	/** 用户名已被注册 */
	USER_ACCOUNT_EXIST(11401,"用户名已被注册"),
	/** 手机号已被注册 */
	USER_TELEPHONE_EXIST(11402,"手机号已被注册"),
	/** 邮箱已被注册 */
	USER_EMAIL_EXIST(11403,"邮箱已被注册"),
	/** worktime用户对应的IM用户不存在 */
	USER_NOT_EXIST_WORKTOIM(11500,"worktime用户对应的IM用户不存在"),
	/** IM用户对应的worktime用户不存在 */
	USER_NOT_EXIST_IMTOWORK(11501,"IM用户对应的worktime用户不存在"),
	/** worktime 用户不存在 */
	USER_NOT_EXIST_WORK(11502,"用户不存在"),
	/** applicationContext User 获取失败 */
	USER_CONTEXT_NOT_EXIST(11503,"获取用户Context信息失败"),
	/** 创建用户state不能为1 */
	USER_STATE_ERROR(11504,"创建用户state不能为1"),
	/** 用户注册失败 */
	USER_CREATE_ERROR(11505,"用户注册失败"),
	/** 用户编辑失败 */
	USER_EDIT_ERROR(11506,"用户编辑失败"),
	/** 用户删除失败 */
	USER_DELETE_ERROR(11507,"用户删除失败"),
	/** 用户未启用 */
	USER_STATE_NOT_START(11508,"用户未启用"),
	/** 用户账号启用失败 */
	USER_SET_START_ERROR(11509,"用户账号启用失败"),
	/** 用户账号停用失败 */
	USER_SET_STOP_ERROR(11510,"用户账号停用失败"),
	/** 用户设置为普通用户失败 */
	USER_SET_NORMAL_ERROR(11511,"用户设置为普通用户失败"),
	/** 用户设置为管理员失败 */
	USER_SET_ADMIN_ERROR(11512,"用户设置为管理员失败"),
	
	/** 租户信息不存在 */
	TENEMENT_NOT_EXIST(11599,"租户信息不存在"),
	
	
	PARTNER_REMOVE_ERROR(1012, "项目参与者是任务执行人，不能移除【taskId=%s,memberId=%s】"),
	
	EXCUTOR_NOT_IN_PARTNERS(1013, "任务执行人不在项目参与人列表中【excutor=%s】"),
	
	REQUEST_PARAM_FORMAT_ILLEGAL(12001, "请求体格式不正确"),

	
	
	/////////////////云盘文档相关异常
	GET_DOCINFO_FAILED(7001, "从文件服务器获取文档信息失败!"),
	MAL_CLOUDDISK_DB_DATA(7002, "云盘数据库数据异常!"),
	CLOUDDISK_ACCESS_FORBIDDEN(7003, "云盘禁止此次访问!"),
	CORRUPTED_DB_DATA(7004, "未预定义的,无法识别的数据库数据!"),
	DELETION_FAILURE(7005, "删除过程出现删除失败的状况"),
	SPACE_CREATION_FAILURE(7006, "空间创建失败！"),
	CONVERT_NOT_FINISHED(7007, "文档尚未转换完成！"),
	SPACE_NOT_EXISTED(7008, "此存储空间尚未初始化!"),
	/** 未预期的异常 */
	UNEXPECTED(5000, "未预期的异常！"), 
	
	IMException(6000,"IM异常，"),
	
	GROUP_Error(6001, ""),
	
	AUTHORIZATION_RESOURCE_EXCEPTION(4001, "资源实例必须唯一");
	

	private int value;

	private String description;

	private ErrorCode(int value, String description) {
		this.value = value;
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

}