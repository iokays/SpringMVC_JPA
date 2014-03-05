package com.iokays.reservation.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iokays.reservation.domain.Reservation;
import com.iokays.reservation.repository.ReservationRepository;
import com.iokays.reservation.service.ReservationService;

@Service("reservationService")
@Transactional
public class ReservationServiceImpl implements ReservationService {

    /* (non-Javadoc)
     * @see com.iokays.homepage.service.impl.ReservationService#findAll(org.springframework.data.domain.Sort)
     */
    @Override
    public List<Reservation> findAll(Sort sort) {
        return reservationRepository.findAll(sort);
    }

    /* (non-Javadoc)
     * @see com.iokays.homepage.service.impl.ReservationService#save(com.iokays.homepage.domain.Reservation)
     */
    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Integer update(Serializable id, Map<String, Object> map) throws Exception {
        return reservationRepository.update(id, map);
    }

    /* (non-Javadoc)
     * @see com.iokays.homepage.service.impl.ReservationService#delete(java.io.Serializable)
     */
    @Override
    public Integer delete(Serializable id) {
        return reservationRepository.deleteById(id);
    }

    /* (non-Javadoc)
     * @see com.iokays.homepage.service.impl.ReservationService#findOne(java.io.Serializable)
     */
    @Override
    public Reservation findOne(Serializable id) {
        return reservationRepository.findOne(id);
    }

    @Resource
    private ReservationRepository reservationRepository;

}
