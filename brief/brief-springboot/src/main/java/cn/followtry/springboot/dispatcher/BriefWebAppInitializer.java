
package cn.followtry.springboot.dispatcher;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**.
 * @author jingzz
 * @since 2016年10月14日 下午2:02:05
 */
public class BriefWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[]{ RootConfig.class };
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[]{ WebConfig.class };
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{ "/" };
  }

}
