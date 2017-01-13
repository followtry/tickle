/**
 * 
 */
package cn.followtry.comm.sysprop;

import java.util.Properties;

/**
 * @author jingzz
 * @time 2016年8月5日 下午3:56:47
 * @name esn-palmyy-plugin/com.yonyou.esn.palmyy.service.test
 * @since 2016年8月5日 下午3:56:47
 */
public class SystemProperties {
	
	private SystemProperties(){}
	
	private static String AWT_TOOLKIT = "awt.toolkit";
	
	private static String FILE_ENCODING = "file.encoding";

	private static String FILE_ENCODING_PKG = "file.encoding.pkg";

	private static String FILE_SEPARATOR = "file.separator";

	private static String JAVA_AWT_GRAPHICSENV = "java.awt.graphicsenv";

	private static String JAVA_AWT_PRINTERJOB = "java.awt.printerjob";

	private static String JAVA_CLASS_PATH = "java.class.path";

	private static String JAVA_CLASS_VERSION = "java.class.version";

	private static String JAVA_ENDORSED_DIRS = "java.endorsed.dirs";

	private static String JAVA_EXT_DIRS = "java.ext.dirs";

	private static String JAVA_HOME = "java.home";

	private static String JAVA_IO_TMPDIR = "java.io.tmpdir";

	private static String JAVA_LIBRARY_PATH = "java.library.path";

	private static String JAVA_RUNTIME_NAME = "java.runtime.name";

	private static String JAVA_RUNTIME_VERSION = "java.runtime.version";

	private static String JAVA_SPECIFICATION_NAME = "java.specification.name";

	private static String JAVA_SPECIFICATION_VENDOR = "java.specification.vendor";

	private static String JAVA_SPECIFICATION_VERSION = "java.specification.version";

	private static String JAVA_VENDOR = "java.vendor";

	private static String JAVA_VENDOR_URL = "java.vendor.url";

	private static String JAVA_VENDOR_URL_BUG = "java.vendor.url.bug";

	private static String JAVA_VERSION = "java.version";

	private static String JAVA_VM_INFO = "java.vm.info";

	private static String JAVA_VM_NAME = "java.vm.name";

	private static String JAVA_VM_SPECIFICATION_NAME = "java.vm.specification.name";

	private static String JAVA_VM_SPECIFICATION_VENDOR = "java.vm.specification.vendor";

	private static String JAVA_VM_SPECIFICATION_VERSION = "java.vm.specification.version";

	private static String JAVA_VM_VENDOR = "java.vm.vendor";

	private static String JAVA_VM_VERSION = "java.vm.version";

	private static String LINE_SEPARATOR = "line.separator";

	private static String OS_ARCH = "os.arch";

	private static String OS_NAME = "os.name";

	private static String OS_VERSION = "os.version";

	private static String PATH_SEPARATOR = "path.separator";

	private static final Properties properties = System.getProperties();

	private static String SUN_ARCH_DATA_MODEL = "sun.arch.data.model";

	private static String SUN_BOOT_CLASS_PATH = "sun.boot.class.path";

	private static String SUN_BOOT_LIBRARY_PATH = "sun.boot.library.path";

	private static String SUN_CPU_ENDIAN = "sun.cpu.endian";

	private static String SUN_CPU_ISALIST = "sun.cpu.isalist";

	private static String SUN_DESKTOP = "sun.desktop";

	private static String SUN_IO_UNICODE_ENCODING = "sun.io.unicode.encoding";

	private static String SUN_JAVA_COMMAND = "sun.java.command";

	private static String SUN_JAVA_LAUNCHER = "sun.java.launcher";

	private static String SUN_JNU_ENCODING = "sun.jnu.encoding";

	private static String SUN_MANAGEMENT_COMPILER = "sun.management.compiler";

	private static String SUN_OS_PATCH_LEVEL = "sun.os.patch.level";

	private static String USER_COUNTRY = "user.country";

	private static String USER_DIR = "user.dir";

	private static String USER_HOME = "user.home";

	private static String USER_LANGUAGE = "user.language";

	private static String USER_NAME = "user.name";

	private static String USER_SCRIPT = "user.script";

	private static String USER_TIMEZONE = "user.timezone";
	
	private static String USER_VARIANT = "user.variant";
	
	private static String getEnvValue(String key){
		return properties.getProperty(key);
	}
	
	/**
	 * awt的工具包
	 * @author jingzz
	 * @return 工具包信息
	 */
	public static String awtToolkit(){
		return getEnvValue(AWT_TOOLKIT);
	}
	
	/**
	 * 本类方法未涉及到的属性
	 * @author jingzz
	 * @param key  属性名称
	 * @return
	 */
	public static String customProperty(String key){
		return getEnvValue(key);
	}
	
	/**
	 * 文件编码
	 * @author jingzz
	 * @return 文件编码格式
	 */
	public static String fileEncoding(){
		return getEnvValue(FILE_ENCODING);
	}
	
	/**
	 * 文件编码包
	 * @author jingzz
	 * @return
	 */
	public static String fileEncodingPkg(){
		return getEnvValue(FILE_ENCODING_PKG);
	}
	
	/**
	 * 文件分隔符
	 * @author jingzz
	 * @return
	 */
	public static String fileSeparator(){
		return getEnvValue(FILE_SEPARATOR);
	}
	
	/**
	 * java的awt图形环境
	 * @author jingzz
	 * @return
	 */
	public static String javaAwtGraphicsenv(){
		return getEnvValue(JAVA_AWT_GRAPHICSENV);
	}
	
	/**
	 * java的awt打印机任务
	 * @author jingzz
	 * @return
	 */
	public static String javaAwtPrinterjob(){
		return getEnvValue(JAVA_AWT_PRINTERJOB);
	}
	
	/**
	 * java的class路径
	 * @author jingzz
	 * @return
	 */
	public static String javaClassPath(){
		return getEnvValue(JAVA_CLASS_PATH);
	}
	
	/**
	 * java的class版本
	 * @author jingzz
	 * @return
	 */
	public static String javaClassVersion(){
		return getEnvValue(JAVA_CLASS_VERSION);
	}
	
	/**
	 * java的授权目录
	 * @author jingzz
	 * @return
	 */
	public static String javaEndorsedDirs(){
		return getEnvValue(JAVA_ENDORSED_DIRS);
	}
	
	/**
	 * java的额外目录
	 * @author jingzz
	 * @return
	 */
	public static String javaExtDirs(){
		return getEnvValue(JAVA_EXT_DIRS);
	}
	
	/**
	 * java运行时环境的主目录
	 * @author jingzz
	 * @return
	 */
	public static String javaHome(){
		return getEnvValue(JAVA_HOME);
	}
	
	/**
	 * java IO操作的临时目录
	 * @author jingzz
	 * @return
	 */
	public static String javaIOTmpdir(){
		return getEnvValue(JAVA_IO_TMPDIR);
	}
	
	/**
	 * java的库路径
	 * @author jingzz
	 * @return
	 */
	public static String javaLibraryPath(){
		return getEnvValue(JAVA_LIBRARY_PATH);
	}
	
	/**
	 * java 运行时环境名称
	 * @author jingzz
	 * @return
	 */
	public static String javaRunTimeName(){
		return getEnvValue(JAVA_RUNTIME_NAME);
	}
	
	/**
	 * java运行时版本号
	 * @author jingzz
	 * @return
	 */
	public static String javaRuntimeVersion(){
		return getEnvValue(JAVA_RUNTIME_VERSION);
	}
	
	/**
	 * java规格名称
	 * @author jingzz
	 * @return
	 */
	public static String javaSpecificationName(){
		return getEnvValue(JAVA_SPECIFICATION_NAME);
	}
	
	/**
	 * java规格供应商
	 * @author jingzz
	 * @return
	 */
	public static String javaSpecificationVendor(){
		return getEnvValue(JAVA_SPECIFICATION_VENDOR);
	}
	
	/**
	 * java规格版本
	 * @author jingzz
	 * @return
	 */
	public static String javaSpecificationVersion(){
		return getEnvValue(JAVA_SPECIFICATION_VERSION);
	}
	
	/**
	 * java供应商
	 * @author jingzz
	 * @return
	 */
	public static String javaVendor(){
		return getEnvValue(JAVA_VENDOR);
	}
	
	/**
	 * java供应商网址
	 * @author jingzz
	 * @return
	 */
	public static String javaVendorUrl(){
		return getEnvValue(JAVA_VENDOR_URL);
	}
	
	/**
	 * java供应商的bug网址
	 * @author jingzz
	 * @return
	 */
	public static String javaVendorUrlBug(){
		return getEnvValue(JAVA_VENDOR_URL_BUG);
	}
	
	/**
	 * java版本
	 * @author jingzz
	 * @return
	 */
	public static String javaVersion(){
		return getEnvValue(JAVA_VERSION);
	}
	
	/**
	 * java虚拟机信息
	 * @author jingzz
	 * @return
	 */
	public static String javaVMInfo(){
		return getEnvValue(JAVA_VM_INFO);
	}
	
	/**
	 * java虚拟机名称
	 * @author jingzz
	 * @return
	 */
	public static String javaVMName(){
		return getEnvValue(JAVA_VM_NAME);
	}
	
	/**
	 * java虚拟机规格名称
	 * @author jingzz
	 * @return
	 */
	public static String javaVMSpecificationName(){
		return getEnvValue(JAVA_VM_SPECIFICATION_NAME);
	}
	
	/**
	 * java虚拟机规格供应商
	 * @author jingzz
	 * @return
	 */
	public static String javaVMSpecificationVendor(){
		return getEnvValue(JAVA_VM_SPECIFICATION_VENDOR);
	}
	
	/**
	 * java虚拟机规格版本
	 * @author jingzz
	 * @return
	 */
	public static String javaVMSpecificationVersion(){
		return getEnvValue(JAVA_VM_SPECIFICATION_VERSION);
	}
	
	/**
	 * java虚拟机供应商
	 * @author jingzz
	 * @return
	 */
	public static String javaVMVendor(){
		return getEnvValue(JAVA_VM_VENDOR);
	}
	
	/**
	 * java虚拟机版本
	 * @author jingzz
	 * @return
	 */
	public static String javaVMVersion(){
		return getEnvValue(JAVA_VM_VERSION);
	}
	
	/**
	 * java线分隔符
	 * @author jingzz
	 * @return
	 */
	public static String lineSeparator(){
		return getEnvValue(LINE_SEPARATOR);
	}
	
	/**
	 * 系统架构
	 * @author jingzz
	 * @return
	 */
	public static String osArch(){
		return getEnvValue(OS_ARCH);
	}
	
	/**
	 * 系统名称
	 * @author jingzz
	 * @return
	 */
	public static String osName(){
		return getEnvValue(OS_NAME);
	}
	
	/**
	 * 系统版本
	 * @author jingzz
	 * @return
	 */
	public static String osVersion(){
		return getEnvValue(OS_VERSION);
	}
	
	/**
	 * 路径分隔符
	 * @author jingzz
	 * @return
	 */
	public static String pathSeparator(){
		return getEnvValue(PATH_SEPARATOR);
	}
	
	/**
	 * sun架构数据模型
	 * @author jingzz
	 * @return
	 */
	public static String sunArchDataModel(){
		return getEnvValue(SUN_ARCH_DATA_MODEL);
	}
	
	/**
	 * sun启动类路径
	 * @author jingzz
	 * @return
	 */
	public static String sunBootClassPath(){
		return getEnvValue(SUN_BOOT_CLASS_PATH);
	}
	
	/**
	 * sun启动库路径
	 * @author jingzz
	 * @return
	 */
	public static String sunBootLibraryPath(){
		return getEnvValue(SUN_BOOT_LIBRARY_PATH);
	}
	
	/**
	 * sun的CPU端顺序（大端存储或者小端存储）
	 * @author jingzz
	 * @return
	 */
	public static String sunCpuEndian(){
		return getEnvValue(SUN_CPU_ENDIAN);
	}
	
	/**
	 * sun的cpu工业标准总线列表
	 * @author jingzz
	 * @return
	 */
	public static String sunCpuIsalist(){
		return getEnvValue(SUN_CPU_ISALIST);
	}
	
	/**
	 * 桌面环境
	 * @author jingzz
	 * @return
	 */
	public static String sunDesktop(){
		return getEnvValue(SUN_DESKTOP);
	}
	
	/**
	 * IO编码
	 * @author jingzz
	 * @return
	 */
	public static String sunIOUnicodeEncoding(){
		return getEnvValue(SUN_IO_UNICODE_ENCODING);
	}
	
	/**
	 * java命令
	 * @author jingzz
	 * @return
	 */
	public static String sunJavaCommand(){
		return getEnvValue(SUN_JAVA_COMMAND);
	}
	
	/**
	 * java启动器
	 * @author jingzz
	 * @return
	 */
	public static String sunJavaLauncher(){
		return getEnvValue(SUN_JAVA_LAUNCHER);
	}
	
	/**
	 * java的jnu编码
	 * @author jingzz
	 * @return
	 */
	public static String sunJnuEncoding(){
		return getEnvValue(SUN_JNU_ENCODING);
	}
	
	/**
	 * 编译器
	 * @author jingzz
	 * @return
	 */
	public static String sunManagementCompiler(){
		return getEnvValue(SUN_MANAGEMENT_COMPILER);
	}
	
	/**
	 * 系统补丁级别
	 * @author jingzz
	 * @return
	 */
	public static String sunOSPatchLevel(){
		return getEnvValue(SUN_OS_PATCH_LEVEL);
	}
	
	/**
	 * 用户国家
	 * @author jingzz
	 * @return
	 */
	public static String userCountry(){
		return getEnvValue(USER_COUNTRY);
	}
	
	/**
	 * 用户目录
	 * @author jingzz
	 * @return
	 */
	public static String userDir(){
		return getEnvValue(USER_DIR);
	}
	
	/**
	 * 用户主目录
	 * @author jingzz
	 * @return
	 */
	public static String userHome(){
		return getEnvValue(USER_HOME);
	}
	
	/**
	 * 用户语言
	 * @author jingzz
	 * @return
	 */
	public static String userLanguage(){
		return getEnvValue(USER_LANGUAGE);
	}
	
	/**
	 * 用户名
	 * @author jingzz
	 * @return
	 */
	public static String userName(){
		return getEnvValue(USER_NAME);
	}
	
	/**
	 * 用户脚本
	 * @author jingzz
	 * @return
	 */
	public static String userScript(){
		return getEnvValue(USER_SCRIPT);
	}
	
	/**
	 * 用户时区
	 * @author jingzz
	 * @return
	 */
	public static String userTimezone(){
		return getEnvValue(USER_TIMEZONE);
	}
	
	/**
	 * 用户变种
	 * @author jingzz
	 * @return
	 */
	public static String userVariant(){
		return getEnvValue(USER_VARIANT);
	}
}
