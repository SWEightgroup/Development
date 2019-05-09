package it.colletta.model.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FavoriteTeacherHelper {
  private List<String> teacherId;
}
