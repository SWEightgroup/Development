package it.colletta.repository.phrase;

import com.mongodb.client.FindIterable;
import com.mongodb.client.result.UpdateResult;
import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.model.helper.FilterHelper;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Filters.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class PhraseRepositoryImpl implements PhraseCustomQueryInterface {

    private MongoTemplate mongoTemplate;

    /**
     *
     */
    @Autowired
    public PhraseRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<PhraseModel> findAllByAuthor(final String authorId) {
        Query query = new Query(Criteria.where("solutions.authorId").is(authorId));
        final List<PhraseModel> phraseModels = mongoTemplate.find(query, PhraseModel.class);
        // TODO eliminare informazioni aggiuntive
        return phraseModels;
    }

    @Override
    public List<SolutionModel> findAllSolutionsByAuthor(final String authorId) {
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

  /*@Override
  public SolutionModel getSolution(final String phraseId, String solutionId) {
    Aggregation aggregation = Aggregation.newAggregation(
        match(Criteria.where("_id").is(new ObjectId(phraseId))), unwind("$solutions"),
        match(Criteria.where("solutions._id").is(new ObjectId(solutionId))));
    AggregationResults<Document> doc =
        mongoTemplate.aggregate(aggregation, "phrases", Document.class);
    Document obj = doc.getUniqueMappedResult().get("solutions", Document.class);
    return SolutionModel.builder().id(obj.getObjectId("_id").toHexString())
        .solutionText(obj.getString("solutionText")).authorId(obj.getString("authorId"))
        .reliability(obj.getInteger("reliability")).dateSolution(obj.getLong("dateSolution"))
        .build();
  }*/

    @Override
    public SolutionModel getSolution(final String phraseId, String solutionId) {
        Aggregation aggregation = Aggregation.newAggregation(
                match(Criteria.where("_id").is(new ObjectId(phraseId))), unwind("$solutions"),
                match(Criteria.where("solutions._id").in(new ObjectId(solutionId))),
                limit(1));
        AggregationResults<Document> doc =
                mongoTemplate.aggregate(aggregation, "phrases", Document.class);
        Document map = Objects.requireNonNull(doc.getUniqueMappedResult());
        Document obj = map.get("solutions", Document.class);
        final SolutionModel build = SolutionModel.builder().id(obj.getObjectId("_id").toHexString())
                .solutionText(obj.getString("solutionText")).authorId(obj.getString("authorId"))
                .reliability(obj.getInteger("reliability")).dateSolution(obj.getLong("dateSolution"))
                .build();
        return build;
    }

    @Override
    public FindIterable<Document> findAllPhrasesAsIterable() {
        return mongoTemplate.getCollection("phrases").find();
    }

    @Override
    public FindIterable<Document> findAllPhrasesWithFilter(FilterHelper filterHelper) {

        /*
        Query query = new Query();

        //filter by language
        query.addCriteria(Criteria.where("language").in(filter.getLanguages()));
        //filter by date
        query.addCriteria(Criteria.where("solutions.dateSolution").gte(filter.getStartDate()).lte(filter.getEndDate()));
        //filter by reliability
        query.addCriteria(Criteria.where("solutions.reliability").gte(filter.getMinReliability()));
        */

        Bson filter = and(
                in("language", filterHelper.getLanguages()),
                gte("solutions.dateSolution", filterHelper.getStartDate()),
                lte("solutions.dateSolution", filterHelper.getEndDate()),
                gte("solutions.reliability", filterHelper.getMinReliability())
        );

        return mongoTemplate.getCollection("phrases").find(filter);
    }

}
