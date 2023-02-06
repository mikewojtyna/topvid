package pl.wojtyna.tasks.front;

import java.util.Optional;

public interface ConfigurationManager {

    Optional<MatchingConfiguration> findMatchingConfiguration();

    void addFilter(Filter filter, String path);

    void addFilterBefore(Filter filter, String path, Class<Filter> beforeThisFilter);
}
