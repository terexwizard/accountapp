/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scc.pay.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author terex
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String d = "25570102";
        
        DecimalFormat df2 = new DecimalFormat( "#,##0.00" );
        
        System.out.println(df2.format(new BigDecimal("12345")));
        
    }
}
