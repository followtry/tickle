package cn.followtry.mongo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用mongo的java驱动直接访问mongo
 * Created by followtry on 2017/4/26. ${END}
 */
public class core {

  private static final Logger LOGGER_MAIL= LoggerFactory.getLogger("AsyncMailerLogger");

  /** main. */
  public static void main(String[] args) {

    System.out.println("开始");
    //连接mongo
    MongoClient mongoClient = new MongoClient("172.20.9.41");

    //从mongo中获取数据库，不存在则新建
    MongoDatabase database = mongoClient.getDatabase("esn-palmyy");

    //从数据库获取集合，不存在则创建
    MongoCollection<Document> logInfo = database.getCollection("log_info");

    //构造集合内文档
    Document document = new Document("thread_name","http-apr-8080-exec-2");
    document.append("class_name","com.yonyou.esn.palmyy.controller.PalmyyController");
    document.append("method_name","getSyncCorpData");
    document.append("begin_name",new Date());
    document.append("logger_level","ERROR");
    LoggerBody loggerBody = LoggerBody.getObj().setMsg("测试mongodb存储对象能力").setDetailInfo("java.lang.NullPointerException");
    Document document1 = new Document(Document.parse(loggerBody.toString()));
    document.append("logger_message",document1);

    //将文档存储进集合
//    logInfo.insertOne(document);

    //查询文档
    Document filter = new Document();
    String conditionValue = "";
    Pattern pattern = buildVagueMatchEntirely(conditionValue);
    //去doc内doc的属性
    //{ "_id" : { "$oid" : "590000c288e84413dc67fcc2" }, "thread_name" : "http-apr-8080-exec-2", "class_name" : "com.yonyou.esn.palmyy.controller.PalmyyController", "method_name" : "getSyncCorpData", "begin_name" : { "$date" : 1493172417907 }, "logger_level" : "ERROR", "logger_message" : { "msg" : "测试mongodb存储对象能力", "stackInfo" : "java.lang.NullPointerException" } }
    filter.append("level",pattern);
    FindIterable<Document> documents = logInfo.find(filter);
    List<JSONObject> jsonObjects = new ArrayList<>();
    for (Document doc : documents) {
      JSONObject message = JSON.parseObject(doc.toJson());
      jsonObjects.add(message);
    }
    System.out.println(JSON.toJSONString(jsonObjects));
//    LOGGER_MAIL.error(JSON.toJSONString(jsonObjects));

    //删除文档
    Document doc = new Document("level",buildVagueMatchEntirely(""));
    DeleteResult deleteResult = logInfo.deleteMany(doc);
    System.out.println("count:"+deleteResult.getDeletedCount());
    System.out.println("结束");
  }

  /**
   * 构造完全模糊匹配的条件
   * @param conditionValue 模糊匹配的条件值
   * @return 构造模糊匹配的正则表达式
   */
  private static Pattern buildVagueMatchEntirely(String conditionValue) {
    return Pattern.compile("^.*"+conditionValue+".*$", Pattern.CASE_INSENSITIVE);
  }

//  /** main. */
//  public static void main(String[] args) {
//    MongoDbFactory mongoDbFactory = new MongoDbFactory() {
//      @Override
//      public DB getDb() throws DataAccessException {
//        MongoClient client = new MongoClient("172.20.9.41",27017);
//        return new DB(client,"esn-palmyy");
//      }
//
//      @Override
//      public DB getDb(String s) throws DataAccessException {
//        MongoClient client = new MongoClient("172.20.9.41",27017);
//        return new DB(client,"esn-palmyy");
//      }
//
//      @Override
//      public PersistenceExceptionTranslator getExceptionTranslator() {
//        return null;
//      }
//    };
//    MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
//    List<LogInfo> all = mongoTemplate.findAll()
//    String s = JSONObject.toJSONString(all);
//    System.out.println(s);
//  }
}
