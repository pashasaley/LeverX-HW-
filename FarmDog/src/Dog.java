abstract class Dog implements AnimalOnFarm {
    private int age;
    protected Age years;
    protected Eat feed;
    protected Train doTask;
    protected Health stateOfHealth;
    protected Ration ration;

    public Dog (int age){
        this.age = age;
        stateOfHealth = AnimalOnFarm.state(); //генерация состояния здоровья собаки
        this.feed = Eat.HUNGRY;
    }

    @Override
    public void eat(){
        if (this.feed == Eat.HUNGRY){
            System.out.println("The dog has been fed, its ration is " + this.ration);
            this.feed = Eat.FULL;
        }
        else {
            System.out.println("The dog doesn't want to eat.");
        }
    }

    @Override
    public void cure() {
        if (this.stateOfHealth == Health.ILL){
            System.out.println("The dog has been cured.");
            this.stateOfHealth = Health.HALE;
        }
        else {
            System.out.println("The dog is healthy.");
        }
    }

    abstract void train();
}