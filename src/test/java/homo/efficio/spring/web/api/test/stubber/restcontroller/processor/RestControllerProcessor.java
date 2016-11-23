package homo.efficio.spring.web.api.test.stubber.restcontroller.processor;

import homo.efficio.spring.web.api.test.stubber.factory_example.annotation.Factory;
import homo.efficio.spring.web.api.test.stubber.factory_example.processor.FactoryAnnotatedClass;
import homo.efficio.spring.web.api.test.stubber.restcontroller.extracted.ExtractedRestController;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.processing.Completion;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author homo.efficio@gmail.com
 *         created on 2016-11-23.
 */
public class RestControllerProcessor extends AbstractStubberProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {

        Set<String> annotataions = new LinkedHashSet<String>();
        annotataions.add(RestController.class.getCanonicalName());
        return annotataions;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("-------------");
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(RestController.class);
        for (Element annotatedElement: elementsAnnotatedWith) {
            if (annotatedElement.getKind() != ElementKind.CLASS) {
                error(annotatedElement, "@%s can be applied only to class",
                        RestController.class.getSimpleName());
                return true;
            }
            System.out.println(annotatedElement);
            TypeElement typeElement = (TypeElement) annotatedElement;

            ExtractedRestController annotatedClass = new ExtractedRestController(typeElement);


        }

        return false;
    }

    @Override
    public Iterable<? extends Completion> getCompletions(Element element, AnnotationMirror annotation, ExecutableElement member, String userText) {
        return Collections.emptyList();
    }
}
