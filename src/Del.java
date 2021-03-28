import java.util.HashMap;
import java.util.Map;

/**
 * Created by 周智慧 on 2020/3/22.
 */
public class Del {

    public static void main(String[] args) {
        Map<Person, Integer> map = new HashMap<Person, Integer>();
        Person p = new Person("zhangsan", 12);

        map.put(p, 1);
        p.setName("lisi"); // 因为p.name参与了hash值的计算，修改了之后hash值发生了变化，所以下面删除不掉
        Object fs = map.remove(p);

        System.out.println(map + " " + fs);
    }

}