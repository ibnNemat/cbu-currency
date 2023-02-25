package cbu.valyuta.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "valyuta")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "currency_id_seq")
    @SequenceGenerator(name = "currency_id_seq", sequenceName = "currency_id_seq", allocationSize = 1)
    private Integer id;
    @Column(name = "code")
    public String code;
    public String ccy;
    @Column(name = "ccy_nm_ru")
    public String ccyNm_RU;
    @Column(name = "ccy_nm_uz")
    public String ccyNm_UZ;
    @Column(name = "ccy_nm_uzc")
    public String ccyNm_UZC;
    @Column(name = "ccy_nm_en")
    public String ccyNm_EN;
    public String nominal;
    public Double rate;
    public String diff;
    public Date date;
}
