package it.colletta.model.helper;

import lombok.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FavoriteTeacherHelper {
  private List<String> teacherId;
}
