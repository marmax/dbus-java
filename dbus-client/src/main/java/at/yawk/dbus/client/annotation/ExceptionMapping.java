package at.yawk.dbus.client.annotation;

import java.lang.annotation.*;
import javax.annotation.RegEx;

/**
 * @author yawkat
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ExceptionMapping.RepeatableExceptionMapping.class)
public @interface ExceptionMapping {
    String value() default "";

    @RegEx String pattern() default "";

    Class<? extends Exception> exception();

    @Target({ ElementType.TYPE, ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    @interface RepeatableExceptionMapping {
        ExceptionMapping[] value();
    }
}
