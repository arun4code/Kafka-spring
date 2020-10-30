package com.tesco.iam.appservice.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.tinkerpop.shaded.jackson.core.JsonProcessingException;
import org.apache.tinkerpop.shaded.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.microsoft.azure.documentdb.ConnectionMode;
import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.PartitionKeyDefinition;
import com.microsoft.azure.documentdb.bulkexecutor.BulkImportResponse;
import com.microsoft.azure.documentdb.bulkexecutor.DocumentBulkExecutor;
import com.tesco.iam.appservice.dto.PeopleDTO;

@Service
public class PeopleGraphServiceImpl implements PeopleGraphService {

	@Autowired
	private DocumentBulkExecutor bulkExecutor;
	
	private String host = "https://iam-097-graphdb.documents.azure.com:443/";

	private String masterKey = "PGrQG82m5CPMwJuYgl4UST7cQjNiizcrHHuVdjsrpyVJYnyHt7YfL28HZwIxsIeieOjIv28rFgVkERCF7Oi9fw==";

	/*
	 * public void bulkUploads(List<PeopleDTO> peopleDTOList) { CosmosClient client
	 * = new CosmosClientBuilder().endpoint(host).key(masterKey) // Setting the
	 * preferred location to Cosmos DB Account region // West US is just an example.
	 * User should set preferred location to the Cosmos // DB region closest to the
	 * application // .preferredRegions(Collections.singletonList("West US"))
	 * .consistencyLevel(ConsistencyLevel.SESSION).buildClient();
	 * 
	 * CosmosDatabaseResponse cosmosDatabaseResponse =
	 * client.createDatabaseIfNotExists("AccessData"); CosmosDatabase database =
	 * client.getDatabase(cosmosDatabaseResponse.getProperties().getId());
	 * 
	 * CosmosContainerProperties containerProperties = new
	 * CosmosContainerProperties("AccessData", "/partition");
	 * 
	 * // Create container with 400 RU/s CosmosContainerResponse
	 * cosmosContainerResponse =
	 * database.createContainerIfNotExists(containerProperties,
	 * ThroughputProperties.createManualThroughput(400)); CosmosContainer container
	 * = database.getContainer(cosmosContainerResponse.getProperties().getId()); //
	 * Create item using container that we created using sync client
	 * 
	 * CosmosItemRequestOptions cosmosItemRequestOptions = new
	 * CosmosItemRequestOptions();
	 * 
	 * peopleDTOList.stream().forEach(item -> { CosmosItemResponse<PeopleDTO> resp =
	 * container.createItem(item, new PartitionKey(item.getLastName()),
	 * cosmosItemRequestOptions); System.out.println(resp); });
	 * 
	 * }
	 */
	@Override
	public void bulkUpload(List<PeopleDTO> peopleDTOList) throws Exception {
		System.out.println("bulkupload size -" + peopleDTOList.size());
		System.out.println("------ first id- " + Thread.currentThread() + " ---- " +  peopleDTOList.get(0).getId());

		List<String> documentList = getJsonString(peopleDTOList);

		
		if(bulkExecutor == null) {
			System.out.println("--------");
		}
//		DocumentBulkExecutor bulkExecutor = build();
		BulkImportResponse bulkImportResponse = bulkExecutor.importAll(documentList, true, true, 100);

		// Validate that all documents inserted to ensure no failure.
		if (bulkImportResponse.getNumberOfDocumentsImported() < peopleDTOList.size()) {
			for (Exception e : bulkImportResponse.getErrors()) {
				// Validate why there were some failures.
				e.printStackTrace();
			}
		}

		//bulkExecutor.close();
		//client.close();

		System.out.println(bulkImportResponse);

	}
	
	@Bean
	public DocumentBulkExecutor build() throws Exception {
		System.out.println("-----build executed---");
		ConnectionPolicy connectionPolicy = new ConnectionPolicy();
		connectionPolicy.setMaxPoolSize(100);
		connectionPolicy.setEnableEndpointDiscovery(true);
		connectionPolicy.setConnectionMode(ConnectionMode.DirectHttps);
		DocumentClient client = new DocumentClient(host, masterKey, connectionPolicy,
				com.microsoft.azure.documentdb.ConsistencyLevel.Eventual);

		client.getConnectionPolicy().getRetryOptions().setMaxRetryWaitTimeInSeconds(30);
		client.getConnectionPolicy().getRetryOptions().setMaxRetryAttemptsOnThrottledRequests(10);

		PartitionKeyDefinition pKey = new PartitionKeyDefinition();
		pKey.set("Key1", "Key1");
		com.microsoft.azure.documentdb.bulkexecutor.DocumentBulkExecutor.Builder bulkExecutorBuilder = DocumentBulkExecutor
				.builder().from(client, "AccessData", "AccessData", pKey, 15000);

		DocumentBulkExecutor bulkExecutor = bulkExecutorBuilder.build();
		return bulkExecutor;
	}

	private List<String> getJsonString(List<PeopleDTO> peopleDTOList) throws Exception {
		List<String> jsonList = new ArrayList<>();
		ObjectMapper objMapper = new ObjectMapper();

		peopleDTOList.stream().forEach(item -> {
			try {
				jsonList.add(objMapper.writeValueAsString(item));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		});
		return jsonList;
	}
}
