/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructures;

import interfaces.OrderedListADT;

public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    public ArrayOrderedList() {
        super();
    }

    public ArrayOrderedList(int inicialCapacity) {
        super(inicialCapacity);
    }

    @Override
    public void add(T element) {
        Comparable<T> temp = (Comparable<T>) element;

        int scan = 0;
        while (scan < this.size() && temp.compareTo(list[scan]) < 0) {
            scan++;
        }

        for (int scan2 = this.size(); scan2 > scan; scan2--) {
            list[scan2] = list[scan2 - 1];
        }

        list[scan] = element;
        this.count++;
    }
}
