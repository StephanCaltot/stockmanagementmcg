package fr.univtln.mcg;

import java.util.List;

/**
 * Created by marti on 28/11/2016.
 */

public interface IGenericManager<T> {
    T create(T pT);
    T update(T pT);
    T find(int pId);
    void delete(int pId);
    List<T> findAll();
}
