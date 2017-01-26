package fr.univtln.mcg.material;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.univtln.mcg.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jlng on 22/11/16.
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = Material.GET_ALL_MATERIALS, query = "select material from Material material")
})
@DiscriminatorColumn(name = "material_type")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id", scope = Material.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "stock")
public abstract class Material implements Serializable{

    public static final String GET_ALL_MATERIALS = "Material.findAll";

    @Id
    @TableGenerator(name = "itemGenerator",allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy= GenerationType.TABLE, generator="materialGenerator")
    @Column(name = "ID")
    private int id;

    @ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name="ROOM_ID")
    private Room room;

    public Material(Room room){
       this.room = room;
    }

}
