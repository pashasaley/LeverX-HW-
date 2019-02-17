public class AdultDog extends Dog {
    public AdultDog(int age) {
        super(age);
        this.years = Age.ADULT;
        this.doTask = Train.TRAINED;
        this.ration = Ration.RATION_ADULT;
    }

    @Override
    public void train(){
        System.out.println("The dog is going to the police.");
        this.feed = Eat.HUNGRY;
    }
}