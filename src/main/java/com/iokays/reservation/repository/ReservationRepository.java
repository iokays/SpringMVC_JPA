package com.iokays.reservation.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iokays.reservation.domain.Reservation;
import com.iokays.reservation.repository.plus.ReservationRepositoryPlus;

public interface ReservationRepository extends JpaRepository<Reservation, Serializable>, ReservationRepositoryPlus {

}
