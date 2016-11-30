package fr.univtln.mcg;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = ActivityLog.class)
@NamedQuery(name="ActivityLog.findAll", query="SELECT a FROM ActivityLog a")
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ACTIVITY_LOG_GEN")
    @Column(name = "ID")
    @NotNull
    private int id;

    @OneToMany(cascade = CascadeType.ALL)
    @Valid
    private List<Activity> mActivityLog;

    public static ActivityLog.ActivityLogBuilder builder(List<Activity> pActivityLog) {
        return nameBuilder().mActivityLog(pActivityLog);
    }
}
