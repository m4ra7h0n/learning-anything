package com.xjjlearning.database.mysql;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjjlearning.database.mysql.mapper.UserMapper;
import com.xjjlearning.database.mysql.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * selectList 和 selectMap
 */
@SpringBootTest
class MybatisPlusCrudApplicationTests {
    @Resource
    UserMapper mapper; //mapper 相当于 ... from user ...
    @Test
    void wrapperTest() {}

    @Test
    void aInsert() {
        User user = new User();
        user.setName("小羊");
        user.setAge(3);
        user.setEmail("abc@mp.com");
        assertThat(mapper.insert(user)).isGreaterThan(0);
        // 成功直接拿回写的 ID
        assertThat(user.getId()).isNotNull();
    }
    @Test
    void bDelete() {
        assertThat(mapper.deleteById(3L)).isNotNull();
        assertThat(mapper.delete(new QueryWrapper<User>().lambda().
                eq(User::getName, "Sandy"))).isGreaterThan(0);
    }

    @Test
    void cUpdate() {
//        assertThat(mapper.updateById(new User().setId(1L).setEmail("xx@qq..com"))).isGreaterThan(0);

        assertThat(
                mapper.update(
                        new User().setName("mp"),
                        Wrappers.<User>lambdaUpdate()
                                .set(User::getAge, 3)
                                .eq(User::getId, 2)
                )
        ).isGreaterThan(0);
        User user = mapper.selectById(2);
        assertThat(user.getAge()).isEqualTo(3);
        assertThat(user.getName()).isEqualTo("mp");

//        //最终update都是根据entity
//        mapper.update(null, //entity可以为null
//                Wrappers.<User>lambdaUpdate().set(User::getEmail, null).eq(User::getId, 2)); //wrapper可以设置值
//        mapper.update(
//                new User().setEmail("miemie@baomidou.com"), //entity可以设置值
//                new QueryWrapper<User>()
//                        .lambda().eq(User::getId, 2) //wrapper可以不设置值
//        );
    }
    @Test
    void dSelect() {
        mapper.insert(
                new User().setId(10086L)
                        .setName("miemie")
                        .setEmail("miemie@baomidou.com")
                        .setAge(3));
        assertThat(mapper.selectById(10086L).getAge()).isEqualTo(3);
        mapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 1L));
        mapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 1L));

        mapper.selectList(Wrappers.<User>lambdaQuery().select(User::getId, User::getName))
                .forEach(x -> {
                });
        mapper.selectList(new QueryWrapper<User>().select("id", "name")) //只要这两个字段
                .forEach(x -> {
                    System.out.println(x.getId());
                    System.out.println(x.getName());
                    System.out.println(x.getAge());
//                    assertThat(x.getId()).isNotNull();
//                    assertThat(x.getEmail()).isNull();
//                    assertThat(x.getName()).isNotNull();
//                    assertThat(x.getAge()).isNull();
                });
    }
    @Test
    void orderByLambda() {
        mapper.selectList(Wrappers.<User>query().orderByAsc("age"));
        mapper.selectList(Wrappers.<User>query().orderByAsc("age", "name").orderByDesc("email"));
    }
    @Test
    void selectMaps() {
        List<Map<String, Object>> mapList = mapper.selectMaps(Wrappers.<User>query().orderByAsc("age"));
        mapList.forEach(map -> {
            System.out.println(map.keySet());
            System.out.println(map.values());
        });
    }
    @Test
    void selectMapsPage() {
        Page<Map<String, Object>> page = mapper.selectMapsPage(new Page<>(1, 2), Wrappers.<User>query().orderByDesc("age"));
        System.out.println(page.getSize());
        System.out.println("总页数" + page.getPages());
        System.out.println("总大小" + page.getTotal());
        page.getRecords().forEach(action -> {
            System.out.println(action.keySet());
            System.out.println(action.values());
        });
    }
    @Test
    void testSelectMaxId() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("max(id) as id");
        User user = mapper.selectOne(wrapper);
        System.out.println("max id: " + user.getId());

        List<User> users = mapper.selectList(Wrappers.<User>lambdaQuery().orderByDesc(User::getId));
        User user1 = users.get(0);
        System.out.println("max id: " + user1.getId());
    }

    @Test
    void testGroup() {
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.select("age, count(*)")
//                .groupBy("age");
//
//        List<User> users = mapper.selectList(wrapper);
//        users.forEach(user -> {
//            System.out.println(user.getAge());
//            //无法显示count(*)字段
//        });
//
//        List<Map<String, Object>> maplist = mapper.selectMaps(wrapper);
//        for (Map<String, Object> mp : maplist) {
//            System.out.println(mp); //count(*) 字段
//        }

        LambdaQueryWrapper<User> lambdaQueryWrapper = new QueryWrapper<User>().lambda()
                .select(User::getAge)
                .groupBy(User::getAge)
                .orderByAsc(User::getAge);
        mapper.selectList(lambdaQueryWrapper).forEach(System.out::println);
    }

    @Test
    void testTableFieldExistFalse() {}
    @Test
    void mine() {
        //id是5的人age+=1
        mapper.update(null, new UpdateWrapper<User>(null).lambda()
                        .set(User::getAge, mapper.selectById(5L).getAge() + 1)
                        .eq(User::getId, 5));
//        mapper.update(null, Wrappers.<User>lambdaUpdate(null)
//                .set(User::getAge, mapper.selectById(5L).getAge() + 1)
//                .eq(User::getId, 5));
    }
}
