package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    public static final String STROKES = "----";
    public static final String SPACE = " ";

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo item: menu) {
            int count = item.getNumber().split("\\.").length - 1;
            System.out.print(STROKES.repeat(count) + item.getName() + SPACE + item.getNumber() + "\n");
        }
    }
}
