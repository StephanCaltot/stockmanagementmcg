package fr.univtln.mcg.material.pedagogic;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.univtln.mcg.enums.EArmChairTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by screetts on 28/11/16.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = ArmChair.class)
@Entity
@Table(schema = "stock")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "typeBuilder")
@NamedQuery(name=ArmChair.GET_ALL_ARMCHAIR, query="SELECT a FROM ArmChair a")
public class ArmChair extends Educational {

    public static final String GET_ALL_ARMCHAIR = "ArmChair.findAll";

    @NotNull
    private EArmChairTypes type;

    @Override
    public String toString() {
        return this.getType() + " ( " + this.getId() + " )" ;
    }

}
