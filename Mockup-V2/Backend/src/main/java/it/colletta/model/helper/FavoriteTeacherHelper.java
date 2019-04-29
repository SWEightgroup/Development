package it.colletta.model.helper;

import lombok.*;
import java.util.ArrayList;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FavoriteTeacherHelper {
  private ArrayList<String> teacherId;
}
