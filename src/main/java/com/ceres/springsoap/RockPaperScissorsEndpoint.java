package com.ceres.springsoap;

import com.ceres.springsoap.soap.FinalResultType;
import com.ceres.springsoap.soap.RockPaperScissorsMatch;
import com.ceres.springsoap.soap.WinnerType;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class RockPaperScissorsEndpoint {

    private static final String NAMESPACE_URI = "http://soap.springsoap.ceres.com";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RockPaperScissorsMatch")
    @ResponsePayload
    public RockPaperScissorsMatch processMatch(
            @RequestPayload RockPaperScissorsMatch request) {

        // Compute final winner based on scores
        int player1Score = request.getPlayers().getPlayer().get(0).getScore();
        int player2Score = request.getPlayers().getPlayer().get(1).getScore();

        FinalResultType finalResult = new FinalResultType();

        if (player1Score > player2Score) {
            finalResult.setWinner(WinnerType.PLAYER_1);
        } else if (player2Score > player1Score) {
            finalResult.setWinner(WinnerType.PLAYER_2);
        } else {
            finalResult.setWinner(WinnerType.DRAW);
        }

        finalResult.setTotalRounds(request.getRounds().getRound().size());

        request.setFinalResult(finalResult);

        return request;
    }
}
