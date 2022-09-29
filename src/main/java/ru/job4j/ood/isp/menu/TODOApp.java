package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TODOApp {

    public static final ActionDelegate STUB_ACTION = System.out::println;
    public static final int ADD_MAIN_TASK = 1;
    public static final int ADD_SUB_TASK = 2;
    public static final int SHOW_ALL_TASKS = 3;
    public static final int EXIT = 4;
    public static final String MENU_MESSAGE = """
            1. Добавить основную задачу
            2. Добавить подзадачу
            3. Вывести список всех задач
            4. Выход
            """;
    public static final String SELECT = "Выберите пункт меню.";
    public static final String MAIN_TASK_MESSAGE = "Введите наименование основной задачи";
    public static final String SUB_TASK_MESSAGE = "Введите наименование подзадачи";
    private final Menu menu = new SimpleMenu();
    private final Scanner scanner = new Scanner(System.in);
    private final MenuPrinter printer = new SimpleMenuPrinter();

    private void startMenu() {
        boolean run = true;
        while (run) {
            System.out.println(MENU_MESSAGE);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (ADD_MAIN_TASK == userChoice) {
                System.out.println(MAIN_TASK_MESSAGE);
                String text = scanner.nextLine();
                menu.add(Menu.ROOT, text, STUB_ACTION);
            } else if (ADD_SUB_TASK == userChoice) {
                System.out.println(MAIN_TASK_MESSAGE);
                String parent = scanner.nextLine();
                if (menu.select(parent).isEmpty()) {
                    menu.add(Menu.ROOT, parent, STUB_ACTION);
                }
                System.out.println(SUB_TASK_MESSAGE);
                String child = scanner.nextLine();
                menu.add(parent, child, STUB_ACTION);
            } else if (SHOW_ALL_TASKS == userChoice) {
                printer.print(menu);
            } else if (EXIT == userChoice) {
                run = false;
            } else {
                System.out.println("Ввели некорректный пункт меню!");
            }
        }
    }

    public static void main(String[] args) {
        new TODOApp().startMenu();
    }
}
