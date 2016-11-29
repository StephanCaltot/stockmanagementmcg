package fr.univtln.mcg.material;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.univtln.mcg.CRoom;
import fr.univtln.mcg.material.pedagogic.CPedagogic;
import fr.univtln.mcg.material.technologic.CTechnologic;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
@NamedQuery(name="CMaterial.findAll", query="SELECT a FROM CMaterial a")
public abstract class CMaterial {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MATERIAL_GEN")
    @Column(name = "ID")
    private int id;

    @ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name="ROOM_ID")
    @NotNull
    private CRoom mRoom;
}
