package ru.job4j.generics;

public class RoleStore implements Store<Role> {

    MemStore<Role> memStore = new MemStore<>();

    @Override
    public void add(Role model) {
        memStore.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return memStore.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return memStore.delete(id);
    }

    @Override
    public Role findById(String id) {
        return memStore.findById(id);
    }
}
