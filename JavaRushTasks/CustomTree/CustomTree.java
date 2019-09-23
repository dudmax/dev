package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Entry<String> root;
    private int size;

    public CustomTree() {
        root = new Entry<>("admin");
    }

    @Override
    public int size() {
        size = 0;
        if (root.leftChild != null) {
            sizeToEntry(root.leftChild);
        }
        if (root.rightChild != null) {
            sizeToEntry(root.rightChild);
        }
        return size;
    }

    public void sizeToEntry(Entry<String> entry) {
        size++;
        if (entry.leftChild != null) {
            sizeToEntry(entry.leftChild);
        }
        if (entry.rightChild != null) {
            sizeToEntry(entry.rightChild);
        }
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(String s) {
        LinkedList<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> temp = queue.remove();
            if (addToEntry(s, temp, queue)) {
                return true;
            }
        }
        return false;
    }

    public boolean addToEntry(String s, Entry<String> parent, LinkedList<Entry<String>> queue) {
        if (parent.leftChild == null) {
            parent.leftChild = new Entry<>(s);
            parent.leftChild.parent = parent;
            return true;
        }
        if (parent.rightChild == null) {
            parent.rightChild = new Entry<>(s);
            parent.rightChild.parent = parent;
            return true;
        }
        queue.add(parent.leftChild);
        queue.add(parent.rightChild);
        return false;
    }


    public String getParent(String s) {
        LinkedList<Entry<String>> queue = new LinkedList<>();
        String result = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> temp = queue.remove();
            if ( (result = getParentToEntry(s, temp, queue)) != null) {
                return result;
            }
        }
        return null;
    }

    public String getParentToEntry(String s, Entry<String> element, LinkedList<Entry<String>> queue) {
        if (element.elementName.equals(s)) {
            return element.parent.elementName;
        }
        if (element.leftChild != null) {
            queue.add(element.leftChild);
        }
        if (element.rightChild != null) {
            queue.add(element.rightChild);
        }
        return null;
    }


    public boolean remove(Object o) {
        if (!(o instanceof String)) {
            throw new UnsupportedOperationException();
        }
        String s = (String) o;
        LinkedList<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> temp = queue.remove();
            if (removeEntry(s, temp, queue)) {
                return true;
            }
        }
        return false;
    }

    public boolean removeEntry(String s, Entry<String> parent, LinkedList<Entry<String>> queue) {
        if (parent.leftChild != null) {
            if (parent.leftChild.elementName.equals(s)) {
                parent.leftChild = null;
                return true;
            }
            queue.add(parent.leftChild);
        }
        if (parent.rightChild != null) {
            if (parent.rightChild.elementName.equals(s)) {
                parent.rightChild = null;
                return true;
            }
            queue.add(parent.rightChild);
        }
        return false;
    }

    static class Entry<T> implements Serializable{
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren|availableToAddRightChildren;
        }
    }
}
