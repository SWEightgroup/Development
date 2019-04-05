package it.colletta.repository.phrase;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.UpdateResult;

import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;

public class PhraseRepositoryImpl implements PhraseCustomQueryInterface {


	private MongoTemplate mongoTemplate;

	@Autowired
	public PhraseRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<PhraseModel> findAllByAuthor(String authorId) {
		Query query = new Query(Criteria.where("solutions.authorId").is(authorId));
		final List<PhraseModel> phraseModels = mongoTemplate.find(query, PhraseModel.class);
		//TODO eliminare informazioni aggiuntive
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
		Query query = new Query(Criteria.where("solutions.solutionText").is(solutionModel.getSolutionText()));
		Update update = new Update().inc("solutions.$.reliability", 1);
		UpdateResult updateResult = mongoTemplate.updateMulti(query,update, PhraseModel.class);
		return updateResult;
	}


	// aggregate([{$match:{"_id":ObjectId("5ca7bcae83406d3d1c5bfb58")}},{$unwind: "$solutions"},{$match:{"solutions._id" : ObjectId("5ca7bcae83406d3d1c5bfb57")}}, { "$project" : { "solutions": 1, "_id": 0 }}

	@Override
	public PhraseModel getSolution(String phraseId, String solutionId) {
		Aggregation agg = newAggregation(
				match(Criteria.where("_id").is(phraseId))
				//unwind("$solutions"),
				//match(Criteria.where("solutions._id").is(new ObjectId(solutionId))),
				//project("solutionText").and("_id").previousOperation()
				);
		
		/*Aggregation agg = newAggregation(
				match(Criteria.where("_id").is(phraseId)),
				unwind("$solutions"),
				match(Criteria.where("solutions.pippo").is(solutionId))					
				);
		//Convert the aggregation result into a List*/
		AggregationResults<PhraseModel> phraseModel =
				mongoTemplate.aggregate(agg,"phrases", PhraseModel.class);
		return phraseModel.getUniqueMappedResult();


	}

}



