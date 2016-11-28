package fr.univtln.mcg;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.univtln.mcg.material.CMaterial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = CActivity.class)
@NamedQuery(name="CActivity.findAll", query="SELECT a FROM CActivity a")
public class CActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ACTIVITY_GEN")
    @Column(name = "ID")
    private int id;

    @ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name="PERSON_ID")
    private CPerson mPerson;
    @ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name="MATERIAL_ID")
    private CMaterial mMaterial;
    @ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name="ROOM_ID")
    private CRoom mRoom;
    private SimpleDateFormat mSdf = new SimpleDateFormat("dd/M/yyyy");


    public static  CActivity.CActivityBuilder builder(CPerson pPerson, CMaterial pMaterial, CRoom pRoom, SimpleDateFormat pSdf) {
        return allBuilder().mPerson(pPerson).mMaterial(pMaterial).mRoom(pRoom).mSdf(pSdf);
    }

}