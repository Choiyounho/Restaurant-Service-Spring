package com.soten.eatgo.reservation;

import com.soten.eatgo.reservation.controller.ReservationController;
import com.soten.eatgo.reservation.domain.Reservation;
import com.soten.eatgo.reservation.service.ReservationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    @DisplayName("/reservations 가게 예약")
    void create() throws Exception {

        Reservation mockReservation = Reservation.builder().id(12L).build();

        given(reservationService.addReservation(any(), any(), any(), any(), any(), any()))
                .willReturn(mockReservation);

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKb2huIn0.8hm6ZOJykSINHxL-rf0yV882fApL3hyQ9-WGlJUyo2A";

        mvc.perform(post("/restaurants/1004/reservations")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"date\":\"2020-12-06\",\"time\":\"20:00\", \"partySize\":20}"))
                .andExpect(status().isCreated());

        Long userId = 1004L;
        String name = "John";
        String date = "2020-12-06";
        String time = "20:00";
        Integer partySize = 20;


        verify(reservationService).addReservation(eq(1004L), eq(userId), eq(name), eq(date), eq(time), eq(partySize));
    }

}
