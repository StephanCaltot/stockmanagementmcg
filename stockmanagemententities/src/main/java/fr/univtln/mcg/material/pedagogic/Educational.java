package fr.univtln.mcg.material.pedagogic;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.univtln.mcg.material.Material;
import fr.univtln.mcg.material.technologic.Technologic;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by screetts on 28/11/16.
 */
//@JsonSubTypes({@JsonSubTypes.Type(value = Chalk.class, name = "Chalk"),
//               @JsonSubTypes.Type(value = Board.class, name = "Board"),
//               @JsonSubTypes.Type(value = ArmChair.class, name = "ArmChair"),})
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@Entity
@Table(schema = "stock")
@Data
@NoArgsConstructor
@NamedQuery(name="Educational.findAll", query="SELECT e FROM Educational e")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property = "@id" ,scope = Educational.class)
public abstract class Educational extends Material {
}
