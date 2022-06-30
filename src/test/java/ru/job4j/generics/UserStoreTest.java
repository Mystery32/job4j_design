package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.hamcrest.core.Is.is;

public class UserStoreTest {

    @Test
    public void whenAddAndFindThenUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        User result = store.findById("1");
        assertThat(result.getUsername(), is("Petr"));
    }

    @Test
    public void whenAddAndFindThenUserIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        User result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.add(new User("1", "Maxim"));
        User result = store.findById("1");
        assertThat(result.getUsername(), is("Petr"));
    }

    @Test
    public void whenReplaceThenUsernameIsMaxim() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.replace("1", new User("1", "Maxim"));
        User result = store.findById("1");
        assertThat(result.getUsername(), is("Maxim"));
    }

    @Test
    public void whenNoReplaceUserThenNoChangeUsername() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.replace("10", new User("10", "Maxim"));
        User result = store.findById("1");
        assertThat(result.getUsername(), is("Petr"));
    }

    @Test
    public void whenDeleteUserThenUserIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.delete("1");
        User result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteUserThenUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.delete("10");
        User result = store.findById("1");
        assertThat(result.getUsername(), is("Petr"));
    }

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
