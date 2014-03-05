package com.iokays.reservation.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;

import com.iokays.reservation.domain.Reservation;

public interface ReservationService {

    public abstract List<Reservation> findAll(Sort sort);

    public abstract Reservation save(Reservation reservation);

    public abstract Integer update(Serializable id, Map<String, Object> map) throws Exception;

    public abstract Integer delete(Serializable id);

    public abstract Reservation findOne(Serializable id);

}