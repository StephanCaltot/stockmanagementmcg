package fr.univtln.mcg.material.technologic;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.univtln.mcg.CRoom;
import fr.univtln.mcg.enums.ERoomTypes;
import fr.univtln.mcg.enums.ETechnologicBrands;
import fr.univtln.mcg.material.CMaterial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Created by screetts on 28/11/16.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = CComputer.class)
@Entity
@Table(schema = "stock")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "touchBuilder")
@NamedQuery(name="CComputer.findAll", query="SELECT c FROM CComputer c")
public class CComputer extends CTechnologic {

    private boolean mTouch;


}
