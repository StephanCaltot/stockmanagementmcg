package fr.univtln.mcg.material.technologic;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = OverheadProjector.class)
@Entity
@Table(schema = "stock")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "hdBuilder")
@NamedQuery(name=OverheadProjector.GET_ALL_OVERHEAD_PROJECTOR, query="SELECT o FROM OverheadProjector o")
public class OverheadProjector extends Technologic {

    public static final String GET_ALL_OVERHEAD_PROJECTOR = "OverheadProjector.findAll";

    @NotNull
    private boolean hd;

    @Override
    public String toString() {
        String toString;
        if (this.hd) {
            toString = "Rétroprojecteur ( " + this.getId() + " ) full HD 1080p " + this.getBrand();
        }
        else {
            toString = "Rétroprojecteur ( " + this.getId() + " )" + this.getBrand();
        }

        return toString;
    }

}
