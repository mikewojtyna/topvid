package pl.wojtyna.tasks.upload;

public class DependencyInjectionContainer {

    public static void main(String[] args) {
        var dependencyInjectionContainer = new DependencyInjectionContainer();
        var mainApplication = dependencyInjectionContainer.getComponent(MainApplication.class);
        mainApplication.run();
    }

    <T> T getComponent(Class<T> type) {
        return null;
    }
}
