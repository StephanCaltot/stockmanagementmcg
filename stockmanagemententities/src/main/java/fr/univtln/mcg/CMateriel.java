package fr.univtln.mcg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by jlng on 22/11/16.
 */
@Entity
@Table(schema = "stock")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "nameBuilder")
public class CMateriel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String mName;

    @ManyToOne
    @JoinColumn(name="ROOM_ID")
    private CRoom mRoom;

    public static CMaterielBuilder builder(String pName) {
        return nameBuilder().mName(pName);
    }

}
