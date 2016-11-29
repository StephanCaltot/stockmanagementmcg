package fr.univtln.mcg.material.pedagogic;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.internal.NotNull;
import fr.univtln.mcg.enums.EChalkColors;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = CChalk.class)
@Entity
@Table(schema = "stock")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "colorBuilder")
@NamedQuery(name="CChalk.findAll", query="SELECT c FROM CChalk c")
public class CChalk extends CPedagogic {

    @NotNull
    private EChalkColors mColor;
}
