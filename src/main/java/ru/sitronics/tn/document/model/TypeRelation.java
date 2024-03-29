package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "type_links")
public class TypeRelation extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeRelationEnum type;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "typeRelation")
    @JsonManagedReference
    @BatchSize(size = 100)
    @OrderBy("serialNumber")
    private List<LinkDocumentsTable> linkedDocuments;

    public enum TypeRelationEnum {
        KIT, //комплект, позволяющий Системе работать с группой документов в связке. стр.67
        SINGLE, //еденичная связь
        AUTO, //Автоматическая - задаётся системой
        MANUAL, //Ручная - задаётся пользователем
        MANDATORY, //Обязательная - При создании документа установленного вида система в обязательном порядке потребует установку преднастроенной связи с другим документом
        OPTIONAL, //Необязательная - Обычный вид связи, наиболее часто используемый в системе.
        AUTOMATIC_FEEDBACK, //Автомат ответная реакция - создавая из документа-источника ссылку на «приемник», система автоматом пропишет связь с предустановленными настройками в обратном порядке: из «приемника» на «источник». Важно отметить, что при удалении любого из этих документов автоматом будут стерты и все имеющиеся связи между ними
        WITHOUT_FEEDBACK, //Без ответной реакции - Привязка документов таким образом не дает возможности посмотреть в карточке «приемника», какие «источники» на него ссылаются
        MAKES_INVALID, //Делает недействующим - Формируется при создании внутренних документов на основании внутренних документов. Обратная связь — Не действует в соответствии.
        BASED_ON_THE_LETTER, //На основании письма - Формируется при создании внутреннего, входящего, исходящего документов, проектов на основании входящего или исходящего письма. Обратная связь — Письмо-основание.
        SENT_THE_DOCUMENT, //Отправило документ - Формируется для исходящего сообщения СВД (Системы внешнего документооборота). Обратная связь — Отправлен сообщением СВД.
        SENT_IN_RESPONSE_TO_A_LETTER, //Отправлен в ответ на письмо - Формируется при создании исходящего документа на основании входящего письма. Обратная связь — Отправлен ответный документ.
        A_RESPONSE_LETTER_WAS_SENT, //Отправлено ответное письмо - Формируется при создании исходящего письма на основании входящего письма или входящего документа. Обратная связь — Письмо отправлено в ответ на.
        THE_ANSWER_WAS_SENT_TO_THE_FORWARDING, //Отправлен ответ переадресовавшему - Формируется при создании исходящего документа, отправленного в качестве ответа переадресовавшему. Обратная связь — Отправлен в ответ на переадресованное.
        ADDRESSING_THE_DOCUMENT, //Переадресация документа - Формируется при создании исходящего документа в качестве переадресации на основании входящего документа. Обратная связь — Переадресован документом.
        CONTAINS_CORRESPONDENCE_BY, //Содержит переписку по - Формируется при создании исходящего документа на основании внутреннего документа, также формируется при создании входящего документа на основа-нии исходящего документа, имеющего связь Содержит переписку по. Обратная связь — Переписка по предмету;
        CONTAINS_A_LINK_TO, //Содержит ссылку на - Универсальная связь устанавливается автоматически программой если для пары документов не настроено однозначной связи.
        SENDING_THE_LETTER, //Пересылка письма - Формируется при пересылке письма. Обратная связь — Переслано письмом;
        REDIRECTION_OF_THE_LETTER, //Перенаправление письма - Формируется при перенаправлении письма. Обратная связь — Перенаправлено письмом;
        THE_LETTER_WAS_SENT_IN_RESPONSE_TO, //Письмо отправлено в ответ на - Формируется при создании исходящего письма на основа-нии входящего документа или письма). Обратная связь — Отправлено ответное письмо и Отправлен ответ (для исходящего письма);
        RECEIVED_IN_RESPONSE_TO, //Получен в ответ на - Формируется при создании входящего документа на основании исхо-дящего документа. Обратная связь — Получен ответ;
        RECEIVED_IN_RESPONSE_TO_A_LETTER, //Получено в ответ на письмо - Формируется при создании входящего письма на основании исходящего письма (то есть вам отвечают на ваше письмо). Обратная связь — Получено ответное письмо;
        RECEIVED_IN_RESPONSE_TO_THE_DOCUMENT, //Получено в ответ на документ - Устанавливается вручную для связи исходящего документа и входящего письма. Обратная связь — Получено ответное письмо.
        CREATED_A_DOCUMENT, //Создало документ - Формируется при создании входящего документа на основании сообщения СВД. Обратная связь — Создан из сообщения СВД.
        IT_IS_A_DUPLICATE_FOR, //Является дубликатом для - Эта связь, используемая для обращений пользователей, описывает ссылку из дубликата (то есть того же самого обращения) на основное обращение. Такая по-требность связана с тем, что пользователь может отправлять обращение сразу в несколько учреждений,
        // но все они перешлют это обращение в учреждение, которое отвечает за данный вопрос.  Также дубликат возникает, когда пользователь еще раз направляет то же самое обращение, не дождавшись истечения срока на подготовку ответа учреждением. Обратная связь — Является основным для.
        IT_IS_REPEATED_FOR //Является повторным для - Связь, используемая для обращений пользователей, описывает ссылку из повторного обращения на первичное. Повторное обращение возникает, когда пользователь не удовлетворен ответом на первичное обращение, либо не получил ответ, либо по первичному обращению не было принято решения.
        // Обычно повторное обращение рассматривается в особом порядке, так как свидетельствует, что вопрос не решен. Обратная связь — Является первичным для.
    }
}
