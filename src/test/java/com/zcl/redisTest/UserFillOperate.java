package com.zcl.redisTest;

import java.util.ArrayList;
import java.util.List;

public class UserFillOperate {
    public static void main(String[] args) {
//        User user = new User();
//        System.out.println(" First " + JSON.toJSONString(user));
//        addUserName(user);
//        System.out.println(" after addName " + JSON.toJSONString(user));
//        addUserAge(user);
//        System.out.println(" after addAge " + JSON.toJSONString(user));
//        addUserHobby(user);
//        System.out.println(" after addHobby " + JSON.toJSONString(user));
        List<User> users = new ArrayList<>();
        User u1 = new User("A1", 0, 11, "clzhou1", "coding");
        User u2 = new User("A2", 1, 12, "clzhou2", "working");
        User u3 = new User("A3", 1, 13, "clzhou3", "walking");
        User u4 = new User("A4", 2, 14, "clzhou3", "cooking");
        User u5 = new User("A5", 3, 15, "clzhou4", "swimming");
        User u6 = new User("A6", 0, 16, "clzhou5", "football");
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);
        users.add(u6);
//        List<String> allIds = users.stream().map(User::getId).collect(Collectors.toList());
//        System.out.println("allIds >>> "+allIds);
//        List<String> needUpdateUserIds = users.stream()
//                .filter(user->user.getStatus().equals(2)||user.getStatus().equals(3))
//                .map(User::getId)
//                .collect(Collectors.toList());
//        System.out.println("needUpdateUserIds >>> "+needUpdateUserIds); // 4,5
//        List<String> noNeedUpdateUserIds = users.stream()
//                .filter(user->user.getStatus().equals(0)||user.getStatus().equals(1))
//                .map(User::getId)
//                .collect(Collectors.toList());
//        System.out.println("noNeedUpdateUserIds >>> "+noNeedUpdateUserIds); // 1,2,3,6
//        
//        List<String> lastIds = new ArrayList<>(CollectionUtils.subtract(allIds, needUpdateUserIds));
//        System.out.println("lastIds >>> "+lastIds);
//        System.out.println("allIds >>> "+allIds);
        
        Long count1 = users.stream().filter(user->user.getName().equals("clzhou3") && user.getAge().equals(13)).count();
        System.out.println("count1 >>> "+count1);
        
        Long count2 = users.stream().filter(user->user.getName().equals("clzhou3") && user.getAge().equals(14)).count();
        System.out.println("count2 >>> "+count2);
        
        Long count3 = users.stream().filter(user->user.getName().equals("clzhou6") && user.getAge().equals(13)).count();
        System.out.println("count3 >>> "+count3);
        
    }

    public static void addUserName(User user) {
        user.setName("clzhou01");
    }

    public static void addUserAge(User user) {
        user.setAge(31);
    }

    public static void addUserHobby(User user) {
        user.setHobby("coding");
    }
    

}
