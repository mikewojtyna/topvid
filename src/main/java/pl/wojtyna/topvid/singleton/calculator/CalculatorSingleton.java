package pl.wojtyna.topvid.singleton.calculator;

class CalculatorSingleton {

    private static final Object guard = new Object();
    private static CalculatorSingleton instance;
    private final SomeVeryComplexExternalService externalService;

    private CalculatorSingleton(SomeVeryComplexExternalService externalService) {
        this.externalService = externalService;
    }

    static CalculatorSingleton getInstance() {
        if (instance != null) {
            return instance;
        }
        else {
            synchronized (guard) {
                if (instance == null) {
                    instance =
                        new CalculatorSingleton(createExternalService());
                }
                return instance;
            }
        }
    }

    private static SomeVeryComplexExternalService createExternalService() {
        return (x, y) -> x + y;
    }

    int add(int x, int y) {
        return externalService.add(x, y);
    }
}
