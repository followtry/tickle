package cn.followtry.java.ext.processor.core;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * 实现java 的Processor机制
 * Created by followtry on 2017/4/28.
 */
@SupportedAnnotationTypes({"cn.followtry.java.ext.processor.anno.TestAnno"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class ExtProcessor extends AbstractProcessor{

  @Override
  public boolean process(Set<? extends TypeElement> annotations,RoundEnvironment roundEnv) {
    Messager messager = processingEnv.getMessager();
    for (TypeElement te : annotations) {
      for (Element e : roundEnv.getElementsAnnotatedWith(te)) {
        messager.printMessage(Diagnostic.Kind.NOTE, "Printing: " + e.toString());
      }
    }
    return true;
  }
}
