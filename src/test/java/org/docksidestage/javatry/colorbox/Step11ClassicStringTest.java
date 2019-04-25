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
package org.docksidestage.javatry.colorbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.docksidestage.bizfw.colorbox.AbstractColorBox;
import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.impl.StandardColorBox;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.javatry.colorbox.base.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, not using Stream API. <br>
 * Show answer by log() for question of javadoc. <br>
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author yuta.doi
 */
public class Step11ClassicStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        ColorBox colorBox = colorBoxList.get(0);
        BoxColor boxColor = colorBox.getColor();
        String colorName = boxColor.getColorName();
        int answer = colorName.length();
        log(answer, colorName); // also show name for visual check
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String maxStr = null;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    String strContent = (String) content;
                    int currentLength = ((String) content).length();
                    if (maxStr == null || maxStr.length() < currentLength) {
                        maxStr = strContent;
                    }
                }
            }
        }
        log(maxStr != null ? maxStr : "*Not found String content");
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        String maxStr = null;
        String minStr = null;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    String strContent = (String) content;
                    int currentLength = ((String) content).length();
                    if (maxStr == null || maxStr.length() < currentLength) {
                        maxStr = strContent;
                    }
                    if (minStr == null || minStr.length() > currentLength) {
                        minStr = strContent;
                    }
                }
            }
        }
        log(maxStr != null ? maxStr : "*Not found String content");
        log(minStr != null ? minStr : "*Not found String content");
        assert maxStr != null;
        log(maxStr.length() - minStr.length());
    }

    /**
     * Which value (toString() if non-string) has second-max legnth in color-boxes? (without sort)<br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (ソートなしで))
     */
    public void test_length_findSecondMax() {

        List<String> content_string_list = get_content_string_list();
        Collections.reverse(content_string_list);

        String str_max = find_max(content_string_list);

        content_string_list.remove(str_max);
        String str_second_max = find_max(content_string_list);

        log(str_max.length() + ": " + str_max);
        log(str_second_max.length() + ": " +str_second_max);
        log_answer(str_second_max);
    }

    private List<String> get_content_string_list() {
        List<String> str_list = new ArrayList<>();
        List<Object> content_list = get_content_list();

        for (Object content : content_list) {
            if (content != null) {
                String contentStr = content.toString();
                str_list.add(contentStr);
            }
        }
        return str_list;
    }

    private String find_max(List<String> str_list){
        String max_content = null;

        for (String contentStr : str_list) {
            if (max_content == null || max_content.length() < contentStr.length()) {
                    max_content = contentStr;
            }
        }
        return max_content;
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int sum_length = 0; // 最終的な答えが0なのか、バグで、回らなかった場合でも、答えが0になってしまう。
                            // ここを、nullにして、格納する際に初めて、数値を追加する。
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    sum_length += ((String) content).length();
                }
            }
        }
        log_answer(sum_length);
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        BoxColor boxColor = null; // ここで、文字列を保存する方がスマート
        if (colorBoxList.isEmpty()) {
            log("あなたの部屋に、カラーボックスがありません。");
            return;
        }

        for (ColorBox colorBox : colorBoxList) {
            BoxColor color = colorBox.getColor();
            int length = color.getColorName().length();
            if (boxColor == null || boxColor.getColorName().length() < length) {
                boxColor = color;
            }
        }

        log_answer(boxColor.getColorName());
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (colorBoxList.isEmpty()) {
            log("あなたの部屋に、カラーボックスがありません。");
            return;
        }

        ColorBox box_in_water = null; // ここをリストで作るという人もいる。これは問題文の読み方
        String content_water = null;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    String contentStr = (String) content;
                    if (contentStr.startsWith("Water")){
                       box_in_water = colorBox;
                       content_water = contentStr;
                       break;
                    }
                }
            }
        }

        log(content_water);
        log_answer(box_in_water != null ? box_in_water.getColor().getColorName() : "*Not found box_in_water");
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        String target_str = "front";
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (colorBoxList.isEmpty()) {
            log("あなたの部屋に、カラーボックスがありません。");
            return;
        }

        ColorBox front_colorBox = null;
        String content_end_front = null;
        outer_loop:for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    String contentStr = (String) content;
                    if (contentStr.endsWith(target_str)) {
                        front_colorBox = colorBox;
                        content_end_front = contentStr;
                        break outer_loop;
                    }
                }
            }
        }
        // outer_loopは、forにタグを付けて、二重化されているループを一気に飛ばすことができる。

        if (front_colorBox == null) {
            log( "{}で終わる文字列は存在しません", target_str); // logから、formatを使うことができる。
            return;
        }
        log(content_end_front);
        log_answer(front_colorBox.getColor().getColorName());
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with "front" of string ending with "front" in color-boxes? <br>
     * (あなたのカラーボックスに入ってる "front" で終わる文字列で、"front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        String target_str = "front";
        String searched_content = get_str_endWith(target_str);
        if (searched_content == null) {
            log("{} で終わる文字列は存在しません", target_str);
            return;
        }

        int target_start_point = searched_content.indexOf(target_str)+1;
        log(searched_content);
        log_answer(target_start_point);
    }

    private String get_str_endWith(String end_str) {

        List<Object> content_list = get_content_list();
        for (Object content : content_list) {
            if (content instanceof String) {
                String contentStr = (String) content;
                if (contentStr.endsWith(end_str)) {
                    return contentStr;
                }
            }
        }
        return null;
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (あなたのカラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        String search_target = "ど";
        List<Object> content_list = get_content_list();
        for (Object content : content_list) {
            if (content instanceof String){
                int start_point_target_str = ((String) content).indexOf(search_target);
                if (start_point_target_str == -1) continue;

                int end_point_target_str = ((String) content).lastIndexOf(search_target);
                if (end_point_target_str != -1 && start_point_target_str != end_point_target_str){
                    log(content);
                    log_answer(end_point_target_str+1);
                    return;
                }
            }

        }
        log("{}を二つ以上含む文字列は存在しません", search_target);
    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * What character is first of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列の最初の一文字は？)
     */
    public void test_substring_findFirstChar() {
        String target_str = "front";
        String searched_str = get_str_endWith(target_str);
        if (searched_str == null) {
            log( "{}で終わる文字列は存在しません", target_str);
            return;
        }

        log(searched_str);

        String first_char = searched_str.substring(0, 1);
        log_answer(first_char);
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        String target_str = "Water";
        String str_startWith = get_str_startWith(target_str);
        if (str_startWith == null) {
            log(target_str + " で始まる文字列は存在しません");
            return;
        }

        String last_char = str_startWith.substring(str_startWith.length() - 1);
        log(str_startWith);
        log_answer(last_char);
    }

    private String get_str_startWith(String start_str) {
        List<Object> content_list = get_content_list();
        for (Object content : content_list) {
            if (content instanceof String) {
                String contentStr = (String) content;
                if (contentStr.startsWith(start_str)) {
                    return contentStr;
                }
            }
        }
        return null;
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * How many characters does string that contains "o" in color-boxes and removing "o" have? <br>
     * (カラーボックスに入ってる "o" (おー) を含んだ文字列から "o" を全て除去したら何文字？)
     */
    public void test_replace_remove_o() {
        String target_str = "o";
        String str_containing_target = null;
        List<String> strings = get_strContent_list();

        for (String string : strings) {
            if (string.contains(target_str)) {
                str_containing_target = string;
                break;
            }
        }
        if (str_containing_target == null) {
            log(target_str + " を含んだ文字列は存在しません");
            return;
        }

        String str_replaced = str_containing_target.replace(target_str, "");
        log(str_containing_target);
        log(str_replaced);
        log_answer(str_replaced.length());

    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        String linux_file_separator = "/";
        String win_file_separator = "\\";

        List<java.io.File> files = get_fileContent_list();

        if (files.isEmpty()) {
            log("java.io.File型のコンテンツは存在しません");
            return;
        }

        String file_str = files.get(0).getPath();
        String replaced_file_str = file_str.replace(linux_file_separator, win_file_separator);
        log(file_str);
        log_answer(replaced_file_str);
    }

    // ===================================================================================
    //                                                                    Welcome to Devil
    //                                                                    ================
    /**
     * What is total length of text of DevilBox class in color-boxes? <br>
     * (カラーボックスの中に入っているDevilBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToDevil() {
        List<Object> content_list = get_content_list();
        List<YourPrivateRoom.DevilBox> devilBoxes = new ArrayList<>();
        for (Object content : content_list) {
            if (content instanceof YourPrivateRoom.DevilBox) {
                devilBoxes.add((YourPrivateRoom.DevilBox) content);
            }
        }

        int sum_length_of_all_devil = 0;
        for (YourPrivateRoom.DevilBox devilBox : devilBoxes) {
            devilBox.wakeUp();
            devilBox.allowMe();
            devilBox.open();

            String devils_text;
            try {
                devils_text = devilBox.getText();
            } catch (YourPrivateRoom.DevilBoxTextNotFoundException e) {
                devils_text = "";
            }
            sum_length_of_all_devil += devils_text.length();
            log(devils_text);
        }

        log_answer(sum_length_of_all_devil);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
        List<Map> mapContent_list = get_mapContent_list();

        for (Map map : mapContent_list) {
            StringBuilder show_map = new StringBuilder("map:{ ");
            for (Object entry : map.entrySet()) {
                show_map.append(entry.toString()).append(" ; ");
            }
            show_map.append(" }");
            log(show_map);
        }
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
        List<Map> mapContent_list = get_mapContent_list();

        for (Map map : mapContent_list) {
            log(get_str_map_value(map));
        }
    }

    private String get_str_map_value(java.util.Map map){
        StringBuilder show_map = new StringBuilder("map:{ ");

        boolean multiple_flag = false;
        for (Object key : map.keySet()) {
            if (multiple_flag) show_map.append(" ; "); // 最後の、セミコロンは入れない

            Object value = map.get(key);
            String valueStr;
            if (value instanceof Map) {
                Map valueMap = (Map) value;
                valueStr = get_str_map_value(valueMap);
            }else{
                valueStr = value.toString();
            }
            show_map.append(key.toString()).append(" = ").append(valueStr);
            multiple_flag = true;
        }
        return show_map.append(" }").toString();
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            if (colorBox.getColor().getColorName().equals("white")) {
                BoxSpace upperSpace = ((StandardColorBox) colorBox).getUpperSpace();
                Object content = upperSpace.getContent();
                if (content instanceof YourPrivateRoom.SecretBox) {
                    String text = ((YourPrivateRoom.SecretBox) content).getText();
                    log(text);
                }
            }
        }
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            if (colorBox.getColor().getColorName().equals("white")) {
                BoxSpace upperSpace = ((StandardColorBox) colorBox).getMiddleSpace();
                Object content = upperSpace.getContent();
                if (content instanceof YourPrivateRoom.SecretBox) {
                    String text = ((YourPrivateRoom.SecretBox) content).getText();
                    log(text);
                }
            }
        }
    }


    private Map comvert_map_from_text(String text){

        if (!text.startsWith("map:{") || !text.endsWith("}")) {
            throw new IllegalStateException("mapを表現しているテキストになっていません");
        }

        text.replace("map:{", "");

    }


    // ===================================================================================
    //  以下便利ツール

    private void log_answer(Object ans_text){
        log("[ans] -> " + ans_text);
    }

    private List<Object> get_content_list() {
        List<Object> contentList = new ArrayList<>();
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                contentList.add(content);
            }
        }

        if (contentList.isEmpty()) {
            throw new IllegalStateException("YourPrivateRoomにコンテンツが入っていません！！");
        }
        return contentList;
    }

    private List<String> get_strContent_list(){
        List<Object> content_list = get_content_list();
        List<String> new_content_list = new ArrayList<>();
        for (Object content : content_list) {
            if (content instanceof String){
                String contentStr = (String) content;
                new_content_list.add(contentStr);
            }
        }
        return new_content_list;
    }

    private List<java.io.File> get_fileContent_list(){
        List<Object> content_list = get_content_list();
        List<java.io.File> new_content_list = new ArrayList<>();
        for (Object content : content_list) {
            if (content instanceof java.io.File){
                java.io.File contentStr = (java.io.File) content;
                new_content_list.add(contentStr);
            }
        }
        return new_content_list;
    }

    private List<java.util.Map> get_mapContent_list(){
        List<Object> content_list = get_content_list();
        List<java.util.Map> new_content_list = new ArrayList<>();
        for (Object content : content_list) {
            if (content instanceof java.util.Map){
                java.util.Map contentStr = (java.util.Map) content;
                new_content_list.add(contentStr);
            }
        }
        return new_content_list;
    }
}
