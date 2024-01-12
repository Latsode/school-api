package com.softgen.school.api.group.model.dao;

import com.softgen.school.api.group.model.enums.GroupStatus;
import com.softgen.school.api.student.model.enums.StudentStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity(name = "group_table")
@EntityListeners(AuditingEntityListener.class)
public class Group {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String groupName;

    @Column(unique = true)
    private Integer groupNumber;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private GroupStatus status;

    @PrePersist
    public void prePersist() {
        this.status = GroupStatus.ACTIVE;
    }


}