package fr.univtln.mcg;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by jlng on 22/11/16.
 */

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = CMaterial.class)
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
public class CMaterial {
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
