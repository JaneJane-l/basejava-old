package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

   private boolean isExist(String uuid) {
        for (int i = 0; i < size; i++) {
            Resume r1 = storage[i];
            if (r1.getUuid().equals(uuid)) {
                //System.out.println("Such resume already exists")
                return true;
            }
        }
        return false;
    }

    public void update (Resume r) {
        if (isExist(r.getUuid()) == false) {
            System.out.println("Such resume not exists");
        } else {
            for (int i = 0; i < size; i++) {
                Resume r2 = storage[i];
                if (r2.getUuid().equals(r.getUuid())) {
                    storage[i] = r;
                    return;
                }
            }
        }
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Too much!!!");
            return;
        }
        if (isExist(r.getUuid()) == true) {
            System.out.println("Such resume already exists");
            return;
        }
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        if (isExist(uuid) == false) {
            System.out.println("Such resume not exists");
            return null;

        }
        for (int i = 0; i < size; i++) {
            Resume r = storage[i];
            if (r.getUuid().equals(uuid)) {
                return r;
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (isExist(uuid) == false) {
            System.out.println("Such resume not exists");
            return ;
        }
        int delIndex = -1;
        for (int i = 0; i < size; i++) {
            Resume r = storage[i];
            if (r.getUuid().equals(uuid)) {
                storage[i] = null;
                delIndex = i;
            }
        }
        if (delIndex >= 0) {
            for (int i = delIndex; i < size; i++) {
                storage[i] = storage[i + 1];
            }
            size = size - 1;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] storageWithoutNull = new Resume[size];
        for (int i = 0; i < size; i++) {
            storageWithoutNull[i] = storage[i];
        }
        return storageWithoutNull;
    }


    public int size() {
        return size;
    }
}
