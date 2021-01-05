package com.sya.model.pk;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class AnnouncementSendPK implements Serializable {

    private Integer announcementId;

    private Integer userId;
}
