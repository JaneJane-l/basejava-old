/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int index = 0;

    void clear() {
        for (int i = 0; i < index; i++) {
            storage[i] = null;
        }
        index = 0;
    }

    void save(Resume r) {
        storage[index] = r;
        index = index + 1;
    }

    Resume get(String uuid) {
        for (int i = 0; i < index; i++) {
            Resume r = storage[i];
            if (r.uuid.equals(uuid)) {
                return r;
            }
        }
        return null;
    }

    void delete(String uuid) {
        int delIndex = 0;
        boolean isDelete = false;
        for (int i = 0; i < index; i++) {
            Resume r = storage[i];
            if (r.uuid.equals(uuid)) {
                storage[i] = null;
                delIndex = i;
                isDelete = true;
            }
        }
        if (isDelete) {
            for (int i = delIndex; i < index; i++) {
                storage[i] = storage[i + 1];
            }
            index = index - 1;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] storageWithoutNull = new Resume[index];
        for (int i = 0; i < index; i++) {
            storageWithoutNull[i] = storage[i];
        }
        return storageWithoutNull;
    }


    int size() {
        return index;
    }
}
