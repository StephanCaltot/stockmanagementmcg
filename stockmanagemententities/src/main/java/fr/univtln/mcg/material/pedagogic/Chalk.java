package fr.univtln.mcg.material.pedagogic;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.univtln.mcg.Room;
import fr.univtln.mcg.enums.EChalkColors;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = Chalk.class)
@Entity
@Table(schema = "stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name=Chalk.GET_ALL_CHALK, query="SELECT c FROM Chalk c")
public class Chalk extends Educational {

    public static final String GET_ALL_CHALK = "Chalk.findAll";

    @NotNull
    private EChalkColors color;


    @Builder
    public Chalk (EChalkColors color, Room room) {
        super(room);
        this.color = color;
    }

    @Override
    public String toString(){
        return "Craie ( " + this.getId() + " ) " + this.getColor()  ;
    }
}
