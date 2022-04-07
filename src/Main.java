import java.io.*;
import java.util.*;
import java.lang.Math;
public class Main {
    static boolean is_player_win;
    static boolean is_ai_win;
    static int ai_move_row;
    static int ai_move_colmn;
    public static void main(String[] args){
        int n = 6;
        int m = 3;
        //初始化数组
        String[][] Tic_Tac_Toe = new String[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                Tic_Tac_Toe[i][j] = "";
            }
        }

        boolean is_player = false;
        boolean is_ai = true;
        String player = "O";
        String ai = "X";
        is_player_win = false;
        is_ai_win = false;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("ai_win", 10);
        map.put("player_win", -10);
        map.put("tie", 0);
        ai_move_row = 0;
        ai_move_colmn = 0;
        while (!check(Tic_Tac_Toe, n, m) && !check_full(Tic_Tac_Toe, n)){
            if (is_ai == true){
                best_move(Tic_Tac_Toe, n, m, map);
//                ai_move_row = i;
//                ai_move_colmn = j;
                System.out.println(ai_move_row);
                System.out.println(ai_move_colmn);



                is_ai = false;
                is_player = true;
                //需要把选择的位置传出
            }else{
                human(Tic_Tac_Toe, n);
                is_player = false;
                is_ai = true;
            }
        }

        if (is_player_win == true){
            System.out.println("player win");
        }
        else if (is_ai_win == true){
            System.out.println("ai win");
        }else{
            System.out.println("tie");
        }

    }
    public static void human(String[][] Tic_Tac_Toe, int n){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (Tic_Tac_Toe[i][j] == ""){
                    Tic_Tac_Toe[i][j] = "O";
                    return;
                }
            }
        }
    }
    public static void best_move(String[][] Tic_Tac_Toe, int n, int m, HashMap<String, Integer> map){
        int max_score = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (Tic_Tac_Toe[i][j] == ""){
                    Tic_Tac_Toe[i][j] = "X";
                    int alpha = Integer.MIN_VALUE;
                    int beta = Integer.MAX_VALUE;
                    int score = Alpha_beta_search(Tic_Tac_Toe, n, m, map, false, alpha, beta);
                    Tic_Tac_Toe[i][j] = "";
                    if (score > max_score){
                        max_score = score;
                        ai_move_row = i;
                        ai_move_colmn = j;
                    }
                }
            }
        }
        Tic_Tac_Toe[ai_move_row][ai_move_colmn] = "X";
    }

    public static int Alpha_beta_search(String[][] Tic_Tac_Toe, int n, int m, HashMap<String, Integer> map, boolean is_max, int alpha, int beta){
        String str = result(Tic_Tac_Toe, n, m);
        if (str != ""){
            return map.get(str);
        }
        if (is_max){
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (Tic_Tac_Toe[i][j] == ""){
                        Tic_Tac_Toe[i][j] = "X";
                        int score = Alpha_beta_search(Tic_Tac_Toe, n, m, map, false, alpha, beta);
                        Tic_Tac_Toe[i][j] = "";
                        max = Math.max(max, score);
                        if (max >= beta){
                            return max;
                        }
                        alpha = Math.max(alpha, max);
                    }
                }
            }
            return max;
        }else{
            int max = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (Tic_Tac_Toe[i][j] == ""){
                        Tic_Tac_Toe[i][j] = "O";
                        int score = Alpha_beta_search(Tic_Tac_Toe, n, m, map, true, alpha, beta);
                        Tic_Tac_Toe[i][j] = "";
                        max = Math.min(max, score);
                        if (max <= alpha){
                            return max;
                        }
                        beta = Math.min(beta, max);
                    }
                }
            }
            return max;
        }
    }
    public static boolean check_full(String[][] Tic_Tac_Toe, int n){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (Tic_Tac_Toe[i][j] == ""){
                    return false;
                }
            }
        }
        return true;
    }
    public static String result(String[][] Tic_Tac_Toe, int n, int m){

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (Tic_Tac_Toe[i][j].equals("X") && check_X_result(Tic_Tac_Toe, i, j, n, m)){
                    return "ai_win";
                }
                else if (Tic_Tac_Toe[i][j].equals("O") && check_O_result(Tic_Tac_Toe, i, j, n, m)){
                    return "player_win";
                }
            }
        }
        if (check_full(Tic_Tac_Toe, n)){
            return "tie";
        }
        return "";
    }
    public static boolean check_X_result(String[][] Tic_Tac_Toe, int i, int j, int n, int m){
        //check row
        int front = j;
        int behind = j;
        int front_num = 0;
        int behind_num = 0;
        for (int c = j; c < n; c++){
            if(Tic_Tac_Toe[i][c].equals("X")){
                front_num++;
            }else{
                break;
            }
        }
        for (int c = j; c >= 0; c--){
            if(Tic_Tac_Toe[i][c].equals("X")){
                behind_num++;
            }else{
                break;
            }
        }
        if (front_num == 0 || behind_num == 0){
            if (front_num >= m || behind_num >= m){

                return true;
            }
        }else{
            if ((front_num + behind_num - 1) >= m){

                return true;
            }
        }

        //check column
        int up = i;
        int down = i;
        int up_num = 0;
        int down_num = 0;
        for (int r = i; r < n; r++){
            if(Tic_Tac_Toe[r][j].equals("X")){
                down_num++;
            }else{
                break;
            }
        }
        for (int r = i; r >= 0; r--){
            if(Tic_Tac_Toe[r][j].equals("X")){
                up_num++;
            }else{
                break;
            }
        }
        if (up_num == 0 || down_num == 0){
            if (up_num >= m || down_num >= m){

                return true;
            }
        }else{
            if ((up_num + down_num - 1) >= m){
                return true;
            }
        }

        //check diagonal
        int up_diagonal = i;
        int down_diagonal = i;
        int up_diagonal_num = 0;
        int down_diagonal_num = 0;
        int r = i;
        int c = j;
        while (r < n && c < n){
            if (Tic_Tac_Toe[r][c].equals("X")){
                down_diagonal_num++;
            }else{
                break;
            }
            r++;
            c++;
        }
        r = i;
        c = j;
        while (r >= 0 && c >= 0){
            if (Tic_Tac_Toe[r][c].equals("X")){
                up_diagonal_num++;
            }else{
                break;
            }
            r--;
            c--;
        }
        if (up_diagonal_num == 0 || down_diagonal_num == 0){
            if (up_diagonal_num >= m || down_diagonal_num >= m){
                return true;
            }
        }else{
            if ((up_diagonal_num + down_diagonal_num - 1) >= m){
                return true;
            }
        }

        return false;
    }
    public static boolean check_O_result(String[][] Tic_Tac_Toe, int i, int j, int n, int m){
        //check row
        int front = j;
        int behind = j;
        int front_num = 0;
        int behind_num = 0;
        for (int c = j; c < n; c++){
            if(Tic_Tac_Toe[i][c].equals("O")){
                front_num++;
            }else{
                break;
            }
        }
        for (int c = j; c >= 0; c--){
            if(Tic_Tac_Toe[i][c].equals("O")){
                behind_num++;
            }else{
                break;
            }
        }
        if (front_num == 0 || behind_num == 0){
            if (front_num >= m || behind_num >= m){
                return true;
            }
        }else{
            if ((front_num + behind_num - 1) >= m){
                return true;
            }
        }

        //check column
        int up = i;
        int down = i;
        int up_num = 0;
        int down_num = 0;
        for (int r = i; r < n; r++){
            if(Tic_Tac_Toe[r][j].equals("O")){
                down_num++;
            }else{
                break;
            }
        }
        for (int r = i; r >= 0; r--){
            if(Tic_Tac_Toe[r][j].equals("O")){
                up_num++;
            }else{
                break;
            }
        }
        if (up_num == 0 || down_num == 0){
            if (up_num >= m || down_num >= m){
                return true;
            }
        }else{
            if ((up_num + down_num - 1) >= m){
                return true;
            }
        }

        //check diagonal
        int up_diagonal = i;
        int down_diagonal = i;
        int up_diagonal_num = 0;
        int down_diagonal_num = 0;
        int r = i;
        int c = j;
        while (r < n && c < n){
            if (Tic_Tac_Toe[r][c].equals("O")){
                down_diagonal_num++;
            }else{
                break;
            }
            r++;
            c++;
        }
        r = i;
        c = j;
        while (r >= 0 && c >= 0){
            if (Tic_Tac_Toe[r][c].equals("O")){
                up_diagonal_num++;
            }else{
                break;
            }
            r--;
            c--;
        }
        if (up_diagonal_num == 0 || down_diagonal_num == 0){
            if (up_diagonal_num >= m || down_diagonal_num >= m){
                return true;
            }
        }else{
            if ((up_diagonal_num + down_diagonal_num - 1) >= m){
                return true;
            }
        }

        return false;
    }

    public static boolean check(String[][] Tic_Tac_Toe, int n, int m){

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (Tic_Tac_Toe[i][j].equals("X") && check_X(Tic_Tac_Toe, i, j, n, m)){
                    return true;
                }
                else if (Tic_Tac_Toe[i][j].equals("O") && check_O(Tic_Tac_Toe, i, j, n, m)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean check_X(String[][] Tic_Tac_Toe, int i, int j, int n, int m){
        //check row
        int front = j;
        int behind = j;
        int front_num = 0;
        int behind_num = 0;
        for (int c = j; c < n; c++){
            if(Tic_Tac_Toe[i][c].equals("X")){
                front_num++;
            }else{
                break;
            }
        }
        for (int c = j; c >= 0; c--){
            if(Tic_Tac_Toe[i][c].equals("X")){
                behind_num++;
            }else{
                break;
            }
        }
        if (front_num == 0 || behind_num == 0){
            if (front_num >= m || behind_num >= m){
                is_ai_win = true;
                return true;
            }
        }else{
            if ((front_num + behind_num - 1) >= m){
                is_ai_win = true;
                return true;
            }
        }

        //check column
        int up = i;
        int down = i;
        int up_num = 0;
        int down_num = 0;
        for (int r = i; r < n; r++){
            if(Tic_Tac_Toe[r][j].equals("X")){
                down_num++;
            }else{
                break;
            }
        }
        for (int r = i; r >= 0; r--){
            if(Tic_Tac_Toe[r][j].equals("X")){
                up_num++;
            }else{
                break;
            }
        }
        if (up_num == 0 || down_num == 0){
            if (up_num >= m || down_num >= m){
                is_ai_win = true;
                return true;
            }
        }else{
            if ((up_num + down_num - 1) >= m){
                is_ai_win = true;
                return true;
            }
        }

        //check diagonal
        int up_diagonal = i;
        int down_diagonal = i;
        int up_diagonal_num = 0;
        int down_diagonal_num = 0;
        int r = i;
        int c = j;
        while (r < n && c < n){
            if (Tic_Tac_Toe[r][c].equals("X")){
                down_diagonal_num++;
            }else{
                break;
            }
            r++;
            c++;
        }
        r = i;
        c = j;
        while (r >= 0 && c >= 0){
            if (Tic_Tac_Toe[r][c].equals("X")){
                up_diagonal_num++;
            }else{
                break;
            }
            r--;
            c--;
        }
        if (up_diagonal_num == 0 || down_diagonal_num == 0){
            if (up_diagonal_num >= m || down_diagonal_num >= m){
                is_ai_win = true;
                return true;
            }
        }else{
            if ((up_diagonal_num + down_diagonal_num - 1) >= m){
                is_ai_win = true;
                return true;
            }
        }

        return false;
    }
    public static boolean check_O(String[][] Tic_Tac_Toe, int i, int j, int n, int m){
        //check row
        int front = j;
        int behind = j;
        int front_num = 0;
        int behind_num = 0;
        for (int c = j; c < n; c++){
            if(Tic_Tac_Toe[i][c].equals("O")){
                front_num++;
            }else{
                break;
            }
        }
        for (int c = j; c >= 0; c--){
            if(Tic_Tac_Toe[i][c].equals("O")){
                behind_num++;
            }else{
                break;
            }
        }
        if (front_num == 0 || behind_num == 0){
            if (front_num >= m || behind_num >= m){
                is_player_win = true;
                return true;
            }
        }else{
            if ((front_num + behind_num - 1) >= m){
                is_player_win = true;
                return true;
            }
        }

        //check column
        int up = i;
        int down = i;
        int up_num = 0;
        int down_num = 0;
        for (int r = i; r < n; r++){
            if(Tic_Tac_Toe[r][j].equals("O")){
                down_num++;
            }else{
                break;
            }
        }
        for (int r = i; r >= 0; r--){
            if(Tic_Tac_Toe[r][j].equals("O")){
                up_num++;
            }else{
                break;
            }
        }
        if (up_num == 0 || down_num == 0){
            if (up_num >= m || down_num >= m){
                is_player_win = true;
                return true;
            }
        }else{
            if ((up_num + down_num - 1) >= m){
                is_player_win = true;
                return true;
            }
        }

        //check diagonal
        int up_diagonal = i;
        int down_diagonal = i;
        int up_diagonal_num = 0;
        int down_diagonal_num = 0;
        int r = i;
        int c = j;
        while (r < n && c < n){
            if (Tic_Tac_Toe[r][c].equals("O")){
                down_diagonal_num++;
            }else{
                break;
            }
            r++;
            c++;
        }
        r = i;
        c = j;
        while (r >= 0 && c >= 0){
            if (Tic_Tac_Toe[r][c].equals("O")){
                up_diagonal_num++;
            }else{
                break;
            }
            r--;
            c--;
        }
        if (up_diagonal_num == 0 || down_diagonal_num == 0){
            if (up_diagonal_num >= m || down_diagonal_num >= m){
                is_player_win = true;
                return true;
            }
        }else{
            if ((up_diagonal_num + down_diagonal_num - 1) >= m){
                is_player_win = true;
                return true;
            }
        }

        return false;
    }

}
