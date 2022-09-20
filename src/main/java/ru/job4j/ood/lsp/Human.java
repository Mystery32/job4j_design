package ru.job4j.ood.lsp;

public class Human {

    protected int age;
    protected double money;

    public Human(int age) {
        this.age = age;
    }

    public Human(double money) {
        this.money = money;
    }

    public void work() {
        if (age < 18) {
            throw new IllegalArgumentException("Не достиг совершеннолетия!");
        }
        System.out.println("Выполнять работу");
    }

    public void buyBentley() {
        if (money < 100500) {
            throw new IllegalArgumentException("Не хватает денежных средств на приобретение!");
        }
        System.out.println("Купить Bentley");
    }

    public void manageEmployees(Human human) {
        if (human.getClass() != President.class) {
            throw new IllegalArgumentException("Данный человек не может руководить!");
        }
        System.out.println("Руководить сотрудниками компании");
    }
}

class President extends Human {

    public President(int age) {
        super(age);
    }

    public President(double money) {
        super(money);
    }

    public void work() {
        if (age < 35) {
            throw new IllegalArgumentException("Не достиг минимально требуемого возраста!");
        }
        System.out.println("Выполнять работу");
    }

    public void buyBentley() {
        System.out.println("Купить Bentley");
    }
}
