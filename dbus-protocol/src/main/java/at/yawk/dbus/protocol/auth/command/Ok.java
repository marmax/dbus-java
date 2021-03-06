package at.yawk.dbus.protocol.auth.command;

import at.yawk.dbus.protocol.DbusUtil;
import java.util.List;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * @author yawkat
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class Ok extends Command {
    public static final String NAME = "OK";
    private final UUID uuid;

    public Ok(UUID uuid) {
        super(AuthDirection.FROM_SERVER, NAME, DbusUtil.printUuid(uuid));
        this.uuid = uuid;
    }

    public static Ok parse(List<String> args) {
        if (args.size() != 1) { throw new IllegalArgumentException("Expected exactly one argument"); }
        return new Ok(DbusUtil.parseUuid(args.get(0)));
    }
}
