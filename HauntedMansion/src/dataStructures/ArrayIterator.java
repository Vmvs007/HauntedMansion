/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 *
 * @author Vitor Santos - 8170312
 */
public class ArrayIterator<T> implements Iterator {

    private int count;   
    private int current;  
    private T[] items;

    public ArrayIterator(T[] collection, int size) {
        items = collection;
        count = size;
        current = 0;
    }


    public boolean hasNext() {
        return (current < count);
    }


    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        current++;
        return items[current - 1];

    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}