package fr.univtln.mcg;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.univtln.mcg.enums.ERoomTypes;
import fr.univtln.mcg.material.Material;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by jlng on 22/11/16.
 */

@Entity
@Table(schema = "stock")
@TableGenerator(name="ROOM_GEN",
        table="ID_GEN_ROOM",
        pkColumnName="GEN_KEY",
        valueColumnName="GEN_VALUE",
        pkColumnValue="ROOM_ID",
        allocationSize=1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "nameBuilder")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = Room.class)
@NamedQuery(name="Room.findAll", query="SELECT room FROM Room room")
public class Room {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ROOM_GEN")
    @Column(name = "ID")
    private int id;

    @NotNull
    @Size(min=2, max=5)
    private String mName;

    @NotNull
    private ERoomTypes mType;

    @OneToMany(mappedBy="room", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @NotNull
    @Valid
    @Size(max=50)
    private List<Material> mMateriels;

    public static RoomBuilder builder(String pName, ERoomTypes pType) {
        return nameBuilder().mName(pName).mType(pType);
    }

}
