package cn.followtry.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 集合的流式用法.
 *
 * <p>Created by followtry on 2017/4/10.
 */
public class CollectionStream {
  private static List<User> users;

  public static void main(String[] args) {

    users = getUserList();

    getFirstHobby("music3");

    getAllHobby("java");

    getClassifyHobby();

    getDissHobbies();

    getOlderThan(233);

    groupBySex();

    groupBySexList();

    getAgeSumGroupBySex();

    getAverAgeGroupBySex();

    getAverAgeAll();
    
    getAllUserId();
  }
  
  /**
   * 获取所有人的userId
   */
  private static void getAllUserId() {
    System.out.println("获取所有人的userId");
    List<Integer> list = new ArrayList<>(users.size());
    for (User user : users) {
      list.add(user.getId());
    }
    System.out.println("原方式：" + list);
    System.out.println("===============================");
    List<Integer> collect = users.stream().map(User::getId).collect(Collectors.toList());
    List<Integer> collect2 = users.stream().map(user -> user.getId()).collect(Collectors.toList());
    System.out.println("新方式：" + collect);
    System.out.println("新方式：" + collect2);
  }

  /**
   * 对所有人计算平均年龄
   */
  private static void getAverAgeAll() {
    System.out.println("对所有人计算平均年龄");
    int size = users.size();
    int sum = 0;
    for (User user : users) {
      sum += user.getAge();
    }
    double average = Double.valueOf(sum) / size;
    System.out.println("原方式：" + average);
    System.out.println("===============================");
    double asDouble = users.parallelStream().mapToInt(User::getAge).average().getAsDouble();
    System.out.println("新方式：" + asDouble);

  }

  /**
   * 按性别分别计算平均年龄
   */
  private static void getAverAgeGroupBySex() {
    Map<Object, Integer> map = new HashMap<>();
    for (User user : users) {
      Integer oldAgeSum = map.get(user.getSex());
      map.put(user.getSex(),oldAgeSum == null ? user.getAge() : oldAgeSum + user.getAge());
      String key = user.getSex() + "&num";
      map.put(key,map.get(key) == null ? 1 : map.get(key) + 1);
    }
    Map<User.SEX, Double> res = new HashMap<>();
    for (Map.Entry<Object, Integer> item : map.entrySet()) {
      Object key = item.getKey();
      if (key.equals(User.SEX.FEMALE) || key.equals(User.SEX.MALE)) {
        String key2 = key + "&num";
        res.put((User.SEX)key,Double.valueOf(item.getValue()) / map.get(key2));
      }
    }
    System.out.println("按照性别求平均年龄");
    System.out.println("原方式：" + res);
    System.out.println("================================");
    Map<User.SEX, Double> collect = users.parallelStream().collect(Collectors.groupingBy(User
            ::getSex,Collectors.averagingInt(User::getAge)));
    System.out.println("新方式：" + collect);
  }

  /**
   * 按性别分类求年龄总和
   */
  private static void getAgeSumGroupBySex() {
    Map<User.SEX, Integer> map = new HashMap<>();
    for (User user : users) {
      Integer oldAgeSum = map.get(user.getSex());
      map.put(user.getSex(),oldAgeSum == null ? user.getAge() : oldAgeSum + user.getAge());
    }
    System.out.println("原方式：" + map);
    System.out.println("====================================");
    //map-reduce方式
    Map<User.SEX, Integer> collect = users.parallelStream().collect(Collectors.groupingBy
            (User::getSex,Collectors.reducing(0,User::getAge,Integer::sum)));
    System.out.println("新方式：" + collect);
  }

  /**
   * 按性别区分不同人名
   */
  private static void groupBySexList() {
    HashMap<User.SEX, List<String>> map = new HashMap<>();
    for (User user : users) {
      List<String> oldValue = map.get(user.getSex());
      if (oldValue == null) {
        oldValue = new ArrayList<>();
      }
      oldValue.add(user.getName());
      map.put(user.getSex(),oldValue);
    }
    System.out.println("原方式：" + map);
    System.out.println("=======================================");
    Map<User.SEX, List<String>> collect = users.parallelStream().collect(Collectors.groupingBy
            (User::getSex,Collectors.mapping(User::getName,Collectors.toList())));
    System.out.println("新方式：" + collect);
  }

  /**
   * 按性别分组人数
   */
  private static void groupBySex() {
    HashMap<User.SEX, Integer> map = new HashMap<>();
    for (User user : users) {
      if (user.getSex() == User.SEX.FEMALE) {
        Integer oldValue = map.get(User.SEX.FEMALE);
        map.put(User.SEX.FEMALE,(oldValue == null ? 0 : oldValue) + 1);
      } else {
        Integer oldValue = map.get(User.SEX.MALE);
        map.put(User.SEX.MALE,(oldValue == null ? 0 : oldValue) + 1);
      }
    }
    System.out.println("原方式：" + map);
    System.out.println("=============================================");
    Map<User.SEX, Integer> collect = users.parallelStream().collect(Collectors.groupingBy
            (User::getSex,Collectors.summingInt(value -> 1)));
    System.out.println("新方式：" + collect);
  }

  /**
   * 获取比指定年龄大的人员集合
   */
  private static void getOlderThan(Integer minAge) {

    List<User> uList = new ArrayList<>();
    for (User user : users) {
      if (user.getAge() > minAge) {
        uList.add(user);
      }
    }
    System.out.println("原方式：" + uList);
    System.out.println("=========================================");
    List<User> userList = users.parallelStream().filter(user -> user.getAge() > minAge).collect
            (Collectors.toList());
    System.out.println("新方式：" + userList);
  }

  /**
   * 获取所有的爱好类别
   */
  private static void getDissHobbies() {
    Set<String> result = new HashSet<>();
    for (User user : users) {
      result.addAll(user.getHobbies());
    }
    System.out.println("原方式：" + result);
    System.out.println("==========================================");
    Set<String> res = users.stream().flatMap(user -> user.getHobbies().stream()).collect
            (Collectors.toSet());
    System.out.println("新方式：" + res);
  }

  /**
   * 对爱好分类
   */
  private static void getClassifyHobby() {
    Map<String, List<User>> result = new HashMap<>();
    for (User user : users) {
      if (result.containsKey(user.getName())) {
        result.get(user.getName()).add(user);
      } else {
        List<User> uList = new ArrayList<>();
        uList.add(user);
        result.put(user.getName(),uList);
      }
    }
    System.out.println(result);
    System.out.println("============================================");
    Map<String, List<User>> res = users.stream().collect(Collectors.groupingBy(User::getName));
    System.out.println(res);
  }

  /**
   * 获取包含指定爱好的所有人员信息
   */
  private static void getAllHobby(String key) {

    List<User> result = new ArrayList<>();
    for (User user : users) {
      if (user.getHobbies().contains(key)) {
        result.add(user);
      }
    }
    System.out.println("原方式：" + result);
    System.out.println("========================================================");
    List<User> res = users.stream().filter(user -> user.getHobbies().contains(key)).collect
            (Collectors.toList());
    System.out.println("新方式：" + res);
  }

  /**
   * 获取第一个匹配的元素
   */
  private static void getFirstHobby(String key) {
    for (User user : users) {
      if (user.getHobbies().contains(key)) {
        System.out.println("原循环方式：" + user);
        break;
      }
    }

    System.out.println("======================================");
    Optional<User> first = users.stream().filter(user -> user.getHobbies().contains(key))
            .findFirst();
    System.out.println("新流式方式：" + first.get());
  }

  private static List<User> getUserList() {
    List<User> users = new ArrayList<User>() {
      {
        add(new User(1,"jingzz2",232,Arrays.asList("Letter writing2","music3","photography6",
                "tennis3"),User.SEX.FEMALE));
        add(new User(2,"jingzz1",231,Arrays.asList("Letter writing1","music4","photography5",
                "tennis4"),User.SEX.MALE));
        add(new User(3,"jingzz3",233,Arrays.asList("Letter writing3","music1","photography8",
                "tennis1"),User.SEX.MALE));
        add(new User(4,"jingzz4",234,Arrays.asList("Letter writing4","music7","photography2",
                "tennis7"),User.SEX.FEMALE));
        add(new User(5,"jingzz0",230,Arrays.asList("Letter writing0","java","photography9",
                "tennis0"),User.SEX.FEMALE));
        add(new User(6,"jingzz6",236,Arrays.asList("Letter writing6","java","photography7",
                "tennis2"),User.SEX.FEMALE));
        add(new User(7,"jingzz7",237,Arrays.asList("Letter writing7","java","photography1",
                "tennis8"),User.SEX.FEMALE));
        add(new User(8,"jingzz9",239,Arrays.asList("Letter writing9","music6","photography3",
                "tennis6"),User.SEX.MALE));
        add(new User(9,"jingzz8",238,Arrays.asList("Letter writing8","music9","photography0",
                "tennis9"),User.SEX.MALE));
        add(new User(10,"jingzz5",235,Arrays.asList("Letter writing5","java","photography4",
                "tennis5"),User.SEX.FEMALE));
      }
    };
    return users;
  }
}
