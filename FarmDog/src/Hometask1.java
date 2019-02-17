public class Hometask1 {
    public static void main (String ... args){
        Dog[] dog = new Dog[10];

        for (int i = 0; i<10; i++){
            int age = (int)(Math.random()*13 + 1);//генерация возраста

            if (age < 4) {
                dog[i] = new YoungDog(age);
            }
            else if (age > 3 && age < 9) {
                dog[i] = new AdultDog(age);
            }
            else {
                dog[i] = new OldDog(age);
            }

            System.out.println("The age of years dog is " + age);
        }

        for (int i = 0; i<10; i++) {//кормежка всех собак
            dog[i].eat();
        }

        for (int i = 0; i<10; i++) {//лечение всех собак
            dog[i].cure();
        }

        AnimalOnFarm.clean();//очистка вольера

        for (int i = 0; i<10; i++) {//собаки идут по своим делам :)
            dog[i].train();
        }

        for (int i = 0; i<10; i++) {//ужин
            dog[i].eat();
        }
    }
}