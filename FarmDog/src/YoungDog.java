public class YoungDog extends Dog {
    public YoungDog(int age) {
        super(age);
        this.years = Age.YOUNG;
        this.doTask = Train.UNTRAINED;
        this.ration = Ration.RATION_YOUNG;
    }

    @Override
    public void train(){
        System.out.println("The puppy has been trained.");
        this.doTask = Train.TRAINED;
        this.feed = Eat.HUNGRY;
    }
}