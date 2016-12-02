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
import java.text.SimpleDateFormat;

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
@NamedQuery(name="Activity.findAll", query="SELECT a FROM Activity a")
public class Activity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ACTIVITY_GEN")
    @Column(name = "ID")
    @NotNull
    private int id;

    @ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name="PERSON_ID")
    @NotNull
    private Person mPerson;

    @ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name="MATERIAL_ID")
    @NotNull
    private Material mMaterial;

    @ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name="ROOM_ID")
    @NotNull
    private Room mRoom;

//    @Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}")
    private SimpleDateFormat mSdf = new SimpleDateFormat("dd/M/yyyy");


    public static  Activity.ActivityBuilder builder(Person pPerson, Material pMaterial, Room pRoom, SimpleDateFormat pSdf) {
        return allBuilder().mPerson(pPerson).mMaterial(pMaterial).mRoom(pRoom).mSdf(pSdf);
    }

}