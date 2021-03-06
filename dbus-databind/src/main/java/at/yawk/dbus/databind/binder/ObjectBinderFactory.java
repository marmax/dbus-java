package at.yawk.dbus.databind.binder;

import at.yawk.dbus.protocol.object.DbusObject;
import at.yawk.dbus.protocol.type.TypeDefinition;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import lombok.Getter;

/**
 * @author yawkat
 */
public class ObjectBinderFactory implements BinderFactory {
    @Getter
    private static final ObjectBinderFactory instance = new ObjectBinderFactory();

    private ObjectBinderFactory() {}

    @Nullable
    @Override
    public Binder<?> getBinder(BinderFactoryContext ctx, Type type) {
        if (type == Object.class) {
            return new Binder<Object>() {
                @Override
                public TypeDefinition getType() {
                    throw new TypeNotAvailableException();
                }

                @Override
                public Object decode(DbusObject object) {
                    return ctx.getDefaultDecodeBinder(object.getType()).decode(object);
                }

                @SuppressWarnings("unchecked")
                @Override
                public DbusObject encode(Object obj) {
                    return ((Binder) ctx.getDefaultEncodeBinder(obj.getClass())).encode(obj);
                }
            };
        }

        return null;
    }
}
