package fr.univtln.mcg.business;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by marti on 28/11/2016.
 */

@Local
public interface IGenericManager<T> {
    T create(T pT);
    T update(T pT);

}
