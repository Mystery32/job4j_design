package ru.job4j.ood.ocp;

import java.util.List;

public class Pets {

    protected final int paws = 4;
    private String name;

    public Sound makeSound() {
        return new Sound();
    }

    private int numbersOfPets(List<Pets> pets) {
        int result = 0;
        for (Pets p: pets) {
            if (p.getClass() == Dog.class) {
                result++;
            }
        }
        return result;
    }

    private static class Dog extends Pets {

        Dog() {
            System.out.println(makeSound());
        }
    }

    private static class Cat extends Pets {

        Cat() {
            System.out.println(makeSound());
        }
    }

    private static class Fish extends Pets {

        Fish() {
            System.out.println(makeSound());
        }
    }
}
