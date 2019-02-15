public class HT1 {
    public static void main (String ... args){
        Dog[] dog = new Dog[10];

        for (int i = 0; i<10; i++){
            int age = (int)(Math.random()*13 + 1);

            if (age < 4)
                dog[i] = new YoungDog(age);
            else if (age > 3 && age < 9)
                dog[i] = new AdultDog(age);
            else dog[i] = new OldDog(age);

            System.out.println("The age of a dog is " + age);
        }

        for (int i = 0; i<10; i++)
            dog[i].eat();

        for (int i = 0; i<10; i++)
            dog[i].cure();

        FarmDog.clean();

        for (int i = 0; i<10; i++)
            dog[i].train();

        for (int i = 0; i<10; i++)
            dog[i].eat();
    }
}