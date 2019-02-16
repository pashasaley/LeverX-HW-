abstract class Dog implements FarmDog {
    private int age;
    Age a;
    Eat e;
    Train t;
    Health h;
    Ration r;

    public Dog (int age){
        this.age = age;
        h = FarmDog.state(); //генерация состояния здоровья собаки
        e = Eat.HUNGRY; //по умолчанию все с утра голодные
    }

    @Override
    public void eat(){
        if (this.e == Eat.HUNGRY){
            System.out.println("The dog has been fed, its ration is " + this.r);
            this.e = Eat.FULL;
        }
        else System.out.println("The dog doesn't want to eat.");
    }

    @Override
    public void cure() {
        if (this.h == Health.ILL){
            System.out.println("The dog has been cured.");
            this.h = Health.HALE;
        }
        else System.out.println("The dog is healthy.");
    }

    abstract void train();
}

class YoungDog extends Dog {
    public YoungDog(int age) {
        super(age);
        this.a = Age.YOUNG;
        this.t = Train.UNTRAINED;
        this.r = Ration.RATION_YOUNG;
    }

    @Override
    public void train(){
        System.out.println("The puppy has been trained.");
        this.t = Train.TRAINED;
        this.e = Eat.HUNGRY;
    }
}

class AdultDog extends Dog {
    public AdultDog(int age) {
        super(age);
        this.a = Age.ADULT;
        this.t = Train.TRAINED;
        this.r = Ration.RATION_ADULT;
    }

    @Override
    public void train(){
        System.out.println("The dog is going to the police.");
        this.e = Eat.HUNGRY;
    }
}

class OldDog extends Dog {
    public OldDog(int age) {
        super(age);
        this.a = Age.OLD;
        this.t = Train.TRAINED;
        this.r = Ration.RATION_OLD;
    }

    @Override
    public void train(){
        System.out.println("The dog is going to rest in the aviary.");
        this.e = Eat.HUNGRY;
    }
}
