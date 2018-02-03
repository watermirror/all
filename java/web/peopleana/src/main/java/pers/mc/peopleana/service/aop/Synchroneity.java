package pers.mc.peopleana.service.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to guarantee the synchroneity. Use it to annotate a method.
 * @version 18.2.1.0
 * @author Michael Che
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Synchroneity {
}
