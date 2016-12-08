package fr.univtln.mcg.material.technologic;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;
import fr.univtln.mcg.enums.EWorkPhoneOs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by screetts on 28/11/16.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = WorkPhone.class)
@Entity
@Table(schema = "stock")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "osBuilder")
@NamedQuery(name=WorkPhone.GET_ALL, query="SELECT w FROM WorkPhone w")
public class WorkPhone extends Technologic {

    public static final String GET_ALL = "WorkPhone.findAll";

    @NotNull
    private EWorkPhoneOs os;
}
