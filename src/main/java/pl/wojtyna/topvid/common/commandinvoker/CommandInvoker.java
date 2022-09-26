package pl.wojtyna.topvid.common.commandinvoker;

import pl.wojtyna.topvid.patterns.CommandPattern;

@CommandPattern
public interface CommandInvoker {

    ExecutionId invoke(Command command);
}
