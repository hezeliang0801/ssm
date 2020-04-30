package com.chatRobot.model;

public class Student {
        private String name;
        private int age;

        public Student (){
        }


        public static void main(String[] args) {
                Student student1 = new Student();
                Student student = new Student();
                student1.age = 12;
                try {
                        student.show(student1.age);
                } catch (Exception e) {
                        System.out.println(student1.age);
                }
        }
        public void show(int age) throws Exception{
                try {
                        int i = 4/0;

                }catch (Exception e){
                        age = 13;
                        throw new Exception();
                }
                System.out.println("talk is cheap,show me the code!");
        }
}