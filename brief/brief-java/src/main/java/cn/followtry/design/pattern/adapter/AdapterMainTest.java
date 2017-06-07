package cn.followtry.design.pattern.adapter;

import java.util.stream.Stream;

/**
 * 适配器模式主测试类
 * Created by followtry on 17/6/7.
 */
public class AdapterMainTest {
  /** main. */
  public static void main(String[] args) {
    Player forwardPlayer = new ForwardPlayer("Garnett","前锋");
    Player centerPlayer = new CenterPlayer("kobe","中锋");
    Player guardsPlayer = new GuardsPlayer("Garnett","前锋");
    Player foreignPlayer = new Translator("姚明","前锋");
  
    Stream.of(forwardPlayer,centerPlayer,guardsPlayer,foreignPlayer).forEach(player -> {
      player.action("attack").attack();
    });
    System.out.println("结束");
  }
}
