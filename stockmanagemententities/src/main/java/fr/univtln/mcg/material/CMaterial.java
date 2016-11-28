package fr.univtln.mcg.material;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.univtln.mcg.CRoom;
import fr.univtln.mcg.material.pedagogic.CPedagogic;
import fr.univtln.mcg.material.technologic.CComputer;
import fr.univtln.mcg.material.technologic.COverheadProjector;
import fr.univtln.mcg.material.technologic.CTechnologic;
import fr.univtln.mcg.material.technologic.CWorkPhone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by jlng on 22/11/16.
 */

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = CMaterial.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({@JsonSubTypes.Type(value = CPedagogic.class, name = "CPedagogic"),
               @JsonSubTypes.Type(value = CTechnologic.class, name = "CTechnologic")})
@Entity
@Table(schema = "stock")
@TableGenerator(name="MATERIAL_GEN",
        table="ID_GEN_MATERIAL",
        pkColumnName="GEN_KEY",
        valueColumnName="GEN_VALUE",
        pkColumnValue="MATERIAL_ID",
        allocationSize=1)

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "nameBuilder")
@NamedQuery(name="CMaterial.findAll", query="SELECT a FROM CMaterial a")
public abstract class CMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MATERIAL_GEN")
    @Column(name = "ID")
    private int id;
    private String mName;

    @ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name="ROOM_ID")
    private CRoom mRoom;

    public static CMaterialBuilder builder(String pName) {
        return nameBuilder().mName(pName);
    }

}
