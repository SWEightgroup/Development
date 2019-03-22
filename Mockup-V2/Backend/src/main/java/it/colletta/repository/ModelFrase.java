package it.colletta.repository;

import com.google.cloud.firestore.DocumentReference;
import java.util.ArrayList;

public class ModelFrase {

  public String autore="";
  public String testo="";
  public ArrayList<DocumentReference> soluzioni = new ArrayList<DocumentReference>();
}
