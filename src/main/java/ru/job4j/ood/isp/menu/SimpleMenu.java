package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean result = false;
        if (findItem(parentName).isEmpty()) {
            rootElements.add(new SimpleMenuItem(parentName, actionDelegate));
            result = true;
        }
        if (findItem(childName).isEmpty()) {
            findItem(childName).get().menuItem.getChildren().add(findItem(childName).get().menuItem);
            result = true;
        }
        return result;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<ItemInfo> item = findItem(itemName);
        MenuItemInfo menuItem = new MenuItemInfo(item.get().menuItem, item.get().number);
        return Optional.of(menuItem);
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new Iterator<MenuItemInfo>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public MenuItemInfo next() {
                return null;
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> result = Optional.empty();
        DFSIterator iterator = new DFSIterator();
        ItemInfo item;
        while (iterator.hasNext()) {
            item = iterator.next();
            if (name.equals(item.menuItem.getName())) {
                result = Optional.of(item);
                break;
            }
        }
        return result;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
