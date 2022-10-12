/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ultimate.model;

import java.math.BigInteger;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author ULTCPU21
 */
@Data
public class UserDetailsDTO {
    
     private Long userNo;
    private String userCode;
    private String userFirstName;
    private String userLastName;
    private String password;
    private Integer groupNo;
    private String userPhone;
    private String userEmail;
    private Date startDateTime;
    private Date endDateTime;
    private Short passwordLoginAttempt;
    private Short inactiveFlg;
    private long crtUsrNo;
    private Date crtDate;
    private String crtTrmnlNm;
    private BigInteger updUsrNo;
    private Date updDate;
    private String updTrmnlNm;
    private int updCnt;
}
