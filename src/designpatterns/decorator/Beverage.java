package designpatterns.decorator;

public abstract class Beverage {
    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}

class Espresso extends Beverage {
    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}

class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "HouseBlend Coffee";
    }

    @Override
    public double cost() {
        return 1.89;
    }
}

class DarkRoast extends Beverage {
    public DarkRoast() {
        description = "DarkRoast Coffee";
    }

    @Override
    public double cost() {
        return 2.11;
    }
}

class Decaf extends Beverage {
    public Decaf() {
        description = "Decaf Coffee";
    }

    @Override
    public double cost() {
        return 2.22;
    }
}

