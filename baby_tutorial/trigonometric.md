三角函数很难反解, 比如向量(-2, 3)的sin(θ)=3/√13，求出的θ可能是56.3˚，但是这个向量的角度是123.7˚，需要确定是在第几象限才行。


### 1 计算1弧度约等于多少角度：180/Math.PI=57.29577951308232

### 2 证明正弦定理和余弦定理




### 一些公式
[知乎：考生必记：三角函数公式汇总+记忆（没有比这更全）](https://zhuanlan.zhihu.com/p/390928056)
#### 诱导公式
1、公式1：设α为任意角，终边相同的角的同一三角函数的值相等, $ k \in Z $,  </br>
$ sin(2kπ+α) ＝ sinα $ </br>
$ cos(2kπ+α) ＝ cosα $ </br>
$ tan(2kπ+α) ＝ tanα $ </br>
$ cot(2kπ+α) ＝ cotα $ </br>


2、公式二：设α为任意角，π+α的三角函数值与α的三角函数值之间的关系  </br>
sin(π+α) ＝ －sinα  </br>
cos(π+α) ＝ －cosα </br>
tan(π+α) ＝ tanα </br>
cot(π+α) ＝cotα </br>

3、公式三：任意角α与-α的三角函数值之间的关系  </br>
sin(－α)=－sinα </br>
cos(－α)=cosα </br>
tan(－α)=－tanα </br>
cot(－α)=－cotα </br>


4、公式四：利用公式二和公式三可以得到π-α与α的三角函数值之间的关系  </br>
sin(π－α)=sinα </br>
cos(π－α)=－cosα </br>
tan(π－α)=－tanα </br>
cot(π－α)=－cotα </br>

5、公式五：利用公式一和公式三可以得到2π-α与α的三角函数值之间的关系  </br>
sin(2π－α)=－sinα </br>
cos(2π－α)=cosα </br>
tan(2π－α)=－tanα </br>
cot(2π－α)=－cotα </br>

6、公式六：π/2±α与α的三角函数值之间的关系  </br>
sin(π/2+α)=cosα </br>
sin(π/2－α)=cosα </br>
cos(π/2+α)=－sinα </br>
cos(π/2－α)=sinα </br>
tan(π/2+α)=－cotα </br>
tan(π/2－α)=cotα </br>
cot(π/2+α)=－tanα </br>
cot(π/2－α)=tanα </br>

记背诀窍：奇变偶不变，符号看象限，即形如（2k+1）90°±α，则函数名称变为余名函 数，正弦变余弦，余弦变正弦，正切变余切，余切变正切。形如2k×90°±α，则函数名称不变。

#### 二角和差公式
$  \cos{(\alpha \pm \beta)} = \cos{\alpha}\cos{\beta} \pm \sin{\alpha}\sin{\beta} $ </br>
$  \sin{(\alpha \pm \beta)} = \sin{\alpha}\cos{\beta} \pm \cos{\alpha}\sin{\beta} $ </br>
$   \tan{\alpha \pm \beta}  = \frac{\tan\alpha \pm \tan\beta }{1\mp \tan\alpha \tan\beta }  $ </br>
$   \cot{\alpha \pm \beta}  = \frac{\cot\alpha\cot\beta \mp 1 }{\cot\beta \pm \cot\alpha }  $ </br>

#### 三角和公式
$   \sin{(\alpha + \beta + \gamma)} = \sin\alpha\cos\beta\cos\gamma +\sin\beta\cos\alpha\cos\gamma + \sin\gamma\cos\alpha\cos\beta - \sin\alpha\sin\beta\sin\gamma $ </br>
$   \cos{(\alpha + \beta + \gamma)} = \cos\alpha\cos\beta\cos\gamma - \cos\alpha\sin\beta\sin\gamma - \cos\beta\sin\alpha\sin\gamma - \cos\gamma\sin\alpha\sin\beta  $ </br>


#### 二角积化和差公式，根据上面的和差公式，很简单能推导出积化和差公式，这里省略

#### 同理可以得出二倍角公式
$ \sin{2\alpha} = 2\sin\alpha\cos\alpha $ </br>
$ \cos{2\alpha} = \cos^2\alpha - \sin^2\alpha = 2\cos^2\alpha - 1 = 1 - 2\sin^2\alpha$

#### 3倍角公式
$ \sin{3\alpha} = 3\sin\alpha - 4\sin^3\alpha $ </br>
$ \sin{3\alpha} = 4\sin\alpha\sin{(60^\circ-\alpha)}\sin{(60^\circ+\alpha)} $ </br>
$ \cos{3\alpha} = 4\cos^3\alpha - 3\cos\alpha $ </br>
$ \cos{3\alpha} = 4\cos\alpha\cos{(60^\circ-\alpha)}\cos{(60^\circ+\alpha)}  $ </br>
$ \tan{3\alpha} = \frac{3\tan\alpha - \tan^3\alpha}{1 - 3\tan^2\alpha} $ </br>
$ \tan{3\alpha} = \tan\alpha\tan{(60^\circ-\alpha)}\tan{(60^\circ+\alpha)}  $ </br>

#### 4倍角公式 省略

#### 5倍角公式 省略

#### 辅助角公式 
$    $ </br>
$  a\sin\alpha + b\cos\beta = \sqrt[]{a^2 + b^2}\sin{(\alpha + \varphi)}, \tan\varphi = \frac{b}{a}  $ </br>

#### 余弦定理 
$  a^2 = b^2 + c^2 - 2bc\cos\alpha  $ </br>
#### 正弦定理 
$  a/sinA = b/sinB =c/sinC = 2r=D（r为外接圆半径，D为直径）  $ </br>

#### 三角形面积
$  S\Delta ABC = \frac{1}{2}bc\sin{A} = \frac{1}{2}ab\sin{C} = \frac{1}{2}ac\sin{B}  $ .面积公式1 </br>
$  S\Delta ABC = \sqrt{s(s-a)(s-b)(s-c)}, s = \frac{a+b+c}{2}  $  .海伦公式： 用余弦定理 + 面积公式1 可以证明海伦公式  </br> 
$    $ </br>

### 2
余弦定理等价于向量的点积公式：
$ \vec{a} \cdot \vec{b} = |\vec{a}| \cdot |\vec{b}| \cdot \cos{\theta} $