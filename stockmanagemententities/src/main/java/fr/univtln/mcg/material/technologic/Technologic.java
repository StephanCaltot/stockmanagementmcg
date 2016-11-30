package fr.univtln.mcg.material.technologic;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.univtln.mcg.enums.ETechnologicBrands;
import fr.univtln.mcg.material.Material;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by screetts on 28/11/16.
 */
//@JsonSubTypes({@JsonSubTypes.Type(value = Computer.class, name = "Computer"),
//               @JsonSubTypes.Type(value = OverheadProjector.class, name = "OverheadProjector"),
//               @JsonSubTypes.Type(value = WorkPhone.class, name = "WorkPhone"),})
@Entity
@Table(schema = "stock")
@Data
@NoArgsConstructor
@NamedQuery(name = "Technologic.findAll", query = "select objet from Technologic objet")
@DiscriminatorValue(value = "technologic")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property = "@id" ,scope = Technologic.class)
public abstract class Technologic extends Material {

    @NotNull
    private ETechnologicBrands mBrand;

}