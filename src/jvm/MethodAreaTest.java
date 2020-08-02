package jvm;

public class MethodAreaTest {
    public static void main(String[] args) {
        testInteger();
        testString();
    }

    static void testInteger(){
        // 方法区中
        int i0 = 40;
        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        // 堆中
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);
        

        
        System.out.println("i1=i2   " + (i1 == i2));//true
        System.out.println("i1=i2+i3   " + (i1 == i2 + i3));//true
        System.out.println("i1=i4   " + (i1 == i4));//false
        System.out.println("i4=i5   " + (i4 == i5));//false
        // + 不适用于Integer，都要自动拆箱
        System.out.println("i4=i5+i6   " + (i4 == i5 + i6));//true
        System.out.println("i0=i4   " + (i0 == i4));//true
        System.out.println("40=i5+i6   " + (40 == i5 + i6));//true
        
    }

    /*
    在堆区的情况
    1. new创建
    2. 运行时创建

    在方法区的情况
    1. 使用 “”
    2. 使用intern方法
    */
    
    static void testString(){
        // str1在方法区
        String str1 = "abcd";
        // str2在堆区，方法区也会有一份
        String str2 = new String("abcd");
        System.out.println(str1==str2);//false
        
        str1 = "str";
        str2 = "ing";
        // str3在方法区，编译时创建
        String str3 = "str" + "ing";
        // str4在堆区， 因为是在运行时创建
        String str4 = str1 + str2;
        System.out.println(str3 == str4);//false
        
        // str5在方法区
        String str5 = "string";
        System.out.println(str3 == str5);//true
    }
}