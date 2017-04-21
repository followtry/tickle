package cn.followtry.rabbitmq.core;

/**.
 * @author jingzz
 */
public interface BaseConfig {

  String QUEUE = "my-test";
  String EXCHANGE = "jingzz_test";
  String ROUTINGKEY = "jingzz";
  String HOST = "localhost";
  String VIRTUALHOST = "jingzz-rabbitmq";

  Boolean DURABLE = false;

  String ADMINPWD = "jingzz";
  String ADMIN = "jingzz";
  int PORT = 5672;
}
