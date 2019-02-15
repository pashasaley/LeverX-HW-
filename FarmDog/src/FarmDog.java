interface FarmDog {
    public enum Age {YOUNG, ADULT, OLD};
    public enum Health {HALE, ILL};
    public enum Train {TRAINED, UNTRAINED};
    public enum Eat {FULL, HUNGRY};
    public enum Ration {RATION_YOUNG, RATION_ADULT, RATION_OLD};

    void eat();
    void cure();

    static void clean() {
        System.out.println("The aviary has been cleaned.");
    }

    static Health state(){
        double s = Math.random();
        if ((int)Math.round(s) == 1)
            return Health.HALE;
        else return Health.ILL;
    }

}
