package com.doit.doitplatform.model;

import com.doit.doitplatform.base.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Category extends BaseEntity<Long> {

    @Column(name = "topic")
    private String topic;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @Column(name = "is_general")
    private Boolean isGeneral;


}
