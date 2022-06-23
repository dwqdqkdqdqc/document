package ru.sitronics.tn.document.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("QUALITY_DOCUMENTS")
public class QualityDocument extends Document{




//    private RelatingDocumentsTable documentTable;





}
