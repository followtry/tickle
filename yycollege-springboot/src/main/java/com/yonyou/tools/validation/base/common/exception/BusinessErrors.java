package com.yonyou.tools.validation.base.common.exception;


/**
 * 创建业务异常工厂类
 * 
 * @author liuxrb
 *
 */
public class BusinessErrors {

	/**
	 * 抛出缺少参数异常
	 * 
	 * @param paramName
	 *            参数名称
	 */
	public static BusinessException missedParamError(String paramName){
		int errorCode = ErrorCode.MISSING_REQUIRED_PARAMETE.getValue();
		String errorMessage = String.format(ErrorCode.MISSING_REQUIRED_PARAMETE.getDescription(), paramName);
		return  new BusinessException(errorCode, errorMessage);
	}

	/**
	 * 抛出参数格式非法异常
	 * 
	 * @param paramName
	 *            参数名称
	 * @throws BusinessException
	 */
	public static BusinessException illegalParamError(String paramName) {
		int errorCode = ErrorCode.ILLEGAL_PARAMETE.getValue();
		String errorMessage = String.format(ErrorCode.ILLEGAL_PARAMETE.getDescription(), paramName);
		return  new BusinessException(errorCode, errorMessage);
	}

	/**
	 * 抛出操作被禁止异常
	 * 
	 * @throws BusinessException
	 */
	public static BusinessException performForbiddenError(){
		return  new BusinessException(ErrorCode.PERFORM_FORBIDDEN.getValue(),
				ErrorCode.PERFORM_FORBIDDEN.getDescription());
	}

	public static BusinessException unexpect(Throwable error) {
		return new BusinessException(ErrorCode.UNEXPECTED.getValue(),
				String.format(ErrorCode.UNEXPECTED.getDescription(), error.getMessage()), error);
	}
	
	public static BusinessException masterNotExist(){
		return new BusinessException(ErrorCode.PROJECT_MASTER_NOT_EXIST.getValue(), 
				ErrorCode.PROJECT_MASTER_NOT_EXIST.getDescription());
	}
	
	public static BusinessException partnerNotExist(String id){
		return new BusinessException(ErrorCode.PROJECT_PARTNER_NOT_EXIST.getValue(),
				String.format(ErrorCode.PROJECT_PARTNER_NOT_EXIST.getDescription(), id));
	}
	
	public static BusinessException sharnerNotExist(Long id){
		return new BusinessException(ErrorCode.PROJECT_SHARNER_NOT_EXIST.getValue(),
				String.format(ErrorCode.PROJECT_SHARNER_NOT_EXIST.getDescription(), id));
	}
	
	
			
	/**
	 * 更新失败或未找到任务
	 */
	public static BusinessException taskNotFoundError() {
		return  new BusinessException(ErrorCode.PROJECT_TASK_NOT＿EXISTS.getValue(),
				ErrorCode.PROJECT_TASK_NOT＿EXISTS.getDescription());
	}

	/**
	 * 任务尚未启动，无法完成
	 */
	public static BusinessException completeTaskNotStartedError(){
		return new BusinessException(ErrorCode.TASK_NOTSTARTED.getValue(),
				ErrorCode.TASK_NOTSTARTED.getDescription());
	}

	/**
	 * 任务已经完成
	 */
	public static BusinessException completeTaskAlreadyCompletedError(){
		return new BusinessException(ErrorCode.TASK_ALREADY_COMPLETED.getValue(),
				ErrorCode.TASK_ALREADY_COMPLETED.getDescription());
	}

	/**
	 * 任务完成时间不能早于开始时间
	 */
	public static BusinessException completeTaskCompleteDateError(){
		return new BusinessException(ErrorCode.TASK_COMPLETEDATE_ERROR.getValue(),
				ErrorCode.TASK_COMPLETEDATE_ERROR.getDescription());
	}
	
	/**
	 * 任务计划完成时间不能早于开始时间
	 */
	public static BusinessException completeTaskPlanCompleteDateError(){
		return new BusinessException(ErrorCode.TASK_PLANCOMPLETEDATE_ERROR.getValue(),
				ErrorCode.TASK_PLANCOMPLETEDATE_ERROR.getDescription());
	}
	/**
	 * 抛出项目不可编辑异常
	 * @throws BusinessException
	 */
	public static BusinessException projectNonEditable(){
		return  new BusinessException(ErrorCode.PROJECT_NOT_EDITABLE.getValue(), 
				ErrorCode.PROJECT_NOT_EDITABLE.getDescription());
	}
	
	
	/**
	 * 抛出截止日期早于开始日期异常
	 * @throws BusinessException
	 */
	public static BusinessException endBeforBegin(){
		return new BusinessException(ErrorCode.ENDDATE_BEFORE_BEGINDATE.getValue(), 
				ErrorCode.ENDDATE_BEFORE_BEGINDATE.getDescription());
	}
	
	/**
	 * 任务创建失败异常
	 * @throws BusinessException
	 */
	public static BusinessException taskCreationFailed(){
		return new BusinessException(ErrorCode.TASK_CREATION_FAILED.getValue(), 
				ErrorCode.TASK_CREATION_FAILED.getDescription());
	}
	
	/**
	 * 成员用户不存在
	 * @throws BusinessException
	 */
	public static BusinessException memberNotExist(){
		return new BusinessException(ErrorCode.MEMBER_NOT_EXIST.getValue(), 
				ErrorCode.MEMBER_NOT_EXIST.getDescription());
	}

	/**
	 * 任务已经启动
	 * @author jingzz
	 * @return
	 */
	public static BusinessException taskAlreadyStarted() {
		return new BusinessException(ErrorCode.PROJECT_TASK_ALREADY_STARTED.getValue(), 
				ErrorCode.PROJECT_TASK_ALREADY_STARTED.getDescription());
	}
	
	/**
	 * 成员添加失败
	 * @author jingzz
	 * @return
	 */
	public static BusinessException memberAddedFailed() {
		return new BusinessException(ErrorCode.MEMBER_ADDED_FAILED.getValue(), 
				ErrorCode.MEMBER_ADDED_FAILED.getDescription());
	}
	
	/**
	 * 项目不存在
	 * @author jingzz
	 * @return
	 */
	public static BusinessException projectNotExists() {
		return new BusinessException(ErrorCode.PROJECT_NOT_EXISTS.getValue(), 
				ErrorCode.PROJECT_NOT_EXISTS.getDescription());
	}
	
	public static BusinessException partnerRemoveError(Long taskId, Long memberId){
		return new BusinessException(ErrorCode.PARTNER_REMOVE_ERROR.getValue(), 
				String.format(ErrorCode.PARTNER_REMOVE_ERROR.getDescription(), taskId, memberId));
	}
	
	/**
	 * 任务执行人不在项目参与人列表
	 * @param taskId
	 * @return
	 */
	public static BusinessException excutorNotInPartners(Long taskId){
		return new BusinessException(ErrorCode.EXCUTOR_NOT_IN_PARTNERS.getValue(), 
				String.format(ErrorCode.EXCUTOR_NOT_IN_PARTNERS.getDescription(), taskId));
	}
	public static BusinessException getDocInfoFailed(String errorMsg){
		return new BusinessException(ErrorCode.GET_DOCINFO_FAILED.getValue(),
				ErrorCode.GET_DOCINFO_FAILED.getDescription() + errorMsg);
	}
	/**
	 * 数据未达到预期的唯一性,异常
	 * @param errorMsg
	 * @return
	 * @author zhuqhc
	 * @date     2016年3月25日 下午2:27:13
	 */
	public static BusinessException notSingleItem(String errorMsg){
		return new BusinessException(ErrorCode.MAL_CLOUDDISK_DB_DATA.getValue(),
				ErrorCode.MAL_CLOUDDISK_DB_DATA.getDescription() + errorMsg);
	}
	/**
	 * 云盘访问操作被禁止
	 * @param errorMsg
	 * @return
	 * @author zhuqhc
	 * @date     2016年3月25日 下午2:38:42
	 */
	public static BusinessException accessForbidden(String errorMsg){
		return new BusinessException(ErrorCode.CLOUDDISK_ACCESS_FORBIDDEN.getValue(),
				ErrorCode.CLOUDDISK_ACCESS_FORBIDDEN.getDescription() + errorMsg);
	}
	/**
	 * 被侵害的数据库数据
	 * @param errorMsg
	 * @return
	 * @author zhuqhc
	 * @date     2016年3月29日 上午10:03:38
	 */
	public static BusinessException corruptedDbData(String errorMsg){
		return new BusinessException(ErrorCode.CORRUPTED_DB_DATA.getValue(),
				ErrorCode.CORRUPTED_DB_DATA.getDescription() + errorMsg);
	}
	/**
	 * 删除文件/文件夹项产生失败
	 * @param errorMsg
	 * @return
	 * @author zhuqhc
	 * @date     2016年3月30日 下午4:35:38
	 */
	public static BusinessException deleteItemFailure(String errorMsg){
		return new BusinessException(ErrorCode.DELETION_FAILURE.getValue(),
				ErrorCode.DELETION_FAILURE.getDescription() + errorMsg);
	}
	/**
	 * 创建空间失败
	 * @param errorMsg
	 * @return
	 * @author zhuqhc
	 * @date     2016年3月31日 下午6:40:35
	 */
	public static BusinessException createSpaceFailure(String errorMsg){
		return new BusinessException(ErrorCode.SPACE_CREATION_FAILURE.getValue(),
				ErrorCode.SPACE_CREATION_FAILURE.getDescription() + errorMsg);
	}
	
	/**
	 * 任务执行人不存在
	 * @param executor
	 * @return
	 */
	public static BusinessException taskExecutorNotExist(String executor){
		return new BusinessException(ErrorCode.TASK_EXECUTOR_NOT_EXIST.getValue(), 
				String.format(ErrorCode.TASK_EXECUTOR_NOT_EXIST.getDescription(), executor));
	}
	/**
	 * 文档尚未转换完成
	 * @param errorMsg
	 * @return
	 * @author zhuqhc
	 * @date     2016年4月7日 上午10:14:46
	 */
	public static BusinessException convertNotFinishedException(String errorMsg){
		return new BusinessException(ErrorCode.CONVERT_NOT_FINISHED.getValue(),
				ErrorCode.CONVERT_NOT_FINISHED.getDescription() + errorMsg);
	}
	
	public static BusinessException IMException(String errorMsg){
		return new BusinessException(ErrorCode.IMException.getValue(),
				ErrorCode.IMException.getDescription() + errorMsg);
	}
	/**
	 * 存储空间不存在
	 * @return
	 * @author zhuqhc
	 * @date     2016年4月22日 上午9:12:00
	 */
	public static BusinessException SpaceNotExistedException(){
		return new BusinessException(ErrorCode.SPACE_NOT_EXISTED.getValue(),
				ErrorCode.SPACE_NOT_EXISTED.getDescription());
	}
	
	public static BusinessException authorizationResourceException(){
		return new BusinessException(ErrorCode.AUTHORIZATION_RESOURCE_EXCEPTION.getValue(), 
				ErrorCode.AUTHORIZATION_RESOURCE_EXCEPTION.getDescription());
	}
	
	public static BusinessException groupError(String errorMsg){
		return new BusinessException(ErrorCode.GROUP_Error.getValue(),
				ErrorCode.GROUP_Error.getDescription() + errorMsg);
	}
}
