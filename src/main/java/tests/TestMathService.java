package tests;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import mathclientserver.MathService;


public class TestMathService {
    private MathService math;
    private static char add = '+';
    private static char sub = '-';
    private static char mul = '*';
    private static char div1 = '/';
    private static char div2 = '/';


    public TestMathService(){
        math = new MathService();
    }
    @Test
    public void testOperator(){
        String addOp = "23.00+45.00";
        String subOp = "43.00-32.00";
        String mulOp = "3.00 * 2.00";
        String divOp = "2.00 / 2.00";
        String divOp2 ="2.00/2.00";
        char addTest = math.determineOperator(addOp);
        char subTest = math.determineOperator(subOp);
        char mulTest = math.determineOperator(mulOp);
        char div1Test = math.determineOperator(divOp);
        char div2Test = math.determineOperator(divOp2);

        System.out.println(addTest);
        System.out.println(subTest);
        System.out.println(mulTest);
        System.out.println(div1Test);
        System.out.println(div2Test);

        assertEquals(add, addTest);
        assertEquals(sub, subTest);
        assertEquals(mul, mulTest);
        assertEquals(div1, div1Test);
        assertEquals(div2, div2Test);



    }

    @Test
    public void testCaculate(){
    double firstValue = 5.00;
    double secondValue =3.00;

    double resultAdd = math.calculate(add, firstValue, secondValue);
    double resultSub = math.calculate(sub, firstValue, secondValue);
    double resultMul = math.calculate(mul, firstValue, secondValue);
    //double resultDiv1= math.calculate(div1, firstValue, secondValue);
    double resultDiv2 =math.calculate(div2, firstValue, secondValue);
    double test = firstValue / secondValue;
        System.out.println("Test: " + test);
    double expectedAdd = 8.00;
    double expectedSub = 2.00;
    double expectedMul = 15.00;
    double expectedDiv1 = 1.66;
    double expectedDiv2 = 1.6666666666666667;
    boolean conditionAdd = resultAdd==expectedAdd;
    boolean conditionSub = resultSub==expectedSub;
    boolean conditionMul = resultMul==expectedMul;
    //boolean conditionDiv1 = resultDiv1==expectedDiv1;
    boolean conditionDiv2=resultDiv2==expectedDiv2;

        System.out.println(resultAdd);
        System.out.println(resultSub);
        System.out.println(resultMul);
        System.out.println(resultDiv2);
    assertTrue(conditionAdd);
    assertTrue(conditionSub);
    assertTrue(conditionMul);
    //assertTrue(conditionDiv1);
   assertTrue(conditionDiv2);


    }
}
