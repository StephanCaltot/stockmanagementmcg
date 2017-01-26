package fr.univtln.mcg;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.univtln.mcg.enums.ERoomTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

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
@NamedQuery(name=Room.GET_ALL, query="SELECT room FROM Room room")
public class Room implements Serializable{

    public static final String GET_ALL = "Room.findAll";

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ROOM_GEN")
    @Column(name = "ID")
    private int id;

    @NotNull
    @Size(min=2, max=5)
    private String name;

    @NotNull
    private ERoomTypes type;

    public static RoomBuilder builder(String name, ERoomTypes type) {
        return nameBuilder().name(name).type(type);
    }


    @Override
    public String toString() {
        String toString;
        if (this.name.equals(ERoomTypes.AMPHI.toString())) {
            toString = "Salle d' " + this.getType() + " " + getName();
        }
        else {
            toString = "Salle de " + this.getType() + " " + getName();
        }
        return toString;
    }

}
