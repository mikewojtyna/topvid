package pl.wojtyna.topvid.common.commandinvoker;

import pl.wojtyna.topvid.patterns.CommandPattern;
import pl.wojtyna.topvid.patterns.FactoryMethodPattern;

import java.util.Arrays;

@CommandPattern
public interface Command {

    void execute();

    @FactoryMethodPattern
    static Command composite(Command... commands) {
        return () -> Arrays.stream(commands).forEach(Command::execute);
    }
}
