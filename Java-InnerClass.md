# java内部类
## 一、认识内部类
### 1. 定义
内部类（ Inner Class ）即定义在一个类里面的类。与之对应，包含内部类的类被称为外部类。
``` java
public class HelloWorld {    
    // 内部类Inner，类Inner在类HelloWorld的内部
    public class Inner { ... ... }
	}
}
```

### 2. 内部类的作用
1. 内部类提供了更好的封装：可以把内部类隐藏在外部类之内，不允许同一个包中的其他类访问该类；
2. 内部类的方法可以直接访问外部类的所有数据，包括私有数据；
3. 内部类所实现的功能使用外部类同样可以实现，只是有时使用内部类更方便。

## 二、内部类的种类
- 成员内部类
- 静态内部类
- 方法内部类
- 匿名内部类

### 1. 成员内部类
也称之为普通内部类，最常见：
``` java
//外部类Outer
public class Outer{    
	//成员内部类Inner
	public class Inner {
		public void show() { 
			... ...
		}
	}
```
1. Inner 类定义在 Outer 类的内部，相当于 Outer 类的一个成员变量的位置；
2. Inner 类可以使用任意访问控制符，如 public 、 protected 、 private 等；
3. 必须使用外部类对象来创建内部类对象；
``` java
Outer out = new Outer();
Inner in = out.new Inner();
in.show();
```
4. 编译完成后出现Outer.class和Outer$Inner.class两个.class文件；

> 提示：
> - 外部类是不能直接使用内部类的成员和方法；
> - 如果外部类和内部类具有相同的成员变量或方法，内部类默认访问自己的成员变量或方法，如果要访问外部类的成员变量，可以使用 this 关键字，`Outer.this.pram`;


### 2. 静态内部类
静态内部类是 static 修饰的内部类
1.  静态内部类*不能直接访问外部类的非静态成员*，但可以通过`new Outer().pram`的方式访问 ；
2.  如果外部类的静态成员与内部类的成员名称相同，可通过`Outer.pram`访问外部类的静态成员；
3.  如果外部类的静态成员与内部类的成员名称不相同，则可通过“成员名”直接调用外部类的静态成员
4.  创建静态内部类的对象时，不需要外部类的对象，可以直接创建`Inner in = new Inner()`；

### 3. 方法内部类
方法内部类即内部类定义在外部类的方法中，方法内部类只在该方法的内部可见，即只在该方法内可以使用。
``` java
//外部类Outer
public class Outer{    
	// 外部类的方法
	public void show() {
		public class Inner {
			... ...
		}
	}
```
> 注意：方法内部类不能在外部类的方法以外的地方使用，因此方法内部类不能使用访问控制符和 static 修饰符。

### 4. 匿名内部类
没有名字的内部类。
``` java
//外部类Outer
public class Outer{    
	// 外部类的方法
	Inner in = new Inner() {
		public void show() {... ...}
	}

	in.show();
```
#### 1. 使用情形
1. 只用到类的一个实例；
2. 类在定义后马上用到；
3. 类非常小（SUN推荐是在4行代码以下）；
4. 给类命名并不会导致你的代码更容易被理解。

#### 2. 使用原则
1. 匿名内部类不能有构造方法；
2. 匿名内部类不能定义任何静态成员、静态方法；
3. 匿名内部类不能是public,protected,private,static；
4. 只能创建匿名内部类的一个实例。