package fr.univtln.mcg.material.technologic;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.univtln.mcg.enums.ETechnologicBrands;
import fr.univtln.mcg.material.CMaterial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created by screetts on 28/11/16.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = CTechnologic.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({@JsonSubTypes.Type(value = CComputer.class, name = "CComputer"),
               @JsonSubTypes.Type(value = COverheadProjector.class, name = "COverheadProjector"),
               @JsonSubTypes.Type(value = CWorkPhone.class, name = "CWorkPhone"),})
@Entity
@Table(schema = "stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "brandBuilder")
@NamedQuery(name="CTechnologic.findAll", query="SELECT t FROM CTechnologic t")
public abstract class CTechnologic extends CMaterial {

    private ETechnologicBrands mBrand;

}