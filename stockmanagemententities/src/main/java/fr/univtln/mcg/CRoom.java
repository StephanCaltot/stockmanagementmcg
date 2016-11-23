package fr.univtln.mcg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jlng on 22/11/16.
 */

@Entity
@Table(schema = "stock")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "nameBuilder")
public class CRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String mName;

    @OneToMany(mappedBy="mRoom")
    private List<CMateriel> mMateriels;

    public static CRoomBuilder builder(String pName) {
        return nameBuilder().mName(pName);
    }

}
