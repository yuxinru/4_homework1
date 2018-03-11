import org.junit.*;
import static org.junit.Assert.*;

public class wordladderTest {

  private static String str1 , str2;               //equal 函数测试
  private static String a,b,c;                     //find函数正确性测试
  private static String d;                         //输入文件名错误
  private static String e;                         //输入word有误

  @Before
  public void setstring(){
    str1 = "aas";
    str2 = "aah";
    a = "dictionary.txt";
    b = "dog";
    c = "hat";
    d = "output.txt";
    e = "";
  }

  @Test
  public void equal(){
    mywordladder wl = new mywordladder();
    boolean bool = wl.equal(str1,str2);
    Assert.assertEquals(true,bool);
  }

  @Test
  public void find(){
    mywordladder wl = new mywordladder();
    int result = wl.find(a,b,c);
    Assert.assertEquals(4,result);

    result = wl.find(d,b,c);
    Assert.assertEquals(0,result);

    result = wl.find(a,e,c);
    Assert.assertEquals(0,result);
  }

}
