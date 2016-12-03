package fr.univtln.mcg.material;

import com.fasterxml.jackson.annotation.*;
import fr.univtln.mcg.Room;
import fr.univtln.mcg.material.pedagogic.Educational;
import fr.univtln.mcg.material.technologic.Computer;
import fr.univtln.mcg.material.technologic.OverheadProjector;
import fr.univtln.mcg.material.technologic.Technologic;
import fr.univtln.mcg.material.technologic.WorkPhone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by jlng on 22/11/16.
 */

//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = Material.class)
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
//@JsonSubTypes({@JsonSubTypes.Type(value = Educational.class, name = "Educational"),
//               @JsonSubTypes.Type(value = Technologic.class, name = "Technologic")})
//@Entity
//@Table(schema = "stock")
//@TableGenerator(name="MATERIAL_GEN",
//        table="ID_GEN_MATERIAL",
//        pkColumnName="GEN_KEY",
//        valueColumnName="GEN_VALUE",
//        pkColumnValue="MATERIAL_ID",
//        allocationSize=1)
//
//@Data
//@NoArgsConstructor
//@NamedQuery(name="Material.findAll", query="SELECT a FROM Material a")
//@Inheritance(strategy=InheritanceType.JOINED)
//@DiscriminatorColumn(name = "material_type")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "Material.findAll", query = "select material from Material material")
})
@DiscriminatorColumn(name = "material_type")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id", scope = Material.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "stock")
public abstract class Material implements Serializable{
    @Id
    @NotNull
    @TableGenerator(name = "itemGenerator",allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy= GenerationType.TABLE, generator="materialGenerator")
    @Column(name = "ID")
    private int id;

    @ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name="ROOM_ID")
    @NotNull
    private Room room;

   public Material(Room room)
   {
       this.room = room;
   }
}
