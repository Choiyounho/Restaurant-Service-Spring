package com.soten.eatgo.reservation.service;

import com.soten.eatgo.reservation.domain.Reservation;
import com.soten.eatgo.reservation.domain.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class ReservationServiceTest {

    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        reservationService = new ReservationService(reservationRepository);
    }

    private List<Reservation> initReservations() {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(Reservation.builder()
                .id(1L)
                .userName("hangyeol")
                .build());

        reservations.add(Reservation.builder()
                .id(2L)
                .userName("woorim")
                .build());
        return reservations;
    }

    @Test
    @DisplayName("예약 불러오기")
    void getReservation() {
        List<Reservation> reservations = initReservations();

        given(reservationRepository.findAllByRestaurantId(1004L))
                .willReturn(reservations);

        Reservation reservation = reservationService.getReservations(1004L).get(1);

        assertThat(reservation.getUserName()).isEqualTo("woorim");
    }

    @Test
    @DisplayName("총 예약 내역 불러오기")
    void getReservations() {
        List<Reservation> reservations = initReservations();

        given(reservationRepository.findAllByRestaurantId(1004L))
                .willReturn(reservations);

        List<Reservation> completedReservation = reservationService.getReservations(1004L);

        assertThat(completedReservation).hasSize(2);
    }

}
