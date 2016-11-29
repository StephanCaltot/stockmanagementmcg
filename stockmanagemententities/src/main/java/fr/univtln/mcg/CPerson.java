package fr.univtln.mcg;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Size;

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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = CPerson.class)
@NamedQuery(name="CPerson.findAll", query="SELECT p FROM CPerson p")
public class CPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PERSON_GEN")
    @Column(name = "ID")
    private int id;

    @Size(min=3, max=15)
    @NotNull
    private String name;

    public static CPerson.CPersonBuilder builder(String pName) {
        return nameBuilder().name(pName);
    }

}
