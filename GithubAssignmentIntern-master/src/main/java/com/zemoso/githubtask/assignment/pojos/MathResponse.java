package com.zemoso.githubtask.assignment.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MathResponse {
    private Integer valueInt;
    private Float valueFloat;

    private Long valueLong;
    private Double valueDouble;
}
