package pl.wojtyna.topvid.common.commandinvoker;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.CommandPattern;

import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

@CommandPattern
public class InMemoryRepeatableCommandInvoker implements RepeatableCommandInvoker {

    private final ConcurrentLinkedQueue<CommandDescriptor> history = new ConcurrentLinkedQueue<>();

    @Override
    public ExecutionId invoke(Command command) {
        var executionId = ExecutionId.unique();
        command.execute();
        history.add(new CommandDescriptor(executionId, command));
        return executionId;
    }

    @Override
    public Optional<ExecutionId> repeat(ExecutionId id) {
        return history.stream()
                      .filter(commandDescriptor -> commandDescriptor.id().equals(id))
                      .findAny()
                      .map(CommandDescriptor::command)
                      .map(this::invoke);
    }

    private record CommandDescriptor(@NonNull ExecutionId id, @NonNull Command command) {

    }
}
