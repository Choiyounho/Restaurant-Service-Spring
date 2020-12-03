package com.soten.eatgo.reservation.controller;

import com.soten.eatgo.reservation.service.ReservationService;
import com.soten.eatgo.reservation.domain.Reservation;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(value = "/restaurants/{restaurantId}/reservations")
    public ResponseEntity<?> create(Authentication authentication,
                                    @PathVariable Long restaurantId,
                                    @Valid @RequestBody Reservation resource) throws URISyntaxException {
        Claims claims = (Claims) authentication.getPrincipal();

        Long userId = claims.get("userId", Long.class);
        String name = claims.get("name", String.class);
        String date = resource.getDate();
        String time = resource.getTime();
        Integer partySize = resource.getPartySize();

        Reservation reservation = reservationService.addReservation(restaurantId, userId, name, date, time, partySize);

        String url = "/restaurants/" + restaurantId + "/reservations/" + reservation.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

}
