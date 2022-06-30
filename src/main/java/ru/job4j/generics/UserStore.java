package ru.job4j.generics;

public class UserStore implements Store<User> {

    MemStore<User> memStore = new MemStore<>();

    @Override
    public void add(User model) {
        memStore.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return memStore.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return memStore.delete(id);
    }

    @Override
    public User findById(String id) {
        return memStore.findById(id);
    }
}
