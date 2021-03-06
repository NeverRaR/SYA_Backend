package com.sya.model.pk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AnnouncementSendPK implements Serializable {

    private Integer announcementId;

    private Integer userId;
}
