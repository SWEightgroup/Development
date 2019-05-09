package it.colletta.model.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
public class FilterHelper {
  // private List<String> roles = new ArrayList<>();
  private ArrayList<String> languages = new ArrayList<>();
  private Long startDate;
  private Long endDate;
  private Integer minReliability;

}
