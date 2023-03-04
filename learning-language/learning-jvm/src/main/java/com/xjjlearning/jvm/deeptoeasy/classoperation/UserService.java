package com.xjjlearning.jvm.deeptoeasy.classoperation;

import com.xjjlearning.jvm.deeptoeasy.classoperation.simple.User;
import com.xjjlearning.jvm.deeptoeasy.classoperation.simple.UserDao;
import lombok.Getter;

@Getter
public
class UserService{
    /**
     * // 类的实例初始化方法是由编译器生成的，对象的字段初始化赋值也被编译进该方法中完成，构造方法也是编译进该方法
     *   public com.xjjlearning.jvm.deeptoeasy.classoperation.UserService();
     *     descriptor: ()V
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=3, locals=1, args_size=1
     *          0: aload_0
     *         // invoke super init method
     *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     *
     *          4: aload_0
     *          5: new           #2                  // class com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User
     *          8: dup
     *          9: invokespecial #3                  // Method com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User."<init>":()V
     *         12: putfield      #4                  // Field user:Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
     *
     *         15: aload_0
     *         16: new           #5                  // class com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao
     *         19: dup
     *         20: invokespecial #6                  // Method com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao."<init>":()V
     *         23: putfield      #7                  // Field userDao:Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao;
     *         26: return
     *       LineNumberTable:
     *         line 9: 0
     *         line 10: 4
     *         line 11: 15
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0      27     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/UserService;
     *
     */

//    public UserService(){
//    }
//
//    public UserService(UserDao userDao){
//        this.userDao = userDao;
//    }


    User user = new User();
    private UserDao userDao = new UserDao();

    public void onInit() {
        this.userDao = new UserDao();
    }
    /**
     *   public void onInit();
     *     descriptor: ()V
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=3, locals=1, args_size=1
     *          0: aload_0
     *          1: new           #5                  // class com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao
     *          4: dup
     *          5: invokespecial #6                  // Method com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao."<init>":()V
     *
     *         // 将当前栈顶元素赋值给指定域, #7参数是指定域
     *          8: putfield      #7                  // Field userDao:Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao;
     *         11: return
     *       LineNumberTable:
     *         line 14: 0
     *         line 15: 11
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0      12     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/UserService;
     */

    public User findUser(String username) {
        return userDao.getUserByName(username);
    }
    /**
     *   public com.xjjlearning.jvm.deeptoeasy.classoperation.simple.User findUser(java.lang.String);
     *     descriptor: (Ljava/lang/String;)Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=2, locals=2, args_size=2
     *         // load $this to var stack
     *          0: aload_0
     *         // take this.userDao to the top of var stack
     *          1: getfield      #7                  // Field userDao:Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao;
     *
     *         // this.userDao.getUserByName()
     *          4: aload_1
     *          5: invokevirtual #8                  // Method com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao.getUserByName:(Ljava/lang/String;)Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
     *          8: areturn
     *       LineNumberTable:
     *         line 12: 0
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0       9     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserService;
     *             0       9     1 username   Ljava/lang/String;
     */
}
/**
 * javap -v com.xjjlearning.jvm.deeptoeasy.classoperation.UserService
 * Classfile /Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/classoperation/UserService.class
 *   Last modified 2023-3-4; size 1282 bytes
 *   MD5 checksum b2d103c67615e1f4c3c3256e3df1fba7
 *   Compiled from "UserService.java"
 * public class com.xjjlearning.jvm.deeptoeasy.classoperation.UserService
 *   minor version: 0
 *   major version: 52
 *   flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 *    #1 = Methodref          #10.#33        // java/lang/Object."<init>":()V
 *    #2 = Class              #34            // com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User
 *    #3 = Methodref          #2.#33         // com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User."<init>":()V
 *    #4 = Fieldref           #9.#35         // com/xjjlearning/jvm/deeptoeasy/classoperation/UserService.user:Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
 *    #5 = Class              #36            // com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao
 *    #6 = Methodref          #5.#33         // com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao."<init>":()V
 *    #7 = Fieldref           #9.#37         // com/xjjlearning/jvm/deeptoeasy/classoperation/UserService.userDao:Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao;
 *    #8 = Methodref          #5.#38         // com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao.getUserByName:(Ljava/lang/String;)Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
 *    #9 = Class              #39            // com/xjjlearning/jvm/deeptoeasy/classoperation/UserService
 *   #10 = Class              #40            // java/lang/Object
 *   #11 = Utf8               user
 *   #12 = Utf8               Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
 *   #13 = Utf8               userDao
 *   #14 = Utf8               Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao;
 *   #15 = Utf8               <init>
 *   #16 = Utf8               ()V
 *   #17 = Utf8               Code
 *   #18 = Utf8               LineNumberTable
 *   #19 = Utf8               LocalVariableTable
 *   #20 = Utf8               this
 *   #21 = Utf8               Lcom/xjjlearning/jvm/deeptoeasy/classoperation/UserService;
 *   #22 = Utf8               onInit
 *   #23 = Utf8               findUser
 *   #24 = Utf8               (Ljava/lang/String;)Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
 *   #25 = Utf8               username
 *   #26 = Utf8               Ljava/lang/String;
 *   #27 = Utf8               getUser
 *   #28 = Utf8               ()Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
 *   #29 = Utf8               getUserDao
 *   #30 = Utf8               ()Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao;
 *   #31 = Utf8               SourceFile
 *   #32 = Utf8               UserService.java
 *   #33 = NameAndType        #15:#16        // "<init>":()V
 *   #34 = Utf8               com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User
 *   #35 = NameAndType        #11:#12        // user:Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
 *   #36 = Utf8               com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao
 *   #37 = NameAndType        #13:#14        // userDao:Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao;
 *   #38 = NameAndType        #41:#24        // getUserByName:(Ljava/lang/String;)Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
 *   #39 = Utf8               com/xjjlearning/jvm/deeptoeasy/classoperation/UserService
 *   #40 = Utf8               java/lang/Object
 *   #41 = Utf8               getUserByName
 * {
 *   com.xjjlearning.jvm.deeptoeasy.classoperation.simple.User user;
 *     descriptor: Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
 *     flags:
 *
 *   public com.xjjlearning.jvm.deeptoeasy.classoperation.UserService();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=3, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: aload_0
 *          5: new           #2                  // class com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User
 *          8: dup
 *          9: invokespecial #3                  // Method com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User."<init>":()V
 *         12: putfield      #4                  // Field user:Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
 *         15: aload_0
 *         16: new           #5                  // class com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao
 *         19: dup
 *         20: invokespecial #6                  // Method com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao."<init>":()V
 *         23: putfield      #7                  // Field userDao:Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao;
 *         26: return
 *       LineNumberTable:
 *         line 9: 0
 *         line 10: 4
 *         line 11: 15
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      27     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/UserService;
 *
 *   public void onInit();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=3, locals=1, args_size=1
 *          0: aload_0
 *          1: new           #5                  // class com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao
 *          4: dup
 *          5: invokespecial #6                  // Method com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao."<init>":()V
 *          8: putfield      #7                  // Field userDao:Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao;
 *         11: return
 *       LineNumberTable:
 *         line 14: 0
 *         line 15: 11
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      12     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/UserService;
 *
 *   public com.xjjlearning.jvm.deeptoeasy.classoperation.simple.User findUser(java.lang.String);
 *     descriptor: (Ljava/lang/String;)Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=2, locals=2, args_size=2
 *          0: aload_0
 *          1: getfield      #7                  // Field userDao:Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao;
 *          4: aload_1
 *          5: invokevirtual #8                  // Method com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao.getUserByName:(Ljava/lang/String;)Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
 *          8: areturn
 *       LineNumberTable:
 *         line 17: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       9     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/UserService;
 *             0       9     1 username   Ljava/lang/String;
 *
 *   public com.xjjlearning.jvm.deeptoeasy.classoperation.simple.User getUser();
 *     descriptor: ()Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: getfield      #4                  // Field user:Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
 *          4: areturn
 *       LineNumberTable:
 *         line 10: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       5     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/UserService;
 *
 *   public com.xjjlearning.jvm.deeptoeasy.classoperation.simple.UserDao getUserDao();
 *     descriptor: ()Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao;
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: getfield      #7                  // Field userDao:Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserDao;
 *          4: areturn
 *       LineNumberTable:
 *         line 11: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       5     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/UserService;
 * }
 * SourceFile: "UserService.java"
 */