package it.colletta.repository.phrase;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import com.mongodb.client.result.UpdateResult;

import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class PhraseRepositoryImpl implements PhraseCustomQueryInterface {

  private MongoTemplate mongoTemplate;

  /**
   * 
   * @param mongoTemplate
   */
  @Autowired
  public PhraseRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public List<PhraseModel> findAllByAuthor(String authorId) {
    Query query = new Query(Criteria.where("solutions.authorId").is(authorId));
    final List<PhraseModel> phraseModels = mongoTemplate.find(query, PhraseModel.class);
    // TODO eliminare informazioni aggiuntive
    return phraseModels;
  }

  @Override
  public List<SolutionModel> findAllSolutionsByAuthor(String authorId) {
    Query query = new Query(Criteria.where("solutions.authorId").is(authorId));
    query.fields().include("solutions");
    return mongoTemplate.find(query, SolutionModel.class);
  }

  @Override
  public UpdateResult increaseReliability(SolutionModel solutionModel) {
    Query query =
        new Query(Criteria.where("solutions.solutionText").is(solutionModel.getSolutionText()));
    Update update = new Update().inc("solutions.$.reliability", 1);
    UpdateResult updateResult = mongoTemplate.updateMulti(query, update, PhraseModel.class);
    return updateResult;
  }

  // aggregate([{$match:{"_id":ObjectId("5ca7bcae83406d3d1c5bfb58")}},{$unwind:
  // "$solutions"},{$match:{"solutions._id" : ObjectId("5ca7bcae83406d3d1c5bfb57")}}, { "$project" :
  // { "solutions": 1, "_id": 0 }}

  @Override
  public SolutionModel getSolution(String phraseId, String solutionId) {
    Aggregation aggregation =
        Aggregation.newAggregation(
            match(Criteria.where("_id").is(new ObjectId(phraseId))),
            unwind("$solutions"),
            match(Criteria.where("solutions._id").is(new ObjectId(solutionId))));
    AggregationResults<Document> doc =
        mongoTemplate.aggregate(aggregation, "phrases", Document.class);
    Document obj = doc.getUniqueMappedResult().get("solutions", Document.class);
    return SolutionModel.builder()
        .id(obj.getObjectId("_id").toHexString())
        .solutionText(obj.getString("solutionText"))
        .authorId(obj.getString("authorId"))
        .reliability(obj.getInteger("reliability"))
        .dateSolution(obj.getLong("dateSolution"))
        .build();
  }
}
