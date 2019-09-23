package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {

    private static final Object PRESENT = new Object();

    private transient HashMap<E,Object> map;

    public AmigoSet() {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        this.map = new HashMap<>(Math.max(16, (int)(collection.size()/0.75f+1)));
        for (E e: collection){
            map.put(e, PRESENT);
        }
    }

    @Override
    public boolean add(E e) {
        return null==map.put(e,PRESENT);
    }


    @Override
    public Iterator iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    public AmigoSet<E> clone(){
        try {
            AmigoSet<E> twin = (AmigoSet)super.clone();
            twin.map = (HashMap)this.map.clone();
            return twin;
        }
        catch (Exception error) {
            throw new InternalError(error);
        }
    }


    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        out.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        out.writeInt(map.keySet().size());
        for(E e : map.keySet()) {
            out.writeObject(e);
        }
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int capacity = in.readInt();
        float loadFactor = in.readFloat();
        map = new HashMap(capacity, loadFactor);
        int size = in.readInt();
        for(int i = 0; i<size; i++) {
            map.put((E)in.readObject(), PRESENT);
        }
    }

//    private void writeObjec(ObjectOutputStream output) throws IOException {
//        output.defaultWriteObject();
//        output.writeInt(HashMapReflectionHelper.callHiddenMethod(map,"capacity"));
//        output.writeFloat(HashMapReflectionHelper.callHiddenMethod(map,"loadFactor"));
//        output.writeInt(map.size());
//        for (E el : map.keySet()) {
//            output.writeObject(el);
//        }
//    }
//
//    private void readObjec(ObjectInputStream input) throws ClassNotFoundException, IOException {
//        input.defaultReadObject();
//        // Read capacity and verify non-negative.
//        int capacity = input.readInt();
//        // Read load factor and verify positive and non NaN.
//        float loadFactor = input.readFloat();
//        // Read size and verify non-negative.
//        int size = input.readInt();
//        // Create backing HashMap
//        map = new HashMap<>(capacity, loadFactor);
//        // Read in all elements in the proper order.
//        for (int i=0; i<size; i++) {
//            E e = (E) input.readObject();
//            map.put(e, PRESENT);
//        }
//    }
}
