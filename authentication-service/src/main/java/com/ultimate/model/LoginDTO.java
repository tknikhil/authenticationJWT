package com.ultimate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@Table(name = "user_details", catalog = "UltimatePosDB", schema = "public")
@XmlRootElement
@Data
//@NamedQueries({
//    @NamedQuery(name = "UserDetails.findAll", query = "SELECT u FROM UserDetails u"),
//    @NamedQuery(name = "UserDetails.findByUserNo", query = "SELECT u FROM UserDetails u WHERE u.userNo = :userNo"),
//    @NamedQuery(name = "UserDetails.findByUserCode", query = "SELECT u FROM UserDetails u WHERE u.userCode = :userCode"),
//    @NamedQuery(name = "UserDetails.findByUserFirstName", query = "SELECT u FROM UserDetails u WHERE u.userFirstName = :userFirstName"),
//    @NamedQuery(name = "UserDetails.findByUserLastName", query = "SELECT u FROM UserDetails u WHERE u.userLastName = :userLastName"),
//    @NamedQuery(name = "UserDetails.findByPassword", query = "SELECT u FROM UserDetails u WHERE u.password = :password"),
//    @NamedQuery(name = "UserDetails.findByGroupNo", query = "SELECT u FROM UserDetails u WHERE u.groupNo = :groupNo"),
//    @NamedQuery(name = "UserDetails.findByUserPhone", query = "SELECT u FROM UserDetails u WHERE u.userPhone = :userPhone"),
//    @NamedQuery(name = "UserDetails.findByUserEmail", query = "SELECT u FROM UserDetails u WHERE u.userEmail = :userEmail"),
//    @NamedQuery(name = "UserDetails.findByStartDateTime", query = "SELECT u FROM UserDetails u WHERE u.startDateTime = :startDateTime"),
//    @NamedQuery(name = "UserDetails.findByEndDateTime", query = "SELECT u FROM UserDetails u WHERE u.endDateTime = :endDateTime"),
//    @NamedQuery(name = "UserDetails.findByPasswordLoginAttempt", query = "SELECT u FROM UserDetails u WHERE u.passwordLoginAttempt = :passwordLoginAttempt"),
//    @NamedQuery(name = "UserDetails.findByInactiveFlg", query = "SELECT u FROM UserDetails u WHERE u.inactiveFlg = :inactiveFlg"),
//    @NamedQuery(name = "UserDetails.findByCrtUsrNo", query = "SELECT u FROM UserDetails u WHERE u.crtUsrNo = :crtUsrNo"),
//    @NamedQuery(name = "UserDetails.findByCrtDate", query = "SELECT u FROM UserDetails u WHERE u.crtDate = :crtDate"),
//    @NamedQuery(name = "UserDetails.findByCrtTrmnlNm", query = "SELECT u FROM UserDetails u WHERE u.crtTrmnlNm = :crtTrmnlNm"),
//    @NamedQuery(name = "UserDetails.findByUpdUsrNo", query = "SELECT u FROM UserDetails u WHERE u.updUsrNo = :updUsrNo"),
//    @NamedQuery(name = "UserDetails.findByUpdDate", query = "SELECT u FROM UserDetails u WHERE u.updDate = :updDate"),
//    @NamedQuery(name = "UserDetails.findByUpdTrmnlNm", query = "SELECT u FROM UserDetails u WHERE u.updTrmnlNm = :updTrmnlNm"),
//    @NamedQuery(name = "UserDetails.findByUpdCnt", query = "SELECT u FROM UserDetails u WHERE u.updCnt = :updCnt")})
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

