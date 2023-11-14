package tn.esprit.devops_project.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ActivitySectorDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idSecteurActivite;
    private String codeSecteurActivite;
    private String libelleSecteurActivite;


    // Constructors, getters, setters...

}
