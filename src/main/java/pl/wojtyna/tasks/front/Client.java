package pl.wojtyna.tasks.front;

public class Client {

    public static void main(String[] args) {
        ConfigurationManager configurationManager;
        configurationManager.addFilter(new SecurityFilter(), "/api/*");
    }
}
