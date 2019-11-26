抽象工厂方法<br>
1.抽象工厂方法特点
```
+抽象工厂角色：ComponentFactory类，它与业务逻辑无关。
+具体工厂角色：MacosComponentFactory类和WindowsComponentFactory类，他们是ComponentFactory类的子类，负责某一类产品的创建。
+抽象产品角色：Button类和Scroll类，其担任ComponentFactory类所需要创建的类型。
+具体产品角色：WindowsButton、WindowsScroll、MacosButton、MacosScroll类，他们分别是Button类和Scroll类中某一类产品的具体实例，其可以包含很多的业务逻辑。
```

2.抽象工厂方法的优点
```
+可以通过抽象工厂模式创建某一系列的产品，无需更改客户端代码即可完成，当加入一个新的产品如Linux时，很容易完成创建而不影响原有系列的产品。
```

3.抽象工厂方法的缺点
```
+如示例代码中所示，现仅创建了Button和Scroll类型，但如果新增一个EditText类型时需改动ComponentFactory及其子类，改动较大，在已投产的等业务中修改风险较大。
```