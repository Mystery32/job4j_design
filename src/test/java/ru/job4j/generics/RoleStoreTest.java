package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRolenameIsTeacher() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Teacher"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Teacher"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Teacher"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRolenameIsTeacher() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Teacher"));
        store.add(new Role("1", "Doctor"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Teacher"));
    }

    @Test
    public void whenReplaceThenRolenameIsDoctor() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Teacher"));
        store.replace("1", new Role("1", "Doctor"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Doctor"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Teacher"));
        store.replace("10", new Role("10", "Doctor"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Teacher"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Teacher"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRolenameIsTeacher() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Teacher"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Teacher"));
    }
}
