package fr.univtln.mcg.material.pedagogic;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.univtln.mcg.material.CMaterial;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by screetts on 28/11/16.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = CPedagogic.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({@JsonSubTypes.Type(value = CChalk.class, name = "CChalk"),
               @JsonSubTypes.Type(value = CBoard.class, name = "CBoard"),
               @JsonSubTypes.Type(value = CArmChair.class, name = "CArmChair"),})
@Entity
@Table(schema = "stock")
@Data
@NoArgsConstructor
@NamedQuery(name="CPedagogic.findAll", query="SELECT p FROM CPedagogic p")
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class CPedagogic extends CMaterial {
}
