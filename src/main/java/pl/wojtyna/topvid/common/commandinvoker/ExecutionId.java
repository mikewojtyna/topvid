package pl.wojtyna.topvid.common.commandinvoker;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.CommandPattern;
import pl.wojtyna.topvid.patterns.FactoryMethodPattern;

import java.util.UUID;

@CommandPattern
public record ExecutionId(@NonNull String value) {

    @FactoryMethodPattern
    public static ExecutionId unique() {
        return new ExecutionId(UUID.randomUUID().toString());
    }
}
