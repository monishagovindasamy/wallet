package com.leovegas.wallet.controller;


import com.leovegas.wallet.model.AccountModel;
import com.leovegas.wallet.model.PlayerModel;
import com.leovegas.wallet.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Player controller.
 */
@RestController
@RequestMapping("player")
public class PlayerController {

    private final PlayerService playerService;

    /**
     * Instantiates a new Player controller.
     *
     * @param playerService the player service
     */
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Gets balance by player.
     *
     * @param id the id
     * @return the balance by player
     */
    @GetMapping("{playerId}/balance")
    public AccountModel getBalanceByPlayer(@PathVariable("playerId") Long id) {
        return playerService.getBalanceByPlayer(id);
    }

    /**
     * Create player model.
     *
     * @param playerModel the player model
     * @return the player model
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerModel create(@RequestBody PlayerModel playerModel) {
        return playerService.create(playerModel);
    }
}
