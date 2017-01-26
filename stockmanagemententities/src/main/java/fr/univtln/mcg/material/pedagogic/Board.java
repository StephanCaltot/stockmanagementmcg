package fr.univtln.mcg.material.pedagogic;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.univtln.mcg.enums.EBoardTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by screetts on 28/11/16.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id",scope = Board.class)
@Entity
@Table(schema = "stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "typeBuilder")
@NamedQuery(name=Board.GET_ALL_BOARD, query="SELECT b FROM Board b")
public class Board extends Educational {

    public static final String GET_ALL_BOARD = "Board.findAll";

    @Min(70)
    @Max(100)
    @NotNull
    private int size;

    @NotNull
    private EBoardTypes type;

    @Override
    public String toString() {
        return "Tableau ( " + this.getId() + " ) de taille " + this.getSize();
    }
}
