/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.basic;

import java.math.BigDecimal;

import org.docksidestage.unit.PlainTestCase;

/**
 * @author jflute
 */
public class Step01VariableTest extends PlainTestCase {

    /**
     * Stringの変数 sea を宣言して、"mystic" で初期化して、log()で出力すると？
     */
    public void test_variable_basic() { // execute after answer
        String sea = "mystic";
        log(sea); // your answer => mystic
    }

    /**
     * 最終的な変数 sea の中身は？
     */
    public void test_variable_reassigned_basic() { // execute after answer
        String sea = "mystic";
        String land = "oneman";
        sea = land;
        log(sea); // your answer =>
    }

    /**
     * 最終的な変数 sea の中身は？
     */
    public void test_variable_reassigned_int() { // execute after answer
        int sea = 94;
        int land = 415;
        sea = land;
        land++;
        log(sea); // your answer =>
    }

    /**
     * 最終的な変数 sea の中身は？
     */
    public void test_variable_reassigned_BigDecimal() { // execute after answer
        BigDecimal sea = new BigDecimal(94);
        BigDecimal land = new BigDecimal(415);
        sea = land;
        sea = land.add(new BigDecimal(1));
        sea.add(new BigDecimal(1));
        log(sea); // your answer =>
    }
}
