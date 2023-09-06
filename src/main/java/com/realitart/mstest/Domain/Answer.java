package com.realitart.mstest.Domain;

import com.realitart.mstest.share.models.AuditModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Answer")
public class Answer extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "QUESTION_ID", nullable=false)
    private Question questionId;
    private String answer;
    private Boolean isCorrect;
}
