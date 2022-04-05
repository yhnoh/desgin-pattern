package com.example.singletonpattern;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class App {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        Settings settings1 = Settings.getInstance();
        Settings settings2 = Settings.getInstance();

        System.out.println(settings1 == settings2);

        //리플렉션을 활용하여 싱글톤을 깨뜨린는 방법
        Constructor<Settings> constructor = Settings.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Settings settings3 = constructor.newInstance();

        System.out.println(settings1 == settings3);

        //직렬화 & 역 직렬화 사용하여 깨뜨리는 방법
        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("settings.obj"))){
            out.writeObject(settings1);
        }
        Settings serializableSettings = null;
        try(ObjectInput in = new ObjectInputStream(new FileInputStream("settings.obj"))){
            serializableSettings = (Settings) in.readObject();
        }

        System.out.println(settings1 == serializableSettings);
    }
}
