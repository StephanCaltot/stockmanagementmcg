package fr.univtln.mcg.material.technologic;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.univtln.mcg.Room;
import fr.univtln.mcg.enums.ETechnologicBrands;
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
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = Computer.class)
@Entity
@Table(schema = "stock")

@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="Computer.findAll", query="SELECT c FROM Computer c")
@JsonDeserialize(as = Computer.class)
public class Computer extends Technologic {

    @NotNull
    private boolean touch;

    @Builder
    public Computer(boolean touch, ETechnologicBrands brand, Room room) {
        super(room, brand);
        this.touch = touch;
    }


}
