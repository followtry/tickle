package cn.followtry.common.nacosconfig.register;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/**
 * @author followtry
 * @since 2021/9/23 3:27 下午
 */
public class AnnotationFieldFilter implements ReflectionUtils.FieldFilter {

    private List<Class<? extends Annotation>> annotationTypes;

    public AnnotationFieldFilter(List<Class<? extends Annotation>> annotationTypes) {
        this.annotationTypes = annotationTypes;
    }

    @Override
    public boolean matches(Field field) {
        for (Class<? extends Annotation> annotationType : annotationTypes) {
            if (AnnotationUtils.getAnnotation(field, annotationType) != null) {
                return true;
            }
        }
        return false;
    }

    public List<Class<? extends Annotation>> getAnnotationTypes() {
        return Collections.unmodifiableList(annotationTypes);
    }
}
