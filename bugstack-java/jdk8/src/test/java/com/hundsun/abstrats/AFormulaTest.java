package com.hundsun.abstrats;

import com.hundsun.abstracts.AFormula;
import com.hundsun.interfaces.IFormula;
import org.junit.Test;

public class AFormulaTest {
    @Test
    public void test_00() {
        AFormula aFormula = new AFormula() {
            @Override
            public double calculate(int a) {
                return a * a;
            }
        };
        System.out.println(aFormula.calculate(2));
        System.out.println(aFormula.sqrt(2));
    }

    @Test
    public void test01() {
        IFormula iFormula = new IFormula() {
            @Override
            public double calculate(int a) {
                return a * a;
            }
        };
        System.out.println(iFormula.calculate(2));
        System.out.println(iFormula.sqrt(2));
    }

    @Test
    public void test02() {
        IFormula iFormula = a -> a * a;
        System.out.println(iFormula.calculate(2));
        System.out.println(iFormula.sqrt(2));
    }
}
