
[markwodn数学公式输入](https://cxy.cc/post/2024/06/12/markdown-math/#:~:text=(n%2B1)-,%E5%A6%82%E4%BD%95%E8%BE%93%E5%85%A5%E7%B4%AF%E5%8A%A0%E3%80%81%E7%B4%AF%E4%B9%98%E8%BF%90%E7%AE%97,%E5%BC%8F%7D%20%E6%9D%A5%E8%BE%93%E5%85%A5%E4%B8%80%E4%B8%AA%E7%B4%AF%E5%8A%A0%E3%80%82)

#### 0
小学题：$   (a+b)*(a-b) = 73, \quad and \quad a \in N^+, b \in N^+   球a和b $.    </br>
anwser: a=43, b-42


####  0-1 拆分数
`公式`:$ \frac{1}{n} = \frac{1}{1+n} + \frac{1}{n*(n+1)}   $    比如：</br>
$\frac{1}{2}=\frac{1}{3}+\frac{1}{6}$</br> 
$\frac{1}{6}=\frac{1}{7}+\frac{1}{42}$</br> 
$\frac{1}{7}=\frac{1}{8}+\frac{1}{56}$  那么：</br> 
$\frac{1}{2}=\frac{1}{3}+\frac{1}{8}+\frac{1}{56}+\frac{1}{42}$</br> 
`思路1`: 对于 $\frac{a}{b}, a<b $, 取x,y其中x和y时b的因数，如果x+y=a就完美了，比如 </br>
$\frac{5}{6} = \frac{1}{2}+\frac{1}{3}其中x=2, y=3, x+y=5，$ </br>
`思路2`: 对于 $\frac{a}{b}, a<b $, 如果b+1是a的倍数就完美了，比如 </br>
$\frac{5}{19} = \frac{19+1}{19*4}=\frac{1}{76}+\frac{1}{4}$ </br>
思路2再结合公式计算:$\frac{6}{23}=\frac{23+1}{23*4}=\frac{1}{4}+\frac{1}{92}=\frac{1}{5}+\frac{1}{20}++\frac{1}{92}$</br>
`待定系数+因式分解思路3`： </br> 
$ \frac{5}{6} =\frac{1}{a}+\frac{1}{b}, eq: 5ab-6a-6b=0, eq: 25ab-30a-30b=36, eq: (5a-6)(5b-6) =36,枚举所有的case可以得出a=2,b=3 $
</br> </br> 
两项和的可以使用待定系数+因式分解：</br> 
$ \frac{2}{15} = \frac{1}{10} + \frac{1}{30}, eq: 4ab -30a -30b +225 =225, eq: (2a -15)(2b -15) =225 枚举所有的case可以得出: $ </br>
1. 1/8 +1/120
2. 1/9 +1/45
3. 1/10 +1/30
4. 1/12 +1/20

$\frac{4}{25}=但是这里使用待定系数法，是无解的。而且分子改造成的25+1不是4的倍数，思路1和思路2没法改造$

也可以使用贪婪算法：
$ a<b, let: b = a*p+r, then $   </br>
$ \frac{a}{b} = \frac{a}{a*p+r} = \frac{a*p+r-r+a}{(a*p+r)(p+1)} =\frac{1}{p+1} + \frac{a-r}{(a*p+r)(p+1)}$
```
import java.util.ArrayList;
import java.util.List;

public class MathDemo {
    public static void main(String[] args) {
        testEgyptFraction(2, 15);
        testEgyptFraction(5, 6);
        testEgyptFraction(8, 11);
    }

    public static List<Integer> testEgyptFraction(int a, int b) {
        List<Integer> ret = new ArrayList<>();
        while (a > 1) {
            int p = b / a;
            if (b % a == 0) {
                b = p;
                a = 1;
                break;
            } else {
                ret.add(p + 1);
                a = a + a * p - b;
                b = b * (p + 1);
            }
//            int gcd = gcd(a, b);
//            if (gcd > 1) {
//                a = a / gcd;
//                b = b / gcd;
//            }
        }
        ret.add(b);
        System.out.println(ret);
        return ret;
    }
}
```
如果要拆解成三项的和，比如：</br>
$ \frac{43}{75}=\frac{3}{75}+\frac{15}{75}+\frac{25}{75} $ </br></br>
$ \frac{13}{47}=\frac{1}{47}+\frac{12}{47} $ </br>
$ \frac{12}{47} =\frac{47+1}{47*4}=\frac{1}{4}+\frac{1}{188} $ </br></br>
$ \frac{4}{83}=\frac{1}{83}+\frac{3}{83} $ </br>
$ \frac{3}{83} =\frac{83+1}{83*28}=\frac{1}{28}+\frac{1}{2324} $ </br></br>



#### 1
$ \sum_{k=1}^{100}k $            </br>
anwser: 5050


#### 2
$ \sum_{k=1}^{100}k^3$ </br>
anwser: 5050*5050=25502500


#### 3
初中数学竞赛, 已知m和n都是正整数且
$ \frac{1}{m} + \frac{1}{n} = \frac{1}{3}, m \in N^+ \quad and \quad n \in N^+ $
, 求m+n </br>
anwser:因式分解:(m-3)(n-3)=9,分三种情况得出1:m=4 n=12, 2: m==12 n=4, 3: m=6 n=6     

#### 4
已知
$m^2 + 7m + 49 = 0，求m^3= $ <u>_____</u>     </br>
anwser: 7^3=343.

#### 5
$  \sqrt{a\sqrt{a\sqrt{a}}} = 128 $         </br>
anwser: $  a^{\frac{1}{2}}*a^{\frac{1}{4}}*a^{\frac{1}{8}} = 128 $  , $ a^{\frac{7}{8}} = 2^7 => a=2^8 => a=256$ 

#### 6 $ a = \frac{1 \pm \sqrt{5}}{2} $ 相关的代数题
##### 6.1
化简 $   \sqrt[3]{2+\sqrt{5}}  $   </br>
化简 $   \sqrt[3]{2-\sqrt{5}}  $   </br>
anwser: $ a^2 = a + 1 => a^3 = 2a + 1$, 因此上面的两个式子化简后就是a。

#### 7 $ a = \frac{-1 \pm \sqrt{5}}{2} $ 相关的代数题
##### 7.1
化简 $   \sqrt[3]{-2+\sqrt{5}}  $   </br>
化简 $   \sqrt[3]{-2-\sqrt{5}}  $   </br>
anwser: $ a^2 = -a + 1 => a^3 = 2a - 1$, 因此上面的两个式子化简后就是a。

#### 8
$
xy=1, 求 \frac{9999}{x^2+1} + \frac{9999}{y^2+1}
$
anwser: 将分母里的两个1都用xy替换，可得结果=9999

#### 9

#### 10

#### 11

#### 12

#### 13

#### 14

#### 15

#### 16

#### 17

#### 18

#### 19

#### 20

#### 21

#### 22

#### 23

#### 24

#### 25

#### 26

#### 27

#### 28

#### 29

#### 30

#### 31

#### 32

#### 33

#### 34

#### 35

#### 36

#### 37

#### 38