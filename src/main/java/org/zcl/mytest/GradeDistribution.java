package org.zcl.mytest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GradeDistribution {
    private String curdate;

    private Integer curgrade;

    private Integer curexp;

    private Integer curusers;

    private Integer countyCode;

}