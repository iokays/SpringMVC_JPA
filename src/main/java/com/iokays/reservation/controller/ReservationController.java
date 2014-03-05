package com.iokays.reservation.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iokays.reservation.domain.Reservation;
import com.iokays.reservation.service.ReservationService;
import com.iokays.template.service.TemplateService;

@Controller
public class ReservationController {


    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public ModelAndView listAll() {
        ModelAndView mav = new ModelAndView("reservations");
        List<Reservation> reservations = reservationService.findAll(new Sort(Direction.DESC, "createDate"));
        mav.addObject("reservations", reservations);
        return mav;
    }

    @RequestMapping(value = "/reservation/{id}", method = RequestMethod.GET)
    public ModelAndView findOne(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView("reservation");
        Reservation reservation = reservationService.findOne(id);
        mav.addObject("reservation", reservation);
        return mav;
    }

    @RequestMapping(value = "/reservation/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id) {
        return reservationService.delete(id).toString();

    }

    @RequestMapping(value = "/reservation", method = RequestMethod.GET)
    public ModelAndView toInsert() {
        ModelAndView mav = new ModelAndView("reservation");
        return mav;
    }

    @RequestMapping(value = "/reservation", method = RequestMethod.POST)
    @ResponseBody
    public String insert(Reservation reservation) {
        reservationService.save(reservation);
        return reservation.getId();
    }

    @RequestMapping(value = "/reservation/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@PathVariable("id") String id, @RequestBody String body) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
        Map<String, Object> map = objectMapper.readValue(body, Map.class);
        final String result = reservationService.update(id, map).toString();
        return result;
    }

    @Resource
    ReservationService reservationService;

    @Resource
    TemplateService templateService;
}
