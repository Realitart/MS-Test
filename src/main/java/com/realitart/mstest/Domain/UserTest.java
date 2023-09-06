package com.realitart.mstest.Domain;

import com.realitart.mstest.share.models.AuditModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UserTest")
public class UserTest extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "TEST_ID", nullable=false)
    private Test TestId;
    @ManyToOne
    @JoinColumn(name = "ANSWER_ID", nullable=false)
    private Answer answerId;
    private Integer score;
}
