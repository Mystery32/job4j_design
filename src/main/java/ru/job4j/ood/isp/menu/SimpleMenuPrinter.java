package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    public static final String STROKES = "-";

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo item: menu) {
            String name = item.getName();
            String number = item.getNumber();
            StringBuilder text = new StringBuilder();
            if (number.length() == 2) {
                text.append(name).append(" ").append(number);
            } else {
                text.append(STROKES.repeat(number.length())).append(name).append(" ").append(number);
            }
            System.out.println(text);
        }
    }
}
