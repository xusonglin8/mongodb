package com.mkyong.core;

import java.net.UnknownHostException;
import java.util.Date;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * Java + MongoDB Hello world Example
 * 
 */
public class App {
	public static void main(String[] args) {

		try {

			/**** Connect to MongoDB ****/
			// Since 2.10.0, uses MongoClient
			MongoClient mongo = new MongoClient("localhost", 27017);

			/**** Get database ****/
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("testdb");

			/**** Get collection / table from 'testdb' ****/
			// if collection doesn't exists, MongoDB will create it for you
			DBCollection table = db.getCollection("user");

//			/**** Insert ****/
//			// create a document to store key and value
//			BasicDBObject document = new BasicDBObject();
//			document.put("name", "nameTest");
//			document.put("age", 22);
//			document.put("createdDate", new Date());
//			table.insert(document);
//			 for(int i=1;i<10;i++) {
//				   BasicDBObject documents = new BasicDBObject();
//				   documents.put("name", "nameTest"+i);
//				   documents.put("age", i);
//				   documents.put("createdDate", new Date());
//				   table.insert(documents);
//			   }

//			/**** Find and display ****/
//			BasicDBObject searchQuery = new BasicDBObject();
//			searchQuery.put("name", "nameTest");
//
//			DBCursor cursor = table.find(searchQuery);
//
//			while (cursor.hasNext()) {
//				System.out.println(cursor.next());
//			}
			DBObject dbObject = new BasicDBObject();
//			 /**** Find All ****/
//			 DBCursor cursor = table.find(dbObject);
//			 while (cursor.hasNext()) {
//					System.out.println(cursor.next());
//				}
			 /**** Find All With limit skip sort****/
			 DBCursor cursor1 = table.find(dbObject).limit(3).skip(3).sort(new BasicDBObject("age",-1));
			 while (cursor1.hasNext()) {
					System.out.println(cursor1.next());
				}
			 
//			/**** Update ****/
//			// search document where name="mkyong" and update it with new values
//			BasicDBObject query = new BasicDBObject();
//			query.put("name", "nameTest");
//
//			BasicDBObject newDocument = new BasicDBObject();
//			newDocument.put("name", "nameTest-updated");
//
//			BasicDBObject updateObj = new BasicDBObject();
//			updateObj.put("$set", newDocument);
//
//			table.update(query, updateObj);
//
//			/**** Find and display ****/
//			BasicDBObject searchQuery2 
//				= new BasicDBObject().append("name", "nameTest-updated");
//
//			DBCursor cursor2 = table.find(searchQuery2);
//
//			while (cursor2.hasNext()) {
//				System.out.println(cursor2.next());
//			}

//			/**** Çå¿Õ¼¯ºÏ ****/
//			DBObject dbObject = new BasicDBObject();
//			table.remove(dbObject);
			
//			/**** deleteone ****/
//			BasicDBObject query1 = new BasicDBObject("_id",new ObjectId("597314e5c976b243c37bcd15"));
//			BasicDBObject object = (BasicDBObject)table.findOne(query);
//			table.remove(object);
			
			/**** Done ****/
			System.out.println("Done");
			
			

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	
  
	}
}
