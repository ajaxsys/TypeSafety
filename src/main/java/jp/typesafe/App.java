package jp.typesafe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MVC の場合
 */
public class App {
    public static void main(String[] args) {

        // View: 画面層
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("a");

        // その他の処理...


        // Controller: コントロール層
        if (!list.isEmpty()) { // 状態1:NotEmpty
            sort(list);        // 状態2:Sorted
            // 問題２、list自身の状態が変わる可能性あり、sorted、remove...

            // その他の処理...

            // Model: モデル層
            List<String> listUnique = distict(list);
                               // 状態3:Distinct
            saveToDB(listUnique);
        }

    }

    public static void saveToDB(List<String> list) {
        // 問題１：違うチーム開発する人は、上記の３つ状態が前提としてら、
        // Distinct済み、Sort済み、Emptyチェック済み
        // なら、どうする？
        // メソッド内部、自己責任で一度チェックするか,
        // 利用側に信じて、何もしないか、
        // 解決案：TpyeSafety

        if (!list.isEmpty() && isSorted(list) && isDistincted(list)) {

            System.out.println("Save to db:" + list);
        }
    }

    private static boolean isDistincted(List<String> list) {
        return true; // TODO
    }

    private static boolean isSorted(List<String> list) {
        return true; // TODO
    }


    private static List<String> distict(List<String> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }

    private static void sort(List<String> list) {
        list.sort(
            (a,b)->
                a.compareTo(b));
    }
}
