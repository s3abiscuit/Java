package designpatterns.observer;

interface Observer {
    void update(float temp, float humidity, float pressure);
}

interface DisplayElement {
    void display();
}

class CurrentConditionsDisplay implements Observer, DisplayElement {

    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay() {
    }

    // 重载构造方法
    public CurrentConditionsDisplay(WeatherData weatherData) {
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    public void display() {
        System.out.println("Current conditions: " + temperature
                + "F degrees and " + humidity + "% humidity");
    }
}

class StatisticsDisplay implements Observer, DisplayElement {

    private float temperature;
    private float humidity;
    private float pressure;

    public StatisticsDisplay(WeatherData weatherData) {
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    public void display() {
        System.out.println("Statistics conditions: " + temperature
                + "F degrees and " + humidity + "% humidity and "
                + pressure + " ");
    }
}
