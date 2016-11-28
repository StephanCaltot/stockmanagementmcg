package fr.univtln.mcg;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

/**
 * Created by screetts on 28/11/16.
 */
@Entity
@Table(schema = "stock")
@TableGenerator(name="ACTIVITY_LOG_GEN", allocationSize=1)

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "nameBuilder")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = CActivityLog.class)
@NamedQuery(name="CActivityLog.findAll", query="SELECT a FROM CActivityLog a")
public class CActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ACTIVITY_LOG_GEN")
    @Column(name = "ID")
    private int id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CActivity> mActivityLog;

    public static CActivityLog.CActivityLogBuilder builder(List<CActivity> pActivityLog) {
        return nameBuilder().mActivityLog(pActivityLog);
    }
}
