package fr.univtln.mcg;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.univtln.mcg.material.Material;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by screetts on 28/11/16.
 */
@Entity
@Table(schema = "stock")
@TableGenerator(name="ACTIVITY_GEN", allocationSize=1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "allBuilder")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = Activity.class)
@NamedQuery(name=Activity.GET_ALL, query="SELECT a FROM Activity a")
public class Activity implements Serializable{

    public static final String GET_ALL = "Activity.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ACTIVITY_GEN")
    @Column(name = "ID")
    @NotNull
    private int id;

    @ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name="MATERIAL_ID")
    @NotNull
    private Material material;

    @ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name="ROOM_ID")
    @NotNull
    private Room room;

    @NotNull
    private String date;

    public static  ActivityBuilder builder(Material material, Room room, String date) {
        return allBuilder().material(material).room(room).date(date);
    }

    @Override
    public String toString(){
        return "Activité ( " + this.getId() + " ) -> " + "Salle : " + this.getRoom().getName() + " Materiel : " + this.getMaterial().toString() + " le " + this.getDate();
    }

}