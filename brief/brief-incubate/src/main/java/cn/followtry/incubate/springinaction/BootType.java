/**
 * 
 */
package cn.followtry.incubate.springinaction;

/**
 * @author jingzz
 * @time 2016年9月20日 上午9:34:25
 * @name brief-example/cn.jingzztech.prac.springinaction.boot.BootType
 * @since 2016年9月20日 上午9:34:25
 */
public enum BootType {
	
	ANNOTATION,//注解方式
	CONFIG,//配置方式
	XML_FS,//xml文件，文件系统方式
	XML_CLASSPATH;//xml文件，类路径方式
}
