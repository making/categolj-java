package am.ik.categolj.app.controller;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import am.ik.categolj.app.controller.EntryController;
import am.ik.categolj.app.domain.Entry;
import am.ik.categolj.app.model.form.EntryForm;

public class EntryControllerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSetUpForm() {
        EntryController controller = new EntryController();
        EntryForm form = controller.setUpForm(null);
        assertNotNull(form);
    }

    @Test
    public void testFromForm() {
        EntryForm form = new EntryForm();
        form.setId(100L);
        form.setTitle("hoge");
        form.setContent("aaaaaaaa");
        form.setUpdatedAt(new Date());
        form.setCreatedAt(new Date());
        form.setCategory(Arrays.asList("aa", "bb"));
        Entry entry = EntryController.fromForm(form);

        assertEquals(form.getId(), entry.getId());
        assertEquals(form.getTitle(), entry.getTitle());
        assertEquals(form.getContent(), entry.getContent());
        assertEquals(form.getCreatedAt(), entry.getCreatedAt());
        assertEquals(form.getCategory(), entry.getCategory());
    }

    @Test
    public void testToForm() {
        Entry entry = new Entry();
        entry.setId(100L);
        entry.setTitle("hoge");
        entry.setContent("aaaaaaaa");
        entry.setUpdatedAt(new Date());
        entry.setCreatedAt(new Date());
        entry.setCategory(Arrays.asList("aa", "bb"));
        EntryForm form = EntryController.toForm(entry);

        assertEquals(entry.getId(), form.getId());
        assertEquals(entry.getTitle(), form.getTitle());
        assertEquals(entry.getContent(), form.getContent());
        assertEquals(entry.getCreatedAt(), form.getCreatedAt());
        assertEquals(entry.getCategory(), form.getCategory());
    }

}
