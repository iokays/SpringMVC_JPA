package com.iokays.reservation.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.iokays.utils.domain.IdEntity;

@Entity
@Table(name = "t_pub_reservation")
public class Reservation extends IdEntity implements Serializable {

    private static final long serialVersionUID = -4784844584245964547L;

    private String name;            //姓名
    private String birthday;        //生日
    private String phone;            //手机
    private String course;            //课程
    private String city;            //城市
    private String remark;            //备注

    public enum Status {
        uncontact, contact
    }

    private Status status;

    @Column(name = "name_", length = 10, nullable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "birthday_", length = 10, nullable = true)
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Column(name = "phone_", length = 11, nullable = true)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "course_", length = 11, nullable = true)
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Column(name = "city_", length = 11, nullable = true)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "remark_", length = 200, nullable = true)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_", nullable = false)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
