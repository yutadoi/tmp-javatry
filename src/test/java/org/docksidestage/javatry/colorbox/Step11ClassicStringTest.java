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
import java.util.List;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
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
 * @author your_name_here
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
        List<String> not_str_list = new ArrayList<>();


        String str_max = find_max_length_str_from_ColorBoxList(not_str_list);

        not_str_list.add(str_max);
        String str_secondMax = find_max_length_str_from_ColorBoxList(not_str_list);

        log(str_max.length() + ": " + str_max);
        log(str_secondMax.length() + ": " +str_secondMax);
        log_answer(str_secondMax);
    }

    private String find_max_length_str_from_ColorBoxList(List<String> exclude_list) {
        String max_content = null;

        List<Object> content_list = get_content_list();
        for (Object content : content_list) {
            if (content != null) {
                String contentStr = content.toString();
                if (max_content == null || max_content.length() < contentStr.length()) {
                    boolean can_add_str_flag = true;
                    for (String s : exclude_list) {
                        if (s.equals(contentStr)) {
                            can_add_str_flag = false;
                            break;
                        }
                    }
                    if (can_add_str_flag) {
                        max_content = contentStr;
                    }
                }
            }
        }

        if (max_content == null) {
            throw new IllegalStateException("全て除外リストで弾かれたため、最大値を見つけることができませんでした。");
        }
        return max_content;
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int sum_length = 0;
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
        BoxColor boxColor = null;
        for (ColorBox colorBox : colorBoxList) {
            BoxColor color = colorBox.getColor();
            int length = color.toString().length();
            if (boxColor == null || boxColor.toString().length() < length) {
                boxColor = color;
            }
        }
        assert boxColor != null;
        log_answer(boxColor.toString());
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
        ColorBox boxInWater = null;
        String contentStrWater = null;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    String contentStr = (String) content;
                    if (contentStr.startsWith("Water")){
                       boxInWater = colorBox;
                       contentStrWater = contentStr;
                       break;
                    }
                }
            }
        }
        log(boxInWater != null ? boxInWater : "*Not found String content");
        log_answer(contentStrWater);
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        String target_str = "front";
        ColorBox front_colorBox = get_colorBox_endWith("front");
        if (front_colorBox != null) {
            log_answer(front_colorBox.getColor());
        }
        else{
            log(target_str + "で終わる文字列は存在しません");
        }
    }

    private ColorBox get_colorBox_endWith(String end_str) {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            String contentStr = get_str_endWith_from_spaceList(spaceList, end_str);
            if (contentStr != null)
                return colorBox;
        }
        return null;
    }

    private String get_str_endWith_from_spaceList(List<BoxSpace> spaceList, String end_str) {
        for (BoxSpace boxSpace : spaceList) {
            Object content = boxSpace.getContent();
            if (content instanceof String) {
                String contentStr = (String) content;
                if (contentStr.endsWith(end_str)) {
                    return contentStr;
                }
            }
        }
        return null;
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
            log(target_str + " で終わる文字列は存在しません");
            return;
        }

        int target_start_point = searched_content.indexOf(target_str)+1;
        log(searched_content);
        log(target_start_point);
    }

    private String get_str_endWith(String end_str) {
        ColorBox color_box = get_colorBox_endWith(end_str);
        if (color_box == null) return null;

        List<BoxSpace> spaceList = color_box.getSpaceList();
        String contentStr = get_str_endWith_from_spaceList(spaceList, end_str);
        if (contentStr == null) return null;

        return contentStr;
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
                    log_answer(end_point_target_str);
                    return;
                }
            }

        }
        log(search_target + "を二つ以上含む文字列は存在しません");
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
            log(target_str + " で終わる文字列は存在しません");
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
        List<String> strings = fillter_strContent(content_list);
        for (String string : strings) {
            if (string.startsWith(start_str)) {
                return string;
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
        List<String> strings = fillter_strContent(get_content_list());
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
        String win_file_separator = "¥";

        List<Object> content_list = get_content_list();
        List<java.io.File> files = fillter_fileContent(content_list);

        if (files.isEmpty()) {
            log("java.io.File型のコンテンツは存在しません");
            return;
        }

        String file_str = files.get(0).toString();
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

            String devils_text = null;
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
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
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
        return contentList;
    }

    private List<String> fillter_strContent(List<Object> content_list){
        List<String> new_content_list = new ArrayList<>();
        for (Object content : content_list) {
            if (content instanceof String){
                String contentStr = (String) content;
                new_content_list.add(contentStr);
            }
        }
        return new_content_list;
    }

    private List<java.io.File> fillter_fileContent(List<Object> content_list){
        List<java.io.File> new_content_list = new ArrayList<>();
        for (Object content : content_list) {
            if (content instanceof java.io.File){
                java.io.File contentStr = (java.io.File) content;
                new_content_list.add(contentStr);
            }
        }
        return new_content_list;
    }
}