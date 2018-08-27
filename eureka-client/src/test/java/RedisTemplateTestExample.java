import com.google.common.collect.Maps;
import org.junit.Test;
import org.springframework.data.redis.core.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by zhenghui on 2017/10/26.
 */
public class RedisTemplateTestExample extends Tester {
    protected static final Map dataMap = Maps.newHashMap();
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //String/Value 操作
    @Test
    public void testValueOperations() {
        String key = "ValueOperations";
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        if (stringRedisTemplate.hasKey(key)) {
            System.out.println(key + "->" + valueOperations.get(key));
        } else {
            valueOperations.set(key, System.currentTimeMillis() + "");
        }
    }

    //List 操作
    @Test
    public void testListOperations() {
        String key = "ListOperations";
        ListOperations<String, String> listOperations = stringRedisTemplate.opsForList();
        if (stringRedisTemplate.hasKey(key)) {
            System.out.println(key + "->" + listOperations.range(key, 0, -1));
        } else {
            listOperations.rightPush(key, dataMap.toString());
        }
    }

    //Set 操作
    @Test
    public void testSetOperations() {
        String key = "SetOperations";
        SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();
        if (stringRedisTemplate.hasKey(key)) {
            System.out.println(key + "->" + setOperations.getOperations());
        } else {
            setOperations.add(key, dataMap.toString());
        }
    }

    //Sort Set 操作，有序
    @Test
    public void testZSetOperations() {
        String key = "ZSetOperations";
        ZSetOperations zSetOperations = stringRedisTemplate.opsForZSet();
        if (stringRedisTemplate.hasKey(key)) {
            System.out.println(key + "->" + zSetOperations.range(key, 0, -1));
            zSetOperations.add(key, System.currentTimeMillis() + "", new Double(2));
        } else {
            zSetOperations.add(key, dataMap.toString(), new Double(1));
        }
    }

    //Hash 操作 ,常用
    @Test
    public void testHashOperations(){
        String key = "HashOperations";
        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        String hKey = "i";
        if (stringRedisTemplate.hasKey(key) && hashOperations.hasKey(key, hKey)) {
            System.out.println(key + "->" + hashOperations.get(key, hKey));
            //删除
            hashOperations.delete(key, hKey);
//            hashOperations.delete(key,"i",dataMap.toString());
        } else {
            hashOperations.put(key, hKey, dataMap.toString());
        }
    }

    static {
        dataMap.put("a", System.currentTimeMillis());
        dataMap.put("b", System.currentTimeMillis());
    }

}
