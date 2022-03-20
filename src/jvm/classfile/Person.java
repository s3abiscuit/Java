package classfile;

public class Person {

    /*
     * 被 getName() 调用
     * 所以在 cp 中有 Fieldref
     */
    private String name;

    /*
     * 没有被调用
     * 所以在 cp 中没有 Fieldref
     */
    private int age = 123456;

    /*
     * 被 printName() 调用
     * 所以在 cp 中有 Methodref
     */

    private String country = "China";

    public String getName() { // Methodref
        return name;
    }

    /*
     * 没有被调用
     * 所以在 cp 中没有 Methodref
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * 没有被调用
     * 所以在 cp 中没有 Methodref
     */
    public void printName() {
        getName();
    }

}

/*
Constant pool:
   #1 = Methodref          #2.#3          // java/lang/Object."<init>":()V
   #2 = Class              #4             // java/lang/Object
   #3 = NameAndType        #5:#6          // "<init>":()V
   #4 = Utf8               java/lang/Object
   #5 = Utf8               <init>
   #6 = Utf8               ()V
   #7 = Fieldref           #8.#9          // classfile/Person.name:Ljava/lang/String;
   #8 = Class              #10            // classfile/Person
   #9 = NameAndType        #11:#12        // name:Ljava/lang/String;
  #10 = Utf8               classfile/Person
  #11 = Utf8               name
  #12 = Utf8               Ljava/lang/String;
  #13 = Methodref          #8.#14         // classfile/Person.getName:()Ljava/lang/String;
  #14 = NameAndType        #15:#16        // getName:()Ljava/lang/String;
  #15 = Utf8               getName
  #16 = Utf8               ()Ljava/lang/String;
  #17 = Utf8               age
  #18 = Utf8               I
  #19 = Utf8               Code
  #20 = Utf8               LineNumberTable
  #21 = Utf8               setName
  #22 = Utf8               (Ljava/lang/String;)V
  #23 = Utf8               printName
  #24 = Utf8               SourceFile
  #25 = Utf8               Person.java
 */