import java.util.HashMap;
import java.util.Scanner;

import com.alibaba.fastjson.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner myObj = new Scanner(System.in);

        System.out.println("Have you already created a game? (Y/N)");
        String yn = myObj.nextLine();

        if (yn.toLowerCase().equals("y")) {

            System.out.println("Please enter the game id, board size, target and priority of our team (1 or 2) to join in (each element separated by space): ");
            String[] inputSplit = myObj.nextLine().split(" ");
            String gameId = inputSplit[0].trim();
            int boardSize = Integer.parseInt(inputSplit[1]), target = Integer.parseInt(inputSplit[2]), priority = Integer.parseInt(inputSplit[3]);
            if (priority == 1) {
                Logic logic = new Logic(Integer.parseInt(gameId), boardSize, target, "O", "X");
                logic.start();
            } else if (priority == 2) {
                Logic logic = new Logic(Integer.parseInt(gameId), boardSize, target, "X", "O");
                logic.start();
            }
        } else if (yn.toLowerCase().equals("n")) {
            System.out.println("Please enter the team id of opponent, board size, target and priority of our team (1 or 2) to create a game (each element separated by space): ");
            String[] inputSplit = myObj.nextLine().split(" ");
            String opponentTeamId = inputSplit[0].trim();
            int boardSize = Integer.parseInt(inputSplit[1]), target = Integer.parseInt(inputSplit[2]), priority = Integer.parseInt(inputSplit[3]);
            int gameId = createGame(opponentTeamId, boardSize, target, priority);
            if (priority == 1) {
                Logic game = new Logic(gameId, boardSize, target, "O", "X");
                game.start();
            } else if (priority == 2) {
                Logic game = new Logic(gameId, boardSize, target, "X", "O");
                game.start();
            }

        }
    }

    public static int createGame(String opponentTeamId, int boardSize, int target, int priority) throws Exception {
        Requests requests = new Requests();
//        HashMap<String, String> params2 = new HashMap<String, String>();
//        params2.put("type", "removeMember");
//        params2.put("teamId", Integer.toString(1291));
//        params2.put("usrId", Integer.toString(1110));
//        //Requests requests = new Requests();
//        String result2 = requests.post(params2);
//
//        JSONObject parsedResult2 = JSON.parseObject(result2);
//        if (!parsedResult2.getString("code").contains("OK")) {
//            throw new Exception("ERROR: making move: " + result2);
//        } else {
//            System.out.println("Remove success" + result2);
//        }
//
        //Requests requests = new Requests();
        HashMap<String, String> params1 = new HashMap<String, String>();
        params1.put("type", "member");
        params1.put("teamId", "1291");
        params1.put("usrId", "1110");
        String result1 = requests.post(params1);
        JSONObject parsedResult1 = JSON.parseObject(result1);
        if (!parsedResult1.getString("code").equals("OK")) {
            throw new Exception("ERROR in add member: " + result1);
        } else {
            System.out.println("Success add member" + result1);
        }

        HashMap<String, String> params5 = new HashMap<String, String>();
        params5.put("type", "member");
        params5.put("teamId", "1291");
        params5.put("usrId", "1110");
        String result5 = requests.post(params5);
        JSONObject parsedResult5 = JSON.parseObject(result5);
        if (!parsedResult5.getString("code").equals("OK")) {
            throw new Exception("ERROR in add member: " + result5);
        } else {
            System.out.println("Success add member" + result5);
        }
//
//        HashMap<String, String> params3 = new HashMap<String, String>();
//        params3.put("type", "myTeams");
//        String result3 = requests.get(params3);
//        JSONObject parsedResult3 = JSON.parseObject(result3);
//        if (!parsedResult3.getString("code").equals("OK")) {
//            throw new Exception("ERROR in find member: " + result3);
//        } else {
//            System.out.println("Success find member" + result3);
//        }

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("type", "game");
        if (priority == 1) {
            // Move first
            params.put("teamId1", "1291");
            params.put("teamId2", opponentTeamId);
        } else if (priority == 2) {
            // Move second
            params.put("teamId1", opponentTeamId);
            params.put("teamId2", "1291");
        }
        params.put("gameType", "TTT");
        params.put("boardSize", Integer.toString(boardSize));
        params.put("target", Integer.toString(target));
        String result = requests.post(params);

        JSONObject parsedResult = JSON.parseObject(result);
        if (!parsedResult.getString("code").equals("OK")) {
            throw new Exception("ERROR in game creation: " + result);
        } else {
            System.out.println("New game " + Integer.toString(parsedResult.getInteger("gameId")));
            return parsedResult.getInteger("gameId");
        }
    }
}
