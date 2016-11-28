package fr.univtln.mcg.material.technologic;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.univtln.mcg.enums.EWorkPhoneOs;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = CWorkPhone.class)
@Entity
@Table(schema = "stock")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "osBuilder")
@NamedQuery(name="CWorkPhone.findAll", query="SELECT w FROM CWorkPhone w")
public class CWorkPhone extends CTechnologic {

    private EWorkPhoneOs mOs;
}
