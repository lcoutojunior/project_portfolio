package com.agrotech.portfoliomanagementsystem.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberId implements Serializable {
    private Long projectId;
    private Long personId;
}