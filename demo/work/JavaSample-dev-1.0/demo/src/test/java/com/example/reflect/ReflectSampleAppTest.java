package com.example.reflect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ReflectSampleAppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testPublicMethod() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // 引数なしのメソッドをテスト
        Class<?> clazz = Class.forName("com.example.ReflectSampleApp");
        // 引数なしのコンストラクタを呼び出し
        ReflectSampleApp instance2 = (ReflectSampleApp) clazz.getDeclaredConstructor().newInstance();
        assertEquals("publicMethod param is none", instance2.publicMethod());
    }

    @Test
    public void testPublicMethod2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // 引数ありのメソッドをテスト
        Class<?> clazz = Class.forName("com.example.ReflectSampleApp");
        // 引数なしのコンストラクタを呼び出し
        ReflectSampleApp instance2 = (ReflectSampleApp) clazz.getDeclaredConstructor().newInstance();
        String pram = "test";
        assertEquals("publicMethod param is " + pram, instance2.publicMethod(pram));
    }

    @Test
    public void testPublicMethod3() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        // 引数なしのメソッドをテスト(getMethod()でメソッドを取得)
        Class<?> clazz = Class.forName("com.example.ReflectSampleApp");
        Method method = clazz.getMethod("publicMethod");
        assertEquals("publicMethod param is none", method.invoke(new ReflectSampleApp()));
    }

    @Test
    public void testPublicMethod4() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        // 引数ありのメソッドをテスト(getMethod()でメソッドを取得)
        Class<?> clazz = Class.forName("com.example.ReflectSampleApp");
        Method method = clazz.getMethod("publicMethod", String.class);
        String pram = "test";
        assertEquals("publicMethod param is " + pram, method.invoke(new ReflectSampleApp(), pram));
    }

    @Test
    public void testPrivateMethod() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // 引数なしのメソッドをテスト
        Class<?> clazz = Class.forName("com.example.ReflectSampleApp");
        Method method = clazz.getDeclaredMethod("privateMethod");
        method.setAccessible(true);
        assertEquals("privateMethod param is none", method.invoke(new ReflectSampleApp()));
    }

    @Test
    public void testPrivateMethod2() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // 引数ありのメソッドをテスト
        Class<?> clazz = Class.forName("com.example.ReflectSampleApp");
        Method method = clazz.getDeclaredMethod("privateMethod", String.class);
        String pram = "test";
        method.setAccessible(true);
        ReflectSampleApp testTarget = new ReflectSampleApp();
        assertEquals("privateMethod param is " + pram, method.invoke(testTarget, pram));

        // インスタンスメソッドの場合
        // ReflectSampleApp (1)testTarget = new ReflectSampleApp();
        // String returnValue = (1)testTarget.(2)privateMethod((3)pram);
        // リフレクションでは以下で呼び出し
        // (2)method.invoke((1)testTarget, (3)pram);

        // クラスメソッドの場合
        // String returnValue = (1)ReflectSampleApp.(2)privateStaticMethod((3)pram);
        // リフレクションでは以下で呼び出し（クラスメソッドの呼び出しにインスタンスは不要）
        // (2)method.invoke((1)null, (3)pram)
        }

    @Test
    public void testStaticPrivateMethod() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // privateStaticMethodの結果を確認
        // 第一引数：メソッド名（文字列）、第二引数：メソッドの引数（型（String.class等））（なければ指定不要）
        // 第一引数で指定したメソッド（privateStaticMethod）を取得する
        Method method = ReflectSampleApp.class.getDeclaredMethod("privateStaticMethod");
        // プライベートメソッドなのでアクセス許可を与える（パブリックメソッドは不要）
        method.setAccessible(true);
        // 第一引数：インスタンス、第二引数：メソッドの引数（実際の値（"test"等））（なければ指定不要）　
        String returnValue = (String) method.invoke(null);
        // 第一引数：期待値、第二引数：実際の値（テスト対象）
        assertEquals("privateStaticMethod param is none", returnValue);
        // privateメソッドのため呼び出せないが呼び出す場合は以下のイメージ
        // String returnValue = ReflectSampleApp.privateStaticMethod();

    }

    // 実装を変更したくない場合（テストの為にsetter,getterは本末転倒）
    @Test
    public void testPrivateField() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        // setup - 事前準備
        // privateFieldに値
        ReflectSampleApp app = new ReflectSampleApp();
        app.setPrivateField("privateFieldの値");

        // exercice - テストの実行
        //フィールドを取得。
        Field field = ReflectSampleApp.class.getDeclaredField("privateField");

        //フィールドにアクセスできるようにする。
        field.setAccessible(true);

        //フィールド名（変数名）を出力。
        System.out.println("変数名：" + field.getName());

        //フィールドの値を出力。
        System.out.println("値：" + field.get(app));

        // verify - 検証
        assertEquals("privateFieldの値" , field.get(app));

        // 後処理 - teardown

    }

    @Test
    public void testPrivateStaticField() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        // setup - 事前準備
        // privateStaticFieldの値
        ReflectSampleApp.setPrivateStaticField("privateStaticFieldの値");

        // privateのメンバ変数に対してアクセスする為のパイプを用意
        // exercice - テストの実行
        Field field = ReflectSampleApp.class.getDeclaredField("privateStaticField");

        // フィールドにアクセスできるようにする
        field.setAccessible(true);

        // verify - 検証
        assertEquals("privateStaticFieldの値", field.get(null));


        // 後処理 - teardown
    }
}
