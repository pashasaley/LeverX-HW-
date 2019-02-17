public class OldDog extends Dog {
    public OldDog(int age) {
        super(age);
        this.years = Age.OLD;
        this.doTask = Train.TRAINED;
        this.ration = Ration.RATION_OLD;
    }

    @Override
    public void train(){
        System.out.println("The dog is going to rest in the aviary.");
        this.feed = Eat.HUNGRY;
    }
}