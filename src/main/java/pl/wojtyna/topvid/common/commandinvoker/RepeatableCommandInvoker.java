package pl.wojtyna.topvid.common.commandinvoker;

import pl.wojtyna.topvid.patterns.CommandPattern;

import java.util.Optional;

@CommandPattern
public interface RepeatableCommandInvoker extends CommandInvoker {

    Optional<ExecutionId> repeat(ExecutionId id);
}
