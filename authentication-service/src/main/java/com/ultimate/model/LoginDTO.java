package com.ultimate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@Table(name = "user_details", catalog = "UltimatePosDB", schema = "public")
@XmlRootElement
@Data
public class LoginDTO implements Serializable {

    @JoinColumn(name = "group_no", referencedColumnName = "group_no")
    @ManyToOne(optional = false)

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "user_no")
    private Long userNo;
    @Basic(optional = false)
    @Column(name = "user_code")
    private String userCode;
    @Basic(optional = false)
    @Column(name = "user_first_name")
    private String userFirstName;
    @Basic(optional = false)
    @Column(name = "user_last_name")
    private String userLastName;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "user_phone")
    private String userPhone;
    @Basic(optional = false)
    @Column(name = "user_email")
    private String userEmail;
    @Basic(optional = false)
    @Column(name = "start_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;
    @Column(name = "end_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;
    @Basic(optional = false)
    @Column(name = "password_login_attempt")
    private Short passwordLoginAttempt;
    @Basic(optional = false)
    @Column(name = "inactive_flg")
    private int inactiveFlg;
    @Basic(optional = false)
    @Column(name = "crt_usr_no", updatable = false)
    private Long crtUsrNo;
    @Basic(optional = false)
    @Column(name = "crt_date", updatable = false)
    @Temporal(TemporalType.DATE)
    private Date crtDate;
    @Column(name = "crt_trmnl_nm", updatable = false)
    private String crtTrmnlNm;
    @Column(name = "upd_usr_no")
    private Long updUsrNo;
    @Column(name = "upd_date")
    @Temporal(TemporalType.DATE)
    private Date updDate;
    @Column(name = "upd_trmnl_nm")
    private String updTrmnlNm;
    @Basic(optional = false)
    @Column(name = "upd_cnt")
    private int updCnt;
    @Column(name = "group_no")
    private Integer groupNo;
}

