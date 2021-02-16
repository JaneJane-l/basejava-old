package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {


//    @Override
    // public void save(Resume r) {
    //    int index = getIndex(r.getUuid());
    //    if (index >= 0) {
    //        System.out.println(" Resume" + r.getUuid() + "already exist");
//        } else if (size >= STORAGE_LIMIT) {
//            System.out.println("Too much!!!");
//        } else {
//            int insertIndex = -(index) - 1;
//
//          System.arraycopy(storage, insertIndex, storage, insertIndex+1, size - insertIndex);
 //         storage[insertIndex] = r;
//            size++;
//        }
//    }

    // @Override
    //public void delete(String uuid) {
     //   int index = getIndex(uuid);
       // if (index < -1) {
         //   System.out.println("Resume" + uuid + "not exist");
        //} else {
          //  System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            //storage[size - 1] = null;
            //size--;
       // }
    //}

    @Override
    protected void fillDeletedElement(int index) {
        int insertIndex = -(index) - 1;
        System.arraycopy(storage, insertIndex, storage, (insertIndex + 1), (size - insertIndex));
    }

    @Override
    protected void insertElement(Resume r, int index) {
        int insertIndex = -(index) - 1;
          System.arraycopy(storage, insertIndex, storage, insertIndex+1, size - insertIndex);
         storage[insertIndex] = r;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
