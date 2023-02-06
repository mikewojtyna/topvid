package pl.wojtyna.tasks.front;

public record ProcessingResult() {

    public static ProcessingResult unauthorized() {
        return null;
    }
}
