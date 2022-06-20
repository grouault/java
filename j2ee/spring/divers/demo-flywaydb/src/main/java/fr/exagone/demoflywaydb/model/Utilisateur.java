package fr.exagone.demoflywaydb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

@Entity
@Table
@Getter
@Setter
@SequenceGenerator(name="seq_id", sequenceName = "seq_utilisateur", allocationSize = 1)
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id")
    private Long id;

    private String nom;

    private String prenom;

    private String email;

}
