package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("CONTRACT")
public class Contract extends Document {
    //  @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date_of_signing"/*, updatable = false*/)
    private LocalDateTime dateOfSigning;

   // @NotNull
    @Column(name = "document_registration_number"/*, updatable = false*/)
    private String documentRegistrationNumber;

  //  @NotNull
    @OneToOne
    @JoinColumn(/*updatable = false*/)
    private NciOst nciOst;

  /*  @OneToMany(mappedBy = "contract")
    private List<Document> documents;*/

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "specification_id")
    private Specification specification;
}
