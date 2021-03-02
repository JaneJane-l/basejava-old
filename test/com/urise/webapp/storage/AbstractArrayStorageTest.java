package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExitStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest  {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void get() {
        Resume r1 = storage.get(UUID_1);
        Assert.assertNotNull(r1);
        Assert.assertEquals(UUID_1, r1.getUuid());
        Resume r2 = storage.get(UUID_2);
        Assert.assertNotNull(r2);
        Assert.assertEquals(UUID_2, r2.getUuid());
        Resume r3 = storage.get(UUID_3);
        Assert.assertNotNull(r3);
        Assert.assertEquals(UUID_3, r3.getUuid());
    }

    @Test
    public void update() {
        Resume r1 = new Resume(UUID_1);
        storage.update(r1);
        Assert.assertSame(r1, storage.get(UUID_1));
    }

    @Test
    public void save() {
        final String uuid4 = "uuid4";
        Resume r4 = new Resume(uuid4);
        storage.save(r4);
        Assert.assertSame(storage.get(uuid4), r4);
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        Resume r2 = storage.get(UUID_2);
        Assert.assertNotNull(r2);
        Assert.assertEquals(UUID_2, r2.getUuid());
        Resume r3 = storage.get(UUID_3);
        Assert.assertNotNull(r3);
        Assert.assertEquals(UUID_3, r3.getUuid());
        Assert.assertEquals(2, storage.size());

    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(3, resumes.length);
        Resume[] target = new Resume[3];
        target[0] = new Resume(UUID_1);
        target[1] = new Resume(UUID_2);
        target[2] = new Resume(UUID_3);
        Assert.assertArrayEquals(target, resumes);
    }

    @Test(expected = NotExitStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = StorageException.class)
    public void overflow() {
        try {
            for ( int i = 4; i < 10_001; i++){
                storage.save(new Resume("uuid"+ i));
            }
        } catch (StorageException e) {
            Assert.fail(e.getMessage());
        }
        storage.save(new Resume("uuidfail"));



    }

}