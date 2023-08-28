package com.realitart.mstest.Domain;

import com.realitart.mstest.share.models.AuditModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Test")
public class Test extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer code;
    private String name;
    private Integer minScoreToAprove;
    private LocalDateTime start;
    private LocalDateTime expiration;
    private Long professorId;
}
