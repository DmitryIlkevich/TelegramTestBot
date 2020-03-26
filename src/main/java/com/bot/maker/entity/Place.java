package com.bot.maker.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "places")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "places_seq")
    @SequenceGenerator(name = "places_seq", sequenceName = "places_id_seq", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String place;
    private String description;
}
