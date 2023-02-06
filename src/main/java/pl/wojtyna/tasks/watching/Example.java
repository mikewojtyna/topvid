package pl.wojtyna.tasks.watching;

public class Example {

    public static void main(String[] args) {
        Hint.Builder builder = new Hint.Builder();

        var hint = builder.withFormat(new Format()).withResolution("1920x1080").build();
    }
}
