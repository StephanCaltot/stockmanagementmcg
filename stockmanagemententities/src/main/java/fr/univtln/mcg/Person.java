package fr.univtln.mcg;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by screetts on 28/11/16.
 */
@Entity
@Table(schema = "stock")
@TableGenerator(name="PERSON_GEN", allocationSize=1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "nameBuilder")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = Person.class)
@NamedQuery(name=Person.GET_ALL, query="SELECT p FROM Person p")
public class Person implements Serializable{

    public static final String GET_ALL = "Person.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PERSON_GEN")
    @Column(name = "ID")
    private int id;

    @Size(min=3, max=15)
    @NotNull
    private String name;

    public static Person.PersonBuilder builder(String name) {
        return nameBuilder().name(name);
    }

}
