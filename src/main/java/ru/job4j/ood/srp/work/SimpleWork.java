package ru.job4j.ood.srp.work;

public class SimpleWork implements Work {

    @Override
    public void makePresentation() {
        System.out.println("Составить презентацию о планах компании");
    }

    @Override
    public void makeReport() {
        System.out.println("Предоставить отчет о проделанной работе");
    }
}
