package fr.univtln.mcg.material.pedagogic;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.univtln.mcg.Room;
import fr.univtln.mcg.enums.ETechnologicBrands;
import fr.univtln.mcg.material.Material;
import fr.univtln.mcg.material.technologic.Technologic;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by screetts on 28/11/16.
 */
@Entity
@Table(schema = "stock")
@Data
@NoArgsConstructor
@NamedQuery(name=Educational.GET_ALL, query="SELECT e FROM Educational e")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property = "@id" ,scope = Educational.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")

public abstract class Educational extends Material {

    public static final String GET_ALL = "Educational.findAll";
    public Educational(Room room)
    {
        super(room);
    }
}
