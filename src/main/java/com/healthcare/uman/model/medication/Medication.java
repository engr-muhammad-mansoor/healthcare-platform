package com.healthcare.uman.model.medication;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Medication implements Serializable {

    private String name;

    private String frequency;

    private boolean live;

}
