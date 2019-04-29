package it.colletta.model.helper;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
public class FilterHelper {

    private List<String> roles = new ArrayList<>();
    private List<String> languages = new ArrayList<>();
    private Long startDate;
    private Long endDate;
    private Integer minReliability;

}
